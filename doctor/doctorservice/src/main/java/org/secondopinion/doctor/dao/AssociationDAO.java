package org.secondopinion.doctor.dao;

import java.util.Collection;
import java.util.List;

import org.secondopinion.dao.IDAO;
import org.secondopinion.doctor.dto.Association;

public interface AssociationDAO extends IDAO<Association,Long >{

	List<Association> findassociationById(Long associationId);

	Collection<Association> findAssocaiationByDoctorId(Long doctorId);
}