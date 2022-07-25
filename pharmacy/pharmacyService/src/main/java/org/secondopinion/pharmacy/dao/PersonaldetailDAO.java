package org.secondopinion.pharmacy.dao;

import org.secondopinion.dao.IDAO;
import org.secondopinion.pharmacy.dto.Personaldetail;


public interface PersonaldetailDAO extends IDAO<Personaldetail,Long >{

	Personaldetail findPersonalDetailBypharmacyId(Long pharmacyId);

}