package com.example.demo.response;

import java.util.Date;

import com.example.demo.entity.TodoOnProjectEntity;
import com.example.demo.entity.TodoOnResponsibleEntity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TodoOnResponsibleResponse {
	private Integer todoOnProjectId;
	private String todoName;
	private Integer todoOnResponsibleId;
	private Integer projectId;
	private Boolean isCompleted;
	private Date startDate;
	private Date finishDate;
	
	public TodoOnResponsibleResponse(TodoOnResponsibleEntity responsible, TodoOnProjectEntity todo) {
		todoName = todo.getTodoName();
		todoOnResponsibleId = responsible.getTodoOnResponsibleId();
		projectId = responsible.getProjectId();
		isCompleted = responsible.getIsCompleted();
		startDate = todo.getStartDate();
		finishDate = todo.getFinishDate();
		todoOnProjectId = todo.getTodoOnProjectId();
	}
}
