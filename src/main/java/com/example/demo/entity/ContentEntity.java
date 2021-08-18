package com.example.demo.entity;

public class ContentEntity {

	private Integer contentId;

	private Integer todoId;

	private String contentTitle;

	private String contentExplanation;

	private Boolean isCompleted;

	private Boolean isDeleted;

	public Integer getContentId() {
		return contentId;
	}

	public void setContentId(Integer contentId) {
		this.contentId = contentId;
	}

	public Integer getTodoId() {
		return todoId;
	}

	public void setTodoId(Integer todoId) {
		this.todoId = todoId;
	}

	public String getContentTitle() {
		return contentTitle;
	}

	public void setContentTitle(String contentTitle) {
		this.contentTitle = contentTitle;
	}

	public String getContentExplanation() {
		return contentExplanation;
	}

	public void setContentExplanation(String contentExplanation) {
		this.contentExplanation = contentExplanation;
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