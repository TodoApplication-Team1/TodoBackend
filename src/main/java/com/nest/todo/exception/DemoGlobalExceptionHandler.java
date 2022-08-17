package com.nest.todo.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class DemoGlobalExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(UserAlreadyExistException.class)
	public ResponseEntity<String> UserAlreadyExistExceptionHandler(Throwable throwable){
		return new ResponseEntity<String>(throwable.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(CategoryAlreadyExistException.class)
	public ResponseEntity<String> CategoryAlreadyExistExceptionHandler(Throwable throwable){
		return new ResponseEntity<String>(throwable.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(UserNotExistException.class)
	public ResponseEntity<String> UserNotExistExceptionHandler(Throwable throwable){
		return new ResponseEntity<String>(throwable.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(CategoryNotExistException.class)
	public ResponseEntity<String> CategoryNotExistExceptionHandler(Throwable throwable){
		return new ResponseEntity<String>(throwable.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(CategoryNameNullException.class)
	public ResponseEntity<String> CategoryNameNullExceptionHandler(Throwable throwable){
		return new ResponseEntity<String>(throwable.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(TaskNullException.class)
	public ResponseEntity<String> TaskNullExceptionHandler(Throwable throwable){
		return new ResponseEntity<String>(throwable.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(TimeNullException.class)
	public ResponseEntity<String> TimeNullExceptionHandler(Throwable throwable){
		return new ResponseEntity<String>(throwable.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(TimeNotValidException.class)
	public ResponseEntity<String> TimeNotValidExceptionHandler(Throwable throwable){
		return new ResponseEntity<String>(throwable.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(DateNullException.class)
	public ResponseEntity<String> DateNullExceptionHandler(Throwable throwable){
		return new ResponseEntity<String>(throwable.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(DateNotValidException.class)
	public ResponseEntity<String> DateNotValidExceptionHandler(Throwable throwable){
		return new ResponseEntity<String>(throwable.getMessage(), HttpStatus.NOT_FOUND);
	}
}
