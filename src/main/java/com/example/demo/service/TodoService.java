package com.example.demo.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.form.TodoForm;
import com.example.demo.response.TodoOnProjectResponse;

@Service
public class TodoService {

	public Integer insert(Integer userId, TodoForm form) {
		// TODO 自動生成されたメソッド・スタブ
		
		return null;//TODO 新しいID
		
	}

	public TodoOnProjectResponse get(Integer userId, Integer todoOnProjectId) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	public List<TodoOnProjectResponse> getList(Integer userId, TodoForm form) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	public void updateIsCopyContentsToResponsible(Integer userId, Integer todoOnProjectId,
			Boolean isCopyContentsToResponsible) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	public void updateTodoName(Integer userId, Integer todoOnProjectId, String todoName) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	public void updateStartDate(Integer userId, Integer todoOnProjectId, Date startDate) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	public void updateFinishDate(Integer userId, Integer todoOnProjectId, Date finishDate) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	public void delete(Integer userId, Integer todoOnProjectId) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	public void setIsCompleted(Integer userId, Integer todoOnProjectId, Boolean isCompleted) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

}
