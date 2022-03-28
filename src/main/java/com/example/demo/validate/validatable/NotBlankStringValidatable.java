package com.example.demo.validate.validatable;

public class NotBlankStringValidatable implements StringValidatable{

	@Override
	public String validate(String str) {
		if(str == null || str.isBlank())
			return "This string must be blabk.";
		else
			return null;
	}
}
