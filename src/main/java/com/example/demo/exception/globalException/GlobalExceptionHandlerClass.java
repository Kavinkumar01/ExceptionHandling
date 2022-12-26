package com.example.demo.exception.globalException;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandlerClass extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(value = NoSuchElementException.class)
	public ResponseEntity<String> NoSuchEntityExceptionHandler(NoSuchElementException e){
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
	}
}
