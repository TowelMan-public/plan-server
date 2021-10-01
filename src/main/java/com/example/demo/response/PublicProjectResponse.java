package com.example.demo.response;

import java.util.Date;

import com.example.demo.entity.PublicProjectEntity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PublicProjectResponse {
	private Integer publicProjectId;
	private String projectName;
	private Date startDate;
	private Date finishDate;
	private Boolean isCompleted;
	
	public PublicProjectResponse(PublicProjectEntity entity) {
		publicProjectId = entity.getPublicProjectId();
		projectName = entity.getProjectName();
		startDate = entity.getStartDate();
		finishDate = entity.getFinishDate();
		isCompleted = entity.getIsCompleted();
	}
}
