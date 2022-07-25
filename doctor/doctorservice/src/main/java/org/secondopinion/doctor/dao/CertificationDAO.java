package org.secondopinion.doctor.dao; 

import java.util.Collection;

import org.secondopinion.dao.IDAO;
import org.secondopinion.doctor.dto.Certification;

public interface CertificationDAO extends IDAO<Certification,Long >{


	Collection<Certification> findDoctorCertificationsBydoctorId(Long doctorId);

	Certification findCertificationById(Long certificationId);
}