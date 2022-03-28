package com.example.demo.form;

import java.util.Date;

import com.example.demo.configurer.DateFormatConfig;
import com.example.demo.exception.ValidateException;
import com.example.demo.validate.ValidateManager;
import com.example.demo.validate.validatable.LengthStringValidatable;
import com.example.demo.validate.validatable.NotBlankStringValidatable;
import com.example.demo.validate.validatable.NotNullValidatable;
import com.example.demo.validate.validator.ObjectValidator;
import com.example.demo.validate.validator.StringValidator;
import com.fasterxml.jackson.annotation.JsonFormat;

import org.springframework.format.annotation.DateTimeFormat;

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
	@JsonFormat(pattern=DateFormatConfig.FORMAT)
	@DateTimeFormat(pattern=DateFormatConfig.FORMAT)
	private Date startDate;
	
	@Getter
	@Setter
	@JsonFormat(pattern=DateFormatConfig.FORMAT)
	@DateTimeFormat(pattern=DateFormatConfig.FORMAT)
	private Date finishDate;
	
	@Getter
	@Setter
	private Boolean isInclideCompletedTodo;
	
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
				.add(new StringValidator(userName)
						.addValidatable(new NotBlankStringValidatable())
						.addValidatable(new LengthStringValidatable(100)))
			.run();
	}
}
