package com.example.demo.response;

import com.example.demo.entity.PrivateProjectEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode
@NoArgsConstructor
public class PrivateProjectResponse {
	private Integer projectId;
	private String projectName;
	
	public PrivateProjectResponse(PrivateProjectEntity entity) {
		projectId = entity.getProjectId();
		projectName = entity.getProjectName();
	}
}
