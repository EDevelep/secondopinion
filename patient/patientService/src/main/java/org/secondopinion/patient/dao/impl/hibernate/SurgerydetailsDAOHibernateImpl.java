package org.secondopinion.patient.dao.impl.hibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.secondopinion.dao.exception.DataAccessException;
import org.secondopinion.patient.dto.Surgerydetails;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class SurgerydetailsDAOHibernateImpl extends BaseSurgerydetailsDAOHibernate {

	
	@Override
	@Transactional
	public void save( Surgerydetails surgerydetails) throws DataAccessException {
		if(Objects.isNull(surgerydetails.getSurgerydetailsid())) {
			surgerydetails.setActive('Y');
		}
		super.save(surgerydetails);
	}
	
	
	@Override
	@Transactional
	public List<Surgerydetails> findSurgerydetailsByUserId(Long userid) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.eq(Surgerydetails.FIELD_userId, userid));
		criterions.add(Restrictions.eq(Surgerydetails.FIELD_active, 'Y'));
		return findByCrieria(criterions);
	}
}