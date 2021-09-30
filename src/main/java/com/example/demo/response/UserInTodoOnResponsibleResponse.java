package com.example.demo.response;

import com.example.demo.entity.TodoOnResponsibleEntity;
import com.example.demo.entity.UserEntity;

import lombok.Data;

@Data
public class UserInTodoOnResponsibleResponse {
	private Integer todoOnProjectId;
	private Boolean isCompleted;
	private String userName;
	
	public UserInTodoOnResponsibleResponse(TodoOnResponsibleEntity todo, UserEntity user) {
		todoOnProjectId = todo.getTodoOnProjectId();
		isCompleted = todo.getIsCompleted();
		userName = user.getUserName();
	}
}
