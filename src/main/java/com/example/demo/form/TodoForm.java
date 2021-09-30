package com.example.demo.form;

import java.util.Date;

import com.example.demo.exception.ValidateException;
import com.example.demo.validate.ValidateManager;
import com.example.demo.validate.validatable.DateAfterValidatable;
import com.example.demo.validate.validatable.NotBlankStringValidatable;
import com.example.demo.validate.validatable.NotNullValidatable;
import com.example.demo.validate.validator.DateValidator;
import com.example.demo.validate.validator.ObjectValidator;
import com.example.demo.validate.validator.StringValidator;

import lombok.Getter;
import lombok.Setter;

public class TodoForm {
	@Getter
	@Setter
	private Integer projectId;
	
	@Getter
	@Setter
	private String todoName;
	
	@Getter
	@Setter
	private Date startDate;
	
	@Getter
	@Setter
	private Date finishDate;
	
	@Getter
	@Setter
	private Boolean isCopyContentsToResponsible;
	
	@Getter
	@Setter
	private Boolean isCompleted;
	
	@Getter
	@Setter
	private Boolean isInPrivateProjectOnly;
	
	public void validatePost()throws ValidateException {
		new ValidateManager()
		.or()
			.add(new ObjectValidator(projectId)
					.addValidatable(new NotNullValidatable()))
			.add(new StringValidator(todoName)
					.addValidatable(new NotBlankStringValidatable()))
			.add(new DateValidator(startDate)
					.addValidatable(new NotNullValidatable()))
			.add(new DateValidator(finishDate)
					.addValidatable(new NotNullValidatable())
					.addValidatable(new DateAfterValidatable(startDate)))
			.add(new ObjectValidator(isCopyContentsToResponsible)
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