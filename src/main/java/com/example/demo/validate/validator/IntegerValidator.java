package com.example.demo.validate.validator;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.validate.validatable.IntegerValidatable;
import com.example.demo.validate.validatable.ObjectValidatable;
import com.example.demo.validate.validatable.StringValidatable;

public class IntegerValidator implements Validator {
	private Integer integer;
	private List<ObjectValidatable> objectValidatable = new ArrayList<>();
	private List<IntegerValidatable> integerValidatableList = new ArrayList<>();

	public IntegerValidator(Integer integer) {this.integer = integer;}
	
	public IntegerValidator addValidatable(ObjectValidatable validatable) {
		objectValidatable.add(validatable);
		return this;
	}
	
	public IntegerValidator addValidatable(IntegerValidatable validatable) {
		integerValidatableList.add(validatable);
		return this;
	}

	@Override
	public String runValidate() {
		String validateMessage = null;
		
		for(ObjectValidatable it: objectValidatable) {
			validateMessage = it.validate(integer);
			if(validateMessage != null)
				break;
		}
		
		if(validateMessage == null) {
			for(IntegerValidatable it: integerValidatableList) {
				validateMessage = it.validate(integer);
				if(validateMessage != null)
					break;
			}
		}
		
		return validateMessage;
	}
}
