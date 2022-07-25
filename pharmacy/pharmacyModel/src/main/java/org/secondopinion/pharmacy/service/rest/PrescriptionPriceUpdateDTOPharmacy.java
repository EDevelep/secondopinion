package org.secondopinion.pharmacy.service.rest;

import java.util.Date;

public class PrescriptionPriceUpdateDTOPharmacy {
	public PrescriptionPriceUpdateDTOPharmacy(Long patientId, Long patientAppointmentId,
			Long medicalPrescriptionId, String medicine, Integer numberOfDays, Date endDate) {
		super();
		
		this.patientId = patientId;
		this.patientAppointmentId = patientAppointmentId;
		this.medicalPrescriptionId = medicalPrescriptionId;
		this.medicine = medicine;
		this.numberOfDays = numberOfDays;
		this.endDate = endDate;
	}


	private Long patientId;
	private Long patientAppointmentId;
	private Long medicalPrescriptionId;
	private String medicine;
	private Integer numberOfDays;
	private Date endDate;

	public Integer getNumberOfDays() {
		return numberOfDays;
	}

	public void setNumberOfDays(Integer numberOfDays) {
		this.numberOfDays = numberOfDays;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	
	public Long getMedicalPrescriptionId() {
		return medicalPrescriptionId;
	}

	public void setMedicalPrescriptionId(Long medicalPrescriptionId) {
		this.medicalPrescriptionId = medicalPrescriptionId;
	}

	public Long getPatientAppointmentId() {
		return patientAppointmentId;
	}

	public void setPatientAppointmentId(Long patientAppointmentId) {
		this.patientAppointmentId = patientAppointmentId;
	}

	public Long getPatientId() {
		return patientId;
	}

	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}

	public String getMedicine() {
		return medicine;
	}

	public void setMedicine(String medicine) {
		this.medicine = medicine;

	}

}
