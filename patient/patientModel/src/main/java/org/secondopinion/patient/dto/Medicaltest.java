package org.secondopinion.patient.dto; 


import javax.persistence.Entity; 
import javax.persistence.Table;
import javax.persistence.Transient;

import org.secondopinion.patient.domain.BaseMedicaltest; 





@Entity 
@Table (name="medicaltest")
public class Medicaltest extends BaseMedicaltest{
	
	private byte[] medicalTestFile;

	@Transient
	public byte[] getMedicalTestFile() {
		return medicalTestFile;
	}

	public void setMedicalTestFile(byte[] medicalTestFile) {
		this.medicalTestFile = medicalTestFile;
	}

	
	
}