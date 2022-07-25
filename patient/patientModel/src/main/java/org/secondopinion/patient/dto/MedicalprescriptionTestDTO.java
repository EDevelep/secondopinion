package org.secondopinion.patient.dto;

import java.math.BigInteger;
import java.util.Date;
import java.util.Map;

public class MedicalprescriptionTestDTO {

	
	private BigInteger medicalPrescriptionId;


	private BigInteger medicaltestprescriptionId;

	private Date appointmentDate;

	private String doctorName;

	private BigInteger doctorId;
	private char prescriptioncontainsImage;

	public MedicalprescriptionTestDTO(BigInteger prescriptionId, Map<String, Object> data) {
		this.medicalPrescriptionId = (BigInteger) data.get("medicalPrescriptionId");
		this.medicaltestprescriptionId = (BigInteger) data.get("medicaltestprescriptionId");
		this.appointmentDate = (Date) data.get("appointmentDate");
		this.doctorName = (String) data.get("doctorName");
		this.doctorId = (BigInteger) data.get("doctorId");
		this.prescriptioncontainsImage = (char) data.get("prescriptioncontainsImage");

	}

	public BigInteger getMedicalPrescriptionId() {
		return medicalPrescriptionId;
	}

	public void setMedicalPrescriptionId(BigInteger medicalPrescriptionId) {
		this.medicalPrescriptionId = medicalPrescriptionId;
	}
	public Date getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(Date appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public BigInteger getMedicaltestprescriptionId() {
		return medicaltestprescriptionId;
	}

	public void setMedicaltestprescriptionId(BigInteger medicaltestprescriptionId) {
		this.medicaltestprescriptionId = medicaltestprescriptionId;
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
}
