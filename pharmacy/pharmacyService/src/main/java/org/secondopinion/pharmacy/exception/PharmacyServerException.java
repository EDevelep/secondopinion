package org.secondopinion.pharmacy.exception;

public class PharmacyServerException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1545984663977767672L;

	public PharmacyServerException(String message, Exception ex) {
		super(message);
	}

	public PharmacyServerException(String message) {
		super(message);
	}

}
