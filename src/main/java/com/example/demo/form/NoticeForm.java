package com.example.demo.form;

import javax.validation.constraints.AssertTrue;

import lombok.Getter;
import lombok.Setter;

public class NoticeForm {
	@Getter
	@Setter
	private String terminalName;
	
	@AssertTrue(message = "too big string!")
	public boolean isBeforeDeadlineForTodoNoticeAppropriate() {
		return terminalName == null ||
				terminalName.length() <= 100;
	}
}
