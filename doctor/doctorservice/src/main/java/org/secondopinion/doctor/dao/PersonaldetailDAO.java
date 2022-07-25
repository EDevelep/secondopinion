package org.secondopinion.doctor.dao; 

import org.secondopinion.dao.IDAO;
import org.secondopinion.doctor.dto.Personaldetail;

public interface PersonaldetailDAO extends IDAO<Personaldetail,Long >{

	Personaldetail findPersonaldetailByIdById(Long personaldetailId);

	Personaldetail findDoctorPersonaldetailByDoctorId(Long doctorId);
}