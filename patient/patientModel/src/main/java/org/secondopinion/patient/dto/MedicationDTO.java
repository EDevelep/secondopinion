package org.secondopinion.patient.dto;

import java.math.BigInteger;
import java.util.Date;

public class MedicationDTO {
 private BigInteger medicalPrescriptionId;
 private BigInteger doctorId;
 private String medicineName;
 private String doctorName;
 private String doctorSpecialization;
	private Date appointmentDate;

	public BigInteger getMedicalPrescriptionId() {
		return medicalPrescriptionId;
	}

	public void setMedicalPrescriptionId(BigInteger medicalPrescriptionId) {
		this.medicalPrescriptionId = medicalPrescriptionId;
	}

	public BigInteger getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(BigInteger doctorId) {
		this.doctorId = doctorId;
	}

	public String getMedicineName() {
		return medicineName;
	}

	public void setMedicineName(String medicineName) {
		this.medicineName = medicineName;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public String getDoctorSpecialization() {
		return doctorSpecialization;
	}

	public void setDoctorSpecialization(String doctorSpecialization) {
		this.doctorSpecialization = doctorSpecialization;
	}

	public Date getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(Date appointmentDate) {
		this.appointmentDate = appointmentDate;
	}
}
