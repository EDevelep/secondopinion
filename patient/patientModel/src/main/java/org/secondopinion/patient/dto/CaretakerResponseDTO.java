package org.secondopinion.patient.dto;

import java.util.List;

public class CaretakerResponseDTO {

	private List<Vitals> vitals;
	private List<Medicalreports> medicalreports;
	private List<Medicalprescription> medicalprescription;
	private List<Appointment> appointment;
	private List<Medication> medications;

	public List<Vitals> getVitals() {
		return vitals;
	}

	public void setVitals(List<Vitals> vitals) {
		this.vitals = vitals;
	}

	public List<Medicalreports> getMedicalreports() {
		return medicalreports;
	}

	public void setMedicalreports(List<Medicalreports> medicalreports) {
		this.medicalreports = medicalreports;
	}

	public List<Medicalprescription> getMedicalprescription() {
		return medicalprescription;
	}

	public void setMedicalprescription(List<Medicalprescription> medicalprescription) {
		this.medicalprescription = medicalprescription;
	}

	public List<Appointment> getAppointment() {
		return appointment;
	}

	public void setAppointment(List<Appointment> appointment) {
		this.appointment = appointment;
	}

	public List<Medication> getMedications() {
		return medications;
	}

	public void setMedications(List<Medication> medications) {
		this.medications = medications;
	}
}
