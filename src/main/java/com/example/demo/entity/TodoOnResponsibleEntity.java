package com.example.demo.entity;

public class TodoOnResponsibleEntity {

	private Integer todoOnResponsibleId;

	private Integer todoOnProjectId;

	private Integer projectId;

	private Integer userId;

	private Boolean isCompleted;

	private Boolean isDeleted;

	public Integer getTodoOnResponsibleId() {
		return todoOnResponsibleId;
	}

	public void setTodoOnResponsibleId(Integer todoOnResponsibleId) {
		this.todoOnResponsibleId = todoOnResponsibleId;
	}
	public Integer getTodoOnProjectId() {
		return todoOnProjectId;
	}
	public void setTodoOnProjectId(Integer todoOnProjectId) {
		this.todoOnProjectId = todoOnProjectId;
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Boolean getIsCompleted() {
		return isCompleted;
	}

	public void setIsCompleted(Boolean isCompleted) {
		this.isCompleted = isCompleted;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
}