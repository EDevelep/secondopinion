package org.secondopinion.doctor.dao;


import java.util.List;

import java.util.Collection;

import org.secondopinion.dao.IDAO;
import org.secondopinion.doctor.dto.Feedetails;

public interface FeedetailsDAO extends IDAO<Feedetails, Long> {

	List<Feedetails> doctorFeeDetails(Long doctorId);


	Feedetails findfeeDetailsById(Long feeDetailsId);

	Collection<Feedetails> findDoctorFeeDetailsBydoctorId(Long doctorId);
}