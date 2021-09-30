package com.example.demo.form;

import lombok.Getter;
import lombok.Setter;

public class UserConfigForm {
	@Getter
	@Setter
	private Boolean beforeDeadlineForTodoNotice;
	
	@Getter
	@Setter
	private Boolean beforeDeadlineForProjectNotice;
	
	@Getter
	@Setter
	private Boolean pushInsertedTodoNotice;
	
	@Getter
	@Setter
	private Boolean isPushSatrtedTodoNotice;
}
