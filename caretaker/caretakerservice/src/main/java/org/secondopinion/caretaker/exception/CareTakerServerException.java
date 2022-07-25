package org.secondopinion.caretaker.exception;

public class CareTakerServerException extends RuntimeException {

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 9040873251432335105L;
	
	public CareTakerServerException(String message, Exception ex) {
		super(message);
	}
	
	public CareTakerServerException(String message) {
		super(message);
	}

}
