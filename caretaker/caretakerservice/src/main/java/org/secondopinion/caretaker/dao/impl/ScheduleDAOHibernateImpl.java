package org.secondopinion.caretaker.dao.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.secondopinion.caretaker.dto.Schedule;
import org.secondopinion.configurations.UtilComponent;

import org.secondopinion.request.Response;
import org.secondopinion.utils.DateUtil;
import org.secondopinioncaretaker.domain.BaseSchedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

@Repository
public class ScheduleDAOHibernateImpl extends BaseScheduleDAOHibernate{
	
	@Autowired
	public UtilComponent utilComponent;

	@Override
	@Transactional(readOnly = true)
	public List<Schedule> getByCaretakerIdAndScheduleDate(Long caretakerId, LocalDate locaDate) {
		List<Criterion> criterions = new ArrayList<>();
		if(Objects.nonNull(caretakerId)) {
			criterions.add(Restrictions.eq(BaseSchedule.FIELD_caretakerId, caretakerId));
		}
		if(Objects.nonNull(locaDate)) {
			criterions.add(Restrictions.eq(BaseSchedule.FIELD_day, locaDate.getDayOfMonth()));
			criterions.add(Restrictions.eq(BaseSchedule.FIELD_month, locaDate.getMonthValue()));
			criterions.add(Restrictions.eq(BaseSchedule.FIELD_year, locaDate.getYear()));
		}
		criterions.add(Restrictions.eq(BaseSchedule.FIELD_active, 'Y'));
		return findByCrieria(criterions, Order.asc(BaseSchedule.FIELD_scheduleDate));
		
	}
	
	@Override
	@Transactional
	public void save(Schedule schedule) {
		
		if(Objects.isNull(schedule.getScheduleId())) {
			schedule.setActive('Y');
		}
		
		super.save(schedule);
	}

	
	@Override
	@Transactional(readOnly = true)
	public List<Schedule> getCaretakerAllUpcomingSchedules(Long caretakerId) {
		LocalDate localDate = LocalDate.now();
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.eq(BaseSchedule.FIELD_caretakerId, caretakerId));
		criterions.add(Restrictions.ge(BaseSchedule.FIELD_day, localDate.getDayOfMonth()+1));
		criterions.add(Restrictions.ge(BaseSchedule.FIELD_month, localDate.getMonthValue()));
		criterions.add(Restrictions.ge(BaseSchedule.FIELD_year, localDate.getYear()));
		criterions.add(Restrictions.eq(BaseSchedule.FIELD_active, 'Y'));
		return findByCrieria(criterions, Order.asc(BaseSchedule.FIELD_scheduleDate));
		
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Schedule> getCaretakerIdAllSchedulesBetweenTheDates(Long caretakerId, Date fromDate, Date toDate) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.eq(BaseSchedule.FIELD_caretakerId, caretakerId));
		criterions.add(Restrictions.between(BaseSchedule.FIELD_scheduleDate, 
				DateUtil.getOnlyDateFromUtilDate(fromDate, utilComponent.getTimeZone()), 
				DateUtil.getOnlyDateFromUtilDate(DateUtil.addAndGetDate(toDate, utilComponent.getTimeZone(), 1), 
						utilComponent.getTimeZone())));
		criterions.add(Restrictions.eq(BaseSchedule.FIELD_active, 'Y'));
		return findByCrieria(criterions, Order.asc(BaseSchedule.FIELD_scheduleDate));
		
	}


	@Override
	@Transactional(readOnly=true)
	public List<Schedule> getByCaretakerId(Long caretakerId) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.eq(BaseSchedule.FIELD_caretakerId, caretakerId));
		criterions.add(Restrictions.eq(BaseSchedule.FIELD_active, 'Y'));
		return findByCrieria(criterions, Order.asc(BaseSchedule.FIELD_scheduleDate));
	}


	@Override
	public Schedule getByScheduleId(Long scheduleId) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.eq(BaseSchedule.FIELD_scheduleId, scheduleId));
		criterions.add(Restrictions.eq(BaseSchedule.FIELD_active, 'Y'));
		List<Schedule> schedules = findByCrieria(criterions, Order.asc(BaseSchedule.FIELD_scheduleDate));
		return schedules.get(0);
	}


	@Override
	@Transactional(readOnly=true)
	public List<Schedule> findScheduleByCaretakerId(Long caretakerId) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.eq(BaseSchedule.FIELD_caretakerId, caretakerId));
		criterions.add(Restrictions.eq(BaseSchedule.FIELD_active, 'Y'));
		return findByCrieria(criterions);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Schedule> findScheduleByBasseScheduleId(Long basseScheduleId) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.eq(BaseSchedule.FIELD_basseScheduleId, basseScheduleId));
		criterions.add(Restrictions.eq(BaseSchedule.FIELD_active, 'Y'));
		return findByCrieria(criterions, Order.asc(BaseSchedule.FIELD_scheduleDate));
	}

	@Override
	@Transactional(readOnly=true)
	public Schedule findScheduleByBasseScheduleIdAndDate(Long basseScheduleId, Long caretakerId, LocalDate localDate) {
		List<Criterion> criterions = new ArrayList<>();
		if(Objects.nonNull(basseScheduleId)) {
			criterions.add(Restrictions.eq(BaseSchedule.FIELD_basseScheduleId, basseScheduleId));
		}
		if(Objects.nonNull(caretakerId)) {
			criterions.add(Restrictions.eq(BaseSchedule.FIELD_caretakerId, caretakerId));
		}
		criterions.add(Restrictions.eq(BaseSchedule.FIELD_active, 'Y'));
		if(Objects.nonNull(localDate)) {
			criterions.add(Restrictions.eq(BaseSchedule.FIELD_day, localDate.getDayOfMonth()));
			criterions.add(Restrictions.eq(BaseSchedule.FIELD_month, localDate.getMonthValue()));
			criterions.add(Restrictions.eq(BaseSchedule.FIELD_year, localDate.getYear()));
		}
		List<Schedule> schedules = findByCrieria(criterions, Order.asc(BaseSchedule.FIELD_scheduleDate));
		if(CollectionUtils.isEmpty(schedules)) {
			return null;
		}
		return schedules.get(0);
	}

	@Override
	@Transactional(readOnly=true)
	public Date findTheMaxDateOfExistingBaseschedule(Long basseScheduleId) {
		
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.eq(BaseSchedule.FIELD_basseScheduleId, basseScheduleId));
		List<Order> orders = new ArrayList<>();
		orders.add(Order.desc(BaseSchedule.FIELD_scheduleDate));
		
		Response<List<Schedule>> response = findByCrieria(criterions, orders, 1, 1);
		if(CollectionUtils.isEmpty(response.getData())) {
			return null;
		}
		return response.getData().get(0).getScheduleDate();
	}

	
	
}