package org.secondopinion.doctor.dto;

public class PushNotificationRequestDto {
	private Long doctorId;
	private String token;
	public Long getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(Long doctorId) {
		this.doctorId = doctorId;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}

}
