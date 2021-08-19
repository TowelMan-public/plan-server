package com.example.demo.exception;

/**
 * 担当者に抜擢されてない「やること」が指定されたときの例外
 */
public class NotSelectedAsTodoResponsibleException extends Exception {
	private static final long serialVersionUID = 1L; 

	/**
	 * コンストラクタ
	 */
	public NotSelectedAsTodoResponsibleException() {
		super("You are not the person in charge of Todo");
	}
}