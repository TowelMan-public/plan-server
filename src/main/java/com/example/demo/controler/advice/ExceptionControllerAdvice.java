package com.example.demo.controler.advice;

import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.Value;

/**
 * 例外ハンドラー。
 * Controllerクラスまでに発生した例外をキャッチしてエラーレスポンスとしてクライアントに返す。
 */
@RestControllerAdvice
public class ExceptionControllerAdvice {
	
	/*
	 * 例
	 */
	/*
	@ExceptionHandler(NotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ErrorResponse notFoundException(NotFoundException e) {
		return new ErrorResponse("NotFoundException",e.getMessage());
	}
	*/
	
	@Value
	public class ErrorResponse {
	    String errorCode;
	    String message;
	}
}
