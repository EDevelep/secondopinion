package org.secondopinion.doctor.dto;

public class DoctorPasswordReguest {

	private String emailId;
	private Integer otp;
	private String type;
	private String newPassword;
	private String resetPwdLink;

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public Integer getOtp() {
		return otp;
	}

	public void setOtp(Integer otp) {
		this.otp = otp;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getResetPwdLink() {
		return resetPwdLink;
	}

	public void setResetPwdLink(String resetPwdLink) {
		this.resetPwdLink = resetPwdLink;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
