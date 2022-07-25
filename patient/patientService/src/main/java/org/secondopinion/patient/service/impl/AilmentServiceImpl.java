package org.secondopinion.patient.service.impl;

import java.util.List;
import java.util.Objects;

import org.secondopinion.patient.dao.AilmentsDAO;
import org.secondopinion.patient.dto.Ailments;
import org.secondopinion.patient.service.IAilments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AilmentServiceImpl implements IAilments {

	@Autowired
	private AilmentsDAO ailmentsDAO;

	@Override
	@Transactional
	public List<Ailments> getaliment(Long userID) {
		return ailmentsDAO.getalimentByUserId(userID);
	}

	@Override
	@Transactional
	public void saveAilment(Ailments ailments) {
		
		if (Objects.isNull(ailments )) {
			throw new IllegalArgumentException("Object Is not be empty");
		}
	
	       ailments.setActive('Y');
		ailmentsDAO.save(ailments);
	}

}
