package org.secondopinion.doctor.daoimpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.secondopinion.configurations.UtilComponent;
import org.secondopinion.doctor.domain.BaseSchedulehours;
import org.secondopinion.doctor.dto.Schedulehours;
import org.secondopinion.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

@Repository
public class SchedulehoursDAOHibernateImpl extends BaseSchedulehoursDAOHibernate{

	@Autowired
	private UtilComponent utilComponent;
	
	
	@Override
	@Transactional(readOnly=true)
	public Schedulehours getByStartAndEndTimeAndScheduleId(Date startTime, Date endTime, Long scheduleId) {
		List<Criterion> criterions = new ArrayList<>();
		if(Objects.nonNull(startTime)) {
			criterions.add(Restrictions.eq(BaseSchedulehours.FIELD_fromTime, startTime));
		}
		if(Objects.nonNull(endTime)) {
			criterions.add(Restrictions.eq(BaseSchedulehours.FIELD_toTime, endTime));
		}
		if(Objects.nonNull(scheduleId)) {
			criterions.add(Restrictions.eq(BaseSchedulehours.FIELD_scheduleId, scheduleId));
		}
		criterions.add(Restrictions.eq(BaseSchedulehours.FIELD_active, 'Y'));
		List<Schedulehours> schedulehours = findByCrieria(criterions, Order.asc(BaseSchedulehours.FIELD_fromTime));
		if(CollectionUtils.isEmpty(schedulehours)) {
			return null;
		}
		return schedulehours.get(0); 
	}
	
	@Override
	@Transactional
	public void save(Schedulehours schedulehours) {
		
		if(Objects.isNull(schedulehours.getScheduleHoursId())) {
			schedulehours.setActive('Y');
		}
		
		super.save(schedulehours);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Schedulehours> getDoctorAllUpcomingScheduleHours(List<Long> scheduleIds, boolean isCurrentDay) {
		List<Criterion> criterions = new ArrayList<>();
		if(isCurrentDay) {
			criterions.add(Restrictions.ge(BaseSchedulehours.FIELD_fromTime, DateUtil.convertLocalTimeToDate(utilComponent.getCurrentTime())));
		}
		
		criterions.add(Restrictions.eq(BaseSchedulehours.FIELD_active, 'Y'));
		criterions.add(Restrictions.in(BaseSchedulehours.FIELD_scheduleId, scheduleIds));
		return findByCrieria(criterions, Order.asc(BaseSchedulehours.FIELD_fromTime));
	}

	@Override
	@Transactional(readOnly=true)
	public List<Schedulehours> getByScheduleId(Long scheduleId) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.eq(BaseSchedulehours.FIELD_active, 'Y'));
		criterions.add(Restrictions.eq(BaseSchedulehours.FIELD_scheduleId, scheduleId));
		return findByCrieria(criterions, Order.asc(BaseSchedulehours.FIELD_fromTime));
	}

	@Override
	@Transactional(readOnly=true)
	public Schedulehours getBySchedulehoursId(Long schedulehoursId) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.eq(BaseSchedulehours.FIELD_active, 'Y'));
		criterions.add(Restrictions.eq(BaseSchedulehours.FIELD_scheduleHoursId, schedulehoursId));
		List<Schedulehours> schedulehours = findByCrieria(criterions, Order.asc(BaseSchedulehours.FIELD_fromTime));
		if(CollectionUtils.isEmpty(schedulehours)) {
			return null;
		}
		return schedulehours.get(0);
	}

}