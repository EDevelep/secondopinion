package org.secondopinion.patient.dto;

import java.math.BigInteger;
import java.util.Date;
import java.util.Map;

public class PrescriptionDTO {

	private BigInteger prescriptionId;
	private BigInteger patientId;
	private char containsMedicalPrescription;
	private char containsTestPrescription;
	private BigInteger medicalPrescriptionId;
	private BigInteger medicaltestprescriptionId;
	private Date appointmentDate;
	private Date lastUpdatedTs;
	private String doctorName;
	private Integer numberOfRefills;
	private BigInteger doctorId;
	private char prescriptioncontainsImage;
	private char prescriptionFill;

	public PrescriptionDTO(BigInteger prescriptionId, Map<String, Object> data) {
		this.prescriptionId = prescriptionId;
		this.patientId = (BigInteger) data.get("patientId");
		this.containsMedicalPrescription = (char) data.get("containsMedicalPrescription");
		this.containsTestPrescription = (char) data.get("containsTestPrescription");
		this.medicalPrescriptionId = (BigInteger) data.get("medicalPrescriptionId");
		this.medicaltestprescriptionId = (BigInteger) data.get("medicaltestprescriptionId");
		this.appointmentDate = (Date) data.get("appointmentDate");
		this.lastUpdatedTs = (Date) data.get("lastUpdatedTs");
		this.doctorName = (String) data.get("doctorName");
		this.numberOfRefills = (Integer) data.get("numberOfRefills");
		this.doctorId = (BigInteger) data.get("doctorId");
		this.prescriptioncontainsImage = (char) data.get("prescriptioncontainsImage");
		this.prescriptionFill = (char) data.get("prescriptionFill");
	}

	public BigInteger getPatientId() {
		return patientId;
	}

	public void setPatientId(BigInteger patientId) {
		this.patientId = patientId;
	}

	public BigInteger getPrescriptionId() {
		return prescriptionId;
	}

	public void setPrescriptionId(BigInteger prescriptionId) {
		this.prescriptionId = prescriptionId;
	}

	public char getContainsMedicalPrescription() {
		return containsMedicalPrescription;
	}

	public void setContainsMedicalPrescription(char containsMedicalPrescription) {
		this.containsMedicalPrescription = containsMedicalPrescription;
	}

	public char getContainsTestPrescription() {
		return containsTestPrescription;
	}

	public void setContainsTestPrescription(char containsTestPrescription) {
		this.containsTestPrescription = containsTestPrescription;
	}

	public BigInteger getMedicaltestprescriptionId() {
		return medicaltestprescriptionId;
	}

	public void setMedicaltestprescriptionId(BigInteger medicaltestprescriptionId) {
		this.medicaltestprescriptionId = medicaltestprescriptionId;
	}

	public Date getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(Date appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public Date getLastUpdatedTs() {
		return lastUpdatedTs;
	}

	public void setLastUpdatedTs(Date lastUpdatedTs) {
		this.lastUpdatedTs = lastUpdatedTs;
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

	public Integer getNumberOfRefills() {
		return numberOfRefills;
	}

	public void setNumberOfRefills(Integer numberOfRefills) {
		this.numberOfRefills = numberOfRefills;
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
