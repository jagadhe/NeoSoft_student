package com.jaagdeesh.student.exception;

public class UserNotFoundException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private String message;
	
	public UserNotFoundException() {
	}

	public UserNotFoundException(String message) {
		this.message = message;
	}

	public UserNotFoundException(Throwable tx) {
		this.message = tx.getMessage();
	}

}
