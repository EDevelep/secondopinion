package org.secondopinion.diagnosticcenter.dao.impl.hibernate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.secondopinion.configurations.UtilComponent;
import org.secondopinion.diagnosticcenter.dto.Schedule;
import org.secondopinion.diagnosticcenter.dto.ScheduleCriteriaDTO;
import org.secondopinion.request.Response;
import org.secondopinion.utils.DateUtil;
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
	public Response<List<Schedule>> getByScheduleDate(ScheduleCriteriaDTO scheduleCrtieriaDTO, LocalDate locaDate) {
		List<Criterion> criterions = new ArrayList<>();
		if(Objects.isNull(scheduleCrtieriaDTO.getDiagnosticCenterAddressId())) {
			throw new IllegalArgumentException("diagnosticCetnerAddressId can not be null.");
		}
		criterions.add(Restrictions.eq(Schedule.FIELD_diagnosticCenterAddressId, scheduleCrtieriaDTO.getDiagnosticCenterAddressId()));
		if(Objects.nonNull(scheduleCrtieriaDTO.getSubmenuId())) {
			criterions.add(Restrictions.eq(Schedule.FIELD_submenuId, scheduleCrtieriaDTO.getSubmenuId()));
		}
		if(Objects.nonNull(scheduleCrtieriaDTO.getPackageId())) {
			criterions.add(Restrictions.eq(Schedule.FIELD_packageId, scheduleCrtieriaDTO.getPackageId()));
		}
		if(Objects.nonNull(scheduleCrtieriaDTO.getDiagnosticCenteruserId())) {
			criterions.add(Restrictions.eq(Schedule.FIELD_diagnosticCenterUserId, scheduleCrtieriaDTO.getDiagnosticCenteruserId()));
		}
		if(Objects.nonNull(locaDate)) {
			criterions.add(Restrictions.eq(Schedule.FIELD_day, locaDate.getDayOfMonth()));
			criterions.add(Restrictions.eq(Schedule.FIELD_month, locaDate.getMonthValue()));
			criterions.add(Restrictions.eq(Schedule.FIELD_year, locaDate.getYear()));
		}
		criterions.add(Restrictions.eq(Schedule.FIELD_active, 'Y'));
		return findByCrieria(criterions, buildOrders(), scheduleCrtieriaDTO.getPageNum(), scheduleCrtieriaDTO.getMaxResutls());
		
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
	public Response<List<Schedule>> getAllUpcomingSchedules(ScheduleCriteriaDTO scheduleCrtieriaDTO) {
		LocalDate localDate = LocalDate.now();
		List<Criterion> criterions = new ArrayList<>();
		if(Objects.isNull(scheduleCrtieriaDTO.getDiagnosticCenterAddressId())) {
			throw new IllegalArgumentException("diagnosticCetnerAddressId can not be null.");
		}
		criterions.add(Restrictions.eq(Schedule.FIELD_diagnosticCenterAddressId, scheduleCrtieriaDTO.getDiagnosticCenterAddressId()));
		if(Objects.nonNull(scheduleCrtieriaDTO.getSubmenuId())) {
			criterions.add(Restrictions.eq(Schedule.FIELD_submenuId, scheduleCrtieriaDTO.getSubmenuId()));
		}
		if(Objects.nonNull(scheduleCrtieriaDTO.getPackageId())) {
			criterions.add(Restrictions.eq(Schedule.FIELD_packageId, scheduleCrtieriaDTO.getPackageId()));
		}
		if(Objects.nonNull(scheduleCrtieriaDTO.getDiagnosticCenteruserId())) {
			criterions.add(Restrictions.eq(Schedule.FIELD_submenuId, scheduleCrtieriaDTO.getDiagnosticCenteruserId()));
		}
		criterions.add(Restrictions.ge(Schedule.FIELD_day, localDate.getDayOfMonth()+1));
		criterions.add(Restrictions.ge(Schedule.FIELD_month, localDate.getMonthValue()));
		criterions.add(Restrictions.ge(Schedule.FIELD_year, localDate.getYear()));
		criterions.add(Restrictions.eq(Schedule.FIELD_active, 'Y'));
		return findByCrieria(criterions, buildOrders(), scheduleCrtieriaDTO.getPageNum(), scheduleCrtieriaDTO.getMaxResutls());
		
	}
	
	@Override
	@Transactional(readOnly = true)
	public Response<List<Schedule>> getAllSchedulesBetweenTheDates(ScheduleCriteriaDTO scheduleCrtieriaDTO, Date fromDate, Date toDate) {
		List<Criterion> criterions = new ArrayList<>();
		if(Objects.isNull(scheduleCrtieriaDTO.getDiagnosticCenterAddressId())) {
			throw new IllegalArgumentException("diagnosticCetnerAddressId can not be null.");
		}
		criterions.add(Restrictions.eq(Schedule.FIELD_diagnosticCenterAddressId, scheduleCrtieriaDTO.getDiagnosticCenterAddressId()));
		if(Objects.nonNull(scheduleCrtieriaDTO.getSubmenuId())) {
			criterions.add(Restrictions.eq(Schedule.FIELD_submenuId, scheduleCrtieriaDTO.getSubmenuId()));
		}
		if(Objects.nonNull(scheduleCrtieriaDTO.getPackageId())) {
			criterions.add(Restrictions.eq(Schedule.FIELD_packageId, scheduleCrtieriaDTO.getPackageId()));
		}
		if(Objects.nonNull(scheduleCrtieriaDTO.getDiagnosticCenteruserId())) {
			criterions.add(Restrictions.eq(Schedule.FIELD_diagnosticCenterUserId, scheduleCrtieriaDTO.getDiagnosticCenteruserId()));
		}
		criterions.add(Restrictions.between(Schedule.FIELD_scheduleDate, 
				DateUtil.getOnlyDateFromUtilDate(fromDate, utilComponent.getTimeZone()), 
				DateUtil.getOnlyDateFromUtilDate(DateUtil.addAndGetDate(toDate, utilComponent.getTimeZone(), 1),
						 utilComponent.getTimeZone())));
		criterions.add(Restrictions.eq(Schedule.FIELD_active, 'Y'));
		
		return findByCrieria(criterions, buildOrders(), scheduleCrtieriaDTO.getPageNum(), scheduleCrtieriaDTO.getMaxResutls());
		
	}

	private List<Order> buildOrders() {
		List<Order> orders = new ArrayList<>();
		orders.add(Order.asc(Schedule.FIELD_scheduleDate));
		return orders;
	}


	@Override
	public Schedule getByScheduleId(Long scheduleId) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.eq(Schedule.FIELD_scheduleId, scheduleId));
		criterions.add(Restrictions.eq(Schedule.FIELD_active, 'Y'));
		List<Schedule> schedules = findByCrieria(criterions, Order.asc(Schedule.FIELD_scheduleDate));
		return schedules.get(0);
	}



	@Override
	@Transactional(readOnly=true)
	public List<Schedule> findScheduleByBasseScheduleId(Long basseScheduleId) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.eq(Schedule.FIELD_baseScheduleId, basseScheduleId));
		criterions.add(Restrictions.eq(Schedule.FIELD_active, 'Y'));
		return findByCrieria(criterions, Order.asc(Schedule.FIELD_scheduleDate));
	}

	@Override
	@Transactional(readOnly=true)
	public Date findTheMaxDateOfExistingBaseschedule(ScheduleCriteriaDTO scheduleCrtieriaDTO) {
		List<Criterion> criterions = new ArrayList<>();
		if(Objects.isNull(scheduleCrtieriaDTO.getDiagnosticCenterAddressId())) {
			throw new IllegalArgumentException("diagnosticCetnerAddressId can not be null.");
		}
		criterions.add(Restrictions.eq(Schedule.FIELD_diagnosticCenterAddressId, scheduleCrtieriaDTO.getDiagnosticCenterAddressId()));
		if(Objects.nonNull(scheduleCrtieriaDTO.getSubmenuId())) {
			criterions.add(Restrictions.eq(Schedule.FIELD_submenuId, scheduleCrtieriaDTO.getSubmenuId()));
		}
		if(Objects.nonNull(scheduleCrtieriaDTO.getPackageId())) {
			criterions.add(Restrictions.eq(Schedule.FIELD_packageId, scheduleCrtieriaDTO.getPackageId()));
		}
		if(Objects.nonNull(scheduleCrtieriaDTO.getDiagnosticCenteruserId())) {
			criterions.add(Restrictions.eq(Schedule.FIELD_submenuId, scheduleCrtieriaDTO.getDiagnosticCenteruserId()));
		}
		if(Objects.nonNull(scheduleCrtieriaDTO.getBasescheduleId())) {
			criterions.add(Restrictions.eq(Schedule.FIELD_baseScheduleId, scheduleCrtieriaDTO.getBasescheduleId()));
		}
		List<Order> orders = new ArrayList<>();
		orders.add(Order.desc(Schedule.FIELD_scheduleDate));
		
		Response<List<Schedule>> response = findByCrieria(criterions, orders, 1, 1);
		if(CollectionUtils.isEmpty(response.getData())) {
			return null;
		}
		return response.getData().get(0).getScheduleDate();
	}
	
	
	
}