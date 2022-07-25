package org.secondopinion.patient.dao.impl.hibernate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.secondopinion.configurations.UtilComponent;
import org.secondopinion.dao.exception.DataAccessException;
import org.secondopinion.patient.domain.BaseNotificationalerts;
import org.secondopinion.patient.dto.Notificationalerts;
import org.secondopinion.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class NotificationalertsDAOHibernateImpl extends BaseNotificationalertsDAOHibernate {

	@Autowired
	private UtilComponent utilComponent;

	@Override
	@Transactional
	public void save(Notificationalerts notificationalerts) throws DataAccessException {
		if (Objects.isNull(notificationalerts.getNotificationalertsId())) {
			notificationalerts.setActive('Y');
			notificationalerts.setCreatedDate(new Date());
		}
		super.save(notificationalerts);
	}

	@Override
	@Transactional
	public List<Notificationalerts> pushNotificationsToUser(Long foruserId) {
		List<Criterion> criteria = new ArrayList<>();
		criteria.add(Restrictions.eq(BaseNotificationalerts.FIELD_patientId, foruserId));
		criteria.add(Restrictions.eq(BaseNotificationalerts.FIELD_viewed, 'N'));
		criteria.add(Restrictions.eq(BaseNotificationalerts.FIELD_active, 'Y'));
		return findByCrieria(criteria, getSortOrderForNotifications());
	}

	@Override
	@Transactional(readOnly = true)
	public List<Notificationalerts> getNotificationsToUserForDate(Long patientId,Date date) {
		List<Criterion> criteria = new ArrayList<>();
		criteria.add(Restrictions.eq(BaseNotificationalerts.FIELD_patientId, patientId));
		criteria.add(Restrictions.ge(BaseNotificationalerts.FIELD_createdDate, date));
		return findByCrieria(criteria, getSortOrderForNotifications());
	}

	@Override
	@Transactional(readOnly = true)
	public List<Notificationalerts> findnotificationalertsByPatientId(Long patientId) {
		List<Criterion> criteria = new ArrayList<>();
		criteria.add(Restrictions.eq(BaseNotificationalerts.FIELD_patientId, patientId));
		criteria.add(Restrictions.eq(BaseNotificationalerts.FIELD_active, 'Y'));
		return findByCrieria(criteria, getSortOrderForNotifications());
	}

	@Override
	@Transactional
	public Notificationalerts findNotificationalertsBynotificationalertsId(Long notificationId) {
		List<Criterion> criteria = new ArrayList<>();
		criteria.add(Restrictions.eq(BaseNotificationalerts.FIELD_notificationalertsId, notificationId));
		criteria.add(Restrictions.eq(BaseNotificationalerts.FIELD_active, 'Y'));
		List<Notificationalerts> notificationalerts = findByCrieria(criteria);
		return notificationalerts.get(0);

	}

	@Override
	@Transactional
	public List<Notificationalerts> getNotificationsNotificationsStatusIsview(Long foruserId) {
		List<Criterion> criteria = new ArrayList<>();
		criteria.add(Restrictions.eq(BaseNotificationalerts.FIELD_patientId, foruserId));
		criteria.add(Restrictions.eq(BaseNotificationalerts.FIELD_viewed, 'Y'));
		criteria.add(Restrictions.eq(BaseNotificationalerts.FIELD_active, 'Y'));
		return findByCrieria(criteria, getSortOrderForNotifications());
	}

	private Order getSortOrderForNotifications() {
		return Order.desc(Notificationalerts.FIELD_createdDate);
	}

	@Override
	@Transactional
	public List<Notificationalerts> findnotificationalertsBydate(Date date) {
		List<Criterion> criteria = new ArrayList<>();
		criteria.add(Restrictions.eq(BaseNotificationalerts.FIELD_expirydate, date));
		criteria.add(Restrictions.eq(BaseNotificationalerts.FIELD_active, 'Y'));
		return findByCrieria(criteria);
	}
}