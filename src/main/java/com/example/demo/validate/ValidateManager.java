package com.example.demo.validate;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.exception.ValidateException;
import com.example.demo.validate.validator.Validator;

public class ValidateManager {
	private List<OrValidator> orValidatorList = new ArrayList<>();
	
	public OrValidator or() {
		return new OrValidator(this);
	}
	
	public void add(OrValidator orValidator) {
		orValidatorList.add(orValidator);
	}
	
	public void run() throws ValidateException {
		String validateMessage = "";
		
		for(OrValidator it: orValidatorList) {
			var message = it.doValidateList();
			if(message == null)
				return;
			else
				validateMessage = message;
		}
		
		throw new ValidateException(validateMessage);
	}
	
	public static class OrValidator{
		private List<Validator> validatorList = new ArrayList<>();
		private ValidateManager manager;
		
		public OrValidator(ValidateManager manager) {
			this.manager = manager;
		}
		
		public OrValidator add(Validator validator) {
			validatorList.add(validator);
			return this;
		}
		
		public OrValidator or() {
			manager.add(this);
			return new OrValidator(manager);
		}
		
		public void run() throws ValidateException {
			manager.run();
		}
		
		public String doValidateList() {
			for(Validator validator: validatorList) {
				var validateMessage = validator.runValidate();
				if(validateMessage != null) {
					return validateMessage;
				}
			}
			
			return null;
		}
	}
}