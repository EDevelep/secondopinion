package org.secondopinion.doctor.dto;

public class OtpVerificationDTO {
  private String type;
  private Integer otp;
  private String emailId;
public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
}
public Integer getOtp() {
	return otp;
}
public void setOtp(Integer otp) {
	this.otp = otp;
}
public String getEmailId() {
	return emailId;
}
public void setEmailId(String emailId) {
	this.emailId = emailId;
}
}
