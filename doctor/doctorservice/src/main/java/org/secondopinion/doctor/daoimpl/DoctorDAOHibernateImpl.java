package org.secondopinion.doctor.daoimpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.Type;
import org.secondopinion.common.dto.RatingsDTO;
import org.secondopinion.doctor.dao.AppointmentDAO;
import org.secondopinion.doctor.dao.FeedetailsDAO;
import org.secondopinion.doctor.dao.InvoiceDAO;
import org.secondopinion.doctor.dao.PersonaldetailDAO;
import org.secondopinion.doctor.domain.BaseCertification;
import org.secondopinion.doctor.domain.BaseDoctor;
import org.secondopinion.doctor.domain.BaseDoctorAddress;
import org.secondopinion.doctor.domain.BaseDoctorratings;
import org.secondopinion.doctor.domain.BaseFeedetails;
import org.secondopinion.doctor.domain.BaseRegistration;
import org.secondopinion.doctor.domain.BaseSpecialization;
import org.secondopinion.doctor.dto.Doctor;
import org.secondopinion.doctor.dto.DoctorSearchRequest;
import org.secondopinion.doctor.dto.Feedetails;
import org.secondopinion.doctor.dto.Personaldetail;
import org.secondopinion.enums.AppointmentStatusEnum;
import org.secondopinion.enums.InvoiceStatusEnum;

import org.secondopinion.request.Response;
import org.secondopinion.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

@Repository
public class DoctorDAOHibernateImpl extends BaseDoctorDAOHibernate {

	private static final Logger LOG = LoggerFactory.getLogger(DoctorDAOHibernateImpl.class);

	private static final String UPDATE_LAST_LOGIN_USER_SQL = "update Doctor set " + BaseDoctor.FIELD_lastLogin + "=:"
			+ BaseDoctor.FIELD_lastLogin + ",  " + BaseDoctor.FIELD_retry + "=:" + BaseDoctor.FIELD_retry + ",  "
			+ BaseDoctor.FIELD_locked + "=:" + BaseDoctor.FIELD_locked + " where " + BaseDoctor.FIELD_doctorId + "= :"
			+ BaseDoctor.FIELD_doctorId;
	private static final String UPDATE_RETRY_USER_SQL = "update Doctor set " + BaseDoctor.FIELD_retry + "=:"
			+ BaseDoctor.FIELD_retry + ",  " + BaseDoctor.FIELD_locked + "=:" + BaseDoctor.FIELD_locked + " where "
			+ BaseDoctor.FIELD_doctorId + "= :" + BaseDoctor.FIELD_doctorId;
	private static final String FETCH_EMAIL_ID_QUERY = "select " + BaseDoctor.FIELD_emailId + " from doctor where "
			+ BaseDoctor.FIELD_doctorId + " =  :" + BaseDoctor.FIELD_doctorId;
	@Autowired
	private PersonaldetailDAO personaldetailDAO;

	@Autowired
	private AppointmentDAO appointmentDAO;

	@Autowired
	private FeedetailsDAO feedetailsDAO;

	@Autowired
	private InvoiceDAO invoiceDAO;

	@Override
	@Transactional
	public void updateLastLoginTime(Long doctorId) {
		Map<String, Object> params = new HashMap<>();

		params.put(BaseDoctor.FIELD_lastLogin, new Date());
		params.put(BaseDoctor.FIELD_doctorId, doctorId);
		params.put(BaseDoctor.FIELD_locked, 'N');
		params.put(BaseDoctor.FIELD_retry, 0);
		executeQuery(UPDATE_LAST_LOGIN_USER_SQL, params);

	}

	@Override
	@Transactional
	public void updateRetryCountIfLoginFailed(Long doctorId, Integer retry) {
		Map<String, Object> params = new HashMap<>();

		params.put(BaseDoctor.FIELD_doctorId, doctorId);
		params.put(BaseDoctor.FIELD_locked, 'N');

		retry = retry == null ? 1 : retry + 1;
		if (retry >= 4) {
			params.put(BaseDoctor.FIELD_locked, 'Y');
		}
		params.put(BaseDoctor.FIELD_retry, retry);

		executeQuery(UPDATE_RETRY_USER_SQL, params);

	}

