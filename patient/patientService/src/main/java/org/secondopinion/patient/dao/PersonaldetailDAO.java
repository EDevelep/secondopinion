package org.secondopinion.patient.dao;

import org.secondopinion.dao.IDAO;
import org.secondopinion.patient.dto.Personaldetail;

public interface PersonaldetailDAO extends IDAO<Personaldetail,Long >{

	Personaldetail findPersonalDetailById(Long personalDetailId);

	Personaldetail findPersonaldetailByUserId(Long userId);
}