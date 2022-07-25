package org.videoroomotpphone.otp;

public enum OtpReasonEnum {

	RESET_PASSWORD_TO_PHONE_MSG("Reset Password"), 
	PHONE_NUMBER_VERIFICATION_MSG("Email Verification"), 
	RESET_PASSWORD_TO_PHONE_VOICE("Reset Password"),
	PHONE_NUMBER_VERIFICATION_VOICE("Phone Number Verification");
	
	public String value;
	
	private OtpReasonEnum(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
}
