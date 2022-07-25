/*
 * package org.secondopnion.patient.service;
 * 
 * import static org.junit.Assert.assertNotNull;
 * 
 * import java.io.IOException; import java.util.List; import java.util.TimeZone;
 * 
 * import org.junit.Test; import org.secondopinion.patient.dto.ForUser; import
 * org.secondopinion.patient.dto.MedcalReportSearchRequest; import
 * org.secondopinion.patient.dto.MedicalSearch;
 * 
 * import org.secondopinion.patient.dto.Medicalreports;
 * 
 * import org.secondopinion.patient.dto.PrescriptionSearch; import
 * org.secondopinion.patient.service.impl.ScheduleService; import
 * org.secondopinion.request.Response; import org.secondopinion.utils.DateUtil;
 * import org.secondopnion.patient.PatientApplicationTest; import
 * org.springframework.beans.factory.annotation.Autowired;
 * 
 * import org.springframework.web.multipart.MultipartFile; import
 * org.springframework.util.StringUtils;
 * 
 * public class ScheduleServiceTest extends PatientApplicationTest {
 * 
 * @Autowired private ScheduleService scheduleService;
 * 
 * @Test public void retrieveMedicalReportByUserId() throws
 * IllegalArgumentException { ForUser forUser = new ForUser();
 * forUser.setUserId(141L); forUser.setForUserId(141L); Integer maxResults = 1;
 * Integer pageNum = 1; Response<List<Medicalreports>> reponce=
 * scheduleService.retrieveMedicalReportByUserId(forUser, pageNum, maxResults);
 * assertNotNull(reponce); }
 * 
 * 
 * 
 * @Test public void deleteMedicalReports() throws IllegalArgumentException {
 * Long medicalreportId=25L;
 * scheduleService.deleteMedicalReports(medicalreportId);
 * 
 * }
 * 
 * @Test public void uploadReport() throws IllegalArgumentException { ForUser
 * forUser = new ForUser(); forUser.setUserId(100L); forUser.setForUserId(141L);
 * Medicalreports medicalReport = new Medicalreports();
 * medicalReport.setAlignment("test");
 * medicalReport.setDocumentLocation("D:New");
 * medicalReport.setAppointmentDate(DateUtil.getDate( TimeZone.getDefault()));
 * medicalReport.setReportTakenOn(DateUtil.getDate( TimeZone.getDefault()));
 * MultipartFile multipartFile = null; scheduleService.uploadReport(forUser,
 * medicalReport, multipartFile);
 * 
 * }
 * 
 * @Test public void notifytoDoctorAppointment() throws IllegalArgumentException
 * { Long appointmentId = 10L; String objectName = "jatinsharma"; String message
 * = "appotment"; scheduleService.notifytoDoctorAppointment(appointmentId,
 * objectName, message);
 * 
 * }
 * 
 * @Test public void getMedicalReportsDocuments() throws
 * IllegalArgumentException { Long medicalReportId = 175L; byte[]
 * medicalReports=scheduleService.getMedicalReportsDocuments(medicalReportId);
 * assertNotNull(medicalReports); }
 * 
 * @Test public void getMedicalReportsForAppointment() throws
 * IllegalArgumentException { Long appointmentId = 10L; ForUser forUser = new
 * ForUser(); forUser.setForUserId(2L); forUser.setUserId(32L);
 * scheduleService.getMedicalReportsForAppointment(forUser, appointmentId, 1,
 * 8); assertNotNull(forUser); }
 * 
 * @Test public void getAllMedicalreportsBySearchCritieria() throws Exception {
 * ForUser forUser = new ForUser(); MedcalReportSearchRequest
 * medcalReportSearchRequest = new MedcalReportSearchRequest();
 * medcalReportSearchRequest.setAlignment("test");
 * medcalReportSearchRequest.setAppointmentDate(DateUtil.getDate(
 * TimeZone.getDefault()));
 * medcalReportSearchRequest.setMaxappointmentdate(DateUtil.getDate(
 * TimeZone.getDefault())); medcalReportSearchRequest.setMedicalTestId(100L);
 * scheduleService.getAllMedicalreportsBySearchCritieria(
 * medcalReportSearchRequest, forUser);
 * 
 * assertNotNull(forUser); }
 * 
 * @Test public void uploadUserReport() throws IllegalArgumentException,
 * IOException { String UPLOADED_FOLDER =
 * "D:\\apps\\besureapp\\CureMetric_fileconfig"; ForUser forUser = new
 * ForUser(); forUser.setForUserId(7L); forUser.setUserId(50L); Medicalreports
 * medicalReport = new Medicalreports(); medicalReport.setAlignment("ctpyp");
 * medicalReport.setAppointmentId(10L);
 * medicalReport.setDocumentLocation("D:File");
 * medicalReport.setDocumentName("Report"); medicalReport.setMedicalTestId(3L);
 * medicalReport.setReportName("causul");
 * medicalReport.setLastUpdatedBy("jatin"); MultipartFile file = null; String
 * fileName = StringUtils.cleanPath(file.getOriginalFilename());
 * scheduleService.uploadReport(forUser, medicalReport, file);
 * assertNotNull(forUser); }
 * 
 * 
 * @Test public void searchMedicaltests() throws Exception { ForUser forUser =
 * new ForUser(); forUser.setForUserId(121L); forUser.setUserId(1L);
 * MedicalSearch medecalSearch = new MedicalSearch();
 * medecalSearch.setAppointmentId(100L); medecalSearch.setNotes("test");
 * medecalSearch.setTestName("medicaltest"); medecalSearch.setTestType("blod");
 * scheduleService.notifytoDoctorAppointment(appointmentId, objectName,
 * message); assertNotNull(forUser); }
 * 
 * 
 * }
 */