package com.example.demo.form;

import com.example.demo.exception.ValidateException;
import com.example.demo.validate.ValidateManager;
import com.example.demo.validate.validatable.NotBlankStringValidatable;
import com.example.demo.validate.validator.StringValidator;

import lombok.Getter;
import lombok.Setter;

public class PrivateProjectForm {
	@Getter
	@Setter
	private String projectName;
	
	public void validatePost()  throws ValidateException {
		new ValidateManager()
			.or()
				.add(new StringValidator(projectName)
						.addValidatable(new NotBlankStringValidatable()))
			.run();
	}
}
