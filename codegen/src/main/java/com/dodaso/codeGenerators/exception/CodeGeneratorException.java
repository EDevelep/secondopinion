package com.dodaso.codeGenerators.exception;

public class CodeGeneratorException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3972058097272294449L;

	public CodeGeneratorException() {
		super();
	}

	public CodeGeneratorException(String message, Throwable cause) {
		super(message, cause);
	}

	public CodeGeneratorException(String message) {
		super(message);
	}

	public CodeGeneratorException(Throwable cause) {
		super(cause);
	}

}
