package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.response.PrivateProjectResponse;

@Service
public class PrivateProjectService {

	@Transactional
	public Integer insert(Integer userId, String projectName) {
		// TODO 自動生成されたメソッド・スタブ
		return null; //TODO 新しいIDを返す
	}

	public List<PrivateProjectResponse> getList(Integer userId) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	public PrivateProjectResponse get(Integer userId, Integer privateProjectId) {//NotFoundValueException
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Transactional
	public void updateProjectName(Integer userId, Integer privateProjectId, String projectName) {//NotFoundValueException
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Transactional
	public void delete(Integer userId, Integer privateProjectId) {//NotFoundValueException
		// TODO 自動生成されたメソッド・スタブ
		
	}

}
