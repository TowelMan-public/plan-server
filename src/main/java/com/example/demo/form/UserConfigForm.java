package com.example.demo.form;

import lombok.Getter;
import lombok.Setter;

public class UserConfigForm {
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
}
