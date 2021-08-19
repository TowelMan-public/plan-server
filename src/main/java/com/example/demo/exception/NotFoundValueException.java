package com.example.demo.exception;

/**
 * 存在しない値が指定されたときに投げられる例外<br>
 * そのフィールド名と値をセットして投げる
 */
public class NotFoundValueException extends Exception {
	private static final long serialVersionUID = 1L; 
	
	private static final String MESSAGE_TEMPLATE = "{{field}} is not found. The value you specified is '{{value}}'";
	private static final String FIELD = "{{field}}";
	private static final String VALUE = "{{value}}";
	
	/**
	 * コンストラクタ
	 * 
	 * @param fieldName フィールド名
	 * @param value その値
	 */
	public NotFoundValueException(String fieldName, String value){
		super(MESSAGE_TEMPLATE
				.replace(FIELD, fieldName)
				.replace(VALUE, value));
	}
}
