package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.exception.AlreadyUsedUserNameException;
import com.example.demo.exception.NotFoundValueException;
import com.example.demo.form.UserForm;
import com.example.demo.logic.UserConfigLogic;
import com.example.demo.logic.UserLogic;
import com.example.demo.response.UserResponse;

/**
 * ユーザーに関するビジネスロジック
 * @author towelman
 *
 */
@Service
public class UserService {
	@Autowired
	UserLogic userLogic;
	@Autowired
	UserConfigLogic userConfigLogic;
	@Autowired
	PasswordEncoder passwordEncoder;
	
	/**
	 * ユーザー名からユーザーを取得する
	 * @param userName ユーザー名
	 * @return ユーザー
	 * @throws NotFoundValueException ユーザー名が存在しない
	 */
	public UserResponse get(String userName) throws NotFoundValueException {
		return new UserResponse(userLogic.getUserByUserName(userName));
	}

	/**
	 * ユーザーIDからユーザーを取得する
	 * @param userId ユーザーID
	 * @return ユーザー
	 */
	public UserResponse get(Integer userId) {
		return new UserResponse(userLogic.getUserByUserId(userId));
	}

	/**
	 * ユーザーを削除する
	 * @param userId ユーザーID
	 */
	@Transactional
	public void delete(Integer userId) {
		userLogic.delete(userId);
	}

	/**
	 * 新規ユーザー登録をする
	 * @param form 新規ユーザーの情報
	 * @throws AlreadyUsedUserNameException 既に使われているユーザー名が指定された
	 */
	@Transactional
	public void insert(UserForm form) throws AlreadyUsedUserNameException {
		var userName = form.getUserName();
		var userNickName = form.getUserNickName();
		var password = passwordEncoder.encode(form.getPassword());
		
		userLogic.validateUserNameNotUsed(userName);
		
		var userId = userLogic.insert(userName, userNickName, password);
		userConfigLogic.insertDefaultConfig(userId);
	}

	/**
	 * パスワードの変更
	 * @param userId ユーザーID
	 * @param rawpassword 生パスワード
	 */
	@Transactional
	public void updatePassword(Integer userId, String rawpassword) {
		var password = passwordEncoder.encode(rawpassword);
		
		userLogic.updatePasswrod(userId, password);
	}

	/**
	 * ユーザーのニックネームを変更する
	 * @param userId ユーザーID
	 * @param userNickName ユーザーニックネーム
	 */
	@Transactional
	public void updateUserNickName(Integer userId, String userNickName) {
		userLogic.updateNickName(userId, userNickName);
	}

	/**
	 * ユーザー名を変更する
	 * @param userId ユーザーID
	 * @param userName 新しいユーザー名
	 * @throws AlreadyUsedUserNameException 指定された新しいユーザー名がすでに使われている
	 */
	@Transactional
	public void updateUserName(Integer userId, String userName) throws AlreadyUsedUserNameException {
		userLogic.validateUserNameNotUsed(userName);
		
		userLogic.updateName(userId, userName);
	}

}
