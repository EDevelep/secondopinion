package org.secondopinion.patient.dto;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.secondopinion.patient.domain.BaseMedicalprescription;

@Entity
@Table(name = "medicalprescription")
public class Medicalprescription extends BaseMedicalprescription {

	
	
private String doctorName;
private Date appointmentDate;
@Transient
	public String getDoctorName() {
	return doctorName;
}

public void setDoctorName(String doctorName) {
	this.doctorName = doctorName;
}
@Transient
public Date getAppointmentDate() {
	return appointmentDate;
}

public void setAppointmentDate(Date appointmentDate) {
	this.appointmentDate = appointmentDate;
}

	private List<Medication> medications;

	@Transient
	public List<Medication> getMedications() {
		return medications;
	}

	public void setMedications(List<Medication> medications) {
		this.medications = medications;
	}

	
}