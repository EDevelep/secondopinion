package org.secondopinion.patient.dao.impl.hibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.secondopinion.dao.exception.DataAccessException;
import org.secondopinion.patient.dto.Socialhistory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class SocialhistoryDAOHibernateImpl extends BaseSocialhistoryDAOHibernate {

	

	@Override
	@Transactional
	public void save( Socialhistory socialhistory) throws DataAccessException {
		if(Objects.isNull(socialhistory.getSocialhistoryid())) {
			socialhistory.setActive('Y');
		}
		super.save(socialhistory);
	}
	
	
	
	
	@Override
	@Transactional
	public List<Socialhistory> findSocialhistoryByUserId(Long userid) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.eq(Socialhistory.FIELD_userId, userid));
		criterions.add(Restrictions.eq(Socialhistory.FIELD_active, 'Y'));
		return findByCrieria(criterions);
	}
}