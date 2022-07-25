package org.securityservice.exception;

public class CustomResponse<T> {

	private String errorCode;
	private String errorMsg;
	private String timestamp;
	private int statusCode;
	private T value;
	
	
	public CustomResponse(T value, String errorCode, String errorMsg, int statusCode) {
		this.value = value;
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
		this.statusCode = statusCode;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
		
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getTimestamp() {
		return timestamp;
	}

	/**
	 * @return the value
	 */
	public T getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(T value) {
		this.value = value;
	}
	

}
