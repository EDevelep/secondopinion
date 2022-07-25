package org.secondopinion.patient.dao.impl.hibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.secondopinion.dao.exception.DataAccessException;
import org.secondopinion.patient.dto.Personaldetail;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

@Repository
public class PersonaldetailDAOHibernateImpl extends BasePersonaldetailDAOHibernate{

	

	@Override
	@Transactional
	public void save(  Personaldetail personaldetail) throws DataAccessException {
		if(Objects.isNull(personaldetail.getPersonalDetailId())) {
			personaldetail.setActive('Y');
		}
		super.save(personaldetail);
	}
	
	
	@Override
	@Transactional
	public Personaldetail findPersonalDetailById(Long personalDetailId) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.eq(Personaldetail.FIELD_personalDetailId, personalDetailId));
		criterions.add(Restrictions.eq(Personaldetail.FIELD_active, 'Y'));
		List<Personaldetail> personaldetails=findByCrieria(criterions);
		if(CollectionUtils.isEmpty(personaldetails)) return null;
		return personaldetails.get(0);
	}

	@Override
	@Transactional
	public Personaldetail findPersonaldetailByUserId(Long userId) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.eq(Personaldetail.FIELD_userId, userId));
		criterions.add(Restrictions.eq(Personaldetail.FIELD_active, 'Y'));
		List<Personaldetail> personaldetails=findByCrieria(criterions);
		if(CollectionUtils.isEmpty(personaldetails)) return null;
		return personaldetails.get(0);
	}
}