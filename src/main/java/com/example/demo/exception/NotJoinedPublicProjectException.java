package com.example.demo.exception;

/**
 * 加入していないか、勧誘されてないパブリックプロジェクトが指定されたときの例外
 */
public class NotJoinedPublicProjectException extends Exception {
	private static final long serialVersionUID = 1L; 

	/**
	 * コンストラクタ
	 */
	public NotJoinedPublicProjectException() {
		super("You are not a member of the project or have been solicited");
	}
}