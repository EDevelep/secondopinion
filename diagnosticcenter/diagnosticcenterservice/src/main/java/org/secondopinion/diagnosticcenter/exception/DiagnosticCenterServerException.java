package org.secondopinion.diagnosticcenter.exception;

public class DiagnosticCenterServerException extends RuntimeException {

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 9040873251432335105L;
	
	public DiagnosticCenterServerException(String message, Exception ex) {
		super(message);
	}
	
	public DiagnosticCenterServerException(String message) {
		super(message);
	}

}
