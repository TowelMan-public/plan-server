package com.example.demo.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.configurer.AuthorityListInPublicProject;
import com.example.demo.entity.PublicProjectEntity;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.NotFoundValueException;
import com.example.demo.exception.NotHaveAuthorityToOperateProjectException;
import com.example.demo.exception.NotJoinedPublicProjectException;
import com.example.demo.form.ProjectForm;
import com.example.demo.logic.NoticeLogic;
import com.example.demo.logic.PublicProjectLogic;
import com.example.demo.logic.SubscriberLogic;
import com.example.demo.response.PublicProjectResponse;

/**
 * パブリックプロジェクトのビジネスロジック
 * @author towelman
 *
 */
@Service
public class PublicProjectService {
	@Autowired
	SubscriberLogic subscriberLogic;	
	@Autowired
	PublicProjectLogic publicProjectLogic;
	@Autowired
	NoticeLogic noticeLogic;
	
	/**
	 * パブリックプロジェクトを新規作成する
	 * @param userId ユーザーID
	 * @param form パブリックプロジェクトの情報
	 * @return 新しいパブリックプロジェクトのID
	 */
	@Transactional
	public Integer insert(Integer userId, ProjectForm form) {
		var publicProjectId = publicProjectLogic.insert(form.getProjectName(), form.getStartDate(), form.getFinishDate());
		subscriberLogic.insert(publicProjectId, userId, AuthorityListInPublicProject.SUPER);
		
		return publicProjectId;
	}

	/**
	 * パブリックプロジェクトを取得する
	 * @param userId ユーザーID
	 * @param publicProjectId パブリックプロジェクトID
	 * @return パブリックプロジェクト
	 * @throws NotFoundValueException パブリックプロジェクトが見つからない
	 * @throws NotJoinedPublicProjectException  パブリックプロジェクトに加入、もしくは勧誘されていない
	 */
	@Transactional
	public PublicProjectResponse get(Integer userId, Integer publicProjectId) throws NotFoundValueException, NotJoinedPublicProjectException {
		var entitiy = publicProjectLogic.get(publicProjectId);
		var authorityId = subscriberLogic.getAuthority(publicProjectId, userId).getAuthorityId();
		
		noticeLogic.erasePublicProjectNotice(userId, publicProjectId);
		return new PublicProjectResponse(entitiy, authorityId);
	}

	/**
	 * 自分が参画しているパブリックプロジェクトリストを取得する
	 * @param userId ユーザー
	 * @return 自分が参画しているパブリックプロジェクトリスト
	 */
	public List<PublicProjectResponse> getList(Integer userId) {
		var subscriberEntityList = subscriberLogic.getSubscriberListByUserId(userId);
		List<PublicProjectResponse> responseList = new ArrayList<>();
		
		for(var subscriberEntity: subscriberEntityList) {
			if(subscriberEntity.getAuthorityId() != AuthorityListInPublicProject.TENTATIVE) {
				var projectEntity = publicProjectLogic.getNonThrow(subscriberEntity.getPublicProjectId());			
				
				if(projectEntity != null)
					responseList.add(new PublicProjectResponse(projectEntity, subscriberEntity.getAuthorityId()));	
			}
		}
		
		return responseList;
	}

	/**
	 * パブリックプロジェクト名を変更する
	 * @param userId ユーザーID
	 * @param publicProjectId パブリックプロジェクトID
	 * @param projectName パブリックプロジェクト名
	 * @throws NotHaveAuthorityToOperateProjectException パブリックプロジェクトに対して操作権限がない
	 * @throws NotFoundValueException パブリックプロジェクトが見つからない
	 */
	@Transactional
	public void updateProjectName(Integer userId, Integer publicProjectId, String projectName) throws NotFoundValueException, NotHaveAuthorityToOperateProjectException {
		this.validateUserCanOperationToProject(userId, publicProjectId);
		
		publicProjectLogic.updateName(publicProjectId, projectName);
		noticeLogic.erasePublicProjectNotice(userId, publicProjectId);
	}

	/**
	 * 締め切り日時を変更する
	 * @param userId ユーザーID
	 * @param publicProjectId パブリックプロジェクトID
	 * @param finishDate 締め切り日時
	 * @throws BadRequestException 締め切り日時に不適切な日時が指定された時に投げる
	 * @throws NotHaveAuthorityToOperateProjectException 操作権限がない
	 * @throws NotFoundValueException パブリックプロジェクトが見つからない
	 */
	@Transactional
	public void updateFinishDate(Integer userId, Integer publicProjectId, Date finishDate) throws BadRequestException, NotFoundValueException, NotHaveAuthorityToOperateProjectException {
		var projectStartDate = this.getProjectAndValidateUserCanOperationToProject(userId, publicProjectId).getStartDate();
		
		if(!finishDate.after(projectStartDate))
			throw new BadRequestException("finishDate is unfair");
		
		publicProjectLogic.updateFinishDate(publicProjectId, finishDate);
		noticeLogic.erasePublicProjectNotice(userId, publicProjectId);
	}

