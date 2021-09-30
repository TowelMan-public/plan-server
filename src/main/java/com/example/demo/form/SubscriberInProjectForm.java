package com.example.demo.form;

import com.example.demo.exception.ValidateException;
import com.example.demo.validate.ValidateManager;
import com.example.demo.validate.validatable.NotBlankStringValidatable;
import com.example.demo.validate.validatable.NotNullValidatable;
import com.example.demo.validate.validator.ObjectValidator;
import com.example.demo.validate.validator.StringValidator;

import lombok.Getter;
import lombok.Setter;

public class SubscriberInProjectForm {
	@Getter
	@Setter
	private String userName;
	
	@Getter
	@Setter
	private Integer authorityId;
	
	public void validatePost() throws ValidateException {
		new ValidateManager()
		.or()
			.add(new StringValidator(userName)
					.addValidatable(new NotBlankStringValidatable()))
		.run();
	}
	
	public void validatePut() throws ValidateException {
		new ValidateManager()
		.or()
			.add(new StringValidator(userName)
					.addValidatable(new NotBlankStringValidatable()))
			.add(new ObjectValidator(authorityId)
					.addValidatable(new NotNullValidatable()))
		.run();
	}
	
	public void validateDelete() throws ValidateException {
		new ValidateManager()
		.or()
			.add(new StringValidator(userName)
					.addValidatable(new NotBlankStringValidatable()))
		.run();
	}
}