package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.form.UserForm;
import com.example.demo.response.UserResponse;

@Service
public class UserService {

	public UserResponse get(String userName) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	public UserResponse get(Integer userId) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	public void delete(Integer userId) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	public void insert(UserForm form) {//AlreadyUsedUserNameException
		// TODO 自動生成されたメソッド・スタブ
		
	}

	public void updatePassword(Integer userId, String password) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	public void updateUserNickName(Integer userId, String userNickName) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	public void updateUserName(Integer userId, String userName) {//AlreadyUsedUserNameException
		// TODO 自動生成されたメソッド・スタブ
		
	}

}
