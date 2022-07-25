package org.secondopinion.patient.dto;


import java.math.BigInteger;
import java.sql.Date;
import java.util.Map;



import org.secondopinion.patient.service.helper.DoseTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

public class MedicationUsageNewDTO {
	private BigInteger patientId;
	private BigInteger medicationId;
	private String medicineName;
	@JsonFormat(pattern="yyyy-MM-dd", shape=Shape.STRING)
	private Date medicinedate;
	@JsonFormat(pattern="yyyy-MM-dd", shape=Shape.STRING)
	private Date appointmentDate;
	private Dose morning;
	private Dose afternoon;
	private Dose evening;
	private String doctorSpecialization;
	private String doctorName;

	public MedicationUsageNewDTO(BigInteger medicationId, Map<String, Object> data) {
		this.medicationId = medicationId;
		this.medicineName = (String) data.get("medicineName");
		this.patientId = (BigInteger) data.get("patientId");
		this.doctorName=(String) data.get("doctorName");
		this.doctorSpecialization=(String) data.get("doctorSpecialization");
		this.appointmentDate= (Date) data.get("appointmentDate");
			}

	
	public String getDoctorSpecialization() {
		return doctorSpecialization;
	}


	public void setDoctorSpecialization(String doctorSpecialization) {
		this.doctorSpecialization = doctorSpecialization;
	}


	public String getDoctorName() {
		return doctorName;
	}


	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}


	public MedicationUsageNewDTO() {
	}

	public void update(Map<String, Object> data) {
		DoseTime doseTime = DoseTime.valueOf((String) data.get("doseTime"));

		switch (doseTime) {
		case MORNING:
			morning = new Dose((BigInteger) data.get("medicationusageId"), (char)data.get("dosageConsumed"));
			break;
		case AFTERNOON:
			afternoon = new Dose((BigInteger) data.get("medicationusageId"), (char)data.get("dosageConsumed"));
			break;
		case EVENING:
			evening = new Dose((BigInteger) data.get("medicationusageId"), (char)data.get("dosageConsumed"));
			break;
		default:
			break;
		}
	}

	

	public String getMedicineName() {
		return medicineName;
	}

	public void setMedicineName(String medicineName) {
		this.medicineName = medicineName;
	}

	public Dose getMorning() {
		return morning;
	}

	public void setMorning(Dose morning) {
		this.morning = morning;
	}

	public Dose getAfternoon() {
		return afternoon;
	}

	public void setAfternoon(Dose afternoon) {
		this.afternoon = afternoon;
	}

	public Dose getEvening() {
		return evening;
	}

	public void setEvening(Dose evening) {
		this.evening = evening;
	}
	@JsonFormat(pattern="yyyy-MM-dd", shape=Shape.STRING)
	public Date getMedicinedate() {
		return medicinedate;
	}


	public void setMedicinedate(Date medicinedate) {
		this.medicinedate = medicinedate;
	}


	public BigInteger getPatientId() {
		return patientId;
	}


	public void setPatientId(BigInteger patientId) {
		this.patientId = patientId;
	}


	public BigInteger getMedicationId() {
		return medicationId;
	}


	public Date getAppointmentDate() {
		return appointmentDate;
	}


	public void setAppointmentDate(Date appointmentDate) {
		this.appointmentDate = appointmentDate;
	}


	public void setMedicationId(BigInteger medicationId) {
		this.medicationId = medicationId;
	}
}