	/**
	 * 開始日時を変更する
	 * @param userId ユーザーID
	 * @param publicProjectId パブリックプロジェクトID
	 * @param startDate 開始日時
	 * @throws BadRequestException 開始日時に不適切な日時が指定された時に投げる
	 * @throws NotHaveAuthorityToOperateProjectException パブリックプロジェクトに対して操作権限がない
	 * @throws NotFoundValueException パブリックプロジェクトが見つからない
	 */
	public void updateStartDate(Integer userId, Integer publicProjectId, Date startDate) throws BadRequestException, NotFoundValueException, NotHaveAuthorityToOperateProjectException {
		var projectFinishDate = this.getProjectAndValidateUserCanOperationToProject(userId, publicProjectId).getStartDate();
		
		if(!projectFinishDate.after(startDate))
			throw new BadRequestException("startDate is unfair");
		
		publicProjectLogic.updateStartDate(publicProjectId, startDate);
		noticeLogic.erasePublicProjectNotice(userId, publicProjectId);
	}

	/**
	 * パブリックプロジェクトを削除する
	 * @param userId ユーザーID
	 * @param publicProjectId パブリックプロジェクトID
	 * @throws NotHaveAuthorityToOperateProjectException パブリックプロジェクトに対して操作権限がない
	 * @throws NotFoundValueException パブリックプロジェクトが見つからない
	 */
	public void delete(Integer userId, Integer publicProjectId) throws NotFoundValueException, NotHaveAuthorityToOperateProjectException {
		this.validateUserCanOperationToProject(userId, publicProjectId);
		var subscriberList = subscriberLogic.getSubscriberByPublicProjectId(publicProjectId);
			
		publicProjectLogic.delete(publicProjectId);
	}

	/**
	 * パブリックプロジェクトに完了状況をセットする
	 * @param userId ユーザーID
	 * @param publicProjectId パブリックプロジェクトID
	 * @param isCompleted 完了状況
	 * @throws NotHaveAuthorityToOperateProjectException パブリックプロジェクトに対して操作権限がない
	 * @throws NotFoundValueException パブリックプロジェクトが見つからない
	 */
	public void setIsCompleted(Integer userId, Integer publicProjectId, Boolean isCompleted) throws NotFoundValueException, NotHaveAuthorityToOperateProjectException {
		var entity = this.getProjectAndValidateUserCanOperationToProject(userId, publicProjectId);
		var subscriberList = subscriberLogic.getSubscriberByPublicProjectId(publicProjectId);
		
		publicProjectLogic.setIsCompleted(publicProjectId, isCompleted);
		
		//後処理
		if(isCompleted.booleanValue() && !entity.getIsCompleted().booleanValue()) {
			for(var subscriber: subscriberList) {
				if(subscriber.getAuthorityId() != AuthorityListInPublicProject.TENTATIVE) {
					noticeLogic.erasePublicProjectNotice(userId, publicProjectId);
					noticeLogic.createCompletedPublicProjectNotice(subscriber.getUserId(), publicProjectId);
				}
			}
		}else if(!isCompleted.booleanValue() && entity.getIsCompleted().booleanValue()) {
			for(var subscriber: subscriberList) {
				if(subscriber.getAuthorityId() != AuthorityListInPublicProject.TENTATIVE) {
					noticeLogic.erasePublicProjectNotice(userId, publicProjectId);
					noticeLogic.createUnCompletedPublicProjectNotice(userId, publicProjectId);
				}
			}
		}
	}

	/**
	 * 勧誘されているパブリックプロジェクトリストを取得する
	 * @param userId ユーザーID
	 * @return 勧誘されているパブリックプロジェクトリスト
	 */
	public List<PublicProjectResponse> getSolicited(Integer userId) {
		var subscriberEntityList = subscriberLogic.getSubscriberListByUserId(userId);
		List<PublicProjectResponse> responseList = new ArrayList<>();
		
		for(var subscriberEntity: subscriberEntityList) {
			if(subscriberEntity.getAuthorityId() == AuthorityListInPublicProject.TENTATIVE) {
				var projectEntity = publicProjectLogic.getNonThrow(subscriberEntity.getPublicProjectId());			
				
				if(projectEntity != null)
					responseList.add(new PublicProjectResponse(projectEntity, subscriberEntity.getAuthorityId()));	
			}
		}
		
		return responseList;
	}

	/**
	 * ユーザーがパブリックプロジェクトを操作しても問題ないか検証する
	 * @param userId ユーザーID
	 * @param publicProjectId パブリックプロジェクトID
	 * @throws NotHaveAuthorityToOperateProjectException パブリックプロジェクトに対して操作権限がない
	 * @throws NotFoundValueException パブリックプロジェクトが見つからない
	 */
	private void validateUserCanOperationToProject(Integer userId, Integer publicProjectId) throws NotFoundValueException, NotHaveAuthorityToOperateProjectException {
		publicProjectLogic.validatePublicProjectIdFound(publicProjectId);
		subscriberLogic.validateHaveSuperAuthority(publicProjectId, userId);
	}
	
	/**
	 * ユーザーがパブリックプロジェクトを操作しても問題ないか検証し、問題なければパブリックプロジェクトを返す
	 * @param userId ユーザーID
	 * @param publicProjectId パブリックプロジェクトID
	 * @return パブリックプロジェクト
	 * @throws NotHaveAuthorityToOperateProjectException パブリックプロジェクトに対して操作権限がない
	 * @throws NotFoundValueException パブリックプロジェクトが見つからない
	 */
	private PublicProjectEntity getProjectAndValidateUserCanOperationToProject(Integer userId, Integer publicProjectId) throws NotFoundValueException, NotHaveAuthorityToOperateProjectException {
		var entity = publicProjectLogic.get(publicProjectId);
		subscriberLogic.validateHaveSuperAuthority(publicProjectId, userId);
		
		return entity;
	}
}
