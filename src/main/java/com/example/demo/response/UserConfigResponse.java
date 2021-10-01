package com.example.demo.response;

import com.example.demo.entity.UserConfigEntity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserConfigResponse {
	private Integer beforeDeadlineForTodoNotice;
	private Integer beforeDeadlineForProjectNotice;
	private Boolean isPushInsertedTodoNotice;
	private Boolean isPushInsertedStartedTodoNotice;
	
	public UserConfigResponse(UserConfigEntity entity){
		beforeDeadlineForTodoNotice = entity.getBeforeDeadlineForTodoNotice();
		beforeDeadlineForProjectNotice = entity.getBeforeDeadlineForProjectNotice();
		isPushInsertedTodoNotice = entity.getIsPushInsertedTodoNotice();
		isPushInsertedStartedTodoNotice = entity.getIsPushInsertedStartedTodoNotice();
	}
}
