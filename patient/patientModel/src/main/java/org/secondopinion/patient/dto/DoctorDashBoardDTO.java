package org.secondopinion.patient.dto;

import java.math.BigInteger;
import java.util.Date;
import java.util.Map;

public class DoctorDashBoardDTO {

	private BigInteger userId;
	private Date latestAppointmentDate;
	private String patientName;
	private BigInteger patientAppointmentId;
	private BigInteger lastestPrescriptionId;
	private BigInteger doctorAppointmentId;

	public DoctorDashBoardDTO(BigInteger userId2, Map<String, Object> n) {
		this.userId = userId2;

		this.latestAppointmentDate = (Date) n.get("latestAppointmentDate");
		this.patientName = (String) n.get("patientName");
		this.patientAppointmentId = (BigInteger) n.get("patientAppointmentId");
		this.lastestPrescriptionId = (BigInteger) n.get("lastestPrescriptionId");
		this.doctorAppointmentId = (BigInteger) n.get("doctorAppointmentId");

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

	public BigInteger getPatientAppointmentId() {
		return patientAppointmentId;
	}

	public void setPatientAppointmentId(BigInteger patientAppointmentId) {
		this.patientAppointmentId = patientAppointmentId;
	}

	public BigInteger getLastestPrescriptionId() {
		return lastestPrescriptionId;
	}

	public void setLastestPrescriptionId(BigInteger lastestPrescriptionId) {
		this.lastestPrescriptionId = lastestPrescriptionId;
	}

	public BigInteger getDoctorAppointmentId() {
		return doctorAppointmentId;
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
