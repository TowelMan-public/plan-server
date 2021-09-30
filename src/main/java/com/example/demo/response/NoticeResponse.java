package com.example.demo.response;

import lombok.Data;

@Data
public class NoticeResponse {
	public static final String TODO_NOTICE = "TODO_NOTICE";
	public static final String PROJECT_NOTICE = "PROJECT_NOTICE";
	
	private String message;
	private Integer id;
	private String noticeType;
}
