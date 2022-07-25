package org.secondopinion.patient.dao;

import java.util.List;

import org.secondopinion.dao.IDAO;
import org.secondopinion.patient.dto.MedicalprescriptionSearchCriteria;
import org.secondopinion.patient.dto.Medication;
import org.secondopinion.request.Response;

public interface MedicationDAO extends IDAO<Medication,Long >{

	Response<List<Medication>> getAllMedications(MedicalprescriptionSearchCriteria medicalprescriptionSearchCriteria);

	List<Medication> findMedicationByPrescriptionId(Long medicalPrescriptionId);

	void cancel(Long medicalPrescriptionId, String userId);

	List<Medication> findMedicationByUserIds(List<Long> userIds);

	List<Medication> getMedicalPrescriptionDetailsWithMedication(List<Long> medicalprescriptionId);
}