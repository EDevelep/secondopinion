package org.secondopinion.patient.dao;

import java.util.Collection;
import java.util.List;

import org.secondopinion.dao.IDAO;
import org.secondopinion.patient.dto.MedicalprescriptionDTO;
import org.secondopinion.patient.dto.MedicalprescriptionTestDTO;
import org.secondopinion.patient.dto.Prescription;
import org.secondopinion.patient.dto.PrescriptionDTO;

public interface PrescriptionDAO extends IDAO<Prescription,Long >{



	Prescription findByPrescrptionId(Long medicalPrescriptionId);

	boolean findPrescriptionRequestByDoctorAppointmentId(Long doctorAppointmentId);

	 Prescription getPrescriptionById(Long prescriptionId, Long userId);

	List<Prescription> getPrecriptionByUserId(Long userId);

	Collection<PrescriptionDTO> getPrecriptionDetailsByUserId(Long userId);

	Collection<MedicalprescriptionDTO> geAlltMedicalPrescription(Long userId);

	Collection<MedicalprescriptionTestDTO> geAlltmedicalprescriptionTest(Long userId);

	Prescription getPrescriptionDetailsBydoctorAppointmentIddoctorAppointmentId(Long doctorAppointmentId, Long userId);

	Prescription getPrescriptionDetailsBydoctorAppointmentIddoctorAppointmentId(Long doctorAppointmentId);


}