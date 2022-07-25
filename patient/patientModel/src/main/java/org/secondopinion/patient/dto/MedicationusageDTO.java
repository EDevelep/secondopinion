package org.secondopinion.patient.dto;

import java.util.Date;

public class MedicationusageDTO {
	private Long patientId;
	private Long medicineId;
	private Date medicinedate;
	private char doseConsume;
	private String doseTime;
	public MedicationusageDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Long getPatientId() {
		return patientId;
	}
	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}
	public char getDoseConsume() {
		return doseConsume;
	}
	public void setDoseConsume(char doseConsume) {
		this.doseConsume = doseConsume;
	}
	public String getDoseTime() {
		return doseTime;
	}
	public void setDoseTime(String doseTime) {
		this.doseTime = doseTime;
	}
	public Date getMedicinedate() {
		return medicinedate;
	}
	public void setMedicinedate(Date medicinedate) {
		this.medicinedate = medicinedate;
	}
	public Long getMedicineId() {
		return medicineId;
	}
	public void setMedicineId(Long medicineId) {
		this.medicineId = medicineId;
	}
}
