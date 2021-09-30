package com.example.demo.form;

import java.util.Date;

import com.example.demo.exception.ValidateException;
import com.example.demo.validate.ValidateManager;
import com.example.demo.validate.validatable.NotNullValidatable;
import com.example.demo.validate.validator.ObjectValidator;

import lombok.Getter;
import lombok.Setter;

public class ResponsibleTodoForm {
	@Getter
	@Setter
	private Integer todoOnProjectId;
	
	@Getter
	@Setter
	private String userName;
	
	@Getter
	@Setter
	private Boolean isCompleted;
	
	@Getter
	@Setter
	private Integer publicProjectId;
	
	@Getter
	@Setter
	private Date startDate;
	
	@Getter
	@Setter
	private Date finishDate;
	
	public void validatePostExit() throws ValidateException {
		new ValidateManager()
		.or()
			.add(new ObjectValidator(todoOnProjectId)
					.addValidatable(new NotNullValidatable()))
		.run();
	}
	
	public void validatePutIsCompleted() throws ValidateException {
		new ValidateManager()
			.or()
				.add(new ObjectValidator(isCompleted)
						.addValidatable(new NotNullValidatable()))
			.run();
	}
	
	public void validatePutIsCompletedOnUser() throws ValidateException {
		new ValidateManager()
			.or()
				.add(new ObjectValidator(isCompleted)
						.addValidatable(new NotNullValidatable()))
			.run();
	}
}
