package com.example.demo.validate.validator;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.validate.validatable.ObjectValidatable;

public class ObjectValidator implements Validator {
	private Object object;
	private List<ObjectValidatable> objectValidatable = new ArrayList<>();

	public ObjectValidator(Object object) {this.object = object;}
	
	public ObjectValidator addValidatable(ObjectValidatable validatable) {
		objectValidatable.add(validatable);
		return null;
	}

	@Override
	public String runValidate() {
		String validateMessage = null;
		
		for(ObjectValidatable it: objectValidatable) {
			validateMessage = it.validate(object);
			if(validateMessage != null)
				break;
		}
		
		return validateMessage;
	}
	
}