package org.secondopinion.caretaker.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.secondopinion.caretaker.dto.Notificationalerts;
import org.secondopinion.configurations.UtilComponent;



import org.secondopinioncaretaker.domain.BaseNotificationalerts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class NotificationalertsDAOHibernateImpl extends BaseNotificationalertsDAOHibernate {

	@Autowired
	private UtilComponent utilComponent;
	
	@Override
	@Transactional(readOnly = true)
	public List<Notificationalerts> getNotificationalerts(Date dateTo,Long caretakerId) {

		List<Criterion> criteria = new ArrayList<>();
		criteria.add(Restrictions.eq(BaseNotificationalerts.FIELD_caretakerId, caretakerId));
		criteria.add(Restrictions.eq(BaseNotificationalerts.FIELD_createdDate, dateTo));

		return findByCrieria(criteria, getSortOrderForNotifications());

	}
	
	@Override
	@Transactional
	public void save(Notificationalerts notificationalerts) {
		
		if(Objects.isNull(notificationalerts.getNotificationalertsId())) {
			notificationalerts.setActive('Y');
			notificationalerts.setCreatedDate(new Date());
		}
		
		super.save(notificationalerts);
	}

	@Override
	@Transactional
	public Notificationalerts findnotificationById(Long notificationId) {
		List<Criterion> criteria = new ArrayList<>();
		criteria.add(Restrictions.eq(BaseNotificationalerts.FIELD_notificationalertsId, notificationId));
		criteria.add(Restrictions.eq(BaseNotificationalerts.FIELD_active, 'Y'));
		List<Notificationalerts> notificationalerts=findByCrieria(criteria);
		return notificationalerts.get(0);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Notificationalerts> findNotificationalertsByCaretakerId(Long caretakerId) {
		List<Criterion> criteria = new ArrayList<>();
		criteria.add(Restrictions.eq(BaseNotificationalerts.FIELD_caretakerId, caretakerId));
		criteria.add(Restrictions.eq(BaseNotificationalerts.FIELD_active, 'Y'));
		return findByCrieria(criteria, getSortOrderForNotifications());
	}
	
	private Order getSortOrderForNotifications() {
		return Order.desc(Notificationalerts.FIELD_createdDate);
	}

}