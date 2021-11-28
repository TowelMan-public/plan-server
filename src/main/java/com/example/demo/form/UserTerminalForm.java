package com.example.demo.form;

import com.example.demo.exception.ValidateException;
import com.example.demo.validate.ValidateManager;
import com.example.demo.validate.validatable.LengthStringValidatable;
import com.example.demo.validate.validatable.NotBlankStringValidatable;
import com.example.demo.validate.validator.StringValidator;

import lombok.Getter;
import lombok.Setter;

public class UserTerminalForm {	
	@Getter
	@Setter
	private String terminalName;
	
	@Getter
	@Setter
	private String oldTerminalName;
	
	@Getter
	@Setter
	private String newTerminalName;
	
	public void validatePost() throws ValidateException {
		new ValidateManager()
			.or()
				.add(new StringValidator(terminalName)
						.addValidatable(new LengthStringValidatable(100))
						.addValidatable(new NotBlankStringValidatable()))
			.run();
	}
	
	public void validateDelete() throws ValidateException {
		new ValidateManager()
			.or()
				.add(new StringValidator(oldTerminalName)
						.addValidatable(new LengthStringValidatable(100))
						.addValidatable(new NotBlankStringValidatable()))
				.add(new StringValidator(newTerminalName)
						.addValidatable(new LengthStringValidatable(100))
						.addValidatable(new NotBlankStringValidatable()))
			.run();
	}
	
	public void validatePut() throws ValidateException {
		new ValidateManager()
			.or()
				.add(new StringValidator(terminalName)
						.addValidatable(new LengthStringValidatable(100))
						.addValidatable(new NotBlankStringValidatable()))
			.run();
	}
}
