package com.example.demo.validate.validatable;

public class EqualValidatable implements ObjectValidatable{

	private Object object;
	
	public EqualValidatable(Object object) {this.object = object;}
	
	@Override
	public String validate(Object object) {
		if(object.equals(this.object))
			return null;
		else
			return "Value is not match, But value must be match.";
	}

}
