package com.dodaso.exception;

@SuppressWarnings("serial")
public class CodeGenException extends RuntimeException{

	/**
	 * 
	 */
	public CodeGenException() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 */
	public CodeGenException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 */
	public CodeGenException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public CodeGenException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
