package com.jbk.API.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(ProductAlreadyExistException.class)
	public ResponseEntity<String> productAlreadyExistException(ProductAlreadyExistException ex){
		String msg= ex.getMessage();
		return new ResponseEntity<String> (msg, HttpStatus.OK);
		
	}
	@ExceptionHandler(ProductNotFoundWithIdException.class)
	public ResponseEntity<String> productNotFoundWithIdException(ProductNotFoundWithIdException iex)
	{
		String msg = iex.getMessage();
		return new ResponseEntity<String> (msg,HttpStatus.OK);
		
	}
	@ExceptionHandler(ListIsEmptyException.class)
	public ResponseEntity<String> listIsEmptyException(ListIsEmptyException lx){
		String msg = lx.getMessage();
		return new ResponseEntity<String> (msg,HttpStatus.OK);
	}
}
