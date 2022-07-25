package org.secondopinion.patient.service;

import java.util.Collection;
import java.util.List;

import org.secondopinion.patient.dto.FillPrescriptionRequestDTO;
import org.secondopinion.patient.dto.Medicalprescription;
import org.secondopinion.patient.dto.MedicalprescriptionSearchCriteria;
import org.secondopinion.patient.dto.Medicaltest;
import org.secondopinion.patient.dto.Medication;
import org.secondopinion.patient.dto.MedicationDTO;
import org.secondopinion.patient.dto.MedicationUsageNewDTO;
import org.secondopinion.patient.dto.Medicationusage;
import org.secondopinion.patient.dto.MedicationusageDTO;
import org.secondopinion.patient.dto.PatientPriceUpdateDTO;
import org.secondopinion.patient.dto.Prescription;
import org.secondopinion.patient.dto.PrescriptionAllRespnceDTO;
import org.secondopinion.patient.dto.PrescriptionDTO;
import org.secondopinion.patient.dto.PrescriptionPriceUpdateDTO;
import org.secondopinion.patient.dto.PrescriptionRespnceDTO;
import org.secondopinion.patient.service.helper.DoseTime;
import org.secondopinion.request.Response;
import org.springframework.web.multipart.MultipartFile;

public interface IPatientPrescriptionService {

	void saveMedicalprescriptionFromDoctor(Prescription prescription, MultipartFile multipartFile);

	PrescriptionRespnceDTO getMedicalPrescriptionDetails(Long medicalPresciptionId);

	PrescriptionRespnceDTO getMedicalTestPrescriptionDetails(Long medicalTestPresciptionId);

	Medicalprescription getByMedicalprescriptionId(Long id);

	void fillPrescriptionRequestToPharmacy(FillPrescriptionRequestDTO fillPrescriptionRequestDTO);

	void addMedicinesToThePrecriptionForImage(PatientPriceUpdateDTO medicine);

	Response<List<Medication>> getAllMedications(MedicalprescriptionSearchCriteria medicalprescriptionSearchCriteria);

	Medicaltest uploadTestDocument(Long medicalPrescriptionId, Medicaltest medicaltest, MultipartFile multipartFile);

	Response<List<Medicaltest>> getAllMedicaltests(MedicalprescriptionSearchCriteria medicalprescriptionSearchCriteria);

	boolean isMedicationTakenOrNot(Long userId, DoseTime doseTime, Long medicationId);

	Collection<MedicationUsageNewDTO> getMedicationsForTheDay(MedicationUsageNewDTO medicationusageDTO);

	void updateMedicationusage(Medicationusage medicationusageDTO);

	// List<MedicationDTO> getMedicationsAllByuserId(Long userId);

	List<Medicationusage> getMedicationusageBymedicationId(Long medicationId);

	void addMedicinesToThePrecription(Medication medication);

	Medicaltest uploadmedicalTestForDiagnosticcenter(Long medicalPrescriptionId, Medicaltest medicaltest,
			MultipartFile multipartFile);

	Prescription getprecriptionByPrescriptionIdAndUserId(Long userId, Long prescriptionId);

	// PrescriptionRespnceDTO geAlltMedicalPrescription();
	Collection<PrescriptionDTO> getPrecriptionByUserId(Long userId);

	PrescriptionAllRespnceDTO geAlltMedicalPrescription(Long userId);

	Prescription getPrescriptionDetailsById(Long presciptionId);

	Prescription getPrescriptionDetailsBydoctorAppointmentId(Long doctorAppointmentId, Long userId);

	Prescription getPrescriptionBydoctorAppointmentId(Long doctorAppointmentId);

	void saveMedicalprescriptionFromPatient(Prescription prescription, MultipartFile multipartFile);

}