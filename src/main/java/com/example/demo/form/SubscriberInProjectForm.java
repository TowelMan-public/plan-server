package com.example.demo.form;

import com.example.demo.exception.ValidateException;
import com.example.demo.validate.ValidateManager;
import com.example.demo.validate.validatable.LengthStringValidatable;
import com.example.demo.validate.validatable.MaxValueIntegerValidatable;
import com.example.demo.validate.validatable.MinValueIntegerValidatable;
import com.example.demo.validate.validatable.NotBlankStringValidatable;
import com.example.demo.validate.validator.IntegerValidator;
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
					.addValidatable(new LengthStringValidatable(100))
					.addValidatable(new NotBlankStringValidatable()))
		.run();
	}
	
	public void validatePut() throws ValidateException {
		new ValidateManager()
		.or()
			.add(new StringValidator(userName)
					.addValidatable(new LengthStringValidatable(100))
					.addValidatable(new NotBlankStringValidatable()))
			.add(new IntegerValidator(authorityId)
					.addValidatable(new MaxValueIntegerValidatable(3))
					.addValidatable(new MinValueIntegerValidatable(1)))
		.run();
	}
	
	public void validateDelete() throws ValidateException {
		new ValidateManager()
		.or()
			.add(new StringValidator(userName)
					.addValidatable(new LengthStringValidatable(100))
					.addValidatable(new NotBlankStringValidatable()))
		.run();
	}
}