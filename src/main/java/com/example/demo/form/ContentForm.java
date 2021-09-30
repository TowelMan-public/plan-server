package com.example.demo.form;

import com.example.demo.exception.ValidateException;
import com.example.demo.validate.ValidateManager;
import com.example.demo.validate.validatable.NotBlankStringValidatable;
import com.example.demo.validate.validatable.NotNullValidatable;
import com.example.demo.validate.validator.ObjectValidator;
import com.example.demo.validate.validator.StringValidator;

import lombok.Getter;
import lombok.Setter;

public class ContentForm {
	@Getter
	@Setter
	private Integer todoId;
	
	@Getter
	@Setter
	private String contentTitle;
	
	@Getter
	@Setter
	private String contentExplanation;
	
	@Getter
	@Setter
	private Boolean isCompleted;
	
	public void validatePost() throws ValidateException {
		new ValidateManager()
		.or()
			.add(new ObjectValidator(todoId)
					.addValidatable(new NotNullValidatable()))
			.add(new StringValidator(contentTitle)
					.addValidatable(new NotBlankStringValidatable()))
			.add(new StringValidator(contentExplanation)
					.addValidatable(new NotBlankStringValidatable()))
		.run();
	}
	
	public void validateGet() throws ValidateException {
		new ValidateManager()
		.or()
			.add(new ObjectValidator(todoId)
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
}
