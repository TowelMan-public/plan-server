package com.example.demo.logic;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dto.PublicProjectEntityExample;
import com.example.demo.entity.ProjectEntity;
import com.example.demo.entity.PublicProjectEntity;
import com.example.demo.exception.NotFoundValueException;
import com.example.demo.repository.ProjectEntityMapper;
import com.example.demo.repository.PublicProjectEntityMapper;

/**
 * パブリックプロジェクトの共通処理
 * @author towelman
 *
 */
@Component
public class PublicProjectLogic {
	@Autowired
	ProjectEntityMapper projectEntityMapper;
	@Autowired
	PublicProjectEntityMapper publicProjectEntityMapper;
	
	/**
	 * パブリックプロジェクトの新規作成
	 * @param projectName パブリックプロジェクト名
	 * @param startDate 開始日時
	 * @param finishDate 締め切り日時
	 * @return 新しいパブリックプロジェクトのID
	 */
	public Integer insert(String projectName, Date startDate, Date finishDate) {
		var parentEntity = new ProjectEntity();
		projectEntityMapper.insertSelective(parentEntity);
		
		var entity = new PublicProjectEntity();
		entity.setPublicProjectId(parentEntity.getProjectId());
		entity.setProjectName(projectName);
		entity.setStartDate(startDate);
		entity.setFinishDate(finishDate);
		
		publicProjectEntityMapper.insertSelective(entity);
		return parentEntity.getProjectId();
	}

	/**
	 * パブリックプロジェクトを取得する
	 * @param publicProjectId パブリックプロジェクトID
	 * @return パブリックプロジェクト
	 * @throws NotFoundValueException パブリックプロジェクトが見つからないときに投げられる
	 */
	public PublicProjectEntity get(Integer publicProjectId) throws NotFoundValueException {
		var entity = this.getNonThrow(publicProjectId);
		
		if(entity == null)
			throw new NotFoundValueException("publicProjectId", publicProjectId.toString());
		else
			return entity;
	}

	/**
	 * パブリックプロジェクトを取得する
	 * <br>例外を投げないため、存在しなくても困らないときのみの使用に限る
	 * @param publicProjectId パブリックプロジェクト
	 * @return パブリックプロジェクト、存在しなければnull
	 */
	public PublicProjectEntity getNonThrow(Integer publicProjectId) {
		var dto = new PublicProjectEntityExample();
		dto.or()
			.andPublicProjectIdEqualTo(publicProjectId)
			.andIsDeletedEqualTo(false);
		
		var entityList = publicProjectEntityMapper.selectByExample(dto);
		
		if(entityList.isEmpty())
			return null;
		else
			return entityList.get(0);
	}

	/**
	 * パブリックプロジェクト名を変更する
	 * @param publicProjectId パブリックプロジェクトID
	 * @param projectName パブリックプロジェクト名
	 */
	public void updateName(Integer publicProjectId, String projectName) {
		var entity = new PublicProjectEntity();
		entity.setPublicProjectId(publicProjectId);
		entity.setProjectName(projectName);
		
		publicProjectEntityMapper.updateByPrimaryKeySelective(entity);
	}

	/**
	 * パブリックプロジェクトが存在するか検証する
	 * @param publicProjectId パブリックプロジェクトID
	 * @throws NotFoundValueException パブリックプロジェクトが見つからないときに投げられる
	 */
	public void validatePublicProjectIdFound(Integer publicProjectId) throws NotFoundValueException {
		this.get(publicProjectId);
	}

	/**
	 * 締め切り日時を変更する
	 * @param publicProjectId パブリックプロジェクトID
	 * @param finishDate 締め切り日時
	 */
	public void updateFinishDate(Integer publicProjectId, Date finishDate) {
		var entity = new PublicProjectEntity();
		entity.setPublicProjectId(publicProjectId);
		entity.setFinishDate(finishDate);
		
		publicProjectEntityMapper.updateByPrimaryKeySelective(entity);
	}

	/**
	 * 開始日時を変更する
	 * @param publicProjectId パブリックプロジェクトID
	 * @param startDate 開始日時
	 */
	public void updateStartDate(Integer publicProjectId, Date startDate) {
		var entity = new PublicProjectEntity();
		entity.setPublicProjectId(publicProjectId);
		entity.setStartDate(startDate);
		
		publicProjectEntityMapper.updateByPrimaryKeySelective(entity);
	}

	/**
	 * パブリックプロジェクトを削除する
	 * @param publicProjectId パブリックプロジェクトID
	 */
	public void delete(Integer publicProjectId) {
		var entity = new PublicProjectEntity();
		entity.setPublicProjectId(publicProjectId);
		entity.setIsDeleted(true);
		
		publicProjectEntityMapper.updateByPrimaryKeySelective(entity);
	}

	/**
	 * パブリックプロジェクトに完了状況をセットする
	 * @param publicProjectId パブリックプロジェクトID
	 * @param isCompleted 完了しているかどうか
	 */
	public void setIsCompleted(Integer publicProjectId, Boolean isCompleted) {
		var entity = new PublicProjectEntity();
		entity.setPublicProjectId(publicProjectId);
		entity.setIsCompleted(isCompleted);
		
		publicProjectEntityMapper.updateByPrimaryKeySelective(entity);
	}

	/**
	 * パブリックプロジェクトが存在するかを返す
	 * @param publicProjectId パブリックプロジェクトID
	 * @return ブリックプロジェクトが存在すればtrue、存在しなければfalseを返す
	 */
	public boolean isFound(Integer publicProjectId) {
		return this.getNonThrow(publicProjectId) != null;
	}

}
