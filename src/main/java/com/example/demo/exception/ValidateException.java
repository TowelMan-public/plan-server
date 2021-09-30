package com.example.demo.exception;

public class ValidateException extends Exception {
	private static final long serialVersionUID = 1L; 

	/**
	 * コンストラクタ
	 * 
	 * @param message バリデーションチェックのメッセージs
	 */
	public ValidateException(String message) {
		super(message);
	}
}
