package org.secondopinion.caretaker.dao;


import java.util.List;

import java.util.Collection;

import org.secondopinion.caretaker.dto.Feedetails;
import org.secondopinion.dao.IDAO;


public interface FeedetailsDAO extends IDAO<Feedetails, Long> {

	List<Feedetails> caretakerFeeDetails(Long doctorId);


	Feedetails findfeeDetailsById(Long feeDetailsId);

	Collection<Feedetails> findCaretakerFeeDetailsBycaretakerId(Long caretakerId);
}