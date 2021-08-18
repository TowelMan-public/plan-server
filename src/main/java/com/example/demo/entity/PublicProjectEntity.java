package com.example.demo.entity;

import java.util.Date;

public class PublicProjectEntity {

	private Integer publicProjectId;

	private String projectName;

	private Date startDate;

	private Date finishDate;

	private Boolean isCompleted;

	private Boolean isDeleted;

	public Integer getPublicProjectId() {
		return publicProjectId;
	}

	public void setPublicProjectId(Integer publicProjectId) {
		this.publicProjectId = publicProjectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
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