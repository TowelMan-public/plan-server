package com.example.demo.validate.validatable;

public class MaxValueIntegerValidatable implements IntegerValidatable {
	private Integer integer;
	
	public MaxValueIntegerValidatable(Integer integer) {this.integer = integer;}

	@Override
	public String validate(Integer integer) {
		
		if(this.integer < integer) {
			return "This value is too long.";
		}
				
		return null;
	}
}
