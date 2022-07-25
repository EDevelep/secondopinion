package org.secondopinion.patient.dao;

import java.util.List;

import org.secondopinion.dao.IDAO;
import org.secondopinion.patient.dto.MedcalReportSearchRequest;
import org.secondopinion.patient.dto.Medicalreports;
import org.secondopinion.request.Response;

public interface MedicalreportsDAO extends IDAO<Medicalreports, Long> {

	Response<List<Medicalreports>> getAllMedicalreportsBySearchCritieria(MedcalReportSearchRequest medcalReportSearchRequest,
			Long userId);

	Medicalreports getmedicalreportsById(Long medicalreportsId);

	Response<List<Medicalreports>> getMedicalReportsByUserAndAppointment(Long userId, Long appointmentId, Integer pageNum,
			Integer maxResults);

	List<Medicalreports> getmedicalreportsByuserId(Long userId);

	List<Medicalreports> getmedicalreportsByuserIds(List<Long> userIds);
}