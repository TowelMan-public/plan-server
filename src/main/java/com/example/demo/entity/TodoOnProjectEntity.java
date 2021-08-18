package com.example.demo.entity;

import java.util.Date;

public class TodoOnProjectEntity {

	private Integer todoOnProjectId;

	private Integer projectId;

	private String todoName;

	private Date startDate;

	private Date finishDate;

	private Boolean isCopyContentsToUsers;

	private Boolean isCompleted;

	private Boolean isDeleted;

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

	public String getTodoName() {
		return todoName;
	}

	public void setTodoName(String todoName) {
		this.todoName = todoName;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getFinishDate() {
		return finishDate;
	}

	public void setFinishDate(Date finishDate) {
		this.finishDate = finishDate;
	}

	public Boolean getIsCopyContentsToUsers() {
		return isCopyContentsToUsers;
	}

	public void setIsCopyContentsToUsers(Boolean isCopyContentsToUsers) {
		this.isCopyContentsToUsers = isCopyContentsToUsers;
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