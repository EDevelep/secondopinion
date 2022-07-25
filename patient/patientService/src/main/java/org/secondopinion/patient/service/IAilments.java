package org.secondopinion.patient.service;

import java.util.List;

import org.secondopinion.patient.dto.Ailments;

public interface IAilments {

	List<Ailments> getaliment(Long userID);

	void saveAilment(Ailments ailments);

	//void updateAilment(Long ailmentId, Ailments ailments);

}
