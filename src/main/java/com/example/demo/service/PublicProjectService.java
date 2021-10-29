package com.example.demo.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.form.ProjectForm;
import com.example.demo.response.PublicProjectResponse;

@Service
public class PublicProjectService {

	@Transactional
	public Integer insert(Integer userId, ProjectForm form) {
		// TODO 自動生成されたメソッド・スタブ
		return null;//TODO 新しいID
	}

	@Transactional
	public PublicProjectResponse get(Integer userId, Integer publicProjectId) {//NotFoundValueException
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	public List<PublicProjectResponse> getList(Integer userId) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Transactional
	public void updateProjectName(Integer userId, Integer publicProjectId, String projectName) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Transactional
	public void updateFinishDate(Integer userId, Integer publicProjectId, Date finishDate) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	public void updateStartDate(Integer userId, Integer publicProjectId, Date startDate) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	public void delete(Integer userId, Integer publicProjectId) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	public void setIsCompleted(Integer userId, Integer publicProjectId, Boolean isCompleted) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	public List<PublicProjectResponse> getSolicited(Integer userId) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

}
