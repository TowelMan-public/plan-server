package com.example.demo.validate.validator;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.demo.validate.validatable.DateValidatable;
import com.example.demo.validate.validatable.ObjectValidatable;

public class DateValidator implements Validator {
	private Date date;
	private List<ObjectValidatable> objectValidatable = new ArrayList<>();
	private List<DateValidatable> dateValidatableList = new ArrayList<>();

	public DateValidator(Date date) {this.date = date;}
	
	public DateValidator addValidatable(ObjectValidatable validatable) {
		objectValidatable.add(validatable);
		return this;
	}
	
	public DateValidator addValidatable(DateValidatable validatable) {
		dateValidatableList.add(validatable);
		return this;
	}

	@Override
	public String runValidate() {
		String validateMessage = null;
				
		for(ObjectValidatable it: objectValidatable) {
			validateMessage = it.validate(date);
			if(validateMessage != null)
				break;
		}
		
		if(validateMessage == null) {
			for(DateValidatable it: dateValidatableList) {
				validateMessage = it.validate(date);
				if(validateMessage != null)
					break;
			}
		}
		
		return validateMessage;
	}
	
}