package com.jbk.API.Exception;

public class ProductAlreadyExistException extends RuntimeException {
public ProductAlreadyExistException(String msg) {
	super(msg);
}

}
