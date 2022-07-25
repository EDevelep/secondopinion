package org.secondopinion.patient.dao.impl.hibernate;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.secondopinion.patient.dao.UserDAO;
import org.secondopinion.patient.domain.BaseVitals;
import org.secondopinion.patient.dto.User;
import org.secondopinion.patient.dto.VitalSearch;
import org.secondopinion.patient.dto.Vitals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

@Repository
public class VitalsDAOHibernateImpl extends BaseVitalsDAOHibernate {

	@Autowired
	private UserDAO userDAO;

	@Override
	@Transactional
	public void save(Long userId, Date vitalsRecordDate, List<Vitals> vitals) {
		super.save(vitals);

		User user = userDAO.findById(userId);
		user.setVitalLastRecordedDate(vitalsRecordDate);
		userDAO.save(user);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Vitals> search(VitalSearch vitalSearch) {
		List<Criterion> criteria = new ArrayList<>();
		criteria.add(Restrictions.eq(BaseVitals.FIELD_userId, vitalSearch.getForUser().getUserId()));
		criteria.add(Restrictions.eq(BaseVitals.FIELD_recordedDate, vitalSearch.getRecordedDate()));
		criteria.add(Restrictions.eq(BaseVitals.FIELD_vitalValue, vitalSearch.getVitalValue()));
		criteria.add(Restrictions.eq(BaseVitals.FIELD_vitalsId, vitalSearch.getVitalId()));
		criteria.add(Restrictions.eq(BaseVitals.FIELD_active, 'Y'));
		return findByCrieria(criteria);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Vitals> getUserVitalsByDay(Long userId, Date date) {

		List<Criterion> criteria = new ArrayList<>();
		criteria.add(Restrictions.eq(BaseVitals.FIELD_userId, userId));
		// criteria.add(Restrictions.eq(BaseVitals.FIELD_recordedDate, date));
		criteria.add(Restrictions.eq(BaseVitals.FIELD_active, 'Y'));
		return findByCrieria(criteria);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Vitals> getUserVitalsByuserId(Long userId) {
		User user = userDAO.findById(userId);

		List<Criterion> criteria = new ArrayList<>();
		criteria.add(Restrictions.eq(BaseVitals.FIELD_userId, userId));
		criteria.add(Restrictions.eq(BaseVitals.FIELD_active, 'Y'));
		criteria.add(Restrictions.eq(BaseVitals.FIELD_recordedDate, user.getVitalLastRecordedDate()));
		return findByCrieria(criteria);
	}

	public static final String VITALS_QUERY = "select v.recordedDate as Recorded, CAST(v.vitalValue as unsigned) As Value from vitals v where v.userId=:userId and v.vitalname=:vitalname and v.recordedDate>:recordedDate";

	@Override
	@Transactional
	public List<Map<String, Object>> executeGraphQuery(Long userId, String vitalname, Date recordedDate) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", userId);
		params.put("vitalname", vitalname);
		params.put("recordedDate", recordedDate);
		return findBySqlQuery(VITALS_QUERY, params);
	}

	@Override
	@Transactional
	public List<Vitals> getUserVitalsByuserId(List<Long> userIds) {
		List<Criterion> criteria = new ArrayList<>();
		criteria.add(Restrictions.in(BaseVitals.FIELD_userId, userIds));
		criteria.add(Restrictions.eq(BaseVitals.FIELD_active, 'Y'));
		return findByCrieria(criteria);
	}

	@Override
	@Transactional
	public List<Vitals> getVitalsByvitalsId(List<Long> vitalsId) {
		List<Criterion> criteria = new ArrayList<>();
		criteria.add(Restrictions.in(BaseVitals.FIELD_vitalsId, vitalsId));
		criteria.add(Restrictions.eq(BaseVitals.FIELD_active, 'Y'));
		List<Vitals> vitals= findByCrieria(criteria);
		if(CollectionUtils.isEmpty(vitals)) {
			return null;
		}
		return vitals;
		
	}
}