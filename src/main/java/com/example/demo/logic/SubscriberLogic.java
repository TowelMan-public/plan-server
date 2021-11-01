package com.example.demo.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.configurer.AuthorityListInPublicProject;
import com.example.demo.dto.SubscriberInPublicProjectEntityExample;
import com.example.demo.entity.SubscriberInPublicProjectEntity;
import com.example.demo.exception.AlreadyJoinedPublicProjectException;
import com.example.demo.exception.BadRequestException;
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
	 * ユーザーの、パブリックプロジェクト内での権限を取得します。
	 * <br>但し、例外を投げないで、存在しない場合はnullを返すため、気をつけてください、
	 * @param publicProjectId パブリックプロジェクトID
	 * @param userId ユーザーID
	 * @return ユーザーの、パブリックプロジェクト内での権限
	 */
	public SubscriberInPublicProjectEntity getAuthorityNonThorow(Integer publicProjectId, Integer userId) {
		var dto = new SubscriberInPublicProjectEntityExample();
		dto.or()
			.andPublicProjectIdEqualTo(publicProjectId)
			.andUserIdEqualTo(userId);
		
		var entityList = subscriberInPublicProjectEntityMapper.selectByExample(dto);
		
		if(entityList.isEmpty())
			return null;
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

	/**
	 * パブリックプロジェクトIDから参画者リストを取得する
	 * @param publicProjectId パブリックプロジェクトID
	 * @param authorityId 権限ID
	 * @return 参画者リスト
	 */
	public List<SubscriberInPublicProjectEntity> getSubscriberByPublicProjectId(Integer publicProjectId, Integer authorityId) {
		var dto = new SubscriberInPublicProjectEntityExample();
		dto.or()
			.andPublicProjectIdEqualTo(publicProjectId)
			.andAuthorityIdEqualTo(authorityId);
		
		return subscriberInPublicProjectEntityMapper.selectByExample(dto);
	}

	/**
	 * プロジェクト内にユーザーが参画（勧誘含む）しているかを検証する
	 * @param publicProjectId パブリックプロジェクトID
	 * @param userId ユーザーID
	 * @throws NotJoinedPublicProjectException
	 */
	public void validateSubscriberFound(Integer publicProjectId, Integer userId) throws NotJoinedPublicProjectException {
		this.getAuthority(publicProjectId, userId);
	}

	/**
	 * 参画者の権限を変更する
	 * @param publicProjectId パブリックプロジェクトID
	 * @param userId ユーザーID
	 * @param authorityId 権限ID
	 */
	public void update(Integer publicProjectId, Integer userId, Integer authorityId) {
		var entity = new SubscriberInPublicProjectEntity();
		entity.setAuthorityId(authorityId);
		
		var dto = new SubscriberInPublicProjectEntityExample();
		dto.or()
			.andPublicProjectIdEqualTo(publicProjectId)
			.andUserIdEqualTo(userId);
		
		subscriberInPublicProjectEntityMapper.updateByExampleSelective(entity, dto);
	}

	/**
	 * 参画者を除外する
	 * @param publicProjectId パブリックプロジェクトID
	 * @param userId ユーザーID
	 */
	public void delete(Integer publicProjectId, Integer userId) {
		var dto = new SubscriberInPublicProjectEntityExample();
		dto.or()
			.andPublicProjectIdEqualTo(publicProjectId)
			.andUserIdEqualTo(userId);
		
		subscriberInPublicProjectEntityMapper.deleteByExample(dto);
	}

	/**
	 * 既にユーザーが勧誘されているかを検証する
	 * @param publicProjectId パブリックプロジェクトID
	 * @param userId ユーザーID
	 * @throws BadRequestException　ユーザーが参画されている状況に置かれていない
	 */
	public void validateAlreadyHaveTentativeAuthority(Integer publicProjectId, Integer userId) throws BadRequestException {
		var dto = new SubscriberInPublicProjectEntityExample();
		dto.or()
			.andPublicProjectIdEqualTo(publicProjectId)
			.andUserIdEqualTo(userId)
			.andAuthorityIdEqualTo(AuthorityListInPublicProject.TENTATIVE);
		
		if(subscriberInPublicProjectEntityMapper.selectByExample(dto).isEmpty())
			throw new BadRequestException("Not in a solicited situation.");
	}

	/**
	 * ユーザーが参画しているかを検証する<br>
	 * 勧誘中のユーザーは省く
	 * @param publicProjectId パブリックプロジェクトID
	 * @param userId ユーザーID
	 * @throws NotJoinedPublicProjectException
	 */
	public void validateUserJoinPublicProject(Integer publicProjectId, Integer userId) throws NotJoinedPublicProjectException {
		var dto = new SubscriberInPublicProjectEntityExample();
		dto.or()
			.andPublicProjectIdEqualTo(publicProjectId)
			.andUserIdEqualTo(userId)
			.andAuthorityIdNotEqualTo(AuthorityListInPublicProject.TENTATIVE);
		
		if(subscriberInPublicProjectEntityMapper.selectByExample(dto).isEmpty())
			throw new NotJoinedPublicProjectException();
	}

	/**
	 * 参画していないことを検証する
	 * @param publicProjectId パブリックプロジェクトID
	 * @param userId ユーザーID
	 * @throws AlreadyJoinedPublicProjectException
	 */
	public void validateNotAlreadyJoinedPublicProject(Integer publicProjectId, Integer userId) throws AlreadyJoinedPublicProjectException {
		var entity = this.getAuthorityNonThorow(publicProjectId, userId);
		
		if(entity != null)
			throw new AlreadyJoinedPublicProjectException();
	}
}
