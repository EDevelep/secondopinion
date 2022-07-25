package org.secondopinion.patient.service;

import java.util.List;

import org.secondopinion.patient.dto.ACCESS_TYPE;
import org.secondopinion.patient.dto.ForUser;
import org.secondopinion.patient.dto.MedcalReportSearchRequest;

import org.secondopinion.patient.dto.Medicalreports;
import org.secondopinion.patient.dto.RELATIONSHIP_TYPE;
import org.secondopinion.patient.dto.Relationship;
import org.secondopinion.request.Response;
import org.springframework.web.multipart.MultipartFile;

public interface IScheduleService {

	//List<Appointment> searchAppointments(ForUser forUser, SearchSchedule search) ;

	Medicalreports uploadReport(ForUser forUser, Medicalreports medicalReport,MultipartFile multipartFile,RELATIONSHIP_TYPE relationship_TYPE) ;


	Medicalreports retrieveMedicalReportById(Long medicalReportId) ;

	Response<List<Medicalreports>> retrieveMedicalReportByUserId(ForUser foruser, Integer pageNum, Integer maxResults,RELATIONSHIP_TYPE relationship_TYPE) ;
	Response<List<Medicalreports>> getAllMedicalreportsBySearchCritieria(MedcalReportSearchRequest medcalReportSearchRequest,ForUser forUser,RELATIONSHIP_TYPE relationship_TYPE);
	Long getAssociateUserIdFromForUser(ForUser forUser, ACCESS_TYPE prescriptionDetails,RELATIONSHIP_TYPE relationship_TYPE);


	byte[] getMedicalReportsDocuments(Long medicalReportId) ;

	void notifytoDoctorAppointment(Long appointmentId, String objectName, String message);

	Response<List<Medicalreports>> getMedicalReportsForAppointment(ForUser forUser, Long appointmentId,
			Integer pageNum, Integer maxResults,RELATIONSHIP_TYPE relationship_TYPE) throws IllegalArgumentException;
	
	void deleteMedicalReports(Long userId);


	Medicalreports uploadReportByDiagnosticCenter(Long diagnosticCenterAppointmentId, String documentLocation,
			String documentName);
}
