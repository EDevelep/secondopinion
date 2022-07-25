package org.secondopinion.doctor.daoimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.secondopinion.doctor.domain.BaseBaseschedule;
import org.secondopinion.doctor.dto.Baseschedule;
import org.secondopinion.request.Response;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

@Repository
public class BasescheduleDAOHibernateImpl extends BaseBasescheduleDAOHibernate{

	@Override
	@Transactional(readOnly=true)
	public Baseschedule findbasseScheduleById(Long basseScheduleId) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.eq(BaseBaseschedule.FIELD_basseScheduleId, basseScheduleId));
		criterions.add(Restrictions.eq(BaseBaseschedule.FIELD_active, 'Y'));
		List<Baseschedule> baseschedules=findByCrieria(criterions);
		if(CollectionUtils.isEmpty(baseschedules)) return null;
		return baseschedules.get(0);
	}
	
	@Override
	@Transactional
	public void save(Baseschedule baseschedule) {
		if(Objects.isNull(baseschedule.getBasseScheduleId())) {
			baseschedule.setActive('Y');
		}
		super.save(baseschedule);
	}

	@Override
	@Transactional(readOnly=true)
	public Response<List<Baseschedule>> getDoctorAllBasesSchedules(Long doctorId, Integer pageNum, Integer maxResults) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.eq(BaseBaseschedule.FIELD_doctorId, doctorId));
		List<Order> orders = new ArrayList<>();
		orders.add(Order.asc(BaseBaseschedule.FIELD_basseScheduleId));
		return findByCrieria(criterions, orders, pageNum, maxResults);
	}
}