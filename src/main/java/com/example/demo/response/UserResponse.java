package com.example.demo.response;

import com.example.demo.entity.UserEntity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserResponse {
	private String userName;
	private String userNickname;
		
	public UserResponse(UserEntity entity) {
		userName = entity.getUserName();
		userNickname = entity.getUserNickname();
	}
}
