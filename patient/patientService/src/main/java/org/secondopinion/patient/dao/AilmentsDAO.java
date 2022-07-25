package org.secondopinion.patient.dao;

import java.util.List;

import org.secondopinion.dao.IDAO;
import org.secondopinion.patient.dto.Ailments;

public interface AilmentsDAO extends IDAO<Ailments,Long >{

	List<Ailments> getalimentByUserId(Long userID);
}