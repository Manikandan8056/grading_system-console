package com.revature.gms.exception;

public class ValidationException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ValidationException(String message, Throwable t) {
		
	super(message, t);
	

}

	public ValidationException(String message) {

		super(message);
	}

}
