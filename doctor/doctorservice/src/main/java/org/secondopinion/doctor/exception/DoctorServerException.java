package org.secondopinion.doctor.exception;

public class DoctorServerException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9040873251432335105L;
	
	public DoctorServerException(String message, Exception ex) {
		super(message);
	}
	
	public DoctorServerException(String message) {
		super(message);
	}

}
