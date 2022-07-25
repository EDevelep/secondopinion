package org.secondopinion.domain.validation;

public class ValidationMessage {

	private String message;
	
	public ValidationMessage(String _message) {
		this.message = _message;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
}
