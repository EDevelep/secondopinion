package org.secondopinion.patient.dao.impl.hibernate;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
import org.secondopinion.configurations.UtilComponent;
import org.secondopinion.enums.AppointmentStatusEnum;
import org.secondopinion.patient.dto.Appointment;
import org.secondopinion.patient.dto.AppointmentSearchRequest;
import org.secondopinion.patient.dto.SearchSchedule;
import org.secondopinion.patient.dto.ViewAppointments;
import org.secondopinion.request.Response;
import org.secondopinion.utils.DateUtil;
import org.secondopinion.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

@Component
public class AppointmentDAOHibernateImpl extends BaseAppointmentDAOHibernate {

	@Autowired
	private UtilComponent utilComponent;

	@Override
	@Transactional
	public void save(Appointment appointment) {
		if (Objects.isNull(appointment.getAppointmentId())) {
			appointment.setActive('Y');
		}
		if (Objects.isNull(appointment.getPrescriptionId()) || appointment.getPrescriptionId() <= 0) {
			appointment.setPrescriptionId(-1L);
		}
		super.save(appointment);

	}

	@Override
	@Transactional(readOnly = true)
	public Response<List<Appointment>> getupcomingAppointments(ViewAppointments viewAppointments) {

		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.ge(Appointment.FIELD_appointmentDate,
				DateUtil.addAndGetDate(utilComponent.getTimeZone(), 1)));
		if (viewAppointments.getPatientId() != null) {
			criterions.add(Restrictions.eq(Appointment.FIELD_userId, viewAppointments.getPatientId()));
		}
		criterions.add(Restrictions.and(
				Restrictions.ne(Appointment.FIELD_appointmentStatus, AppointmentStatusEnum.ENTITY_CANCELLED.name()),
				Restrictions.ne(Appointment.FIELD_appointmentStatus, AppointmentStatusEnum.PATIENT_CANCELLED.name())));
		return findByCrieria(criterions, buildOrders(), viewAppointments.getPageNum(),
				viewAppointments.getMaxResults());
	}

	private List<Order> buildOrders() {
		List<Order> orders = new ArrayList<>();
		orders.add(Order.asc(Appointment.FIELD_appointmentDate));
		orders.add(Order.asc(Appointment.FIELD_fromTime));

		return orders;
	}

	@Override
	@Transactional(readOnly = true)
	public Appointment findById(Long appointmentId) {
		Appointment appointment = super.findById(appointmentId);
		/*
		 * List<Patientratings> patientratingsdata =
		 * patientratingsDAO.findByProperty(Patientratings.field, appointmentId);
		 * if(patientratingsdata != null) {
		 * appointment.setPatientratings(patientratingsdata); }
		 */
		return appointment;
	}

	@Override
	@Transactional(readOnly = true)
	public Response<List<Appointment>> searchAppointments(Long userId, SearchSchedule search) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.eq(Appointment.FIELD_userId, userId));
		criterions.add(Restrictions.eq(Appointment.FIELD_appointmentFor, search.getAppointmentFor()));
		criterions.add(Restrictions.eq(Appointment.FIELD_appointmentDate, search.getAppointmentDate()));
		criterions.add(Restrictions.eq(Appointment.FIELD_active, 'Y'));
		return findByCrieria(criterions, buildOrders(), search.getPageNum(), search.getMaxResults());
	}

	@Override
	@Transactional(readOnly = true)
	public Response<List<Appointment>> getByEntityAndPatientId(String appointmentFor, Long referenceEntityId,
			Long patientId, Integer pageNum) {
		List<Criterion> criteria = new ArrayList<>();
		if (!StringUtils.isEmpty(appointmentFor)) {
			criteria.add(Restrictions.eq(Appointment.FIELD_appointmentFor, appointmentFor));
		}
		if (Objects.nonNull(patientId)) {
			criteria.add(Restrictions.eq(Appointment.FIELD_userId, patientId));
		}
		if (Objects.nonNull(referenceEntityId)) {
			criteria.add(Restrictions.eq(Appointment.FIELD_referenceEntityId, referenceEntityId));
		}
		criteria.add(Restrictions.eq(Appointment.FIELD_active, 'Y'));
		return findByCrieria(criteria, buildOrders(), pageNum, null);
	}

	@Override
	@Transactional(readOnly = true)
	public Response<List<Appointment>> previousAppointments(ViewAppointments viewAppointments) {
		List<Criterion> criterions = new ArrayList<>();

		SimpleExpression appointmentDateLessthan = Restrictions.le(Appointment.FIELD_appointmentDate,
				DateUtil.getCurrentDateOnly(utilComponent.getTimeZone()));
		SimpleExpression fromTimeLessthan = Restrictions.lt(Appointment.FIELD_fromTime,
				DateUtil.convertLocalTimeToDate(utilComponent.getCurrentTime()));
		LogicalExpression apDateAndFTLessthan = Restrictions.and(appointmentDateLessthan, fromTimeLessthan);

		criterions.add(Restrictions.or(appointmentDateLessthan, apDateAndFTLessthan));
		if (viewAppointments.getPatientId() != null) {
			criterions.add(Restrictions.eq(Appointment.FIELD_userId, viewAppointments.getPatientId()));
		}
		if (Objects.nonNull(viewAppointments.getReferenceEntityId())) {
			criterions
					.add(Restrictions.eq(Appointment.FIELD_referenceEntityId, viewAppointments.getReferenceEntityId()));
		}
		criterions.add(Restrictions.eq(Appointment.FIELD_active, 'Y'));
		return findByCrieria(criterions, buildOrders(), viewAppointments.getPageNum(),
				viewAppointments.getMaxResults());
	}

	@Override
	@Transactional(readOnly = true)
	public Response<List<Appointment>> currentAppointments(ViewAppointments viewAppointments) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.eq(Appointment.FIELD_appointmentDate,
				DateUtil.getCurrentDateOnly(utilComponent.getTimeZone())));
		criterions.add(Restrictions.between(Appointment.FIELD_fromTime,
				DateUtil.convertLocalTimeToDate(utilComponent.getCurrentTime()),
				DateUtil.convertLocalTimeToDate(DateUtil.getMaxTime())));
		if (viewAppointments.getPatientId() != null) {
			criterions.add(Restrictions.eq(Appointment.FIELD_userId, viewAppointments.getPatientId()));
		}
		criterions.add(Restrictions.and(
				Restrictions.ne(Appointment.FIELD_appointmentStatus, AppointmentStatusEnum.ENTITY_CANCELLED.name()),
				Restrictions.ne(Appointment.FIELD_appointmentStatus, AppointmentStatusEnum.PATIENT_CANCELLED.name())));
		criterions.add(Restrictions.eq(Appointment.FIELD_active, 'Y'));
		return findByCrieria(criterions, buildOrders(), viewAppointments.getPageNum(),
				viewAppointments.getMaxResults());
	}

	@Override
	@Transactional(readOnly = true)
	public Response<List<Appointment>> getAppointmentFor(Long refrenceEntityId, String appoitmentfor, Integer pageNum) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.eq(Appointment.FIELD_referenceEntityId, refrenceEntityId));
		criterions.add(Restrictions.eq(Appointment.FIELD_appointmentFor, appoitmentfor));
		criterions.add(Restrictions.eq(Appointment.FIELD_active, 'Y'));
		return findByCrieria(criterions, buildOrders(), pageNum, null);

	}

	@Override
	@Transactional(readOnly = true)
	public Response<List<Appointment>> getAllAppointmentBySearchCritieria(
			AppointmentSearchRequest appointmentSearchRequest, Long userId) {
		List<Criterion> criterions = new ArrayList<>();

		if (Objects.isNull(userId)) {
			throw new IllegalArgumentException("User/Patient Id can not be null");
		}

		criterions.add(Restrictions.eq(Appointment.FIELD_userId, userId));
		if (Objects.nonNull(appointmentSearchRequest.getAppointmentDate())) {
			criterions.add(
					Restrictions.eq(Appointment.FIELD_appointmentDate, appointmentSearchRequest.getAppointmentDate()));
		}
		if (Objects.nonNull(appointmentSearchRequest.getAmountPaid())) {
			criterions.add(Restrictions.eq(Appointment.FIELD_amountPaid, appointmentSearchRequest.getAmountPaid()));
		}

		if (!StringUtil.isNullOrEmpty(appointmentSearchRequest.getReferenceEntityName())) {
			criterions.add(Restrictions.ilike(Appointment.FIELD_referenceEntityName,
					appointmentSearchRequest.getReferenceEntityName(), MatchMode.ANYWHERE));
		}
		if (Objects.nonNull(appointmentSearchRequest.getReferenceEntityId())) {
			criterions.add(Restrictions.eq(Appointment.FIELD_referenceEntityId,
					appointmentSearchRequest.getReferenceEntityId()));
		}
		if (!StringUtil.isNullOrEmpty(appointmentSearchRequest.getAppointmentFor())) {
			criterions.add(
					Restrictions.eq(Appointment.FIELD_appointmentFor, appointmentSearchRequest.getAppointmentFor()));
		}
		if (!StringUtil.isNullOrEmpty(appointmentSearchRequest.getAppointmentStatus())) {
			criterions.add(Restrictions.eq(Appointment.FIELD_appointmentStatus,
					appointmentSearchRequest.getAppointmentStatus()));
		}

		if (!StringUtil.isNullOrEmpty(appointmentSearchRequest.getAilment())) {
			criterions.add(Restrictions.ilike(Appointment.FIELD_ailment, appointmentSearchRequest.getAilment(),
					MatchMode.ANYWHERE));
		}
		// if both dates are not null
		if (Objects.nonNull(appointmentSearchRequest.getFromDate())
				&& Objects.nonNull(appointmentSearchRequest.getToDate())) {
			criterions.add(Restrictions.between(Appointment.FIELD_appointmentDate,
					DateUtil.getMinDateTimeOfDate(appointmentSearchRequest.getFromDate()),
					DateUtil.getMinDateTimeOfDate(appointmentSearchRequest.getToDate())));
			criterions.add(Restrictions.between(Appointment.FIELD_fromTime, appointmentSearchRequest.getFromDate(),
					appointmentSearchRequest.getToDate()));
		} // if appointmentDate is not null
		else if (Objects.nonNull(appointmentSearchRequest.getFromDate())
				&& Objects.isNull(appointmentSearchRequest.getToDate())) {
			criterions.add(Restrictions.eq(Appointment.FIELD_appointmentDate,
					DateUtil.getMinDateTimeOfDate(appointmentSearchRequest.getFromDate())));
			criterions.add(Restrictions.eq(Appointment.FIELD_fromTime, appointmentSearchRequest.getFromDate()));
		} // if maxAppointmentDate is not null
		else if (Objects.isNull(appointmentSearchRequest.getFromDate())
				&& Objects.nonNull(appointmentSearchRequest.getToDate())) {
			criterions.add(Restrictions.le(Appointment.FIELD_appointmentDate,
					DateUtil.getMinDateTimeOfDate(appointmentSearchRequest.getToDate())));
			criterions.add(Restrictions.le(Appointment.FIELD_fromTime, appointmentSearchRequest.getToDate()));
		}
		criterions.add(Restrictions.eq(Appointment.FIELD_active, 'Y'));
		return findByCrieria(criterions, buildOrders(), appointmentSearchRequest.getPageNum(),
				appointmentSearchRequest.getMaxResults());
	}

	@Override
	@Transactional(readOnly = true)
	public Response<List<Appointment>> retrieveCancelledAppoitments(ViewAppointments viewAppointments) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.eq(Appointment.FIELD_userId, viewAppointments.getPatientId()));
		criterions.add(Restrictions.or(
				Restrictions.eq(Appointment.FIELD_appointmentStatus, AppointmentStatusEnum.ENTITY_CANCELLED.name()),
				Restrictions.eq(Appointment.FIELD_appointmentStatus, AppointmentStatusEnum.PATIENT_CANCELLED.name())));
		criterions.add(Restrictions.eq(Appointment.FIELD_active, 'Y'));
		return findByCrieria(criterions, buildOrders(), viewAppointments.getPageNum(),
				viewAppointments.getMaxResults());
	}

	@Override
	@Transactional(readOnly = true)
	public Response<List<Appointment>> retrieveRescheduledAppoitments(ViewAppointments viewAppointments) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.eq(Appointment.FIELD_userId, viewAppointments.getPatientId()));
		criterions.add(
				Restrictions.eq(Appointment.FIELD_appointmentStatus, AppointmentStatusEnum.ENTITY_RESCHEDULED.name()));
		criterions.add(Restrictions.eq(Appointment.FIELD_active, 'Y'));
		return findByCrieria(criterions, buildOrders(), viewAppointments.getPageNum(),
				viewAppointments.getMaxResults());
	}

	@Override
	@Transactional(readOnly = true)
	public List<Appointment> getByPatientAndRoomSID(Long userId, String roomSID) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.eq(Appointment.FIELD_userId, userId));
		criterions.add(Restrictions.eq(Appointment.FIELD_chatRoomSID, roomSID));
		criterions.add(Restrictions.eq(Appointment.FIELD_active, 'Y'));
		return findByCrieria(criterions);
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
	public Appointment findAppointmentIdById(Long appointmentId) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.eq(Appointment.FIELD_appointmentId, appointmentId));
		criterions.add(Restrictions.eq(Appointment.FIELD_active, 'Y'));
		List<Appointment> appointments = findByCrieria(criterions);
		if (CollectionUtils.isEmpty(appointments))
			return null;
		return appointments.get(0);
	}

	@Override
	@Transactional(readOnly = true)
	public Appointment findAppointmentByReferenceAppointmentId(Long referenceAppointmentId, String appointmentFor) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.eq(Appointment.FIELD_referenceAppointmentId, referenceAppointmentId));
		criterions.add(Restrictions.eq(Appointment.FIELD_appointmentFor, appointmentFor));
		criterions.add(Restrictions.eq(Appointment.FIELD_active, 'Y'));
		List<Appointment> appointments = findByCrieria(criterions);
		if (CollectionUtils.isEmpty(appointments))
			return null;
		return appointments.get(0);
	}

	@Override
	@Transactional
	public Response<List<Appointment>> notAttendAppointments(ViewAppointments viewAppointments) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.eq(Appointment.FIELD_appointmentDate,
				DateUtil.getCurrentDateOnly(utilComponent.getTimeZone())));
		criterions.add(Restrictions.between(Appointment.FIELD_fromTime,
				DateUtil.convertLocalTimeToDate(utilComponent.getCurrentTime()),
				DateUtil.convertLocalTimeToDate(DateUtil.getMaxTime())));
		if (viewAppointments.getPatientId() != null) {
			criterions.add(Restrictions.eq(Appointment.FIELD_userId, viewAppointments.getPatientId()));
		}
		criterions.add(Restrictions.and(
				Restrictions.ne(Appointment.FIELD_appointmentStatus, AppointmentStatusEnum.ENTITY_CANCELLED.name()),
				Restrictions.ne(Appointment.FIELD_appointmentStatus, AppointmentStatusEnum.PATIENT_CANCELLED.name())));
		criterions.add(Restrictions.eq(Appointment.FIELD_active, 'Y'));
		return findByCrieria(criterions, buildOrders(), viewAppointments.getPageNum(),
				viewAppointments.getMaxResults());
	}

	@Override
	@Transactional
	public List<Appointment> findTotelByReferenceAppointmentId(Long refrenceEntityId) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.eq(Appointment.FIELD_referenceEntityId, refrenceEntityId));
		criterions.add(Restrictions.eq(Appointment.FIELD_active, 'Y'));
		return findByCrieria(criterions);
	}

	@Override
	@Transactional
	public List<Appointment> getAppointmentForCaretaker(Long careTakerId) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.eq(Appointment.FIELD_referenceEntityId, careTakerId));
		criterions.add(Restrictions.eq(Appointment.FIELD_appointmentFor, "CARETAKER"));
		criterions.add(Restrictions.eq(Appointment.FIELD_active, 'Y'));
		List<Appointment> appointments = findByCrieria(criterions);
		if (appointments != null & appointments.size() > 0) {
			return appointments;
		}
		return null;
	}

	@Override
	@Transactional
	public List<Appointment> currentAppointmentsForCareTaker(List<Long> userIds) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.eq(Appointment.FIELD_appointmentDate,
				DateUtil.getCurrentDateOnly(utilComponent.getTimeZone())));
		criterions.add(Restrictions.between(Appointment.FIELD_fromTime,
				DateUtil.convertLocalTimeToDate(utilComponent.getCurrentTime()),
				DateUtil.convertLocalTimeToDate(DateUtil.getMaxTime())));

		if (userIds != null) {
			criterions.add(Restrictions.in(Appointment.FIELD_userId, userIds));
		}
		criterions.add(Restrictions.eq(Appointment.FIELD_appointmentFor, "CARETAKER"));
		criterions.add(Restrictions.eq(Appointment.FIELD_active, 'Y'));
		return findByCrieria(criterions);
	}

	@Override
	@Transactional
	public List<Appointment> upcomeingAppointmentsForCareTaker(List<Long> userIds) {
		List<Criterion> criterions = new ArrayList<>();

		criterions.add(Restrictions.ge(Appointment.FIELD_appointmentDate,
				DateUtil.addAndGetDate(utilComponent.getTimeZone(), 1)));
		if (userIds != null) {
			criterions.add(Restrictions.in(Appointment.FIELD_userId, userIds));
		}
		criterions.add(Restrictions.eq(Appointment.FIELD_appointmentFor, "CARETAKER"));
		criterions.add(Restrictions.eq(Appointment.FIELD_active, 'Y'));
		return findByCrieria(criterions);
	}

	
	@Override
	@Transactional
	public List<Appointment> getFollowupupdate(Date currentDateOnly, LocalTime currentLocalTime) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.eq(Appointment.FIELD_followupDate, currentDateOnly));
		criterions.add(Restrictions.ge(Appointment.FIELD_fromTime, DateUtil.convertLocalTimeToDate(currentLocalTime)));
		criterions.add(
				Restrictions.eq(Appointment.FIELD_appointmentStatus, AppointmentStatusEnum.ENTITY_ACCEPTED.name()));
		criterions.add(Restrictions.eq(Appointment.FIELD_active, 'Y'));
		return findByCrieria(criterions);
	}

}