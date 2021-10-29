package com.example.demo.response;

import com.example.demo.entity.ContentEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode
@NoArgsConstructor
public class ContentResponse {
	private Integer contentId;
	private Integer todoId;
	private String contentTitle;
	private String contentExplanation;
	private Boolean isCompleted;
	
	public ContentResponse(ContentEntity entity) {
		contentId = entity.getContentId();
		todoId = entity.getTodoId();
		contentTitle = entity.getContentTitle();
		contentExplanation = entity.getContentExplanation();
		isCompleted = entity.getIsCompleted();
	}
}
