package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.exception.AlreadyJoinedPublicProjectException;
import com.example.demo.exception.NotHaveAuthorityToOperateProjectException;
import com.example.demo.form.SubscriberInProjectForm;
import com.example.demo.response.SubscriberInPublicProjectResponse;

@Service
public class SubscriberProjectService {

	public void insert(Integer userId, Integer publicProjectId, SubscriberInProjectForm form) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	public List<SubscriberInPublicProjectResponse> getList(Integer userId, Integer publicProjectId) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	public void update(Integer userId, Integer publicProjectId, String userName, Integer authorityId) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	public void delete(Integer userId, Integer publicProjectId, String userName) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	public void accept(Integer userId, Integer publicProjectId) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	public void bloak(Integer userId, Integer publicProjectId) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	public void exit(Integer userId, Integer publicProjectId) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

}
