package com.example.demo.exception;

public class FailureCreateAuthenticationTokenException  extends Exception{
	//warningを回避するための宣言
	private static final long serialVersionUID = 1L; 
	
	public FailureCreateAuthenticationTokenException(){
		super("Failed to generate the authentication token.");
	}
}