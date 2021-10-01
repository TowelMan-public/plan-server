package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.form.ResponsibleTodoForm;
import com.example.demo.response.TodoOnResponsibleResponse;
import com.example.demo.response.UserInTodoOnResponsibleResponse;

@Service
public class ResponsibleTodoService {

	public void insert(Integer userId, Integer todoOnProjectId, String userName) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	public List<TodoOnResponsibleResponse> getList(Integer userId, ResponsibleTodoForm form) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	public TodoOnResponsibleResponse get(Integer userId, Integer todoOnProjectId) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	public List<UserInTodoOnResponsibleResponse> getResponsiblePeopleList(Integer userId, Integer todoOnProjectId) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	public void delete(Integer userId, Integer todoOnProjectId, String userName) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	public void exit(Integer userId, Integer todoOnProjectId) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	public void setIsCompletedAll(Integer userId, Integer todoOnProjectId, Boolean isCompleted) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	public void setIsCompleted(Integer userId, Integer todoOnProjectId, Boolean isCompleted) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

}
