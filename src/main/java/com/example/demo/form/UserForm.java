package com.example.demo.form;

import com.example.demo.exception.ValidateException;
import com.example.demo.validate.ValidateManager;
import com.example.demo.validate.validatable.LengthStringValidatable;
import com.example.demo.validate.validatable.NotBlankStringValidatable;
import com.example.demo.validate.validator.StringValidator;

import lombok.Getter;
import lombok.Setter;

public class UserForm {
	@Getter
	@Setter
	private String userName;
	
	@Getter
	@Setter
	private String userNickName;
	
	@Getter
	@Setter
	private String password;
	
	@Getter
	@Setter
	private String refreshJwtToken;
	
	public void validatePostToken() throws ValidateException {
		new ValidateManager()
			.or()
				.add(new StringValidator(userName)
						.addValidatable(new NotBlankStringValidatable())
						.addValidatable(new LengthStringValidatable(100)))
				.add(new StringValidator(password)
						.addValidatable(new NotBlankStringValidatable())
						.addValidatable(new LengthStringValidatable(100)))
			.or()
				.add(new StringValidator(refreshJwtToken)
						.addValidatable(new NotBlankStringValidatable()))
			.run();
	}
	
	public void validatePost() throws ValidateException {
		new ValidateManager()
			.or()
				.add(new StringValidator(userName)
						.addValidatable(new NotBlankStringValidatable())
						.addValidatable(new LengthStringValidatable(100)))
				.add(new StringValidator(userNickName)
						.addValidatable(new NotBlankStringValidatable())
						.addValidatable(new LengthStringValidatable(100)))
				.add(new StringValidator(password)
						.addValidatable(new NotBlankStringValidatable())
						.addValidatable(new LengthStringValidatable(100)))
			.run();
	}
	
	public void validatePut() throws ValidateException {
		new ValidateManager()
		.or()
			.add(new StringValidator(userName)
					.addValidatable(new NotBlankStringValidatable())
					.addValidatable(new LengthStringValidatable(100)))
		.or()
			.add(new StringValidator(userNickName)
					.addValidatable(new NotBlankStringValidatable())
					.addValidatable(new LengthStringValidatable(100)))
		.or()
			.add(new StringValidator(password)
					.addValidatable(new NotBlankStringValidatable())
					.addValidatable(new LengthStringValidatable(100)))
		.run();
	}
}
