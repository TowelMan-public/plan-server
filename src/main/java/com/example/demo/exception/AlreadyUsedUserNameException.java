package com.example.demo.exception;

/**
 * 既に使われているユーザー名が指定されたときの例外
 */
public class AlreadyUsedUserNameException extends Exception {
	private static final long serialVersionUID = 1L; 

	/**
	 * コンストラクタ
	 * 
	 * @param userName ユーザー名
	 */
	public AlreadyUsedUserNameException(String userName) {
		super("This userName is already used. The value you specified is '" + userName + "'.");
	}
}