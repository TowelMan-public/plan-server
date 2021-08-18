package com.example.demo.entity;

public class UserConfigEntity {

	private Integer userId;

	private Integer beforeDeadlineForTodoNotice;

	private Integer beforeDeadlineForProjectNotice;

	private Boolean isPushInsertedTodoNotice;

	private Boolean isPushInsertedStartedTodoNotice;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getBeforeDeadlineForTodoNotice() {
		return beforeDeadlineForTodoNotice;
	}

	public void setBeforeDeadlineForTodoNotice(Integer beforeDeadlineForTodoNotice) {
		this.beforeDeadlineForTodoNotice = beforeDeadlineForTodoNotice;
	}

	public Integer getBeforeDeadlineForProjectNotice() {
		return beforeDeadlineForProjectNotice;
	}

	public void setBeforeDeadlineForProjectNotice(Integer beforeDeadlineForProjectNotice) {
		this.beforeDeadlineForProjectNotice = beforeDeadlineForProjectNotice;
	}
	public Boolean getIsPushInsertedTodoNotice() {
		return isPushInsertedTodoNotice;
	}

	public void setIsPushInsertedTodoNotice(Boolean isPushInsertedTodoNotice) {
		this.isPushInsertedTodoNotice = isPushInsertedTodoNotice;
	}

	public Boolean getIsPushInsertedStartedTodoNotice() {
		return isPushInsertedStartedTodoNotice;
	}

	public void setIsPushInsertedStartedTodoNotice(Boolean isPushInsertedStartedTodoNotice) {
		this.isPushInsertedStartedTodoNotice = isPushInsertedStartedTodoNotice;
	}
}