package org.secondopinion.patient.service.impl;

import java.util.List;
import java.util.Objects;
import org.secondopinion.configurations.AppProperties;
import org.secondopinion.configurations.UtilComponent;
import org.secondopinion.enums.AppointmentForEnum;
import org.secondopinion.fileservice.FileService;
import org.secondopinion.patient.dao.AppointmentDAO;
import org.secondopinion.patient.dao.MedicalprescriptionDAO;
import org.secondopinion.patient.dao.MedicalreportsDAO;
import org.secondopinion.patient.dao.MedicaltestDAO;
import org.secondopinion.patient.dao.MedicaltestprescriptionDAO;
import org.secondopinion.patient.dao.PrescriptionDAO;
import org.secondopinion.patient.dao.UserDAO;
import org.secondopinion.patient.dto.ACCESS_TYPE;
import org.secondopinion.patient.dto.Appointment;
import org.secondopinion.patient.dto.ForUser;
import org.secondopinion.patient.dto.MedcalReportSearchRequest;
import org.secondopinion.patient.dto.Medicalprescription;
import org.secondopinion.patient.dto.Medicalreports;
import org.secondopinion.patient.dto.Medicaltest;
import org.secondopinion.patient.dto.Medicaltestprescription;
import org.secondopinion.patient.dto.Prescription;
import org.secondopinion.patient.dto.RELATIONSHIP_TYPE;
import org.secondopinion.patient.dto.Relationship;
import org.secondopinion.patient.service.IScheduleService;
import org.secondopinion.patient.service.rest.DoctorRestAPIService;
import org.secondopinion.request.Response;
import org.secondopinion.utils.DateUtil;
import org.secondopinion.utils.NotificationAlert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ScheduleService implements IScheduleService {
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private AppointmentDAO appointmentDAO;

	@Autowired
	private MedicalreportsDAO medicalreportsDAO;

	@Autowired
	private AppProperties appProperties;

	@Autowired
	private DoctorRestAPIService doctorRestAPIService;

	@Autowired
	private MedicalprescriptionDAO medicalprescriptionDAO;

	@Autowired
	private PrescriptionDAO prescriptionDAO;
	@Autowired
	private MedicaltestDAO medicalTestDAO;
	@Autowired
	private MedicaltestprescriptionDAO medicaltestprescriptionDAO;

	@Autowired
	private UtilComponent utilComponent;

	@Override
	public void notifytoDoctorAppointment(Long appointmentId, String objectName, String message) {
		if (appointmentId == -1) {
			return;
		}
		Appointment appointment = appointmentDAO.findAppointmentIdById(appointmentId);
		if (Objects.isNull(appointment)) {
			throw new IllegalArgumentException("Appointment not found");
		}
		NotificationAlert alertsUtilDTO = new NotificationAlert(appointment.getUserId(),
				appointment.getReferenceAppointmentId(), objectName, message);
		doctorRestAPIService.notifiationAlertsTotheDoctor(alertsUtilDTO);
	}

	@Override
	@Transactional(readOnly = true)
	public Response<List<Medicalreports>> getMedicalReportsForAppointment(ForUser forUser, Long appointmentId,
			Integer pageNum, Integer maxResults, RELATIONSHIP_TYPE relationship_TYPE) throws IllegalArgumentException {
		Long userId = getAssociateUserIdFromForUser(forUser, ACCESS_TYPE.PRESCRIPTION_DETAILS, relationship_TYPE);
		return medicalreportsDAO.getMedicalReportsByUserAndAppointment(userId, appointmentId, pageNum, maxResults);

	}

	@Override
	@Transactional(readOnly = true)
	public byte[] getMedicalReportsDocuments(Long medicalReportId) {
		Medicalreports medicalReports = medicalreportsDAO.getmedicalreportsById(medicalReportId);
		if (Objects.isNull(medicalReports)) {
			throw new IllegalArgumentException("medicalReports not found.");
		}
		return FileService.readFile(appProperties.getFc(), medicalReports.getDocumentLocation());

	}

	public Long getAssociateUserIdFromForUser(ForUser forUser, ACCESS_TYPE accessDetails,
			RELATIONSHIP_TYPE relationship_TYPE) {
		if (Objects.isNull(forUser)) {
			throw new IllegalArgumentException("forUser can not be null");
		}
		Long userId = null;
		if (userDAO.hasUserAccessToDetails(forUser.getUserId(), forUser.getForUserId(), accessDetails,
				relationship_TYPE)) {
			userId = forUser.getForUserId();
		} else if (forUser.isCallForSelf()) {
			userId = forUser.getUserId();
		}

		if (Objects.isNull(userId)) {
			throw new IllegalArgumentException("Invalid ForUser");
		}
		return userId;
	}

	@Override
	@Transactional
	public Medicalreports uploadReport(ForUser forUser, Medicalreports medicalReport, MultipartFile multipartFile,
			RELATIONSHIP_TYPE relationship_TYPE) {
		Long userId = getAssociateUserIdFromForUser(forUser, ACCESS_TYPE.MEDICAL_RECORDS, relationship_TYPE);
		medicalReport.setUserId(userId);
		medicalReport.setAppointmentId(medicalReport.getAppointmentId());
		medicalReport.setActive('Y');
		medicalreportsDAO.save(medicalReport);
		if (Objects.isNull(multipartFile)) {
			return medicalReport;
		}
		// upload file
		String uploadReference = FileService.uploadFile(appProperties.getFc(), medicalReport.getUserId(), multipartFile,
				medicalReport.getMedicalReportsId());

		medicalReport.setDocumentName(multipartFile.getOriginalFilename());
		medicalReport.setDocumentLocation(uploadReference);
		medicalReport.setMedicalReportsId(medicalReport.getMedicalReportsId());
		medicalReport.setActive('Y');
		medicalreportsDAO.save(medicalReport);

		notifytoDoctorAppointment(medicalReport.getAppointmentId(), "New Medical Report",
				"Medical report : " + medicalReport.getReportName() + " uploaded by patient.");

		return medicalReport;

	}

	@Override
	@Transactional
	public Medicalreports uploadReportByDiagnosticCenter(Long diagnosticCenterAppointmentId, String documentLocation,
			String documentName) {

		Appointment dcAppointment = appointmentDAO.findAppointmentByReferenceAppointmentId(
				diagnosticCenterAppointmentId, AppointmentForEnum.DIAGNOSTIC_CENTER.name());
		if (dcAppointment == null) {
			throw new IllegalArgumentException("appointment not found.");
		}
		Long prescriptionId = dcAppointment.getPrescriptionId();

		Medicalreports medicalReport = new Medicalreports();
		medicalReport.setUserId(dcAppointment.getUserId());
		medicalReport.setAppointmentId(dcAppointment.getAppointmentId());
		medicalReport.setDiagnosticCenterAppointmentId(dcAppointment.getReferenceAppointmentId());
		medicalReport.setDoctorAppointmentId(-1L);
		medicalReport.setAppointmentDate(dcAppointment.getAppointmentDate());
		medicalReport.setReportName(documentName);
		medicalReport.setReportTakenOn(DateUtil.getDate(utilComponent.getTimeZone()));
		medicalReport.setMedicalTestId(-1L);
		medicalReport.setActive('Y');
		medicalReport.setDocumentName(documentName);
		medicalReport.setDocumentLocation(documentLocation);

		if (prescriptionId >= 1) {
			Prescription prescription = prescriptionDAO.findById(prescriptionId);
			if (prescription == null) {
				throw new IllegalArgumentException("Invalid medicalPrescription");
			}
			Medicaltestprescription medicaltestprescription = medicaltestprescriptionDAO
					.findOneByProperty(Medicaltestprescription.FIELD_prescriptionId, prescription.getPrescriptionId());

			Medicaltest medicalTest = medicalTestDAO.findOneByProperty(Medicaltest.FIELD_medicalTestPrescriptionId,
					medicaltestprescription.getMedicalTestPrescriptionId());
			if (medicalTest == null) {
				throw new IllegalArgumentException("Invalid medicalPrescription");
			}
			Appointment doctorAppointment = appointmentDAO.findAppointmentByReferenceAppointmentId(
					prescription.getDoctorAppointmentId(), AppointmentForEnum.DOCTOR.name());
			if (doctorAppointment == null) {
				throw new IllegalArgumentException("doctor appoinntment not found.");
			}
			medicalReport.setMedicalTestId(medicalTest.getMedicalTestId());
			medicalReport.setAppointmentId(doctorAppointment.getAppointmentId());
			medicalReport.setDoctorAppointmentId(doctorAppointment.getReferenceAppointmentId());

			notifytoDoctorAppointment(medicalReport.getAppointmentId(), "New Medical Report",
					"Medical report : " + medicalReport.getReportName() + " uploaded by diagnostic center.");

		}
		medicalreportsDAO.save(medicalReport);

		return medicalReport;

	}

	@Override
	@Transactional(readOnly = true)
	public Medicalreports retrieveMedicalReportById(Long medicalReportId) {
		Medicalreports medicalReports = medicalreportsDAO.getmedicalreportsById(medicalReportId);
		if (Objects.isNull(medicalReports)) {
			throw new IllegalArgumentException("medicalReports not found.");
		}
		medicalReports.setMedicalReportFile(
				FileService.readFile(appProperties.getFc(), medicalReports.getDocumentLocation()));
		return medicalReports;
	}

	@Override
	@Transactional
	public Response<List<Medicalreports>> retrieveMedicalReportByUserId(ForUser forUser, Integer pageNum,
			Integer maxResults, RELATIONSHIP_TYPE relationship_TYPE) {

		Long userId = getAssociateUserIdFromForUser(forUser, ACCESS_TYPE.MEDICAL_RECORDS, relationship_TYPE);
		return medicalreportsDAO.getMedicalReportsByUserAndAppointment(userId, null, pageNum, maxResults);
	}

	@Override
	@Transactional
	public Response<List<Medicalreports>> getAllMedicalreportsBySearchCritieria(
			MedcalReportSearchRequest medcalReportSearchRequest, ForUser forUser, RELATIONSHIP_TYPE relationship_TYPE) {

		Long userId = getAssociateUserIdFromForUser(forUser, ACCESS_TYPE.MEDICAL_RECORDS, relationship_TYPE);
		return medicalreportsDAO.getAllMedicalreportsBySearchCritieria(medcalReportSearchRequest, userId);
	}

	@Override
	@Transactional
	public void deleteMedicalReports(Long medicalreportsId) {
		Medicalreports medicalreports = medicalreportsDAO.getmedicalreportsById(medicalreportsId);

		if (Objects.isNull(medicalreports)) {
			throw new IllegalArgumentException("Data is Not Found For given userId");
		}
		medicalreports.setActive('N');
		medicalreportsDAO.save(medicalreports);

	}

}
