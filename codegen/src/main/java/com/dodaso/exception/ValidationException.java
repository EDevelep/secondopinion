package com.dodaso.exception;

@SuppressWarnings("serial")
public class ValidationException extends RuntimeException{

	/**
	 * 
	 */
	public ValidationException() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public ValidationException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 */
	public ValidationException(String arg0) {
		super(arg0);
	}
	/**
	 * @param arg0
	 */
	public ValidationException(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

}
