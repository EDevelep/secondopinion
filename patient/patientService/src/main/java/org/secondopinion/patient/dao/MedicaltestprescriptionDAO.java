package org.secondopinion.patient.dao;

import java.util.List;

import org.secondopinion.dao.IDAO;
import org.secondopinion.patient.dto.Medicaltestprescription;

;

public interface MedicaltestprescriptionDAO extends IDAO<Medicaltestprescription,Long >{

	List<Medicaltestprescription> findAllMedicaltestprescriptionyPrescriptionId(Long prescriptionId);

	List<Medicaltestprescription> findAllMedicaltestprescriptionByMedicalTestPresciptionId(
			Long medicalTestPresciptionId);

	Medicaltestprescription findMedicaltestprescriptionyPrescriptionId(Long prescriptionId);
}