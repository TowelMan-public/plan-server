package com.example.demo.exception;

/**
 * 既にプロジェクトに加入してるか、勧誘されてるユーザーが指定されたときの例外
 */
public class AlreadyJoinedPublicProjectException extends Exception {
	private static final long serialVersionUID = 1L; 

	/**
	 * コンストラクタ
	 */
	public AlreadyJoinedPublicProjectException() {
		super("The user has already joined the publicProject or has been recruited");
	}
}
