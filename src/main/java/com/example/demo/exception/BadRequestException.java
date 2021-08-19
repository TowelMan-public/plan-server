package com.example.demo.exception;

/**
 * 不正なリクエストが来たときの例外
 */
public class BadRequestException extends Exception {
	private static final long serialVersionUID = 1L; 

	/**
	 * コンストラクタ
	 * 
	 * @param message 例外メッセージ
	 */
	public BadRequestException(String message) {
		super(message);
	}
}
