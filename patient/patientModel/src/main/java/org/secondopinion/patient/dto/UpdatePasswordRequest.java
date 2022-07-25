package org.secondopinion.patient.dto;

public class UpdatePasswordRequest {

	private String emailId;
	private String resetPasswordLink;
	private String newPassword;
	private Integer otp;
    private String userName;
	public String getEmailId() {
		return emailId;
	}

	

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getUserName() {
		return userName;
	}



	public void setUserName(String userName) {
		this.userName = userName;
	}



	public String getResetPasswordLink() {
		return resetPasswordLink;
	}

	public void setResetPasswordLink(String resetPasswordLink) {
		this.resetPasswordLink = resetPasswordLink;
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
