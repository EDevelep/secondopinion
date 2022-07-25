package org.secondopinion.patient.dao;

import java.util.List;

import org.secondopinion.dao.IDAO;
import org.secondopinion.patient.dto.Carddetails;

public interface CarddetailsDAO extends IDAO<Carddetails,Long >{

	List<Carddetails> getByPatientAndCardNumber(Long patientId, String cardnumber);

	Carddetails findCarddetailsById(Long cardId);

	List<Carddetails> findCarddetailsByUserId(Long userId);
}