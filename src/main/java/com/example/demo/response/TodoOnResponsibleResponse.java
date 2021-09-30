package com.example.demo.response;

import com.example.demo.entity.TodoOnProjectEntity;
import com.example.demo.entity.TodoOnResponsibleEntity;

import lombok.Data;

@Data
public class TodoOnResponsibleResponse {
	private String todoName;
	private Integer todoOnResponsibleId;
	private Integer projectId;
	private Boolean isCompleted;
	
	public TodoOnResponsibleResponse(TodoOnResponsibleEntity responsible, TodoOnProjectEntity todo) {
		todoName = todo.getTodoName();
		todoOnResponsibleId = responsible.getTodoOnResponsibleId();
		projectId = responsible.getProjectId();
		isCompleted = responsible.getIsCompleted();
	}
}
