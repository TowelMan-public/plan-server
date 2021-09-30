package com.example.demo.validate.validatable;

public class LengthStringValidatable implements StringValidatable{

	private Integer max = null;
	private Integer min = null;
	
	LengthStringValidatable(int max){this.max = max;}
	
	LengthStringValidatable(int min, int max){
		this.max = max;
		this.min = min;
	}
	
	@Override
	public String validate(String str) {
		if(max != null && str.length() > max) {
			return "This string is too long. it must be shorter than " + max.toString() + ".";
		}
		if(min != null && str.length() < min) {
			return "This string is too short. it must be longer than " + min.toString() + ".";
		}
		
		return null;
	}

}