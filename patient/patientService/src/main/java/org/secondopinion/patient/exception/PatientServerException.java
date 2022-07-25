package org.secondopinion.patient.exception;

public class PatientServerException extends RuntimeException {
	/*
	 * 
	 */
	private static final long serialVersionUID = 9040873251432335105L;

	public PatientServerException(String message, Exception ex) {
		super(message);
	}

	public PatientServerException(String message) {
		super(message);
	}
	public PatientServerException(Exception ex) {
		super(ex.getMessage());
	}
}
