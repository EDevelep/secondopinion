package org.secondopinion.diagnosticcenter.dao.impl.hibernate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.secondopinion.configurations.UtilComponent;
import org.secondopinion.diagnosticcenter.domain.BaseNotificationalerts;
import org.secondopinion.diagnosticcenter.dto.Notificationalerts;
import org.secondopinion.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

@Repository
public class DiagnosticcenterNotificationalertsDAOHibernateImpl extends BaseNotificationalertsDAOHibernate{
	
	
	@Autowired
	private UtilComponent utilComponent;
	
	@Override
	@Transactional(readOnly = true)
	public List<Notificationalerts> getNotificationalerts(Long diagnosticcenterAddressId) {
		List<Criterion> criteria = new ArrayList<>();
		criteria.add(Restrictions.eq(BaseNotificationalerts.FIELD_diagnosticCenterAddressId, diagnosticcenterAddressId));
		criteria.add(Restrictions.eq(BaseNotificationalerts.FIELD_viewed, 'N'));
		criteria.add(Restrictions.between(BaseNotificationalerts.FIELD_lastUpdatedTs, DateUtil.addAndGetDate(utilComponent.getTimeZone(), -7), utilComponent.getCurrentDate()));
		criteria.add(Restrictions.eq(BaseNotificationalerts.FIELD_active, 'Y'));
		criteria.add(Restrictions.ge(BaseNotificationalerts.FIELD_expirydate, utilComponent.getCurrentDate()));
		
		return findByCrieria(criteria);

	}
	@Override
	@Transactional(readOnly = true)
	public List<Notificationalerts> getNotificationalerts(Long diagnosticcenterAddressId,Date date) {
		List<Criterion> criteria = new ArrayList<>();
		criteria.add(Restrictions.eq(BaseNotificationalerts.FIELD_diagnosticCenterAddressId, diagnosticcenterAddressId));
	//	criteria.add(Restrictions.eq(BaseNotificationalerts.FIELD_viewed, 'N'));
		criteria.add(Restrictions.eq(BaseNotificationalerts.FIELD_createdDate, date));
		criteria.add(Restrictions.eq(BaseNotificationalerts.FIELD_active, 'Y'));
		//criteria.add(Restrictions.ge(BaseNotificationalerts.FIELD_expirydate, utilComponent.getCurrentDate()));
		
		return findByCrieria(criteria);

	}

	@Override
	@Transactional(readOnly=true)
	public Notificationalerts getByAlertId(Long notificationId) {
		List<Criterion> criteria = new ArrayList<>();
		criteria.add(Restrictions.eq(BaseNotificationalerts.FIELD_diagnosticcenternotifiactionalertsid, notificationId));
		criteria.add(Restrictions.eq(BaseNotificationalerts.FIELD_active, 'Y'));
		List<Notificationalerts> alerts =  findByCrieria(criteria);
		if(CollectionUtils.isEmpty(alerts)) {
			return null;
		}
		return alerts.get(0);
	}

	@Override
	@Transactional
	public void save(Notificationalerts diagnosticcenternotificationalerts) {
		diagnosticcenternotificationalerts.setActive('Y');
		super.save(diagnosticcenternotificationalerts);
	}

	@Override
	@Transactional
	public void deleteNotificationalerts(Notificationalerts dbnotificationalerts) {
		dbnotificationalerts.setActive('N');
		super.save(dbnotificationalerts);
	}
}