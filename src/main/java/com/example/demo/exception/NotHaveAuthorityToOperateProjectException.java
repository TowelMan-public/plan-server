package com.example.demo.exception;

/**
 * 操作をする権限がないパブリックプロジェクトを操作しようとしたときに投げられる例外
 */
public class NotHaveAuthorityToOperateProjectException extends Exception {
	private static final long serialVersionUID = 1L; 

	/**
	 * コンストラクタ
	 */
	public NotHaveAuthorityToOperateProjectException() {
		super("You do not have permission to operate the project");
	}
}