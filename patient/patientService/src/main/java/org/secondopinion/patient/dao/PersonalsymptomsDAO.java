package org.secondopinion.patient.dao;

import java.util.List;

import org.secondopinion.dao.IDAO;
import org.secondopinion.patient.dto.Personalsymptoms;

public interface PersonalsymptomsDAO extends IDAO<Personalsymptoms ,Long >{

	List<Personalsymptoms> findFamilyhistoryByUserId(Long userid);
}