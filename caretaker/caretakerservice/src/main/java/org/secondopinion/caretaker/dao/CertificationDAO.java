package org.secondopinion.caretaker.dao; 

import java.util.Collection;

import org.secondopinion.caretaker.dto.Certification;
import org.secondopinion.dao.IDAO;


public interface CertificationDAO extends IDAO<Certification,Long >{


	Collection<Certification> findCaretakerCertificationsByCaretakerId(Long caretakerId);

	Certification findCertificationById(Long certificationId);
}