package org.secondopinion.caretaker.dao.impl;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.Type;
import org.secondopinion.caretaker.dto.Appointment;
import org.secondopinion.caretaker.dto.AppointmentSearchRequest;
import org.secondopinion.configurations.UtilComponent;

import org.secondopinion.enums.AppointmentStatusEnum;
import org.secondopinion.request.Response;
import org.secondopinion.utils.DateUtil;
import org.secondopinioncaretaker.domain.BaseAppointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

@Repository
public class AppointmentDAOHibernateImpl extends BaseAppointmentDAOHibernate {

	@Autowired
	private UtilComponent utilComponent;
	
	private static final String UPDATE_APPOINNTMENT = "update Appointment set appointmentStatus = :appointmentStatus "
			+ "where appointmentId != :appointmentId and schedulehoursId = :schedulehoursId";

	@Override
	@Transactional(readOnly=true)
	public void rejectOtherAppointments(Long appointmentId, Long schedulehoursId) {
		Map<String, Object> params = new HashMap<>();

		params.put("appointmentStatus", AppointmentStatusEnum.ENTITY_CANCELLED.name());
		params.put("appointmentId", appointmentId);
		params.put("schedulehoursId", schedulehoursId);

		executeQuery(UPDATE_APPOINNTMENT, params);

	}
	
	@Override
	@Transactional
	public void save(Appointment appointment) {
		if(Objects.isNull(appointment.getAppointmentId())) {
			appointment.setActive('Y');
		}
		super.save(appointment);
	}

	@Override
	@Transactional
	public Response<List<Appointment>> getAllAppointmentBySearchCritieria(AppointmentSearchRequest appointmentSearchRequest) {
		List<Criterion> criterions = new ArrayList<>();
		
		criterions.add(Restrictions.eq(BaseAppointment.FIELD_caretakerId, appointmentSearchRequest.getCaretakerId()));
		criterions.add(Restrictions.eq(BaseAppointment.FIELD_active, 'Y'));
		
		if (Objects.nonNull(appointmentSearchRequest.getAppointmentDate())) {
			criterions.add(Restrictions.eq(BaseAppointment.FIELD_appointmentDate, appointmentSearchRequest.getAppointmentDate()));
		}
		if (Objects.nonNull(appointmentSearchRequest.getPatientId())) {
			criterions.add(Restrictions.eq(BaseAppointment.FIELD_userId, appointmentSearchRequest.getPatientId()));
		}
		if (Objects.nonNull(appointmentSearchRequest.getPatientName())) {
			criterions.add(Restrictions.ilike(BaseAppointment.FIELD_patientName, appointmentSearchRequest.getPatientName(), MatchMode.ANYWHERE));
		}
		if (Objects.nonNull(appointmentSearchRequest.getSchedulehoursId())) {
			criterions.add(Restrictions.eq(BaseAppointment.FIELD_schedulehoursId, appointmentSearchRequest.getSchedulehoursId()));
		}
		if (Objects.nonNull(appointmentSearchRequest.getAppointmentStatus())) 
		{
			criterions.add(Restrictions.eq(BaseAppointment.FIELD_appointmentStatus,	appointmentSearchRequest.getAppointmentStatus()));
		}

		// if both dates are not null
		if (Objects.nonNull(appointmentSearchRequest.getFromDate())
				&& Objects.nonNull(appointmentSearchRequest.getToDate())) {
			criterions.add(Restrictions.between(BaseAppointment.FIELD_appointmentDate,
					DateUtil.getMinDateTimeOfDate(appointmentSearchRequest.getFromDate()),
					DateUtil.getMinDateTimeOfDate(appointmentSearchRequest.getToDate())));
			criterions.add(Restrictions.between(BaseAppointment.FIELD_fromTime, 
					appointmentSearchRequest.getFromDate(),
					appointmentSearchRequest.getToDate()));
		} // if appointmentDate is not null
		else if (Objects.nonNull(appointmentSearchRequest.getFromDate())
				&& Objects.isNull(appointmentSearchRequest.getToDate())) {
			criterions.add(Restrictions.eq(BaseAppointment.FIELD_appointmentDate, 
							DateUtil.getMinDateTimeOfDate(appointmentSearchRequest.getFromDate())));
			criterions.add(Restrictions.eq(BaseAppointment.FIELD_fromTime, 
					appointmentSearchRequest.getFromDate()));
		} // if maxAppointmentDate is not null
		else if (Objects.isNull(appointmentSearchRequest.getFromDate())
				&& Objects.nonNull(appointmentSearchRequest.getToDate())) {
			criterions.add(Restrictions.le(BaseAppointment.FIELD_appointmentDate,
					DateUtil.getMinDateTimeOfDate(appointmentSearchRequest.getToDate())));
			criterions.add(Restrictions.le(BaseAppointment.FIELD_fromTime, 
					appointmentSearchRequest.getToDate()));
		}
		
		return findByCrieria(criterions, buildOrders(), appointmentSearchRequest.getPageNum(), appointmentSearchRequest.getMaxResults());
		

	}

