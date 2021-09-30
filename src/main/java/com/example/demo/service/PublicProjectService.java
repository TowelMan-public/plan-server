package com.example.demo.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.form.ProjectForm;
import com.example.demo.response.PublicProjectResponse;

@Service
public class PublicProjectService {

	public Integer insert(Integer userId, ProjectForm form) {
		// TODO 自動生成されたメソッド・スタブ
		return null;//TODO 新しいID
	}

	public PublicProjectResponse get(Integer userId, Integer publicProjectId) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	public List<PublicProjectResponse> getList(Integer userId) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	public void updateProjectName(String projectName) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	public void updateFinishDate(Date finishDate) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	public void updateStartDate(Date startDate) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	public void delete(Integer userId, Integer publicProjectId) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	public void setIsCompleted(Integer userId, Boolean isCompleted) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

}
