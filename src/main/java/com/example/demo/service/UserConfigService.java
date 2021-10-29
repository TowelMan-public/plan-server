package com.example.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.response.UserConfigResponse;

@Service
public class UserConfigService {

	public UserConfigResponse get(Integer userId) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Transactional
	public void updateBeforeDeadlineForProjectNotice(Integer userId, Integer beforeDeadlineForProjectNotice) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Transactional
	public void updateBeforeDeadlineForTodoNotice(Integer userId, Integer beforeDeadlineForTodoNotice) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Transactional
	public void updateIsPushSatrtedTodoNotice(Integer userId, Boolean isPushSatrtedTodoNotice) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Transactional
	public void updatePushInsertedTodoNotice(Integer userId, Boolean pushInsertedTodoNotice) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

}
