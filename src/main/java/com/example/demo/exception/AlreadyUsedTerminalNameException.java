package com.example.demo.exception;

/**
 * 既に使われている機種名が指定されたときの例外
 */
public class AlreadyUsedTerminalNameException extends Exception {
	private static final long serialVersionUID = 1L; 

	/**
	 * コンストラクタ
	 * 
	 * @param terminalName 機種名
	 */
	public AlreadyUsedTerminalNameException(String terminalName) {
		super("This terminalName is already used in other your terminalNames. The value you specified is '" + terminalName + "'.");
	}
}