package com.example.demo.response;

import java.util.Date;

import com.example.demo.entity.TodoOnProjectEntity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TodoOnProjectResponse {
	private Integer todoOnProjectId;
	private Integer projectId;
	private String todoName;
	private Date startDate;
	private Date finishDate;
	private Boolean isCopyContentsToUsers;
	private Boolean isCompleted;
	
	public TodoOnProjectResponse(TodoOnProjectEntity entity) {
		todoOnProjectId = entity.getTodoOnProjectId();
		projectId = entity.getProjectId();
		todoName = entity.getTodoName();
		startDate = entity.getStartDate();
		finishDate = entity.getFinishDate();
		isCopyContentsToUsers = entity.getIsCopyContentsToUsers();
		isCompleted = entity.getIsCompleted();
	}
}