	private List<Order> buildOrders() {
		List<Order> orders = new ArrayList<>();
		orders.add(Order.asc(BaseAppointment.FIELD_appointmentDate));
		orders.add(Order.asc(BaseAppointment.FIELD_fromTime));
		
		return orders;
	}
	
	@Override
	@Transactional(readOnly=true)
	public Response<List<Appointment>> previousAppointments(Long caretakerId,Long patientId,Integer pageNum,Integer maxResult) {
		List<Criterion> criterions = new ArrayList<>();
		
		SimpleExpression appointmentDateLessthan = Restrictions.lt(BaseAppointment.FIELD_appointmentDate, DateUtil.getCurrentDateOnly(utilComponent.getTimeZone()));
		SimpleExpression appointmentDateEquals = Restrictions.eq(BaseAppointment.FIELD_appointmentDate, DateUtil.getCurrentDateOnly(utilComponent.getTimeZone()));
		SimpleExpression fromTimeLessthan = Restrictions.lt(BaseAppointment.FIELD_fromTime, DateUtil.convertLocalTimeToDate(utilComponent.getCurrentTime()));
		LogicalExpression apDateAndFTLessthan = Restrictions.and(appointmentDateEquals, fromTimeLessthan);
		
		criterions.add(Restrictions.or(appointmentDateLessthan, apDateAndFTLessthan));
		
		criterions.add(Restrictions.eq(BaseAppointment.FIELD_active, 'Y'));
		if(caretakerId != null) {
			criterions.add(Restrictions.eq(BaseAppointment.FIELD_caretakerId, caretakerId));
		}
		if(patientId != null) {
			criterions.add(Restrictions.eq(BaseAppointment.FIELD_userId, patientId));
		}
		
		return findByCrieria(criterions, buildOrders(), pageNum, maxResult);
	}

