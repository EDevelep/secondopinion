package org.secondopnion.patient.service;

import static org.junit.Assert.assertNotNull;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.junit.Test;
import org.secondopinion.patient.dto.FillPrescriptionRequestDTO;
import org.secondopinion.patient.dto.ForUser;
import org.secondopinion.patient.dto.Medicalprescription;
import org.secondopinion.patient.dto.MedicalprescriptionSearchCriteria;
import org.secondopinion.patient.dto.Medicaltest;
import org.secondopinion.patient.dto.Medicaltestprescription;
import org.secondopinion.patient.dto.Medication;
import org.secondopinion.patient.dto.MedicationUsageNewDTO;
import org.secondopinion.patient.dto.Medicationusage;
import org.secondopinion.patient.dto.MedicationusageDTO;
import org.secondopinion.patient.dto.Prescription;
import org.secondopinion.patient.dto.PrescriptionAllRespnceDTO;
import org.secondopinion.patient.dto.PrescriptionRespnceDTO;
import org.secondopinion.patient.service.IPatientPrescriptionService;
import org.secondopinion.patient.service.helper.DoseTime;
import org.secondopinion.request.Response;
import org.secondopinion.utils.DateUtil;
import org.secondopnion.patient.PatientApplicationTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

public class PatientPrescriptionServiceTest extends PatientApplicationTest {

	@Autowired
	private IPatientPrescriptionService prescriptionService;

	@Test
	public void savePrescrptionFromPatientTest() {
		Medicalprescription medicalPrescription = new Medicalprescription();
		// medicalPrescription.setPatientAppointmentId(-1L);
		// medicalPrescription.setPatientId(50L);
		// medicalPrescription.setDoctorAppointmentId(89L);
		// medicalPrescription.setDoctorName("jatin");
		Medicaltest medicaltest = new Medicaltest();
		medicaltest.setNotes("test");
		medicaltest.setTestName("blood test");
		medicaltest.setTestType("manual");
		Medication medication = new Medication();
		Medication medication1 = new Medication();
		Medication medication2 = new Medication();
		Medication medication3 = new Medication();
		Medication medication4 = new Medication();

		medication.setEnddate(DateUtil.addAndGetDate(TimeZone.getDefault(), 2));
		medication1.setEnddate(DateUtil.addAndGetDate(TimeZone.getDefault(), 2));
		medication2.setEnddate(DateUtil.addAndGetDate(TimeZone.getDefault(), 2));
		medication.setMedicineName("dolo");
		medication1.setMedicineName("BENDEX 400 TABLET");
		medication2.setMedicineName("FUNGICIP 150MG TABLET");
		medication3.setMedicineName("RISOFOS 150 TABLET");
		medication4.setMedicineName("FORCAN 150 TABLET");
		medication.setMedicineUsage("daily 2 LocalTimes");
		medication.setNotes("test");
		medication.setNumberOfDays(2);
		medication.setQuantity(4L);
		medication.setNumberofrefills(2L);
		medication.setPower("650mg");
		medication.setEvening(1);
		medication.setAfternoon(1);
		medication.setType("tables");
		medication.setMorning(1);

		medication1.setNumberOfDays(2);
		medication1.setQuantity(4L);
		medication1.setNumberofrefills(2L);
		medication1.setPower("650mg");
		medication1.setEvening(1);
		medication1.setAfternoon(1);
		medication1.setType("tables");
		medication1.setMorning(1);

		medication2.setNumberOfDays(2);
		medication2.setQuantity(4L);
		medication2.setNumberofrefills(2L);
		medication2.setPower("650mg");
		medication2.setEvening(1);
		medication2.setAfternoon(1);
		medication2.setType("tables");
		medication2.setMorning(1);
		List<Medication> medications = new ArrayList<Medication>();
		medications.add(medication);
		medications.add(medication1);
		medications.add(medication2);
		// medications.add(medication3);
		// medications.add(medication4);
		// medicalPrescription.setMedications(medications);
		// medicalPrescription.setMedicalTests(Arrays.asList(medicaltest));
		// prescriptionService.savePrescrptionFromPatient(medicalPrescription, null);
	}

