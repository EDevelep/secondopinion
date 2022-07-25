package org.secondopinion.doctor.dto;

import java.math.BigInteger;
import java.util.Date;
import java.util.Map;

public class DoctorDashBoardDTO {
	private BigInteger userId;
	private Date latestAppointmentDate;
	private String patientName;
	private BigInteger doctorAppointmentId;
	private String ailmentdata;

	public DoctorDashBoardDTO(BigInteger userId2, Map<String, Object> n) {
		this.userId = userId2;

		this.latestAppointmentDate = (Date) n.get("latestAppointmentDate");
		this.patientName = (String) n.get("patientName");
		this.doctorAppointmentId = (BigInteger) n.get("doctorAppointmentId");
		this.ailmentdata = (String) n.get("ailmentdata");

	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public BigInteger getUserId() {
		return userId;
	}

	public void setUserId(BigInteger userId) {
		this.userId = userId;
	}

	public BigInteger getDoctorAppointmentId() {
		return doctorAppointmentId;
	}

	public String getAilmentdata() {
		return ailmentdata;
	}

	public void setAilmentdata(String ailmentdata) {
		this.ailmentdata = ailmentdata;
	}

	public void setDoctorAppointmentId(BigInteger doctorAppointmentId) {
		this.doctorAppointmentId = doctorAppointmentId;
	}

	public Date getLatestAppointmentDate() {
		return latestAppointmentDate;
	}

	public void setLatestAppointmentDate(Date latestAppointmentDate) {
		this.latestAppointmentDate = latestAppointmentDate;
	}

}
