package com.ksy.syserver.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestController
@ControllerAdvice //restcontroller 에서 발생한에러를 한 곳에서 관리하게 도아줌 , 즉 스프링에서 발생한 에러를 전역적으로 처리하기 위해
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler{

	@ExceptionHandler
	public final ResponseEntity<Object> handleProjectIdException(ProjectIdException ex, WebRequest request){
		ProjectIdExceptionResponse exceptionResponse = new ProjectIdExceptionResponse(ex.getMessage());
		return new ResponseEntity(exceptionResponse,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler
	public final ResponseEntity<Object> handleProjectNotFoundException(ProjectNotFoundException ex, WebRequest request){
		ProjectNotFoundExceptionResponse exceptionResponse = new ProjectNotFoundExceptionResponse(ex.getMessage());
		return new ResponseEntity(exceptionResponse,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler
	public final ResponseEntity<Object> handleUsernameExist(UsernameExistException ex, WebRequest request){
		UsernameCheckResponse exceptionResponse = new UsernameCheckResponse(ex.getMessage());
		return new ResponseEntity(exceptionResponse,HttpStatus.BAD_REQUEST);
	}
		
}
