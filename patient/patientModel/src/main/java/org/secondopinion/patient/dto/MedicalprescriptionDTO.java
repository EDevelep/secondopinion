package org.secondopinion.patient.dto;

import java.math.BigInteger;
import java.util.Date;
import java.util.Map;

public class MedicalprescriptionDTO {

	private BigInteger medicalPrescriptionId;

	private Date appointmentDate;

	private String doctorName;

	private BigInteger doctorId;
	private char prescriptioncontainsImage;
	private char prescriptionFill;

	public MedicalprescriptionDTO(BigInteger prescriptionId, Map<String, Object> data) {
        this.medicalPrescriptionId=(BigInteger) data.get("medicalPrescriptionId");
		this.appointmentDate = (Date) data.get("appointmentDate");
		this.doctorName = (String) data.get("doctorName");
		this.doctorId=(BigInteger) data.get("doctorId");
		this.prescriptioncontainsImage=(char) data.get("prescriptioncontainsImage");
		this.prescriptionFill=(char) data.get("prescriptionFill");

	}

	public Date getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(Date appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public BigInteger getMedicalPrescriptionId() {
		return medicalPrescriptionId;
	}

	public void setMedicalPrescriptionId(BigInteger medicalPrescriptionId) {
		this.medicalPrescriptionId = medicalPrescriptionId;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public BigInteger getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(BigInteger doctorId) {
		this.doctorId = doctorId;
	}

	public char getPrescriptioncontainsImage() {
		return prescriptioncontainsImage;
	}

	public void setPrescriptioncontainsImage(char prescriptioncontainsImage) {
		this.prescriptioncontainsImage = prescriptioncontainsImage;
	}

	public char getPrescriptionFill() {
		return prescriptionFill;
	}

	public void setPrescriptionFill(char prescriptionFill) {
		this.prescriptionFill = prescriptionFill;
	}
}
