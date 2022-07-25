package org.secondopinion.caretaker.dao;

import java.util.List;

import org.secondopinion.caretaker.dto.CareTakerSearchRequest;
import org.secondopinion.caretaker.dto.Caretaker;
import org.secondopinion.dao.IDAO;
import org.springframework.stereotype.Repository;

@Repository
public interface CaretakerDAO extends IDAO<Caretaker,Long >{

	String getCaretakerEmailId(Long userPrimaryKey);

	void updateLastLoginTime(Long caretakerId);

	Caretaker findCaretakerBynameAndEmail(String userName);

	List<Caretaker> findAllcareTakerBySearchRequest(CareTakerSearchRequest careTakerSearchRequest);
}