package com.example.demo.response;

import com.example.demo.entity.SubscriberInPublicProjectEntity;
import com.example.demo.entity.UserEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode
@NoArgsConstructor
public class SubscriberInPublicProjectResponse {
	private Integer publicProjectId;
	private String userName;
	private Integer authorityId;
	
	public SubscriberInPublicProjectResponse(SubscriberInPublicProjectEntity subscriber, UserEntity user) {
		userName = user.getUserName();
		publicProjectId = subscriber.getPublicProjectId();
		authorityId = subscriber.getAuthorityId();
	}
}