	@Override
	@Transactional(readOnly = true)
	public boolean checkIfEmailInUseForOtherDcotors(Long doctorId, String userName) {
		Criterion userNameCriterion = Restrictions.eq(BaseDoctor.FIELD_emailId, userName);
		Criterion activeCriterion = Restrictions.ne(BaseDoctor.FIELD_doctorId, doctorId);
		List<Doctor> doctors = findByCrieria(Restrictions.and(userNameCriterion, activeCriterion));
		return !doctors.isEmpty();
	}

	@SuppressWarnings("rawtypes")

	@Override
	@Transactional(readOnly = true)
	public List<Doctor> getAllDoctorsBySearchCritieria(DoctorSearchRequest doctorSearchRequest) {

		StringBuilder selectClause = new StringBuilder("SELECT distinct d." + BaseDoctor.FIELD_doctorId);
		StringBuilder fromClause = new StringBuilder(" FROM doctor d ");
		StringBuilder joinClause = new StringBuilder(
				" INNER JOIN registration r on r." + BaseRegistration.FIELD_doctorId + " = d."
						+ BaseDoctor.FIELD_doctorId + " and (LOWER( d.isprofilecompleted)) ='Y' ");
		StringBuilder whereClause = new StringBuilder(" WHERE (r." + BaseDoctor.FIELD_active + "='Y')");
		// StringBuilder whereClaused = new StringBuilder(" WHERE (d." +
		// BaseDoctor.FIELD_isprofilecompleted + "='Y')");

		// doctor
		if (!StringUtil.isNullOrEmpty(doctorSearchRequest.getDoctorName())) {
			String doctorName = "%" + doctorSearchRequest.getDoctorName().toLowerCase() + "%";
			whereClause.append(" AND (LOWER(d." + BaseDoctor.FIELD_firstName + ") LIKE '" + doctorName + "' OR LOWER(d."
					+ BaseDoctor.FIELD_lastName + ") LIKE '" + doctorName + "' OR LOWER(d."
					+ BaseDoctor.FIELD_middleName + ") LIKE '" + doctorName + "')");
		}

		if (!StringUtil.isNullOrEmpty(doctorSearchRequest.getEmailId())) {
			String emailId = "%" + doctorSearchRequest.getEmailId().toLowerCase() + "%";
			whereClause.append(" OR ").append("(LOWER(d." + BaseDoctor.FIELD_emailId + ") LIKE '" + emailId + "')");

		}

		if (!StringUtil.isNullOrEmpty(doctorSearchRequest.getGender())) {
			String gender = doctorSearchRequest.getGender().toLowerCase();
			whereClause.append(" AND ").append("(LOWER(d." + BaseDoctor.FIELD_gender + ") = '" + gender + "')");
		}

		if (!Objects.isNull(doctorSearchRequest.getTotalExperience())) {
			Integer totalExp = doctorSearchRequest.getTotalExperience();
			whereClause.append(" AND ").append("(d." + BaseDoctor.FIELD_totalExperience + " = '" + totalExp + "')");
		}

		// ratings
		String ratingJoin = " LEFT JOIN ratings r on r." + BaseDoctorratings.FIELD_doctorid + " = d."
				+ BaseDoctor.FIELD_doctorId;
		Integer rating = doctorSearchRequest.getRating();
		if (!Objects.isNull(rating)) {
			joinClause.append(ratingJoin);
			whereClause.append(" AND ").append("(r." + BaseDoctorratings.FIELD_rating + " = '" + rating + "')");
		}

		// specialization
		String specializationJoin = " LEFT JOIN specialization sp on sp." + BaseSpecialization.FIELD_doctorId + " = d."
				+ BaseDoctor.FIELD_doctorId;
		String specializations = doctorSearchRequest.getSpecializations();
		if (!StringUtil.isNullOrEmpty(specializations)) {
			joinClause.append(specializationJoin);
			specializations = "%" + specializations.toLowerCase() + "%";
			whereClause.append(" AND ")
					.append("(LOWER(d." + BaseDoctor.FIELD_specialization + ") LIKE '" + specializations
							+ "' OR LOWER(sp." + BaseSpecialization.FIELD_specializations + ") LIKE '" + specializations
							+ "')");
		}

		// Address
		String addressJoin = " INNER JOIN address ad on ad." + BaseDoctorAddress.FIELD_doctorId + " = d."
				+ BaseDoctor.FIELD_doctorId;
		Double latitude = doctorSearchRequest.getLatitude();
		Double longitude = doctorSearchRequest.getLongitude();
		if (!Objects.isNull(latitude) && !Objects.isNull(longitude)) {
			joinClause.append(addressJoin);
			whereClause.append(" AND ");
			whereClause.append("(ad." + BaseDoctorAddress.FIELD_latitude + " >= '" + latitude + "' AND ad."
					+ BaseDoctorAddress.FIELD_longitude + " <= '" + longitude + "')");
		}

		// certification
		String instituteName = doctorSearchRequest.getInstituteName();
		String certificationJoin = " LEFT JOIN certification ct on sp." + BaseCertification.FIELD_doctorId + " = d."
				+ BaseDoctor.FIELD_doctorId;
		if (!StringUtil.isNullOrEmpty(instituteName)) {
			joinClause.append(certificationJoin);
			instituteName = "%" + instituteName.toLowerCase() + "%";
			whereClause.append(" AND ")
					.append("(LOWER(d." + BaseCertification.FIELD_instituteName + ") LIKE '" + specializations
							+ "' OR LOWER(sp." + BaseSpecialization.FIELD_specializations + ") LIKE '" + specializations
							+ "')");
		}

		String finalQuery = selectClause.append(fromClause).append(joinClause).append(whereClause).toString();
		LOG.info("doctor search criteria : {} ", finalQuery);

		Map<String, Type> scalarMapping = new HashMap<>();
		scalarMapping.put(BaseDoctor.FIELD_doctorId, StandardBasicTypes.LONG);

		List doctorIds = querySqlMapTransformer(finalQuery, null, null, null, scalarMapping);
		if (doctorIds.isEmpty()) {
			return new ArrayList<>();
		}

		List<Doctor> doctors = findByCrieria(Restrictions.in(BaseDoctor.FIELD_doctorId, doctorIds));

		doctors = doctors.stream().map(doctor -> {
			doctor.setPassword("");

			Personaldetail pd = personaldetailDAO.findOneByProperty(BaseDoctorAddress.FIELD_doctorId,
					doctor.getDoctorId());

			doctor.setPersonaldetail(pd);

			return doctor;
		}).collect(Collectors.toList());
		Feedetails feedetails = feedetailsDAO.findOneByProperty(BaseFeedetails.FIELD_doctorId,
				doctors.get(0).getDoctorId());

		return doctors;
	}

