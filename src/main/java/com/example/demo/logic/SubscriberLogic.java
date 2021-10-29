package com.example.demo.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.configurer.AuthorityListInPublicProject;
import com.example.demo.dto.SubscriberInPublicProjectEntityExample;
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
	
}
