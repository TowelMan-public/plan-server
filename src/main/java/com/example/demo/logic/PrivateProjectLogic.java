package com.example.demo.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dto.PrivateProjectEntityExample;
import com.example.demo.entity.PrivateProjectEntity;
import com.example.demo.entity.ProjectEntity;
import com.example.demo.exception.NotFoundValueException;
import com.example.demo.repository.PrivateProjectEntityMapper;
import com.example.demo.repository.ProjectEntityMapper;

/**
 * プライベートプロジェクトの共通処理
 * @author towelman
 *
 */
@Component
public class PrivateProjectLogic {
	@Autowired
	PrivateProjectEntityMapper privateProjectEntityMapper;
	@Autowired
	ProjectEntityMapper projectEntityMapper;
	
	/**
	 * プロジェクトIDが、プライベートプロジェクトであるかどうかを調べる
	 * @param projectId プロジェクトID
	 * @return プライベートプロジェクトであればtrue、そうでなければfalse
	 */
	public boolean isPrivateProject(Integer projectId) {
		var entity = privateProjectEntityMapper.selectByPrimaryKey(projectId);
		
		return entity != null && !entity.getIsDeleted();
	}

	/**
	 * プライベートプロジェクトを作成する
	 * @param userId ユーザーID
	 * @param projectName プライベートプロジェクト名
	 * @return 新しいプライベートプロジェクトID
	 */
	public Integer insert(Integer userId, String projectName) {
		var parentEntity = new ProjectEntity();
		projectEntityMapper.insertSelective(parentEntity);
		
		
		var privateProjectEntity = new PrivateProjectEntity();
		privateProjectEntity.setProjectId(parentEntity.getProjectId());
		privateProjectEntity.setProjectName(projectName);
		privateProjectEntity.setUserId(userId);
		privateProjectEntity.setIsDeleted(false);
		privateProjectEntityMapper.insert(privateProjectEntity);
		
		return parentEntity.getProjectId();
	}

	/**
	 * 1ユーザーが持っているプライベートプロジェクトリストを取得する
	 * @param userId ユーザーID
	 * @return 1ユーザーが持っているプライベートプロジェクトリスト
	 */
	public List<PrivateProjectEntity> getList(Integer userId) {
		var dto = new PrivateProjectEntityExample();
		dto.or()
			.andIsDeletedEqualTo(false)
			.andUserIdEqualTo(userId);
		
		return privateProjectEntityMapper.selectByExample(dto);
	}

	/**
	 * プライベートプロジェクトを取得する
	 * @param userId ユーザーID
	 * @param privateProjectId プライベートプロジェクトID
	 * @return プライベートプロジェクト
	 * @throws NotFoundValueException 指定されたプライベートプロジェクトIDが見つからないときに投げられる
	 */
	public PrivateProjectEntity getPrivateProject(Integer userId, Integer privateProjectId) throws NotFoundValueException {
		var dto = new PrivateProjectEntityExample();
		dto.or()
			.andIsDeletedEqualTo(false)
			.andProjectIdEqualTo(privateProjectId)
			.andUserIdEqualTo(userId);
		
		var entityList = privateProjectEntityMapper.selectByExample(dto);
		if(entityList.isEmpty())
			throw new NotFoundValueException("privateProjectId", privateProjectId.toString());
		else
			return entityList.get(0);
	}

	/**
	 * プライベートプロジェクトが存在するかを検証する
	 * @param userId ユーザーID
	 * @param privateProjectId プライベートプロジェクトID
	 * @throws NotFoundValueException 指定されたプライベートプロジェクトIDが見つからないときに投げられる
	 */
	public void validatePrivateProjectFound(Integer userId, Integer privateProjectId) throws NotFoundValueException {
		this.getPrivateProject(userId, privateProjectId);
	}

	/**
	 * プライベートプロジェクト名を変更する
	 * @param privateProjectId プライベートプロジェクトID
	 * @param projectName プライベートプロジェクト名
	 */
	public void updateName(Integer privateProjectId, String projectName) {
		var entity = new PrivateProjectEntity();
		entity.setProjectId(privateProjectId);
		entity.setProjectName(projectName);
		
		privateProjectEntityMapper.updateByPrimaryKeySelective(entity);
	}

	/**
	 * プライベートプロジェクトを削除する<br>
	 * 論理削除なので物理的には残っている
	 * @param privateProjectId プライベートプロジェクトID
	 */
	public void delete(Integer privateProjectId) {
		var entity = new PrivateProjectEntity();
		entity.setProjectId(privateProjectId);
		entity.setIsDeleted(true);
		
		privateProjectEntityMapper.updateByPrimaryKeySelective(entity);
	}
	
}
