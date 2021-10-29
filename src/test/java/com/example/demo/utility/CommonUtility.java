package com.example.demo.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class CommonUtility {
	/**
	 * default = "yyyy-M-d"
	 */
	public Date stringToDate(String str) {
		return stringToDate(str, "yyyy-M-d");
	}
	
	public Date stringToDate(String str, String formatString) {
		try {
			var format = new SimpleDateFormat(formatString);
			return format.parse(str);
		}catch(ParseException e) {
			throw new RuntimeException(e.getMessage());
		}
	}
}
