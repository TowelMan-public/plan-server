package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.response.UserConfigResponse;

@Service
public class UserConfigServer {

	public UserConfigResponse get(Integer userId) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	public void updateBeforeDeadlineForProjectNotice(Integer userId, Boolean beforeDeadlineForProjectNotice) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	public void updateBeforeDeadlineForTodoNotice(Integer userId, Boolean beforeDeadlineForTodoNotice) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	public void updateIsPushSatrtedTodoNotice(Integer userId, Boolean isPushSatrtedTodoNotice) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	public void updatePushInsertedTodoNotice(Integer userId, Boolean pushInsertedTodoNotice) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

}
