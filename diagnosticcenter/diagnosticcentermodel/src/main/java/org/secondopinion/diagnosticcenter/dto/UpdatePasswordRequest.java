package org.secondopinion.diagnosticcenter.dto;

public class UpdatePasswordRequest {

	private String emailId;
	private String verificationid;
	private String newPassword;
	private Integer otp;
	
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getVerificationid() {
		return verificationid;
	}
	public void setVerificationid(String verificationid) {
		this.verificationid = verificationid;
	}
	
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public Integer getOtp() {
		return otp;
	}
	public void setOtp(Integer otp) {
		this.otp = otp;
	}

	
	
}
