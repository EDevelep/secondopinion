package org.securityservice.exception;

public class TokenHandlerException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8314469840751432101L;

	private Exception exception;
	private String errorMessage;
	
	public TokenHandlerException(String message, Exception ex) {
		super(message);
		this.errorMessage = message;
		this.exception = ex;
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public Exception getException() {
		return exception;
	}

	public void setException(Exception exception) {
		this.exception = exception;
	}
	
	
	
}
