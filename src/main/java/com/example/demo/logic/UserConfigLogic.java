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
}
