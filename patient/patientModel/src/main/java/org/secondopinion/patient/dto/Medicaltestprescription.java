package org.secondopinion.patient.dto; 


import java.util.List;

import javax.persistence.Entity; 
import javax.persistence.SequenceGenerator; 
import javax.persistence.Table;
import javax.persistence.Transient;

import org.secondopinion.patient.domain.BaseMedicaltestprescription; 





@SuppressWarnings({ "serial"})
@Entity 
@Table (name="medicaltestprescription")
public class Medicaltestprescription extends BaseMedicaltestprescription{
	private List<Medicaltest> medicalTests;

	@Transient
	public List<Medicaltest> getMedicalTests() {
		return medicalTests;
	}

	public void setMedicalTests(List<Medicaltest> medicalTests) {
		this.medicalTests = medicalTests;
	}
	

}