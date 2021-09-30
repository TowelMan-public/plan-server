package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.form.ContentForm;
import com.example.demo.response.ContentResponse;

@Service
public class ContentService {

	public Integer insert(Integer userId, ContentForm form) {
		// TODO 自動生成されたメソッド・スタブ
		return null;//TODO 新しいID
	}

	public List<ContentResponse> getList(Integer userId, Integer todoId) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	public ContentResponse get(Integer userId, Integer contentId) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	public void updateTitle(Integer userId, Integer contentId, String contentTitle) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	public void updateExplanation(Integer userId, Integer contentId, String contentExplanation) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	public void delete(Integer userId, Integer contentId) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	public void setIsCompleted(Integer userId, Integer contentId, Boolean isCompleted) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

}
