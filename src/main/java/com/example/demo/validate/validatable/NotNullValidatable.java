package com.example.demo.validate.validatable;

public class NotNullValidatable implements ObjectValidatable {

	@Override
	public String validate(Object object) {
		if(object == null)
			return "Value is null, But value can not be null.";
		else
			return null;
	}

}