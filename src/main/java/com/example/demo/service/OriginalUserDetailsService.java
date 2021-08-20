package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.exception.NotFoundValueException;
import com.example.demo.security.UserDetailsImp;

/**
 * 認証回りにかかわるビジネスロジックを書くクラス
 */
@Service
public class OriginalUserDetailsService {

	//@Autowired
	//UserLogic userLogic;
	
	/**
	 * ユーザー名からユーザ情報の取得
	 * @param userName　ユーザー名
	 * @return ユーザ情報
	 * @throws com.example.demo.exception.NotFoundValueException ユーザー名が存在しない
	*/
	public UserDetailsImp loadUserByUserName(String userName) throws NotFoundValueException {
		//return new UserDetailsImp(
		//		userLogic.getUserByUserIdName(userIdName));
		//TODO
		return null;
	}

	/**
	 * ユーザーIdからユーザ情報の取得
	 * @param userId　ユーザーId
	 * @return ユーザ情報
	 * @throws com.example.demo.exception.NotFoundValueException ユーザーIdが存在しない
	*/
	public UserDetailsImp loadUserByUserId(Integer userId) throws NotFoundValueException {		
		//return new UserDetailsImp(
		//		userLogic.getUserByUserId(userId));
		//TODO
		return null;
	}
}
