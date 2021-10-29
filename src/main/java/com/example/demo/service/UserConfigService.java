package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.logic.UserConfigLogic;
import com.example.demo.response.UserConfigResponse;

@Service
public class UserConfigService {
	@Autowired
	UserConfigLogic userConfigLogic;
	
	/**
	 * ユーザーの設定を取得する
	 * @param userId ユーザーID
	 * @return ユーザーの設定
	 */
	public UserConfigResponse get(Integer userId) {
		return new UserConfigResponse(userConfigLogic.get(userId));
	}

	/**
	 * beforeDeadlineForProjectNoticeの値を変更する
	 * @param userId ユーザーID
	 * @param beforeDeadlineForProjectNotice 単位は秒
	 */
	@Transactional
	public void updateBeforeDeadlineForProjectNotice(Integer userId, Integer beforeDeadlineForProjectNotice) {
		userConfigLogic.updateBeforeDeadlineForProjectNotice(userId, beforeDeadlineForProjectNotice);
	}

	/**
	 * beforeDeadlineForTodoNoticeの値を変更する
	 * @param userId ユーザーID
	 * @param beforeDeadlineForTodoNotice 単位は秒
	 */
	@Transactional
	public void updateBeforeDeadlineForTodoNotice(Integer userId, Integer beforeDeadlineForTodoNotice) {
		userConfigLogic.updateBeforeDeadlineForTodoNotice(userId, beforeDeadlineForTodoNotice);		
	}

	/**
	 * isPushSatrtedTodoNoticeの値を変更する
	 * @param userId ユーザーID
	 * @param isPushSatrtedTodoNotice trueならON、falseならOFF
	 */
	@Transactional
	public void updateIsPushSatrtedTodoNotice(Integer userId, Boolean isPushSatrtedTodoNotice) {
		userConfigLogic.updateIsPushSatrtedTodoNotice(userId, isPushSatrtedTodoNotice);
	}

	/**
	 * isPushInsertedTodoNoticeの値を変更する
	 * @param userId ユーザーID
	 * @param isPushInsertedTodoNotice trueならON、falseならOFF
	 */
	@Transactional
	public void updateIsPushInsertedTodoNotice(Integer userId, Boolean isPushInsertedTodoNotice) {
		userConfigLogic.updatePushInsertedTodoNotice(userId, isPushInsertedTodoNotice);
	}

}
