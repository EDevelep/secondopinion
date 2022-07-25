package org.secondopinion.doctor.dao; 

import java.util.List;
import java.util.Map;

import org.secondopinion.dao.IDAO;
import org.secondopinion.doctor.dto.Doctor;
import org.secondopinion.doctor.dto.DoctorFlagsRequest.DoctorFlag;
import org.secondopinion.doctor.dto.Registration;
import org.secondopinion.common.dto.SortDTO.SortDirection;

public interface RegistrationDAO extends IDAO<Registration,Long >{

	List<Doctor> fetchDoctorsByFlagWithPagination(DoctorFlag doctorFlag, Map<String, SortDirection> sortMap,
			Integer pageNo, Integer pageSize);
}