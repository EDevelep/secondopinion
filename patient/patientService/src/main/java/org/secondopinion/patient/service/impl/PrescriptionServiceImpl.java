
package org.secondopinion.patient.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.secondopinion.configurations.AppProperties;
import org.secondopinion.configurations.UtilComponent;
import org.secondopinion.fileservice.FileService;
import org.secondopinion.patient.dao.AddressDAO;
import org.secondopinion.patient.dao.AppointmentDAO;
import org.secondopinion.patient.dao.InvoiceDAO;
import org.secondopinion.patient.dao.MedicalprescriptionDAO;
import org.secondopinion.patient.dao.MedicaltestDAO;
import org.secondopinion.patient.dao.MedicaltestprescriptionDAO;
import org.secondopinion.patient.dao.MedicationDAO;
import org.secondopinion.patient.dao.MedicationusageDAO;
import org.secondopinion.patient.dao.PrescriptionDAO;
import org.secondopinion.patient.dto.Address;
import org.secondopinion.patient.dto.Appointment;
import org.secondopinion.patient.dto.FillPrescriptionRequestDTO;
import org.secondopinion.patient.dto.Invoice;
import org.secondopinion.patient.dto.Medicalprescription;
import org.secondopinion.patient.dto.MedicalprescriptionDTO;
import org.secondopinion.patient.dto.MedicalprescriptionSearchCriteria;
import org.secondopinion.patient.dto.MedicalprescriptionTestDTO;
import org.secondopinion.patient.dto.Medicaltest;
import org.secondopinion.patient.dto.Medicaltestprescription;
import org.secondopinion.patient.dto.Medication;
import org.secondopinion.patient.dto.MedicationDTO;
import org.secondopinion.patient.dto.MedicationUsageNewDTO;
import org.secondopinion.patient.dto.Medicationusage;
import org.secondopinion.patient.dto.PatientPaymentDetails;
import org.secondopinion.patient.dto.PatientPriceUpdateDTO;
import org.secondopinion.patient.dto.Patientpreference;
import org.secondopinion.patient.dto.Prescription;
import org.secondopinion.patient.dto.PrescriptionAllRespnceDTO;
import org.secondopinion.patient.dto.PrescriptionDTO;
import org.secondopinion.patient.dto.PrescriptionPriceUpdateDTOPharmacy;
import org.secondopinion.patient.dto.PrescriptionRespnceDTO;
import org.secondopinion.patient.service.INotificationalertsService;
import org.secondopinion.patient.service.IPatientPrescriptionService;
import org.secondopinion.patient.service.IPatientpreferenceService;
import org.secondopinion.patient.service.IScheduleService;
import org.secondopinion.patient.service.helper.DoseTime;
import org.secondopinion.patient.service.rest.PharmacyRestAPIService;
import org.secondopinion.request.Response;
import org.secondopinion.utils.NotificationAlert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PrescriptionServiceImpl implements IPatientPrescriptionService {

	@Autowired
	private MedicalprescriptionDAO medicalprescriptionDAO;

	@Autowired
	private IPatientpreferenceService iPatientpreferenceService;

	@Autowired
	private MedicaltestDAO medicaltestDAO;

	@Autowired
	private IScheduleService scheduleService;

	@Autowired
	private MedicationDAO medicationDAO;

	@Autowired
	private AppProperties appProperties;

	@Autowired
	private AppointmentDAO appointmentDAO;

	@Autowired
	private INotificationalertsService notificationalertsServiceImpl;

	@Autowired
	private AddressDAO addressDAO;

	@Autowired
	private InvoiceDAO invoiceDAO;

	@Autowired
	private PharmacyRestAPIService pharmacyRestAPIService;

	@Autowired
	private UtilComponent utilComponent;
	@Autowired
	private MedicationusageDAO medicationusageDAO;

	@Autowired
	private PrescriptionDAO prescriptionDAO;
	@Autowired
	private MedicaltestprescriptionDAO medicaltestprescriptionDAO;

	@Override
	@Transactional
	public void fillPrescriptionRequestToPharmacy(FillPrescriptionRequestDTO fillPrescriptionRequestDTO) {
		fillPrescriptionRequestDTO.validatefillPrescriptionRequestToPharmacy();

		Address shippingAddress = addressDAO.getByUserIdAndAddressId(fillPrescriptionRequestDTO.getPatientId(),
				fillPrescriptionRequestDTO.getShippingAddressId());

		Medicalprescription medicalprescription = medicalprescriptionDAO.findOneByProperty(
				Medicalprescription.FIELD_medicalPrescriptionId, fillPrescriptionRequestDTO.getMedicalprescriptionid());

		if (Objects.isNull(medicalprescription)) {
			throw new IllegalArgumentException("Prescription not found.");
		}
		List<Medication> medications = medicationDAO
				.findMedicationByPrescriptionId(medicalprescription.getMedicalPrescriptionId());
		Prescription prescription = prescriptionDAO.findByPrescrptionId(medicalprescription.getPrescriptionId());
		prescription.setPrescriptionFill('Y');
		prescription.setMedications(medications);
		if (Objects.isNull(fillPrescriptionRequestDTO.getPharmacyaddressId())) {
			Patientpreference patientpreference = iPatientpreferenceService
					.getPatientpreferenceForpharmacyfillReq(fillPrescriptionRequestDTO.getPatientId());
			if (Objects.isNull(patientpreference)) {
				prescription.setPharmacyaddressId(1L);
			}
			prescription.setPharmacyaddressId(patientpreference.getPharmacyid());
		} else {
			prescription.setPharmacyaddressId(fillPrescriptionRequestDTO.getPharmacyaddressId());
		}

		prescription.setPatientName(fillPrescriptionRequestDTO.getForUserName());
		medicalprescription.setLastfilereqtime(new Date());

		medicalprescription.setNumberofrefills(medicalprescription.getNumberofrefills() + 1);

		prescription.setShippingAddress(shippingAddress);
		prescriptionDAO.save(prescription);
		medicalprescriptionDAO.save(medicalprescription);
		prescription.setMedicalPrescriptionId(medicalprescription.getMedicalPrescriptionId());
		pharmacyRestAPIService.prescriptionRequestToPharmacy(prescription);

		Invoice invoice = Invoice.buildNewInvoiceForPrescription(prescription, new PatientPaymentDetails());
		invoiceDAO.save(invoice);

		// notification alert
		NotificationAlert alert = new NotificationAlert(prescription.getPatientId(), prescription.getPrescriptionId(),
				"Medical Prescription", "Your prescription is placed successfully.");

		notificationalertsServiceImpl.sendNotification(alert);
	}

	@Override
	@Transactional
	public void addMedicinesToThePrecriptionForImage(PatientPriceUpdateDTO patientPriceUpdateDTO) {
		List<Medication> medications = new ArrayList<>();
		for (PrescriptionPriceUpdateDTOPharmacy prescriptionPriceUpdateDTO : patientPriceUpdateDTO
				.getPrescriptionPriceUpdateDTOs()) {
			Medication medication = new Medication();
			medication.setMedicineName(prescriptionPriceUpdateDTO.getMedicine());
			medication.setPatientId(prescriptionPriceUpdateDTO.getPatientId());
			medication.setEnddate(prescriptionPriceUpdateDTO.getEndDate());
			medication.setNumberOfDays(prescriptionPriceUpdateDTO.getNumberOfDays());
			medication.setMedicalPrescriptionId(prescriptionPriceUpdateDTO.getMedicalPrescriptionId());
			medication.setNumberofrefills(1L);
			medication.setActive('Y');
			medications.add(medication);
		}
		medicationDAO.save(medications);
	}

	@Override
	@Transactional
	public void updateMedicationusage(Medicationusage medicationusageDTO) {
		Medicationusage medicationusage = medicationusageDAO.findById(medicationusageDTO.getMedicationusageId());
		medicationusage.setDosageConsumed(medicationusageDTO.getDosageConsumed());
		medicationusageDAO.save(medicationusage);

	}

	@Override
	@Transactional
	public Medicaltest uploadTestDocument(Long medicalPrescriptionId, Medicaltest medicaltest,
			MultipartFile multipartFile) {

		if (multipartFile == null) {
			return null;
		}

		Prescription medicalprescription = prescriptionDAO.findByPrescrptionId(medicalPrescriptionId);
		if (Objects.isNull(medicalprescription)) {
			throw new IllegalArgumentException("medicalprescription not found.");
		}

		medicaltest.setMedicalTestPrescriptionId(medicalPrescriptionId);
		medicaltest.setActive('Y');

		// not so many arguments. we have to send object send
		String uploadReference;
		uploadReference = FileService.uploadFile(appProperties.getFc(), medicalprescription.getPatientId(),
				multipartFile, medicaltest.getMedicalTestId());

		medicaltest.setDocumentName(multipartFile.getOriginalFilename());
		medicaltest.setDocumentLocation(uploadReference);
		medicaltest.setMedicalTestId(medicaltest.getMedicalTestId());
		medicaltestDAO.save(medicaltest);

		// notify to doctor for the appointment
		scheduleService.notifytoDoctorAppointment(medicalprescription.getDoctorAppointmentId(), "New test report",
				"New test report : " + medicaltest.getTestName() + " uploaded by patient.");

		return medicaltest;
	}

	@Override
	@Transactional(readOnly = true)
	public Response<List<Medication>> getAllMedications(
			MedicalprescriptionSearchCriteria medicalprescriptionSearchCriteria) {
		Response<List<Medication>> medication = medicationDAO.getAllMedications(medicalprescriptionSearchCriteria);
		return medication;
	}

	@Override
	@Transactional(readOnly = true)
	public Response<List<Medicaltest>> getAllMedicaltests(
			MedicalprescriptionSearchCriteria medicalprescriptionSearchCriteria) {
		Response<List<Medicaltest>> response = medicaltestDAO.getAllMedicaltests(medicalprescriptionSearchCriteria);
		return response;
	}

	@Override
	@Transactional
	public boolean isMedicationTakenOrNot(Long userId, DoseTime doseTime, Long medicationId) {
		Medicationusage medicationusage = medicationusageDAO.isMedicationTakenOrNot(userId, doseTime, medicationId);
		if (medicationusage.getDosageConsumed() == null || medicationusage.getDosageConsumed() == 'N') {
			return false;
		}
		return true;
	}

	@Override
	@Transactional
	public List<Medicationusage> getMedicationusageBymedicationId(Long medicationId) {

		return medicationusageDAO.getMedicationusageBymedicationId(medicationId);
	}

	private void saveMedicationusage(List<Medication> medicines) {
		List<Medicationusage> medicationusage = new ArrayList<>();

		for (Medication medication : medicines) {
			List<Medicationusage> medicationusage1 = Medicationusage.build(medication);
			medicationusage.addAll(medicationusage1);

		}
		medicationusage.stream().forEach(n -> {
			n.setDosageConsumed('N');
		});

		medicationusageDAO.save(medicationusage);
	}

	@Override
	@Transactional
	public void addMedicinesToThePrecription(Medication medication) {
		medication.setActive('Y');
		medicationDAO.save(medication);

	}

	@Override
	@Transactional
	public Medicaltest uploadmedicalTestForDiagnosticcenter(Long medicalPrescriptionId, Medicaltest medicaltest,
			MultipartFile multipartFile) {

		if (multipartFile == null) {
			return null;
		}

		Prescription medicalprescription = prescriptionDAO.findByPrescrptionId(medicalPrescriptionId);
		if (Objects.isNull(medicalprescription)) {
			throw new IllegalArgumentException("medicalprescription not found.");
		}

		medicaltest.setMedicalTestPrescriptionId(medicalPrescriptionId);
		medicaltest.setActive('Y');

		// not so many arguments. we have to send object send
		String uploadReference;
		uploadReference = FileService.uploadFile(appProperties.getFc(), medicalprescription.getPatientId(),
				multipartFile, medicaltest.getMedicalTestId());

		medicaltest.setDocumentName(multipartFile.getOriginalFilename());
		medicaltest.setDocumentLocation(uploadReference);
		medicaltest.setMedicalTestId(medicaltest.getMedicalTestId());
		medicaltestDAO.save(medicaltest);

		// notify to doctor for the appointment
		scheduleService.notifytoDoctorAppointment(medicalprescription.getDoctorAppointmentId(), "New test report",
				"New test report : " + medicaltest.getTestName() + " uploaded by patient.");

		return medicaltest;

	}

	@Override
	@Transactional
	public void saveMedicalprescriptionFromDoctor(Prescription prescription, MultipartFile multipartFile) {
		Appointment appointment = null;
		Long doctorAppointmentId = prescription.getDoctorAppointmentId();
		if (prescription.getMedicalPrescritionFor().equals("IMAGE")) {
			prescription.setContainsTestPrescription('N');
			prescription.setContainsMedicalPrescription('N');
			prescription.setPrescriptioncontainsImage('Y');
		} else {
			prescription.setPrescriptioncontainsImage('N');
		}

		if (Objects.isNull(doctorAppointmentId)) {
			throw new IllegalArgumentException(
					"Field " + Prescription.FIELD_doctorAppointmentId + " can not be null. ");
		}

		boolean isExistsForTheAppointment = prescriptionDAO
				.findPrescriptionRequestByDoctorAppointmentId(prescription.getDoctorAppointmentId());

		if (isExistsForTheAppointment) {
			throw new IllegalArgumentException(
					"Prescription  already exists for this appointment. You can update the existing prescription. ");
		}
		appointment = appointmentDAO.findAppointmentByReferenceAppointmentId(prescription.getDoctorAppointmentId(),
				prescription.getAppointmentFor());

		if (Objects.isNull(appointment)) {
			throw new IllegalArgumentException("Appointment not found");
		}

		savePrescriptionForDoctor(prescription, appointment, multipartFile);

	}

	@Override
	@Transactional
	public void saveMedicalprescriptionFromPatient(Prescription prescription, MultipartFile multipartFile) {
		Appointment appointment = null;

		if (prescription.getMedicalPrescritionFor().equals("IMAGE")) {
			prescription.setContainsTestPrescription('N');
			prescription.setContainsMedicalPrescription('N');
			prescription.setPrescriptioncontainsImage('Y');
		} else {
			prescription.setPrescriptioncontainsImage('N');
		}

		savePrescriptionForPatient(prescription, appointment, multipartFile);

	}

	private void savePrescriptionForPatient(Prescription prescription, Appointment appointment,
			MultipartFile multipartFile) {

		prescription.updateForSelf();

		prescription.setPrescriptionFill('N');
		prescription.setActive('Y');
		prescriptionDAO.save(prescription);
		if (multipartFile != null) {
			String uploadReference;
			uploadReference = FileService.uploadFile(appProperties.getFc(), prescription.getPatientId(), multipartFile,
					prescription.getPrescriptionId());
			prescription.setDocumentName(multipartFile.getOriginalFilename());
			prescription.setDocumentLocation(uploadReference);
			prescriptionDAO.save(prescription);
		}
		Medicalprescription medicalprescription = prescription.getMedicalprescription();
		if (medicalprescription != null) {
			medicalprescription.setPrescriptionId(prescription.getPrescriptionId());
			medicalprescription.setPatientId(prescription.getPatientId());

			if (medicalprescription.getNumberofrefills() == null) {
				medicalprescription.setNumberofrefills(1);
			}
			medicalprescription.setPrescriptionId(prescription.getPrescriptionId());
			medicalprescription.setLastfilereqtime(new Date());
			medicalprescription.setPatientId(prescription.getPatientId());
			medicalprescriptionDAO.save(medicalprescription);
		} else {
			Medicalprescription medicalprescriptiondb = new Medicalprescription();
			medicalprescriptiondb.setPrescriptionId(prescription.getPrescriptionId());
			medicalprescriptiondb.setLastfilereqtime(new Date());
			medicalprescriptiondb.setPatientId(prescription.getPatientId());
			medicalprescriptiondb.setNumberofrefills(1);
			medicalprescriptionDAO.save(medicalprescriptiondb);
		}
		if (prescription.getMedicalprescription() != null) {
			List<Medication> medicines = prescription.getMedicalprescription().getMedications();
			if (!CollectionUtils.isEmpty(medicines)) {
				medicines = medicines.stream().map(mc -> {
					mc.setMedicalPrescriptionId(medicalprescription.getMedicalPrescriptionId());
					mc.setPatientId(medicalprescription.getPatientId());
					if (Objects.isNull(mc.getNumberofrefills())) {
						mc.setNumberofrefills(1L);
					}
					if (Objects.isNull(mc.getNumberOfDays())) {
						mc.setNumberOfDays(1);
					}
					mc.setActive('Y');
					return mc;
				}).collect(Collectors.toList());
				medicationDAO.save(medicines);
				saveMedicationusage(medicines);

			}
			Medicaltestprescription medicaltestprescription = prescription.getMedicaltestprescription();
			if (medicaltestprescription != null) {
				medicaltestprescription.setPrescriptionId(prescription.getPrescriptionId());
				medicaltestprescription.setPatientId(prescription.getPatientId());
				medicaltestprescriptionDAO.save(medicaltestprescription);
			}

			List<Medicaltest> medicaltests = prescription.getMedicaltestprescription().getMedicalTests();

			if (!CollectionUtils.isEmpty(medicaltests)) {
				medicaltests = medicaltests.stream().map(mt -> {
					mt.setMedicalTestPrescriptionId(medicaltestprescription.getMedicalTestPrescriptionId());
					mt.setPatientId(medicalprescription.getPatientId());
					mt.setActive('Y');
					return mt;
				}).collect(Collectors.toList());
				medicaltestDAO.save(medicaltests);

			}
		}

		NotificationAlert alert = new NotificationAlert(prescription.getPatientId(), prescription.getPrescriptionId(),
				"Medical Prescription", "You have received a new prescription.");

		notificationalertsServiceImpl.sendNotification(alert);

	}

	private void savePrescriptionForDoctor(Prescription prescription, Appointment appointment,
			MultipartFile multipartFile) {

		if (Objects.isNull(appointment)) {
			prescription.update(appointment);

		}
		prescription.setPrescriptionFill('N');
		prescription.setActive('Y');
		prescriptionDAO.save(prescription);
		appointment.setPrescriptionId(prescription.getPrescriptionId());
		appointment.setFollowup(prescription.getFollowup());
		appointment.setFollowupDate(prescription.getFollowupDate());
		appointmentDAO.save(appointment);
		if (multipartFile != null) {
			String uploadReference;
			uploadReference = FileService.uploadFile(appProperties.getFc(), prescription.getPatientId(), multipartFile,
					prescription.getPrescriptionId());
			prescription.setDocumentName(multipartFile.getOriginalFilename());
			prescription.setDocumentLocation(uploadReference);
			prescriptionDAO.save(prescription);
		}
		Medicalprescription medicalprescription = prescription.getMedicalprescription();
		if (medicalprescription != null) {
			medicalprescription.setPrescriptionId(prescription.getPrescriptionId());
			medicalprescription.setPatientId(prescription.getPatientId());

			if (medicalprescription.getNumberofrefills() == null) {
				medicalprescription.setNumberofrefills(1);
			}
			medicalprescription.setPrescriptionId(prescription.getPrescriptionId());
			medicalprescription.setLastfilereqtime(new Date());
			medicalprescription.setPatientId(prescription.getPatientId());
			medicalprescriptionDAO.save(medicalprescription);
		} else {
			Medicalprescription medicalprescriptiondb = new Medicalprescription();
			medicalprescriptiondb.setPrescriptionId(prescription.getPrescriptionId());
			medicalprescriptiondb.setLastfilereqtime(new Date());
			medicalprescriptiondb.setPatientId(prescription.getPatientId());
			medicalprescriptiondb.setNumberofrefills(1);
			medicalprescriptionDAO.save(medicalprescriptiondb);
		}
		if (prescription.getMedicalprescription() != null) {
			List<Medication> medicines = prescription.getMedicalprescription().getMedications();
			if (!CollectionUtils.isEmpty(medicines)) {
				medicines = medicines.stream().map(mc -> {
					mc.setMedicalPrescriptionId(medicalprescription.getMedicalPrescriptionId());
					mc.setPatientId(medicalprescription.getPatientId());
					if (Objects.isNull(mc.getNumberofrefills())) {
						mc.setNumberofrefills(1L);
					}
					if (Objects.isNull(mc.getNumberOfDays())) {
						mc.setNumberOfDays(1);
					}
					mc.setActive('Y');
					return mc;
				}).collect(Collectors.toList());
				medicationDAO.save(medicines);
				saveMedicationusage(medicines);

			}
			Medicaltestprescription medicaltestprescription = prescription.getMedicaltestprescription();
			if (medicaltestprescription != null) {
				medicaltestprescription.setPrescriptionId(prescription.getPrescriptionId());
				medicaltestprescription.setPatientId(prescription.getPatientId());
				medicaltestprescriptionDAO.save(medicaltestprescription);
			}

			List<Medicaltest> medicaltests = prescription.getMedicaltestprescription().getMedicalTests();

			if (!CollectionUtils.isEmpty(medicaltests)) {
				medicaltests = medicaltests.stream().map(mt -> {
					mt.setMedicalTestPrescriptionId(medicaltestprescription.getMedicalTestPrescriptionId());
					mt.setPatientId(medicalprescription.getPatientId());
					mt.setActive('Y');
					return mt;
				}).collect(Collectors.toList());
				medicaltestDAO.save(medicaltests);

			}
		}

		NotificationAlert alert = new NotificationAlert(prescription.getPatientId(), prescription.getPrescriptionId(),
				"Medical Prescription", "You have received a new prescription.");

		notificationalertsServiceImpl.sendNotification(alert);
	}

	@Override
	@Transactional
	public Medicalprescription getByMedicalprescriptionId(Long id) {
		return medicalprescriptionDAO.findById(id);
	}

	@Override
	@Transactional
	public Collection<MedicationUsageNewDTO> getMedicationsForTheDay(MedicationUsageNewDTO medicationusageDTO) {
		return medicationusageDAO.getMedicationsForTheDay(medicationusageDTO);
	}

	/*
	 * @Override
	 * 
	 * @Transactional public List<MedicationDTO> getMedicationsAllByuserId(Long
	 * userId) { return medicalprescriptionDAO.getMedicationsAllByuserId(userId); }
	 */

	@Override
	@Transactional
	public Prescription getprecriptionByPrescriptionIdAndUserId(Long userId, Long prescriptionId) {
		Prescription prescription = prescriptionDAO.getPrescriptionById(userId, prescriptionId);
		return prescription;
	}

	@Override
	@Transactional
	public Collection<PrescriptionDTO> getPrecriptionByUserId(Long userId) {

		Collection<PrescriptionDTO> prescription = prescriptionDAO.getPrecriptionDetailsByUserId(userId);

		return prescription;
	}

	@Override
	@Transactional
	public PrescriptionRespnceDTO getMedicalPrescriptionDetails(Long medicalPresciptionId) {
		PrescriptionRespnceDTO prescriptionRespnceDTO = new PrescriptionRespnceDTO();

		List<Medicalprescription> medicalprescriptions = medicalprescriptionDAO
				.findAllMedicalprescriptionByPrescriptionId(medicalPresciptionId);

		if (Objects.isNull(medicalprescriptions)) {
			throw new IllegalArgumentException(" Medicalprescriptions  Not Found");
		}

		List<Long> medicalprescriptionId = medicalprescriptions.stream()
				.map(Medicalprescription::getMedicalPrescriptionId).collect(Collectors.toList());
		List<Medication> medications = medicationDAO.getMedicalPrescriptionDetailsWithMedication(medicalprescriptionId);

		if (Objects.isNull(medications)) {
			prescriptionRespnceDTO.setMedication(new ArrayList<>());

		}

		prescriptionRespnceDTO.setMedicalprescription(medicalprescriptions);
		prescriptionRespnceDTO.setMedication(medications);
		return prescriptionRespnceDTO;
	}

	@Override
	@Transactional
	public PrescriptionAllRespnceDTO geAlltMedicalPrescription(Long userId) {

		Collection<MedicalprescriptionDTO> prescription = prescriptionDAO.geAlltMedicalPrescription(userId);
		Collection<MedicalprescriptionTestDTO> medicalprescriptionTestDTO = prescriptionDAO
				.geAlltmedicalprescriptionTest(userId);
		PrescriptionAllRespnceDTO prescriptionRespnceDTO = new PrescriptionAllRespnceDTO();
		prescriptionRespnceDTO.setMedicalprescriptionDTOs(prescription);
		prescriptionRespnceDTO.setMedicalprescriptionTestDTOs(medicalprescriptionTestDTO);
		return prescriptionRespnceDTO;
	}

	@Override
	@Transactional
	public PrescriptionRespnceDTO getMedicalTestPrescriptionDetails(Long medicalTestPresciptionId) {
		PrescriptionRespnceDTO prescriptionRespnceDTO = new PrescriptionRespnceDTO();
		List<Medicaltestprescription> medicaltestprescriptions = medicaltestprescriptionDAO
				.findAllMedicaltestprescriptionByMedicalTestPresciptionId(medicalTestPresciptionId);

		if (Objects.isNull(medicaltestprescriptions)) {
			throw new IllegalArgumentException(" Medicaltestprescription  Not Found");
		}

		prescriptionRespnceDTO.setMedicaltestprescription(medicaltestprescriptions);
		List<Long> medicaltestprescriptionId = medicaltestprescriptions.stream()
				.map(Medicaltestprescription::getMedicalTestPrescriptionId).collect(Collectors.toList());

		List<Medicaltest> medicaltests = medicaltestDAO
				.findAllMedicaltestByMedicalTestPresciptionId(medicaltestprescriptionId);
		prescriptionRespnceDTO.setMedicaltest(medicaltests);
		return prescriptionRespnceDTO;
	}

	@Override
	@Transactional
	public Prescription getPrescriptionDetailsById(Long presciptionId) {
		Prescription prescription = prescriptionDAO.findById(presciptionId);
		byte[] file = FileService.readFile(appProperties.getFc(), prescription.getDocumentLocation());
		if (file != null) {
			prescription.setImage(file);
		}
		return prescription;
	}

	@Override
	@Transactional
	public Prescription getPrescriptionDetailsBydoctorAppointmentId(Long doctorAppointmentId, Long userId) {
		Prescription prescription = prescriptionDAO
				.getPrescriptionDetailsBydoctorAppointmentIddoctorAppointmentId(doctorAppointmentId, userId);
		byte[] file = FileService.readFile(appProperties.getFc(), prescription.getDocumentLocation());
		if (file != null) {
			prescription.setImage(file);
		}
		return prescription;
	}

	@Override
	@Transactional
	public Prescription getPrescriptionBydoctorAppointmentId(Long doctorAppointmentId) {
		Prescription prescription = prescriptionDAO
				.getPrescriptionDetailsBydoctorAppointmentIddoctorAppointmentId(doctorAppointmentId);
		if (Objects.isNull(prescription)) {
			return null;
		}
		byte[] file = FileService.readFile(appProperties.getFc(), prescription.getDocumentLocation());
		if (file != null) {
			prescription.setImage(file);
		}

		Medicalprescription medicalprescription = medicalprescriptionDAO
				.findMedicalprescriptionByPrescriptionId(prescription.getPrescriptionId());
		if (Objects.nonNull(medicalprescription)) {
			List<Medication> medications = medicationDAO
					.findMedicationByPrescriptionId(medicalprescription.getMedicalPrescriptionId());
			if (Objects.nonNull(medications)) {
				prescription.setMedications(medications);
			}
		}
		Medicaltestprescription medicaltestprescription = medicaltestprescriptionDAO
				.findMedicaltestprescriptionyPrescriptionId(prescription.getPrescriptionId());
		if (Objects.nonNull(medicaltestprescription)) {
			List<Medicaltest> medicaltest = medicaltestDAO
					.findMedicaltestByMedicalTestId(medicaltestprescription.getMedicalTestPrescriptionId());
			if (Objects.nonNull(medicaltest)) {
				prescription.setMedicaltests(medicaltest);
			}
		}

		return prescription;
	}

}
