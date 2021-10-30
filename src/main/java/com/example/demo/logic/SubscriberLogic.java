package com.example.demo.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.configurer.AuthorityListInPublicProject;
import com.example.demo.dto.SubscriberInPublicProjectEntityExample;
import com.example.demo.entity.SubscriberInPublicProjectEntity;
import com.example.demo.exception.NotHaveAuthorityToOperateProjectException;
import com.example.demo.exception.NotJoinedPublicProjectException;
import com.example.demo.repository.SubscriberInPublicProjectEntityMapper;

/**
 * パブリックプロジェクトの加入者の共通処理
 * @author towelman
 *
 */
@Component
public class SubscriberLogic {
	@Autowired
	SubscriberInPublicProjectEntityMapper subscriberInPublicProjectEntityMapper;
	
	/**
	 * ユーザーが、パブリックプロジェクト内で、ノーマル権限以上の権限を持っているかどうかを返す。
	 * @param userId ユーザーID
	 * @param publicProjectId パブリックプロジェクトID
	 * @return ユーザーが、パブリックプロジェクト内で、ノーマル権限以上の権限を持っていればtrue、そうでなければfalseを返す
	 */
	public boolean haveNormalAuthority(Integer userId, Integer publicProjectId) {
		var dto = new SubscriberInPublicProjectEntityExample();
		dto.or()
			.andAuthorityIdNotEqualTo(AuthorityListInPublicProject.TENTATIVE)
			.andPublicProjectIdEqualTo(publicProjectId)
			.andUserIdEqualTo(userId);
		
		return !subscriberInPublicProjectEntityMapper.selectByExample(dto).isEmpty();
	}

	/**
	 * パブリックプロジェクトに参画者を登録する
	 * @param publicProjectId パブリックプロジェクトID
	 * @param userId ユーザーID
	 * @param authorityId 権限ID
	 */
	public void insert(Integer publicProjectId, Integer userId, Integer authorityId) {
		var entity = new SubscriberInPublicProjectEntity();
		entity.setPublicProjectId(publicProjectId);
		entity.setUserId(userId);
		entity.setAuthorityId(authorityId);
		
		subscriberInPublicProjectEntityMapper.insert(entity);
	}

	/**
	 * パブリックプロジェクトの加入しているユーザーの権限を取得
	 * @param publicProjectId パブリックプロジェクトID
	 * @param userId ユーザーID
	 * @return パブリックプロジェクトの加入しているユーザーの権限
	 * @throws NotJoinedPublicProjectException パブリックプロジェクトに加入していないユーザーが指定されたときに投げられる
	 */
	public SubscriberInPublicProjectEntity getAuthority(Integer publicProjectId, Integer userId) throws NotJoinedPublicProjectException {
		var dto = new SubscriberInPublicProjectEntityExample();
		dto.or()
			.andPublicProjectIdEqualTo(publicProjectId)
			.andUserIdEqualTo(userId);
		
		var entityList = subscriberInPublicProjectEntityMapper.selectByExample(dto);
		
		if(entityList.isEmpty())
			throw new NotJoinedPublicProjectException();
		else
			return entityList.get(0);
	}

	/**
	 * ユーザーが参画しているパブリックプロジェクトの権利リストを取得する
	 * @param userId ユーザーID
	 * @return ユーザーが参画しているパブリックプロジェクトの権利リスト
	 */
	public List<SubscriberInPublicProjectEntity> getSubscriberListByUserId(Integer userId) {
		var dto = new SubscriberInPublicProjectEntityExample();
		dto.or()
			.andUserIdEqualTo(userId);
		
		return subscriberInPublicProjectEntityMapper.selectByExample(dto);
	}

	/**
	 * ユーザーがパブリックプロジェクトのSUPER権限を持っているかを検証する
	 * @param publicProjectId パブリックプロジェクトID
	 * @param userId ユーザーID
	 * @throws NotHaveAuthorityToOperateProjectException パブリックプロジェクトに対して操作権限のないユーザーが指定されたときに投げられる
	 */
	public void validateHaveSuperAuthority(Integer publicProjectId, Integer userId) throws NotHaveAuthorityToOperateProjectException {
		var dto = new SubscriberInPublicProjectEntityExample();
		dto.or()
			.andPublicProjectIdEqualTo(publicProjectId)
			.andUserIdEqualTo(userId)
			.andAuthorityIdEqualTo(AuthorityListInPublicProject.SUPER);
		
		if(subscriberInPublicProjectEntityMapper.selectByExample(dto).isEmpty())
			throw new NotHaveAuthorityToOperateProjectException();
	}

	/**
	 * パブリックプロジェクトの参画者リストの取得
	 * @param publicProjectId パブリックプロジェクトID
	 * @return パブリックプロジェクトの参画者リスト
	 */
	public List<SubscriberInPublicProjectEntity> getSubscriberByPublicProjectId(Integer publicProjectId) {
		var dto = new SubscriberInPublicProjectEntityExample();
		dto.or()
			.andPublicProjectIdEqualTo(publicProjectId);
		
		return subscriberInPublicProjectEntityMapper.selectByExample(dto);
	}
	
}
