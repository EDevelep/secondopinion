package org.secondopinion.patient.dao;

import java.util.List;

import org.secondopinion.dao.IDAO;
import org.secondopinion.patient.dto.MedicalprescriptionSearchCriteria;
import org.secondopinion.patient.dto.Medicaltest;
import org.secondopinion.request.Response;

public interface MedicaltestDAO extends IDAO<Medicaltest,Long >{

	Response<List<Medicaltest>> getAllMedicaltests(
			MedicalprescriptionSearchCriteria medicalprescriptionSearchCriteria);

	List<Medicaltest> findMedicaltestByMedicalTestId(Long medicalTestPrescriptionId);

	void cancel(Long medicalPrescriptionId, String userId);

	List<Medicaltest> findAllMedicaltestByMedicalTestPresciptionId(List<Long> medicaltestprescriptionId);


}