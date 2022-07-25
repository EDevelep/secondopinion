package org.secondopinion.patient.dto; 


import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.secondopinion.patient.domain.BaseMedicalreports; 



@Entity 
@Table (name="medicalreports")
public class Medicalreports extends BaseMedicalreports{
	
	private byte[] medicalReportFile;

	@Transient
	public byte[] getMedicalReportFile() {
		return medicalReportFile;
	}

	public void setMedicalReportFile(byte[] medicalReportFile) {
		this.medicalReportFile = medicalReportFile;
	}
}