	@Override
	@Transactional(readOnly=true)
	public Response<List<Appointment>> currentAppointments(Long caretakerId,Integer pageNum,Integer maxResult) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.eq(BaseAppointment.FIELD_appointmentDate, DateUtil.getCurrentDateOnly(utilComponent.getTimeZone())));
		criterions.add(Restrictions.between(BaseAppointment.FIELD_fromTime, 
				DateUtil.convertLocalTimeToDate(utilComponent.getCurrentTime()), 
				DateUtil.convertLocalTimeToDate(DateUtil.getMaxTime())));
		criterions.add(Restrictions.eq(BaseAppointment.FIELD_active, 'Y'));
		if(caretakerId != null) {
			criterions.add(Restrictions.eq(BaseAppointment.FIELD_caretakerId, caretakerId));
		}
		criterions.add(Restrictions.and(
				Restrictions.ne(BaseAppointment.FIELD_appointmentStatus, AppointmentStatusEnum.ENTITY_CANCELLED.name()),
				Restrictions.ne(BaseAppointment.FIELD_appointmentStatus, AppointmentStatusEnum.PATIENT_CANCELLED.name())));
		return findByCrieria(criterions, buildOrders(), pageNum, maxResult);
	}

	@Override
	@Transactional(readOnly=true)
	public Response<List<Appointment>> upcomingAppointments(Long caretakerId,Integer pageNum,Integer maxResult) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.ge(BaseAppointment.FIELD_appointmentDate, DateUtil.addAndGetDate(utilComponent.getTimeZone(), 1)));
		criterions.add(Restrictions.eq(BaseAppointment.FIELD_active, 'Y'));
		if(caretakerId != null) {
			criterions.add(Restrictions.eq(BaseAppointment.FIELD_caretakerId, caretakerId));
		}
		criterions.add(Restrictions.and(
				Restrictions.ne(BaseAppointment.FIELD_appointmentStatus, AppointmentStatusEnum.ENTITY_CANCELLED.name()),
				Restrictions.ne(BaseAppointment.FIELD_appointmentStatus, AppointmentStatusEnum.PATIENT_CANCELLED.name())));
		return findByCrieria(criterions, buildOrders(), pageNum, maxResult);
	}

	@Override
	@Transactional(readOnly = true)
	public Response<List<Appointment>> getBySchedulehours(List<Long> schedulehoursId) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.in(BaseAppointment.FIELD_schedulehoursId, schedulehoursId));
		criterions.add(Restrictions.eq(BaseAppointment.FIELD_appointmentStatus, AppointmentStatusEnum.ENTITY_ACCEPTED.name()));
		criterions.add(Restrictions.eq(BaseAppointment.FIELD_active, 'Y'));
		return findByCrieria(criterions, buildOrders(), null, null);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Appointment> getByAppointmentDateAndFromTime(Date date, LocalTime localTime) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.eq(BaseAppointment.FIELD_appointmentDate, date));
		criterions.add(Restrictions.ge(BaseAppointment.FIELD_fromTime, DateUtil.convertLocalTimeToDate(localTime)));
		criterions.add(Restrictions.eq(BaseAppointment.FIELD_appointmentStatus, AppointmentStatusEnum.ENTITY_ACCEPTED.name()));
		criterions.add(Restrictions.eq(BaseAppointment.FIELD_active, 'Y'));
		return findByCrieria(criterions);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Response<List<Appointment>> retrieveCancelledAppoitments(Long caretakerId,Integer pageNum,Integer maxResult) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.eq(BaseAppointment.FIELD_caretakerId,caretakerId));
		criterions.add(Restrictions.eq(BaseAppointment.FIELD_active, 'Y'));
		criterions.add(Restrictions.or(
				Restrictions.eq(BaseAppointment.FIELD_appointmentStatus, AppointmentStatusEnum.ENTITY_CANCELLED.name()),
				Restrictions.eq(BaseAppointment.FIELD_appointmentStatus, AppointmentStatusEnum.PATIENT_CANCELLED.name())));
		
		return findByCrieria(criterions, buildOrders(), pageNum, maxResult);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Response<List<Appointment>> retrieveRescheduledAppoitments(Long caretakerId,Integer pageNum,Integer maxResult) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.eq(BaseAppointment.FIELD_caretakerId, caretakerId));
		criterions.add(Restrictions.eq(BaseAppointment.FIELD_appointmentStatus, AppointmentStatusEnum.ENTITY_RESCHEDULED.name()));
		criterions.add(Restrictions.eq(BaseAppointment.FIELD_active, 'Y'));
		return findByCrieria(criterions, buildOrders(), pageNum, maxResult);
	}

	@Override
	@Transactional(readOnly = true)
	public Response<List<Appointment>> getAppointmentsByCaretakerId(Long caretakerId) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.eq(BaseAppointment.FIELD_caretakerId, caretakerId));
		criterions.add(Restrictions.eq(BaseAppointment.FIELD_active, 'Y'));
		return findByCrieria(criterions, buildOrders(), null, null);
	}

	@Override
	@Transactional(readOnly=true)
	public Long getTotalPatientsOfDoctor(Long caretakerId, String appointmentStatus) {
		String aliasName = "totalPatients";
		String sqlQuery = "select COUNT(distinct " + BaseAppointment.FIELD_userId + ") " + aliasName + " from appointment"
				+ " where " 
				+ BaseAppointment.FIELD_caretakerId + " = " + caretakerId 
				+ " and " 
				+ BaseAppointment.FIELD_appointmentStatus + " = '"+ appointmentStatus + "'";
		Long totalPatients = 0L;
		Map<String, Type> scalarMapping = new HashMap<>();
		scalarMapping.put(aliasName, StandardBasicTypes.LONG);
		List<Map<String, Object>> resultList = findBySqlQueryMapTransformer(sqlQuery, null, scalarMapping);
		if(!CollectionUtils.isEmpty(resultList)) {
			Map<String, Object> resultMap = resultList.get(0);
			totalPatients = (Long) resultMap.get(aliasName);
			
		}
		return totalPatients;
	}

	@Override
	@Transactional(readOnly = true)
	public Response<List<Appointment>> previousAppointments(Long patientId, Long caretakerId) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.le(BaseAppointment.FIELD_appointmentDate, DateUtil.getCurrentDateOnly(utilComponent.getTimeZone())));
		criterions.add(Restrictions.le(BaseAppointment.FIELD_fromTime,DateUtil.convertLocalTimeToDate(utilComponent.getCurrentTime())));
		criterions.add(Restrictions.eq(BaseAppointment.FIELD_active, 'Y'));
		if(caretakerId != null) {
			criterions.add(Restrictions.eq(BaseAppointment.FIELD_caretakerId, caretakerId));
		}
		if(patientId != null) {
			criterions.add(Restrictions.eq(BaseAppointment.FIELD_userId, patientId));
		}
		
		return findByCrieria(criterions, buildOrders(), null, null);
	}

	@Override
	@Transactional(readOnly = true)
	public Appointment findappointmentDetailsById(Long appointmentId) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.eq(BaseAppointment.FIELD_appointmentId, appointmentId));
		criterions.add(Restrictions.eq(BaseAppointment.FIELD_active, 'Y'));
		List<Appointment> appointments=findByCrieria(criterions);
		if(CollectionUtils.isEmpty(appointments)) return null;
		return appointments.get(0);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Appointment> findappointmentByScheduleHoursId( Long scheduleHoursId) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.eq(BaseAppointment.FIELD_schedulehoursId, scheduleHoursId));
		criterions.add(Restrictions.eq(BaseAppointment.FIELD_active, 'Y'));
		return findByCrieria(criterions);
	}

}