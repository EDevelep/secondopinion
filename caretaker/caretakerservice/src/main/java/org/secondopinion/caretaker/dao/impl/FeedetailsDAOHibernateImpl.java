package org.secondopinion.caretaker.dao.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.secondopinion.caretaker.dto.Feedetails;
import org.secondopinioncaretaker.domain.BaseFeedetails;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

@Repository
public class FeedetailsDAOHibernateImpl extends BaseFeedetailsDAOHibernate {

	@Override
	@Transactional(readOnly=true)
	public List<Feedetails> caretakerFeeDetails(Long caretakerId) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.eq(BaseFeedetails.FIELD_caretakerId, caretakerId));
		criterions.add(Restrictions.eq(BaseFeedetails.FIELD_active, 'Y'));
		return findByCrieria(criterions);
	}
	
	@Override
	@Transactional
	public void save(Feedetails feedetails) {
		if(Objects.isNull(feedetails.getFee()) || 
				9999d < feedetails.getFee()) {
			throw new IllegalArgumentException("fee can not be null or it can not be greater than 9999/-");
		}
		if(Objects.isNull(feedetails.getFeeDetailsId())) {
			feedetails.setActive('Y');
		}
		
		super.save(feedetails);
	}
	@Override
	@Transactional(readOnly = true)
	public Feedetails findfeeDetailsById(Long caretakerId) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.eq(BaseFeedetails.FIELD_caretakerId,caretakerId));
		criterions.add(Restrictions.eq(BaseFeedetails.FIELD_active, 'Y'));
		
		List<Feedetails> feedetails=findByCrieria(criterions);
		if(CollectionUtils.isEmpty(feedetails)) return null;
		return feedetails.get(0);
	}

	@Override
	@Transactional(readOnly = true)
	public Collection<Feedetails> findCaretakerFeeDetailsBycaretakerId(Long caretakerId) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.eq(BaseFeedetails.FIELD_caretakerId,caretakerId));
		criterions.add(Restrictions.eq(BaseFeedetails.FIELD_active, 'Y'));
		return findByCrieria(criterions);
	}
}