	@Test
	public void savePrescrptionFromDoctorTest() {
		Medicalprescription medicalPrescription = new Medicalprescription();
		// medicalPrescription.setDoctorAppointmentId(65L);
		// medicalPrescription.setDoctorSpecialization("cardiologist");
		// medicalPrescription.setRefill(Boolean.TRUE);
		medicalPrescription.setNumberofrefills(2);
		medicalPrescription.setLastfilereqtime(new Date());
		Medicaltest medicaltest = new Medicaltest();
		medicaltest.setNotes("test");
		medicaltest.setTestName("blood test");
		medicaltest.setTestType("manual");
		medicaltest.setPatientId(50L);
		medicaltest.setInstructions("before meal");

		Medication medication = new Medication();
		medication.setEnddate(DateUtil.addAndGetDate(TimeZone.getDefault(), 2));
		medication.setMedicineName("dolo");
		medication.setMedicineUsage("daily 2 LocalTimes");
		medication.setNotes("test");
		medication.setNumberOfDays(2);
		medication.setQuantity(4L);
		medication.setNumberofrefills(2L);
		medication.setPower("650mg");
		medication.setType("tables");
		medication.setMorning(2);
		medication.setAfternoon(1);
		medication.setEvening(1);
		medication.setInstructions("before meal");

		// medicalPrescription.setMedications(Arrays.asList(medication));
		// medicalPrescription.setMedicalTests(Arrays.asList(medicaltest));
		// prescriptionService.savePrescrptionFromDoctor(medicalPrescription, null);
		assertNotNull(medicalPrescription.getMedicalPrescriptionId());
	}

	@Test
	public void saveMedicalprescriptionFromDoctor() {
		Prescription prescription = new Prescription();
		prescription.setDoctorAppointmentId(41L);

		// prescription.setNumberofrefills(2L);
		prescription.setAppointmentFor("DOCTOR");
		;
		// prescription.setLastfilereqtime(new Date());
		Medicaltest medicaltest = new Medicaltest();
		medicaltest.setNotes("test");
		medicaltest.setTestName("blood test");
		medicaltest.setTestType("manual");
		medicaltest.setPatientId(50L);
		medicaltest.setInstructions("before meal");
		Medicaltestprescription medicaltestprescription = new Medicaltestprescription();
		medicaltestprescription.setActive('Y');
		// medicaltestprescription.setTestName("blood test");
		// medicaltestprescription.setTestType("manual");
		Medication medication = new Medication();
		medication.setEnddate(DateUtil.addAndGetDate(TimeZone.getDefault(), 2));
		medication.setMedicineName("dolo");
		medication.setMedicineUsage("daily 2 LocalTimes");
		medication.setNotes("test");
		medication.setNumberOfDays(2);
		medication.setQuantity(4L);
		medication.setNumberofrefills(2L);
		medication.setPower("650mg");
		medication.setType("tables");
		medication.setMorning(2);
		medication.setAfternoon(1);
		medication.setEvening(1);
		medication.setInstructions("before meal");

		Medicalprescription medicalPrescription = new Medicalprescription();
		// medicalPrescription.setDoctorAppointmentId(65L);
		// medicalPrescription.setDoctorSpecialization("cardiologist");
		// medicalPrescription.setRefill(Boolean.TRUE);
		medicalPrescription.setNumberofrefills(2);
		// medicalPrescription.setDoctorId(2L);
		// medicalPrescription.setDoctorName("Bala raju");
		medicalPrescription.setActive('Y');
		medicalPrescription.setLastfilereqtime(new Date());
		// prescription.setMedication(Arrays.asList(medication));
		// ..prescription.setMedicalprescription(medicalPrescription);
		// prescription.setMedicaltest(Arrays.asList(medicaltest));
		prescription.setMedicaltestprescription(medicaltestprescription);
		prescriptionService.saveMedicalprescriptionFromDoctor(prescription, null);
		assertNotNull(prescription);
	}

