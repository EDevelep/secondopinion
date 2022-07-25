package org.secondopinion.pharmacy.dao.impl.hibernate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.secondopinion.configurations.UtilComponent;
import org.secondopinion.pharmacy.domain.BaseNotificationalerts;
import org.secondopinion.pharmacy.dto.Notificationalerts;
import org.secondopinion.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

@Repository
public class NotificationalertsDAOHibernateImpl extends BaseNotificationalertsDAOHibernate{
	
	@Autowired
	private UtilComponent utilComponent;
	
	@Override
	@Transactional(readOnly = true)
	public List<Notificationalerts> getNotificationalerts(Long pharmacyAddressId,Date date) {
		List<Criterion> criteria = new ArrayList<>();
		criteria.add(Restrictions.eq(BaseNotificationalerts.FIELD_pharmacyaddressId, pharmacyAddressId));
		//criteria.add(Restrictions.eq(BaseNotificationalerts.FIELD_viewed, 'Y'));
		criteria.add(Restrictions.eq(BaseNotificationalerts.FIELD_createdDate, date));
		criteria.add(Restrictions.eq(BaseNotificationalerts.FIELD_active, 'Y'));
		//criteria.add(Restrictions.ge(BaseNotificationalerts.FIELD_expirydate, utilComponent.getCurrentDate()));
		return findByCrieria(criteria);

	}

	@Override
	@Transactional(readOnly = true)
	public List<Notificationalerts> getNotificationalerts(Long pharmacyAddressId) {
		List<Criterion> criteria = new ArrayList<>();
		criteria.add(Restrictions.eq(BaseNotificationalerts.FIELD_pharmacyaddressId, pharmacyAddressId));
		//criteria.add(Restrictions.eq(BaseNotificationalerts.FIELD_viewed, 'Y'));
		//criteria.add(Restrictions.eq(BaseNotificationalerts.FIELD_expirydate, date));
		criteria.add(Restrictions.eq(BaseNotificationalerts.FIELD_active, 'Y'));
		//criteria.add(Restrictions.ge(BaseNotificationalerts.FIELD_expirydate, utilComponent.getCurrentDate()));
		return findByCrieria(criteria);

	}

	@Override
	@Transactional(readOnly=true)
	public Notificationalerts getByAlertId(Long notificationId) {
		List<Criterion> criteria = new ArrayList<>();
		criteria.add(Restrictions.eq(BaseNotificationalerts.FIELD_notificationalertsId, notificationId));
		criteria.add(Restrictions.eq(BaseNotificationalerts.FIELD_active, 'Y'));
		List<Notificationalerts> alerts =  findByCrieria(criteria);
		if(CollectionUtils.isEmpty(alerts)) {
			return null;
		}
		return alerts.get(0);
	}

	@Override
	@Transactional
	public void save(Notificationalerts notificationalerts) {
		notificationalerts.setActive('Y');
		super.save(notificationalerts);
	}

	@Override
	@Transactional
	public void deleteNotificationalerts(Notificationalerts dbnotificationalerts) {
		dbnotificationalerts.setActive('N');
		super.save(dbnotificationalerts);
	}
}