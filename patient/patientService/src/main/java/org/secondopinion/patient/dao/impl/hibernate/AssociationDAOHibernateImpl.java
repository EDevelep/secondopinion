package org.secondopinion.patient.dao.impl.hibernate;

import java.util.Objects;

import org.secondopinion.patient.dto.Association;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class AssociationDAOHibernateImpl extends BaseAssociationDAOHibernate{
	
	@Override
	@Transactional
	public void save(Association association ) {
		if(Objects.isNull(association.getAssociationId())) {
			association.setActive('Y');
		}
		super.save(association);
	}
	
}