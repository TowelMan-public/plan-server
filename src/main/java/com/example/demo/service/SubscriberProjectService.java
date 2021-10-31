package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.configurer.AuthorityListInPublicProject;
import com.example.demo.exception.AlreadyJoinedPublicProjectException;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.NotFoundValueException;
import com.example.demo.exception.NotHaveAuthorityToOperateProjectException;
import com.example.demo.exception.NotJoinedPublicProjectException;
import com.example.demo.logic.NoticeLogic;
import com.example.demo.logic.PublicProjectLogic;
import com.example.demo.logic.SubscriberLogic;
import com.example.demo.logic.UserLogic;
import com.example.demo.response.SubscriberInPublicProjectResponse;

/**
 * パブリックプロジェクトの参加者のビジネスロジック
 * @author towelman
 *
 */
@Service
public class SubscriberProjectService {
	@Autowired
	SubscriberLogic subscriberLogic;
	@Autowired
	PublicProjectLogic publicProjectLogic;
	@Autowired
	UserLogic userLogic;
	@Autowired
	NoticeLogic noticeLogic;
	
	/**
	 * 新しく参画者に勧誘する
	 * @param userId ユーザーID
	 * @param publicProjectId パブリックプロジェクトID
	 * @param userName ユーザー名
	 * @throws NotFoundValueException
	 * @throws NotHaveAuthorityToOperateProjectException
	 * @throws AlreadyJoinedPublicProjectException 
	 */
	@Transactional
	public void insert(Integer userId, Integer publicProjectId, String userName)
			throws NotFoundValueException, NotHaveAuthorityToOperateProjectException, AlreadyJoinedPublicProjectException {
		this.validateUserCanOperationToProject(userId, publicProjectId);
		var userEntity = userLogic.getUserByUserName(userName);
		subscriberLogic.validateNotAlreadyJoinedPublicProject(publicProjectId, userEntity.getUserId());
		
		subscriberLogic.insert(publicProjectId, userEntity.getUserId(), AuthorityListInPublicProject.TENTATIVE);
		
		noticeLogic.createInvitationPublicProjectNotice(userEntity.getUserId(), publicProjectId);
	}

	/**
	 * パブリックプロジェクトの参画者リストを取得する
	 * @param userId ユーザーID
	 * @param publicProjectId パブリックプロジェクトID
	 * @return パブリックプロジェクトの参画者リスト
	 * @throws NotFoundValueException
	 * @throws NotJoinedPublicProjectException
	 */
	public List<SubscriberInPublicProjectResponse> getList(Integer userId, Integer publicProjectId)
			throws NotFoundValueException, NotJoinedPublicProjectException {
		publicProjectLogic.validatePublicProjectIdFound(publicProjectId);
		subscriberLogic.validateSubscriberFound(publicProjectId, userId);
		
		var entityList = subscriberLogic.getSubscriberByPublicProjectId(publicProjectId);
		List<SubscriberInPublicProjectResponse> responseList = new ArrayList<>();
		
		for(var entity: entityList) {
			var userEntity = userLogic.getUserByUserId(entity.getUserId());
			
			if(userEntity != null)
				responseList.add(new SubscriberInPublicProjectResponse(entity, userEntity));
		}
		
		return responseList;
	}

	/**
	 * 参加者の権限を更新する
	 * @param userId ユーザーID
	 * @param publicProjectId パブリックプロジェクトID
	 * @param userName ユーザー名
	 * @param authorityId 権限ID
	 * @throws NotFoundValueException
	 * @throws BadRequestException
	 * @throws NotHaveAuthorityToOperateProjectException
	 * @throws NotJoinedPublicProjectException
	 */
	@Transactional
	public void update(Integer userId, Integer publicProjectId, String userName, Integer authorityId)
			throws NotFoundValueException, BadRequestException, NotHaveAuthorityToOperateProjectException, NotJoinedPublicProjectException {
		this.validateUserCanOperationToProject(userId, publicProjectId);
		var userEntity = userLogic.getUserByUserName(userName);
		var subscriberEntitiy = subscriberLogic.getAuthority(publicProjectId, userEntity.getUserId());
		
		if(subscriberEntitiy.getAuthorityId() == AuthorityListInPublicProject.TENTATIVE)
			throw new BadRequestException("This user has not joined yet.");
		else if(authorityId != AuthorityListInPublicProject.SUPER)
			this.validateUserCanBeDeleted(userEntity.getUserId(), publicProjectId);
		
		subscriberLogic.update(publicProjectId, userEntity.getUserId(), authorityId);
	}

