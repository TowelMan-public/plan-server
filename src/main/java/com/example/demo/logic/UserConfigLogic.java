package com.example.demo.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.entity.UserConfigEntity;
import com.example.demo.repository.UserConfigEntityMapper;

/**
 * ユーザーの設定の共通処理
 * @author towelman
 *
 */
@Component
public class UserConfigLogic {
	private static final Integer DEFAULT_DEADLINE = 86400;
	
	@Autowired
	UserConfigEntityMapper userConfigEntityMapper;

	/**
	 * ユーザーの、既定の設定を登録する
	 * @param userId ユーザーID
	 */
	public void insertDefaultConfig(Integer userId) {
		var entity = new UserConfigEntity();
		entity.setUserId(userId);
		entity.setIsPushInsertedStartedTodoNotice(true);
		entity.setIsPushInsertedTodoNotice(true);
		entity.setBeforeDeadlineForProjectNotice(DEFAULT_DEADLINE);
		entity.setBeforeDeadlineForTodoNotice(DEFAULT_DEADLINE);
		
		userConfigEntityMapper.insert(entity);
	}

	/**
	 * ユーザーの設定を取得する
	 * @param userId ユーザーID
	 * @return ユーザーの設定
	 */
	public UserConfigEntity get(Integer userId) {
		return userConfigEntityMapper.selectByPrimaryKey(userId);
	}

	/**
	 * ユーザーの、beforeDeadlineForProjectNoticeの値を変える
	 * @param userId　ユーザーID
	 * @param beforeDeadlineForProjectNotice　単位は秒
	 */
	public void updateBeforeDeadlineForProjectNotice(Integer userId, Integer beforeDeadlineForProjectNotice) {
		var entity = new UserConfigEntity();
		entity.setUserId(userId);
		entity.setBeforeDeadlineForProjectNotice(beforeDeadlineForProjectNotice);
		
		userConfigEntityMapper.updateByPrimaryKeySelective(entity);
	}

	/**
	 * ユーザーの、beforeDeadlineForTodoNoticeの値を変える
	 * @param userId　ユーザーID
	 * @param beforeDeadlineForTodoNotice　単位は秒
	 */
	public void updateBeforeDeadlineForTodoNotice(Integer userId, Integer beforeDeadlineForTodoNotice) {
		var entity = new UserConfigEntity();
		entity.setUserId(userId);
		entity.setBeforeDeadlineForTodoNotice(beforeDeadlineForTodoNotice);
		
		userConfigEntityMapper.updateByPrimaryKeySelective(entity);
	}

	/**
	 * ユーザーの、isPushSatrtedTodoNoticeの値を変える
	 * @param userId　ユーザーID
	 * @param isPushSatrtedTodoNotice　trueならON、falseならOFF
	 */
	public void updateIsPushSatrtedTodoNotice(Integer userId, Boolean isPushSatrtedTodoNotice) {
		var entity = new UserConfigEntity();
		entity.setUserId(userId);
		entity.setIsPushInsertedStartedTodoNotice(isPushSatrtedTodoNotice);
		
		userConfigEntityMapper.updateByPrimaryKeySelective(entity);
	}

	/**
	 * ユーザーの、isPushInsertedTodoNoticeの値を変える
	 * @param userId　ユーザーID
	 * @param isPushInsertedTodoNotice　trueならON、falseならOFF
	 */
	public void updatePushInsertedTodoNotice(Integer userId, Boolean isPushInsertedTodoNotice) {
		var entity = new UserConfigEntity();
		entity.setUserId(userId);
		entity.setIsPushInsertedTodoNotice(isPushInsertedTodoNotice);
		
		userConfigEntityMapper.updateByPrimaryKeySelective(entity);
	}
}
