package org.secondopinion.patient.dao;

import java.util.List;

import org.secondopinion.dao.IDAO;
import org.secondopinion.patient.dto.Medicalprescription;
import org.secondopinion.patient.dto.MedicationDTO;
import org.secondopinion.patient.dto.PrescriptionSearch;
import org.secondopinion.request.Response;

public interface MedicalprescriptionDAO extends IDAO<Medicalprescription,Long >{

	Medicalprescription findByPrescrptionId(Long referenceId);

	boolean findPrescriptionRequestByDoctorAppointmentId(Long doctorAppointmentId);

	List<MedicationDTO> getMedicationsAllByuserId(Long userId);

	Medicalprescription getprecriptionByDoctorIdAndUserId(Long userId, Long medicalPrescriptionId);

	List<Medicalprescription> findAllMedicalprescriptionByPrescriptionId(Long prescriptionId);
	Medicalprescription findMedicalprescriptionByPrescriptionId(Long prescriptionId);

	List<Medicalprescription> findMedicationBymedicalprescriptionId(List<Long> medicalprescriptionId);

	List<Medicalprescription> findAllMedicalprescriptionByUserId(Long userId);


}