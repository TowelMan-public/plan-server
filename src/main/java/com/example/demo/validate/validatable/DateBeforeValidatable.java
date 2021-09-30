package com.example.demo.validate.validatable;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateBeforeValidatable implements DateValidatable{

	private Date date;
	
	public DateBeforeValidatable(Date date) {this.date = date;}

	@Override
	public String validate(Date date) {
		
		if(date.compareTo(this.date) > 0) {
			return "This date is too much future. it must be before " + 
				new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(date) + ".";
		}
				
		return null;
	}
		
}