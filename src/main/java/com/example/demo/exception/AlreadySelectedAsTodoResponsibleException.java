package com.example.demo.exception;

/**
 * 既に「やること」の担当者に抜擢されているユーザーが指定されたときの例外
 */
public class AlreadySelectedAsTodoResponsibleException extends Exception {
	private static final long serialVersionUID = 1L; 

	/**
	 * コンストラクタ
	 */
	public AlreadySelectedAsTodoResponsibleException() {
		super("The user has already been selected as a Todo representative");
	}
}