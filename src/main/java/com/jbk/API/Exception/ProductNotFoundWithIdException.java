package com.jbk.API.Exception;


public class ProductNotFoundWithIdException extends RuntimeException{
public ProductNotFoundWithIdException(String msg) {
	super(msg);
}
}
