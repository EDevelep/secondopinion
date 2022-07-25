package org.secondopinion.superadmin.exception;

public class SuperAdminServerException extends RuntimeException {
	/*
	 * 
	 */
	private static final long serialVersionUID = 9040873251432335105L;

	public SuperAdminServerException(String message, Exception ex) {
		super(message);
	}

	public SuperAdminServerException(String message) {
		super(message);
	}
	public SuperAdminServerException(Exception ex) {
		super(ex.getMessage());
	}
}
