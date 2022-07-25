package org.secondopinion.doctor.dao;

import java.util.List;
import java.util.Map;
import org.secondopinion.common.dto.RatingsDTO;
import org.secondopinion.dao.IDAO;
import org.secondopinion.doctor.dto.Doctor;
import org.secondopinion.doctor.dto.DoctorSearchRequest;
import org.secondopinion.request.Response;


public interface DoctorDAO extends IDAO<Doctor, Long> {

	void updateLastLoginTime(Long doctorId);

	List<Doctor> getAllDoctorsBySearchCritieria(DoctorSearchRequest doctorSearchRequest);

	String getDoctorsEmailId(Long doctorId);

	void updateRatings(Long doctorid, RatingsDTO values);

	boolean checkIfEmailInUseForOtherDcotors(Long doctorId, String userName);

	Map<String, Object> getMyReports(Long doctorid);

	Doctor findDoctorById(Long doctorId);

	

	Response<List<Doctor>> getAllDoctors(Integer pageNum, Integer maxResults);

	void updateRetryCountIfLoginFailed(Long doctorId, Integer retry);

	Doctor findByDoctorAndNutrations(String userName , String type);

	

}