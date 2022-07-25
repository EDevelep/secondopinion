package org.secondopinion.patient.dao; 

import java.util.List;
import java.util.Map;

import org.secondopinion.dao.IDAO;
import org.secondopinion.common.dto.SortDTO.SortDirection;
import org.secondopinion.patient.dto.PatientFlagsRequest.PatientFlag;
import org.secondopinion.patient.dto.Registration;
import org.secondopinion.patient.dto.User;

public interface RegistrationDAO extends IDAO<Registration,Long >{

	List<User> fetchDoctorsByFlagWithPagination(PatientFlag patientFlag, Map<String, SortDirection> sortMap,
			Integer pageNo, Integer limit);
}