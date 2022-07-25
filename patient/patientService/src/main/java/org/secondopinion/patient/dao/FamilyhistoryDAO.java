package org.secondopinion.patient.dao;

import java.util.List;

import org.secondopinion.dao.IDAO;
import org.secondopinion.patient.dto.Familyhistory;

public interface FamilyhistoryDAO extends IDAO<Familyhistory,Long >{

	List<Familyhistory> findPersonalsymptomsByUserId(Long userid);

	
}