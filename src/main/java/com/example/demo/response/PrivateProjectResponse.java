package com.example.demo.response;

import com.example.demo.entity.PrivateProjectEntity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PrivateProjectResponse {
	private Integer projectId;
	private String projectName;
	
	public PrivateProjectResponse(PrivateProjectEntity entity) {
		projectId = entity.getProjectId();
		projectName = entity.getProjectName();
	}
}
