package org.secondopinion.patient.dto;

import java.util.Collection;
import java.util.List;

public class PrescriptionRespnceDTO {
	private List<Medicalprescription> medicalprescription;

	public List<Medicalprescription> getMedicalprescription() {
		return medicalprescription;
	}

	public void setMedicalprescription(List<Medicalprescription> medicalprescription) {
		this.medicalprescription = medicalprescription;
	}

	public List<Medication> getMedication() {
		return medication;
	}

	public void setMedication(List<Medication> medication) {
		this.medication = medication;
	}

	public List<Medicaltestprescription> getMedicaltestprescription() {
		return medicaltestprescription;
	}

	public void setMedicaltestprescription(List<Medicaltestprescription> medicaltestprescription) {
		this.medicaltestprescription = medicaltestprescription;
	}

	public List<Medicaltest> getMedicaltest() {
		return medicaltest;
	}

	public void setMedicaltest(List<Medicaltest> medicaltest) {
		this.medicaltest = medicaltest;
	}

	private List<Medication> medication;
	private List<Medicaltestprescription> medicaltestprescription;
	private List<Medicaltest> medicaltest;

	private List<Prescription> prescriptions;

	public List<Prescription> getPrescriptions() {
		return prescriptions;
	}

	public void setPrescriptions(List<Prescription> prescriptions) {
		this.prescriptions = prescriptions;
	}
}