	/**
	 * 参加者を除外する
	 * @param userId ユーザーID
	 * @param publicProjectId パブリックプロジェクトID
	 * @param userName ユーザー名
	 * @throws BadRequestException
	 * @throws NotFoundValueException
	 * @throws NotHaveAuthorityToOperateProjectException
	 * @throws NotJoinedPublicProjectException
	 */
	@Transactional
	public void delete(Integer userId, Integer publicProjectId, String userName)
			throws BadRequestException, NotFoundValueException, NotHaveAuthorityToOperateProjectException, NotJoinedPublicProjectException {
		this.validateUserCanOperationToProject(userId, publicProjectId);
		var userEntity = userLogic.getUserByUserName(userName);
		
		this.validateUserCanBeDeleted(userEntity.getUserId(), publicProjectId);
		
		subscriberLogic.delete(publicProjectId, userEntity.getUserId());
		noticeLogic.erasePublicProjectNotice(userEntity.getUserId(), publicProjectId);
	}

	/**
	 * 勧誘を受け入れる
	 * @param userId ユーザーID
	 * @param publicProjectId パブリックプロジェクトID
	 * @throws NotFoundValueException
	 * @throws BadRequestException
	 */
	@Transactional
	public void accept(Integer userId, Integer publicProjectId) throws NotFoundValueException, BadRequestException {
		publicProjectLogic.validatePublicProjectIdFound(publicProjectId);
		subscriberLogic.validateAlreadyHaveTentativeAuthority(publicProjectId, userId);
		
		subscriberLogic.update(publicProjectId, userId, AuthorityListInPublicProject.NORMAL);
		noticeLogic.erasePublicProjectNotice(userId, publicProjectId);
	}

	/**
	 * 勧誘を断る
	 * @param userId ユーザーID
	 * @param publicProjectId パブリックプロジェクトID
	 * @throws NotFoundValueException
	 * @throws BadRequestException
	 */
	@Transactional
	public void bloak(Integer userId, Integer publicProjectId) throws NotFoundValueException, BadRequestException {
		publicProjectLogic.validatePublicProjectIdFound(publicProjectId);
		subscriberLogic.validateAlreadyHaveTentativeAuthority(publicProjectId, userId);
		
		subscriberLogic.delete(publicProjectId, userId);
		noticeLogic.erasePublicProjectNotice(userId, publicProjectId);
	}

	/**
	 * 参画をやめる
	 * @param userId ユーザーID
	 * @param publicProjectId パブリックプロジェクトID
	 * @throws BadRequestException
	 * @throws NotFoundValueException
	 * @throws NotJoinedPublicProjectException
	 */
	@Transactional
	public void exit(Integer userId, Integer publicProjectId)
			throws BadRequestException, NotFoundValueException, NotJoinedPublicProjectException {
		publicProjectLogic.validatePublicProjectIdFound(publicProjectId);
		subscriberLogic.validateUserJoinPublicProject(publicProjectId, userId);
		
		this.validateUserCanBeDeleted(userId, publicProjectId);
		
		subscriberLogic.delete(publicProjectId, userId);
		noticeLogic.erasePublicProjectNotice(userId, publicProjectId);
	}

	/**
	 * 参画者が、操作権限があるか検証する
	 * @param userId ユーザーID
	 * @param publicProjectId パブリックプロジェクトID
	 * @throws NotFoundValueException
	 * @throws NotHaveAuthorityToOperateProjectException
	 */
	private void validateUserCanOperationToProject(Integer userId, Integer publicProjectId)
			throws NotFoundValueException, NotHaveAuthorityToOperateProjectException {
		publicProjectLogic.validatePublicProjectIdFound(publicProjectId);
		subscriberLogic.validateHaveSuperAuthority(publicProjectId, userId);
	}
	
	/**
	 * そのユーザーが脱退しても大丈夫か検証する
	 * @param userId ユーザーID
	 * @param publicProjectId パブリックプロジェクトID
	 * @throws BadRequestException
	 * @throws NotJoinedPublicProjectException
	 */
	private void validateUserCanBeDeleted(Integer userId, Integer publicProjectId)
			throws BadRequestException, NotJoinedPublicProjectException {
		var entitiy = subscriberLogic.getAuthority(publicProjectId, userId);
		
		if(entitiy.getAuthorityId() == AuthorityListInPublicProject.SUPER) {
			var superSubscriberCount = subscriberLogic.getSubscriberByPublicProjectId(publicProjectId, AuthorityListInPublicProject.SUPER).size();
			if(superSubscriberCount <= 1)
				throw new BadRequestException("This user can not exit or be deleted");
		}
	}
}
