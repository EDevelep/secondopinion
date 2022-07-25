package org.secondopinion.diagnosticcenter.dao.impl.hibernate;

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
import org.secondopinion.configurations.UtilComponent;
import org.secondopinion.diagnosticcenter.dto.Appointment;
import org.secondopinion.diagnosticcenter.dto.AppointmentSearchRequest;
import org.secondopinion.enums.AppointmentStatusEnum;
import org.secondopinion.request.Response;
import org.secondopinion.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

@Repository
public class AppointmentDAOHibernateImpl extends BaseAppointmentDAOHibernate {

	@Autowired
	private UtilComponent utilComponent;

	@Override
	public Response<List<Appointment>> getAllAppointmentBySearchCritieria(AppointmentSearchRequest appointmentDTO) {
		List<Criterion> criterions = new ArrayList<>();

		criterions.add(Restrictions.eq(Appointment.FIELD_diagnosticCenterAddressId,
				appointmentDTO.getDiagnosticCenterAddressId()));
		criterions.add(Restrictions.eq(Appointment.FIELD_active, 'Y'));

		if (Objects.nonNull(appointmentDTO.getAppointmentDate())) {
			criterions.add(Restrictions.eq(Appointment.FIELD_appointmentDate, appointmentDTO.getAppointmentDate()));
		}
		if (Objects.nonNull(appointmentDTO.getPatientId())) {
			criterions.add(Restrictions.eq(Appointment.FIELD_patientId, appointmentDTO.getPatientId()));
		}
		if (Objects.nonNull(appointmentDTO.getPatientName())) {
			criterions.add(Restrictions.ilike(Appointment.FIELD_patientName, appointmentDTO.getPatientName(),
					MatchMode.ANYWHERE));
		}
		if (Objects.nonNull(appointmentDTO.getSchedulehoursId())) {
			criterions.add(Restrictions.eq(Appointment.FIELD_schedulehoursId, appointmentDTO.getSchedulehoursId()));
		}
		if (Objects.nonNull(appointmentDTO.getAppointmentStatus())) {
			criterions.add(Restrictions.eq(Appointment.FIELD_appointmentStatus, appointmentDTO.getAppointmentStatus()));
		}

		// if both dates are not null
		if (Objects.nonNull(appointmentDTO.getFromDate()) && Objects.nonNull(appointmentDTO.getToDate())) {
			criterions.add(Restrictions.between(Appointment.FIELD_appointmentDate,
					DateUtil.getMinDateTimeOfDate(appointmentDTO.getFromDate()),
					DateUtil.getMinDateTimeOfDate(appointmentDTO.getToDate())));
			criterions.add(Restrictions.between(Appointment.FIELD_fromTime, appointmentDTO.getFromDate(),
					appointmentDTO.getToDate()));
		} // if appointmentDate is not null
		else if (Objects.nonNull(appointmentDTO.getFromDate()) && Objects.isNull(appointmentDTO.getToDate())) {
			criterions.add(Restrictions.eq(Appointment.FIELD_appointmentDate,
					DateUtil.getMinDateTimeOfDate(appointmentDTO.getFromDate())));
			criterions.add(Restrictions.eq(Appointment.FIELD_fromTime, appointmentDTO.getFromDate()));
		} // if maxAppointmentDate is not null
		else if (Objects.isNull(appointmentDTO.getFromDate()) && Objects.nonNull(appointmentDTO.getToDate())) {
			criterions.add(Restrictions.le(Appointment.FIELD_appointmentDate,
					DateUtil.getMinDateTimeOfDate(appointmentDTO.getToDate())));
			criterions.add(Restrictions.le(Appointment.FIELD_fromTime, appointmentDTO.getToDate()));
		}

		return findByCrieria(criterions, buildOrders(), appointmentDTO.getPageNum(), appointmentDTO.getMaxResults());

	}