	@Test
	public void addMedicinesToThePrecription() {
		Medication medicine = new Medication();
		medicine.setMedicalPrescriptionId(27L);
		medicine.setPatientId(50L);
		medicine.setMedicineName("Cplox");
		medicine.setMedicineName("astha");
		medicine.setMedicineUsage("2time");
		medicine.setMedicineUsage("3time");
		medicine.setEnddate(new Date());
		medicine.setNumberofrefills(3L);
		medicine.setMorning(1);
		medicine.setEvening(1);
		medicine.setAfternoon(1);
		medicine.setInstructions("before meal");
		medicine.setNumberOfDays(3);
		// prescriptionService.addMedicinesToThePrecription(medicine);
	}

	@Test
	public void updateMedicationusag() {
		Medicationusage medicationusageDTO = new Medicationusage();
		// medicationusageDTO.setDoseConsume('Y');
		// medicationusageDTO.setMedicineId(96L);
		// medicationusageDTO.setPatientId(50L);
		// medicationusageDTO.setDoseTime("AFTERNOON");
		prescriptionService.updateMedicationusage(medicationusageDTO);
	}

	@Test
	public void isMedicationTakenOrNot() {
		Long medcationId = 60L;
		prescriptionService.isMedicationTakenOrNot(50L, DoseTime.AFTERNOON, medcationId);

	}

	@Test
	public void getAllMedications() {
		MedicalprescriptionSearchCriteria medicalprescriptionSearchCriteria = new MedicalprescriptionSearchCriteria();
		medicalprescriptionSearchCriteria.setForUserId(141L);

		medicalprescriptionSearchCriteria.setIsOnlyActive(true);
		medicalprescriptionSearchCriteria.setPageNum(1);
		medicalprescriptionSearchCriteria.setMaxResults(1);
		prescriptionService.getAllMedications(medicalprescriptionSearchCriteria);

		medicalprescriptionSearchCriteria.setIsOnlyActive(true);

		Response<List<Medication>> medications = prescriptionService
				.getAllMedications(medicalprescriptionSearchCriteria);
		assertNotNull(medications);

	}

	@Test
	public void getAllPrescriptionsOfAPatient() {

		PrescriptionAllRespnceDTO prescriptionAllRespnceDTO = prescriptionService.geAlltMedicalPrescription(1L);
		assertNotNull(prescriptionAllRespnceDTO);
	}

	@Test
	public void getByPrescrptionId() {
		Long id = 35L;
		Medicalprescription medicalprescription = prescriptionService.getByMedicalprescriptionId(id);
		assertNotNull(medicalprescription);

	}

	@Test
	public void getMedicalTestPrescriptionDetails() {

		PrescriptionRespnceDTO prescriptionRespnceDTO = prescriptionService.getMedicalTestPrescriptionDetails(2L);
		assertNotNull(prescriptionRespnceDTO);
	}

	@Test
	public void updateMedicationusage() {
		Medicationusage medicationusageDTO = new Medicationusage();

		prescriptionService.updateMedicationusage(medicationusageDTO);
	}

	@Test
	public void fillPrescrptionRequestFromPatientTest() {
		FillPrescriptionRequestDTO dto = new FillPrescriptionRequestDTO();
		dto.setForUserName("ganesh");
		dto.setMedicalprescriptionid(30L);
		dto.setPharmacyaddressId(6L);
		dto.setShippingAddressId(1L);
		dto.setPatientId(30L);
		prescriptionService.fillPrescriptionRequestToPharmacy(dto);
	}

	@Test
	public void getMedicationusageBymedicationId() {

		List<Medicationusage> medicationUsageDTOs = prescriptionService.getMedicationusageBymedicationId(2L);
		assertNotNull(medicationUsageDTOs);

	}

	@Test
	public void getMedicationsForTheDay() {
		MedicationUsageNewDTO medicationusageDTO = new MedicationUsageNewDTO();
		medicationusageDTO.setPatientId(BigInteger.valueOf(12L));
		medicationusageDTO.setMedicinedate((java.sql.Date) new Date());
		Collection<MedicationUsageNewDTO> medicationUsageDTOs = prescriptionService
				.getMedicationsForTheDay(medicationusageDTO);
		assertNotNull(medicationUsageDTOs);

	}

	@Test
	public void uploadTest() {
		Long medicalPrescriptionId = 7L;
		Medicaltest medicaltest = new Medicaltest();
		MultipartFile multipartFile = null;
		prescriptionService.uploadTestDocument(medicalPrescriptionId, medicaltest, multipartFile);
	}
}
