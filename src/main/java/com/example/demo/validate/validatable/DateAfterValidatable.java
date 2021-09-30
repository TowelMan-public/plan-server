package com.example.demo.validate.validatable;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateAfterValidatable implements DateValidatable{

	private Date date;
	
	public DateAfterValidatable(Date date) {this.date = date;}

	@Override
	public String validate(Date date) {
		
		if(date.compareTo(this.date) < 0) {
			return "This date is too past. it must be after " + 
				new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(date) + ".";
		}
				
		return null;
	}
		
}