	@Override
	@Transactional(readOnly = true)
	public List<Appointment> getByAppointmentDateAndFromTime(Date date, LocalTime LocalTime) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.eq(Appointment.FIELD_appointmentDate, date));
		criterions.add(Restrictions.ge(Appointment.FIELD_fromTime, DateUtil.convertLocalTimeToDate(LocalTime)));
		criterions.add(
				Restrictions.eq(Appointment.FIELD_appointmentStatus, AppointmentStatusEnum.ENTITY_ACCEPTED.name()));
		criterions.add(Restrictions.eq(Appointment.FIELD_active, 'Y'));
		return findByCrieria(criterions);
	}

	@Override
	@Transactional(readOnly = true)
	public Response<List<Appointment>> currentAppointments(Long diagnosticCenterAddressId, Integer pageNum,
			Integer maxResult) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.eq(Appointment.FIELD_appointmentDate,
				DateUtil.getCurrentDateOnly(utilComponent.getTimeZone())));
		criterions.add(Restrictions.between(Appointment.FIELD_fromTime,
				DateUtil.convertLocalTimeToDate(utilComponent.getCurrentTime()),
				DateUtil.convertLocalTimeToDate(DateUtil.getMaxTime())));
		criterions.add(Restrictions.eq(Appointment.FIELD_active, 'Y'));
		if (diagnosticCenterAddressId != null) {
			criterions.add(Restrictions.eq(Appointment.FIELD_diagnosticCenterAddressId, diagnosticCenterAddressId));
		}
		criterions.add(Restrictions.and(
				Restrictions.ne(Appointment.FIELD_appointmentStatus, AppointmentStatusEnum.ENTITY_CANCELLED.name()),
				Restrictions.ne(Appointment.FIELD_appointmentStatus, AppointmentStatusEnum.PATIENT_CANCELLED.name())));
		return findByCrieria(criterions, buildOrders(), pageNum, maxResult);
	}

	@Override
	@Transactional(readOnly = true)
	public Response<List<Appointment>> previousAppointments(Long diagnosticCenterAddressId, Long patientId,
			Integer pageNum, Integer maxResult) {
		List<Criterion> criterions = new ArrayList<>();

		SimpleExpression appointmentDateLessthan = Restrictions.lt(Appointment.FIELD_appointmentDate,
				DateUtil.getCurrentDateOnly(utilComponent.getTimeZone()));
		SimpleExpression appointmentDateEquals = Restrictions.eq(Appointment.FIELD_appointmentDate,
				DateUtil.getCurrentDateOnly(utilComponent.getTimeZone()));
		SimpleExpression fromTimeLessthan = Restrictions.lt(Appointment.FIELD_fromTime,
				DateUtil.convertLocalTimeToDate(utilComponent.getCurrentTime()));
		LogicalExpression apDateAndFTLessthan = Restrictions.and(appointmentDateEquals, fromTimeLessthan);

		criterions.add(Restrictions.or(appointmentDateLessthan, apDateAndFTLessthan));

		criterions.add(Restrictions.eq(Appointment.FIELD_active, 'Y'));
		if (diagnosticCenterAddressId != null) {
			criterions.add(Restrictions.eq(Appointment.FIELD_diagnosticCenterAddressId, diagnosticCenterAddressId));
		}
		if (patientId != null) {
			criterions.add(Restrictions.eq(Appointment.FIELD_patientId, patientId));
		}

		return findByCrieria(criterions, buildOrders(), pageNum, maxResult);
	}

	@Override
	@Transactional(readOnly = true)
	public Response<List<Appointment>> upcomingAppointments(Long diagnosticCenterAddressId, Integer pageNum,
			Integer maxResult) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.ge(Appointment.FIELD_appointmentDate,
				DateUtil.addAndGetDate(utilComponent.getTimeZone(), 1)));
		criterions.add(Restrictions.eq(Appointment.FIELD_active, 'Y'));
		if (diagnosticCenterAddressId != null) {
			criterions.add(Restrictions.eq(Appointment.FIELD_diagnosticCenterAddressId, diagnosticCenterAddressId));
		}
		criterions.add(Restrictions.and(
				Restrictions.ne(Appointment.FIELD_appointmentStatus, AppointmentStatusEnum.ENTITY_CANCELLED.name()),
				Restrictions.ne(Appointment.FIELD_appointmentStatus, AppointmentStatusEnum.PATIENT_CANCELLED.name())));
		return findByCrieria(criterions, buildOrders(), pageNum, maxResult);
	}

	@Override
	@Transactional(readOnly = true)
	public Response<List<Appointment>> retrieveCancelledAppoitments(Long diagnosticCenterAddressId, Integer pageNum,
			Integer maxResult) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.eq(Appointment.FIELD_diagnosticCenterAddressId, diagnosticCenterAddressId));
		criterions.add(Restrictions.eq(Appointment.FIELD_active, 'Y'));
		criterions.add(Restrictions.or(
				Restrictions.eq(Appointment.FIELD_appointmentStatus, AppointmentStatusEnum.ENTITY_CANCELLED.name()),
				Restrictions.eq(Appointment.FIELD_appointmentStatus, AppointmentStatusEnum.PATIENT_CANCELLED.name())));

		return findByCrieria(criterions, buildOrders(), pageNum, maxResult);
	}

	@Override
	@Transactional(readOnly = true)
	public Response<List<Appointment>> retrieveRescheduledAppoitments(Long diagnosticCenterAddressId, Integer pageNum,
			Integer maxResult) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.eq(Appointment.FIELD_diagnosticCenterAddressId, diagnosticCenterAddressId));
		criterions.add(
				Restrictions.eq(Appointment.FIELD_appointmentStatus, AppointmentStatusEnum.ENTITY_RESCHEDULED.name()));
		criterions.add(Restrictions.eq(Appointment.FIELD_active, 'Y'));
		return findByCrieria(criterions, buildOrders(), pageNum, maxResult);
	}

	private List<Order> buildOrders() {
		List<Order> orders = new ArrayList<>();
		orders.add(Order.asc(Appointment.FIELD_appointmentDate));
		orders.add(Order.asc(Appointment.FIELD_fromTime));

		return orders;
	}

	@Override
	@Transactional(readOnly = true)
	public Long getTotalPatientsOfDiagnosticCenter(Long diagnosticCenterAddressid, String appointmentStatus) {
		String aliasName = "totalPatients";
		String sqlQuery = "select COUNT(distinct " + Appointment.FIELD_diagnosticCenterAddressId + ") " + aliasName
				+ " from appointment" + " where " + Appointment.FIELD_diagnosticCenterAddressId + " = "
				+ diagnosticCenterAddressid + " and " + Appointment.FIELD_appointmentStatus + " = '" + appointmentStatus
				+ "'";
		Long totalPatients = 0L;
		Map<String, Type> scalarMapping = new HashMap<>();
		scalarMapping.put(aliasName, StandardBasicTypes.LONG);
		List<Map<String, Object>> resultList = findBySqlQueryMapTransformer(sqlQuery, null, scalarMapping);
		if (!CollectionUtils.isEmpty(resultList)) {
			Map<String, Object> resultMap = resultList.get(0);
			totalPatients = (Long) resultMap.get(aliasName);

		}
		return totalPatients;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Appointment> findappointmentByScheduleHoursId(Long scheduleHoursId) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.eq(Appointment.FIELD_schedulehoursId, scheduleHoursId));
		criterions.add(Restrictions.eq(Appointment.FIELD_active, 'Y'));
		return findByCrieria(criterions);
	}

	@Override
	@Transactional(readOnly = true)
	public Appointment getAppointmentbypatientUserId(Long patientUserId,Long diagnosticCenterAddressId) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.eq(Appointment.FIELD_patientId, patientUserId));
		criterions.add(Restrictions.eq(Appointment.FIELD_diagnosticCenterAddressId, diagnosticCenterAddressId));
		criterions.add(Restrictions.eq(Appointment.FIELD_active, 'Y'));
		List<Appointment> appointments = findByCrieria(criterions);
		return appointments.get(0);
	}

}