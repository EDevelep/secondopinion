package org.secondopinion.diagnosticcenter.dao;

import org.secondopinion.dao.IDAO;
import org.secondopinion.diagnosticcenter.dto.Personaldetail;



public interface PersonaldetailDAO extends IDAO<Personaldetail,Long >{

	Personaldetail findPersonalDetailBydiagnosticcenterId(Long diagnosticcenterId);

}