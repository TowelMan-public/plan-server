package com.example.demo.form;

import javax.validation.constraints.AssertTrue;

import lombok.Getter;
import lombok.Setter;

public class UserConfigForm {
	private static final Integer MIN_DEADLINE = 86400;
	
	@Getter
	@Setter
	private Integer beforeDeadlineForTodoNotice;
	
	@Getter
	@Setter
	private Integer beforeDeadlineForProjectNotice;
	
	@Getter
	@Setter
	private Boolean pushInsertedTodoNotice;
	
	@Getter
	@Setter
	private Boolean isPushSatrtedTodoNotice;
	
	@AssertTrue(message = "beforeDeadlineForTodoNotice must be bigger than 86400 or as big as 86400.")
	public boolean isBeforeDeadlineForTodoNoticeAppropriate() {
		return beforeDeadlineForTodoNotice == null ||
				beforeDeadlineForTodoNotice >= MIN_DEADLINE;
	}
	
	@AssertTrue(message = "beforeDeadlineForProjectNotice must be bigger than 86400 or as big as 86400.")
	public boolean isBeforeDeadlineForProjectNoticeAppropriate() {
		return beforeDeadlineForProjectNotice == null ||
				beforeDeadlineForProjectNotice >= MIN_DEADLINE;
	}
}
