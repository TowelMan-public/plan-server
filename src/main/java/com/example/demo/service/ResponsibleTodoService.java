package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.form.ResponsibleTodoForm;
import com.example.demo.response.TodoOnResponsibleResponse;
import com.example.demo.response.UserInTodoOnResponsibleResponse;

@Service
public class ResponsibleTodoService {

	@Transactional
	public void insert(Integer userId, Integer todoOnProjectId, String userName) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	public List<TodoOnResponsibleResponse> getList(Integer userId, ResponsibleTodoForm form) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Transactional
	public TodoOnResponsibleResponse get(Integer userId, Integer todoOnProjectId) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	public List<UserInTodoOnResponsibleResponse> getResponsiblePeopleList(Integer userId, Integer todoOnProjectId) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Transactional
	public void delete(Integer userId, Integer todoOnProjectId, String userName) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Transactional
	public void exit(Integer userId, Integer todoOnProjectId) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Transactional
	public void setIsCompletedAll(Integer userId, Integer todoOnProjectId, Boolean isCompleted) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Transactional
	public void setIsCompleted(Integer userId, Integer todoOnProjectId, Boolean isCompleted) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

}
