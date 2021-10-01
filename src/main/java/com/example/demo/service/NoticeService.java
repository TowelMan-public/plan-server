package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.response.NoticeResponse;

@Service
public class NoticeService {

	@Transactional
	public List<NoticeResponse> getUnacquiredNotice(Integer userId, String terminalName) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}
	
	public List<NoticeResponse> getNotice(Integer userId) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

}
