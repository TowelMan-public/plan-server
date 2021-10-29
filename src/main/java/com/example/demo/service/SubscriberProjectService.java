package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.response.SubscriberInPublicProjectResponse;

@Service
public class SubscriberProjectService {

	@Transactional
	public void insert(Integer userId, Integer publicProjectId, String userName) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	public List<SubscriberInPublicProjectResponse> getList(Integer userId, Integer publicProjectId) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Transactional
	public void update(Integer userId, Integer publicProjectId, String userName, Integer authorityId) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Transactional
	public void delete(Integer userId, Integer publicProjectId, String userName) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Transactional
	public void accept(Integer userId, Integer publicProjectId) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Transactional
	public void bloak(Integer userId, Integer publicProjectId) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Transactional
	public void exit(Integer userId, Integer publicProjectId) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

}
