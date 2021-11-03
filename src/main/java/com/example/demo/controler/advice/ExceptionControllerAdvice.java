package com.example.demo.controler.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.demo.exception.AlreadyJoinedPublicProjectException;
import com.example.demo.exception.AlreadySelectedAsTodoResponsibleException;
import com.example.demo.exception.AlreadyUsedTerminalNameException;
import com.example.demo.exception.AlreadyUsedUserNameException;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.FailureCreateAuthenticationTokenException;
import com.example.demo.exception.NotFoundValueException;
import com.example.demo.exception.NotHaveAuthorityToOperateProjectException;
import com.example.demo.exception.NotJoinedPublicProjectException;
import com.example.demo.exception.NotSelectedAsTodoResponsibleException;
import com.example.demo.exception.ValidateException;

import lombok.Value;

/**
 * 例外ハンドラー。
 * Controllerクラスまでに発生した例外をキャッチしてエラーレスポンスとしてクライアントに返す。
 */
@RestControllerAdvice
public class ExceptionControllerAdvice {
	
	@ExceptionHandler(ValidateException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorResponse validateException(ValidateException e) {
		return new ErrorResponse("ValidateException",e.getMessage());
	}
	
	@ExceptionHandler(NotFoundValueException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ErrorResponse notFoundException(NotFoundValueException e) {
		return new ErrorResponse("NotFoundValueException",e.getMessage());
	}
	
	@ExceptionHandler(AlreadyJoinedPublicProjectException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorResponse alreadyJoinedPublicProjectException(AlreadyJoinedPublicProjectException e) {
		return new ErrorResponse("AlreadyJoinedPublicProjectException",e.getMessage());
	}
	
	@ExceptionHandler(AlreadySelectedAsTodoResponsibleException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorResponse alreadySelectedAsTodoResponsibleException(AlreadySelectedAsTodoResponsibleException e) {
		return new ErrorResponse("AlreadySelectedAsTodoResponsibleException",e.getMessage());
	}
	
	@ExceptionHandler(AlreadyUsedTerminalNameException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorResponse alreadyUsedTerminalNameException(AlreadyUsedTerminalNameException e) {
		return new ErrorResponse("AlreadyUsedTerminalNameException",e.getMessage());
	}
	
	@ExceptionHandler(AlreadyUsedUserNameException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorResponse alreadyUsedUserNameException(AlreadyUsedUserNameException e) {
		return new ErrorResponse("AlreadyUsedUserNameException",e.getMessage());
	}
	
	@ExceptionHandler(BadRequestException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorResponse badRequestException(BadRequestException e) {
		return new ErrorResponse("BadRequestException",e.getMessage());
	}
	
	@ExceptionHandler(FailureCreateAuthenticationTokenException.class)
	@ResponseStatus(HttpStatus.FORBIDDEN)
	public ErrorResponse failureCreateAuthenticationTokenException(FailureCreateAuthenticationTokenException e) {
		return new ErrorResponse("FailureCreateAuthenticationTokenException",e.getMessage());
	}
	
	@ExceptionHandler(NotHaveAuthorityToOperateProjectException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorResponse notHaveAuthorityToOperateProjectException(NotHaveAuthorityToOperateProjectException e) {
		return new ErrorResponse("NotHaveAuthorityToOperateProjectException",e.getMessage());
	}
	
	@ExceptionHandler(NotJoinedPublicProjectException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorResponse notJoinedPublicProjectException(NotJoinedPublicProjectException e) {
		return new ErrorResponse("NotJoinedPublicProjectException",e.getMessage());
	}
	
	@ExceptionHandler(NotSelectedAsTodoResponsibleException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorResponse notSelectedAsTodoResponsibleException(NotSelectedAsTodoResponsibleException e) {
		return new ErrorResponse("NotSelectedAsTodoResponsibleException",e.getMessage());
	}
	
	@Value
	public class ErrorResponse {
	    String errorCode;
	    String message;
	}
}