	@Override
	@Transactional(readOnly = true)
	public String getDoctorsEmailId(Long doctorId) {
		Map<String, Object> params = new HashMap<>();
		params.put(BaseDoctor.FIELD_doctorId, doctorId);

		Map<String, Type> scalarMapping = new HashMap<>();
		scalarMapping.put(BaseDoctor.FIELD_emailId, StandardBasicTypes.STRING);

		String emailId = null;

		List<Map<String, Object>> objects = findBySqlQueryMapTransformer(FETCH_EMAIL_ID_QUERY, params, scalarMapping);
		for (Iterator<Map<String, Object>> iterator = objects.iterator(); iterator.hasNext();) {
			Map<String, Object> userEmailidMap = iterator.next();
			emailId = (String) userEmailidMap.get(BaseDoctor.FIELD_emailId);
		}

		return emailId;
	}

	@Override
	@Transactional
	public void updateRatings(Long doctorid, RatingsDTO ratings) {
		Doctor doctor = findById(doctorid);
		doctor.setAveragerating(ratings.getAverage());
		doctor.setActive('Y');
		save(doctor);

	}

	@Override
	@Transactional(readOnly = true)
	public Map<String, Object> getMyReports(Long doctorid) {
		// 1. Total patients: number of patients (distinct patientids from appointment
		// where doctorid)
		Long totalAcceptedAppointmentsByDoctor = appointmentDAO.getTotalPatientsOfDoctor(doctorid,
				AppointmentStatusEnum.ENTITY_ACCEPTED.name());
		// 2. Total Cancel:(distinct patientids from appointment where doctorid and
		// status = cancel)
		Long totalCancelledAppointmentsByDoctor = appointmentDAO.getTotalPatientsOfDoctor(doctorid,
				AppointmentStatusEnum.ENTITY_CANCELLED.name());
		Long totalCancelledAppointmentsByPatient = appointmentDAO.getTotalPatientsOfDoctor(doctorid,
				AppointmentStatusEnum.PATIENT_CANCELLED.name());
		// 3. Total reschedules: (distinct patientids from appointment where doctorid
		// and status = reschedule)
		Long totalRescheduledAppointmentsByDoctor = appointmentDAO.getTotalPatientsOfDoctor(doctorid,
				AppointmentStatusEnum.ENTITY_RESCHEDULED.name());
		// 4. Total Revenues:(distinct patientids from invoice where doctorid and
		// invoicestatus=booked)
		Double totalRevenueByBookedStatus = invoiceDAO.getTotalRevenue(doctorid, InvoiceStatusEnum.PAYMENT_DONE.name());

		Map<String, Object> myReportsMap = new HashMap<>();
		myReportsMap.put("Total_Accepted_Appointments", totalAcceptedAppointmentsByDoctor);
		myReportsMap.put("Total_Cancelled_Appointments",
				Long.sum(totalCancelledAppointmentsByDoctor, totalCancelledAppointmentsByPatient));
		myReportsMap.put("Total_Rescheduled_Appointments", totalRescheduledAppointmentsByDoctor);
		myReportsMap.put("Total_Booked_Revenue", totalRevenueByBookedStatus);

		return myReportsMap;
	}

