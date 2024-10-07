package com.crud.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.crud.dto.ResponseSender;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourseNotFoundException.class)
	public ResponseEntity<?> allExceptionHandler (ResourseNotFoundException exception) {
		
		String message = exception.getMessage();
		
	return new	ResponseEntity<ResponseSender>(new ResponseSender(message,false),HttpStatus.NOT_FOUND);
	}
}
