package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.exception.NotFoundValueException;
import com.example.demo.logic.PrivateProjectLogic;
import com.example.demo.response.PrivateProjectResponse;

/**
 * プライベートプロジェクトに関するビジネスロジック
 * @author towelman
 *
 */
@Service
public class PrivateProjectService {
	@Autowired
	PrivateProjectLogic privateProjectLogic;
	
	/**
	 * プライベートプロジェクトの作成
	 * @param userId ユーザーID
	 * @param projectName プライベートプロジェクト名
	 * @return 新しく作ったプライベートプロジェクトのID
	 */
	@Transactional
	public Integer insert(Integer userId, String projectName) {
		return privateProjectLogic.insert(userId, projectName);
	}

	/**
	 * ユーザーが保持するプライベートプロジェクトリストを取得する
	 * @param userId ユーザーID
	 * @return ユーザーが保持するプライベートプロジェクトリスト
	 */
	public List<PrivateProjectResponse> getList(Integer userId) {
		var entityList = privateProjectLogic.getList(userId);
		List<PrivateProjectResponse> responseList = new ArrayList<>();
		
		for(var entity: entityList)
			responseList.add(new PrivateProjectResponse(entity));
		
		return responseList;
	}

	/**
	 * プライベートプロジェクトを取得する
	 * @param userId ユーザーID
	 * @param privateProjectId プライベートプロジェクトID
	 * @return プライベートプロジェクト
	 * @throws NotFoundValueException 指定されたプライベートプロジェクトIDが見つからない
	 */
	public PrivateProjectResponse get(Integer userId, Integer privateProjectId) throws NotFoundValueException {
		return new PrivateProjectResponse(privateProjectLogic.getPrivateProject(userId, privateProjectId));
	}

	/**
	 * プライベートプロジェクト名を変更する
	 * @param userId ユーザーID
	 * @param privateProjectId プライベートプロジェクトID
	 * @param projectName プライベートプロジェクト名
	 * @throws NotFoundValueException 指定されたプライベートプロジェクトIDが見つからない
	 */
	@Transactional
	public void updateProjectName(Integer userId, Integer privateProjectId, String projectName) throws NotFoundValueException {
		privateProjectLogic.validatePrivateProjectFound(userId, privateProjectId);
		
		privateProjectLogic.updateName(privateProjectId, projectName);
	}

	/**
	 * プライベートプロジェクトを削除する
	 * @param userId ユーザーID
	 * @param privateProjectId プライベートプロジェクトID
	 * @throws NotFoundValueException 指定されたプライベートプロジェクトIDが見つからない
	 */
	@Transactional
	public void delete(Integer userId, Integer privateProjectId) throws NotFoundValueException {
		privateProjectLogic.validatePrivateProjectFound(userId, privateProjectId);
		
		privateProjectLogic.delete(privateProjectId);
	}

}
