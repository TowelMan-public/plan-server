package com.example.demo.validate.validatable;

public class MinValueIntegerValidatable implements IntegerValidatable {
	private Integer integer;
	
	public MinValueIntegerValidatable(Integer integer) {this.integer = integer;}

	@Override
	public String validate(Integer integer) {
		
		if(this.integer > integer) {
			return "This value is too short.";
		}
				
		return null;
	}
}