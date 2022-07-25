package org.secondopinion.patient.service;

import org.secondopinion.patient.dto.MedicalHistoryDto;

public interface IMedicalHistory {

	
	void saveMedicalHistory(MedicalHistoryDto medicalHistoryDto);
	MedicalHistoryDto getAllMedicalhistoryDetail(Long userid);
	void  deleteFamilyHistory(Long familyHistoryId);
	void  deleteSurgerydetails(Long surgerydetailsId);
}
