package com.example.demo.validate.validator;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.validate.validatable.ObjectValidatable;
import com.example.demo.validate.validatable.StringValidatable;

public class StringValidator implements Validator {
	private String str;
	private List<ObjectValidatable> objectValidatable = new ArrayList<>();
	private List<StringValidatable> stringValidatableList = new ArrayList<>();

	public StringValidator(String str) {this.str = str;}
	
	public StringValidator addValidatable(ObjectValidatable validatable) {
		objectValidatable.add(validatable);
		return this;
	}
	
	public StringValidator addValidatable(StringValidatable validatable) {
		stringValidatableList.add(validatable);
		return this;
	}

	@Override
	public String runValidate() {
		String validateMessage = null;
		
		for(ObjectValidatable it: objectValidatable) {
			validateMessage = it.validate(str);
			if(validateMessage != null)
				break;
		}
		
		if(validateMessage == null) {
			for(StringValidatable it: stringValidatableList) {
				validateMessage = it.validate(str);
				if(validateMessage != null)
					break;
			}
		}
		
		return validateMessage;
	}
	
}