	@Override
	@Transactional(readOnly = true)
	public Doctor findDoctorById(Long doctorId) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.eq(BaseDoctor.FIELD_doctorId, doctorId));
		criterions.add(Restrictions.eq(BaseDoctor.FIELD_active, 'Y'));
		List<Doctor> doctors = findByCrieria(criterions);
		if (CollectionUtils.isEmpty(doctors))
			return null;
		return doctors.get(0);
	}

	@Transactional
	public void save(Doctor doctor) {
		if (Objects.isNull(doctor.getDoctorId())) {
			doctor.setActive('N');
			doctor.setLocked('N');
			doctor.setRetry(0);
		}
		super.save(doctor);
	}

	@Override
	@Transactional(readOnly = true)
	public Response<List<Doctor>> getAllDoctors(Integer pageNum, Integer maxResults) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.eq(BaseDoctor.FIELD_active, 'Y'));
		return findByCrieria(criterions, null, pageNum, maxResults);
	}

	@Override
	@Transactional
	public Doctor findByDoctorAndNutrations(String userName, String type) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.or(Restrictions.eq(BaseDoctor.FIELD_userName, userName),
				Restrictions.eqOrIsNull(BaseDoctor.FIELD_emailId, userName)));
		criterions.add(Restrictions.eq(BaseDoctor.FIELD_type, type));

		List<Doctor> doctors = findByCrieria(criterions);
		if (doctors != null && doctors.size() > 0) {
			return doctors.get(0);
		}

		return null;
	}

}
