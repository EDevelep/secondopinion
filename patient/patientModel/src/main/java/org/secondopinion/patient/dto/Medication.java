package org.secondopinion.patient.dto;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.secondopinion.patient.domain.BaseMedication;

@Entity
@Table(name = "medication")
public class Medication extends BaseMedication {
	

	private List<Medicationusage> medicationusage;
	private List<Medicalprescription> medicalprescriptions;
	@Transient
	public List<Medicalprescription> getMedicalprescriptions() {
		return medicalprescriptions;
	}

	public void setMedicalprescriptions(List<Medicalprescription> medicalprescriptions) {
		this.medicalprescriptions = medicalprescriptions;
	}
	@Transient
	public List<Medicationusage> getMedicationusage() {
		return medicationusage;
	}

	public void setMedicationusage(List<Medicationusage> medicationusage) {
		this.medicationusage = medicationusage;
	}
}