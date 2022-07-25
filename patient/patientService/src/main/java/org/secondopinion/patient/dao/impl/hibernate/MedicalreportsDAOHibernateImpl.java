package org.secondopinion.patient.dao.impl.hibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.secondopinion.patient.domain.BaseMedicalreports;
import org.secondopinion.patient.dto.MedcalReportSearchRequest;
import org.secondopinion.patient.dto.Medicalreports;
import org.secondopinion.request.Response;
import org.secondopinion.utils.StringUtil;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

@Component
public class MedicalreportsDAOHibernateImpl extends BaseMedicalreportsDAOHibernate {

	@Override
	@Transactional
	public void save( Medicalreports medicalreports)  {
		if(Objects.isNull(medicalreports.getMedicalReportsId())) {
			medicalreports.setActive('Y');
		}
		if(Objects.isNull(medicalreports.getDoctorAppointmentId()) || medicalreports.getDoctorAppointmentId() <= 0 ) {
			medicalreports.setDoctorAppointmentId(-1L);
		}
		if(Objects.isNull(medicalreports.getDiagnosticCenterAppointmentId()) || medicalreports.getDiagnosticCenterAppointmentId() <= 0 ) {
			medicalreports.setDiagnosticCenterAppointmentId(-1L);
		}
		super.save(medicalreports);
	}
	
	
	
	@Override
	@Transactional
	public Response<List<Medicalreports>> getMedicalReportsByUserAndAppointment(Long userId, Long appointmentId, Integer pageNum, Integer maxResults) {
		List<Criterion> criteria = new ArrayList<>();
		if(Objects.nonNull(userId)) {
			criteria.add(Restrictions.eq(BaseMedicalreports.FIELD_userId, userId));
		}
		if(Objects.nonNull(appointmentId)) {
			criteria.add(Restrictions.eq(BaseMedicalreports.FIELD_appointmentId, appointmentId));
		}
		criteria.add(Restrictions.eq(BaseMedicalreports.FIELD_active, 'Y'));
		
		return findByCrieria(criteria, buildOrders(), pageNum, maxResults);
	}

	private List<Order> buildOrders() {
		List<Order> orders = new ArrayList<>();
		orders.add(Order.desc(BaseMedicalreports.FIELD_medicalReportsId));
		
		return orders;
	}
	
	@Override
	@Transactional(readOnly=true)
	public Response<List<Medicalreports>> getAllMedicalreportsBySearchCritieria(
			MedcalReportSearchRequest medcalReportSearchRequest, Long userId) {
		List<Criterion> criterions = new ArrayList<>();
		if (Objects.isNull(userId)) {
			throw new IllegalArgumentException("User/Patient Id can not be null");
		}
		
		criterions.add(Restrictions.eq(BaseMedicalreports.FIELD_userId, userId));
		criterions.add(Restrictions.eq(BaseMedicalreports.FIELD_active, 'Y'));
		if (Objects.isNull(userId)) {
			criterions.add(Restrictions.like(BaseMedicalreports.FIELD_userId, userId));
		}
		if (StringUtil.isNullOrEmpty(medcalReportSearchRequest.getAlignment())) {
			criterions.add(Restrictions.eq(BaseMedicalreports.FIELD_alignment, medcalReportSearchRequest.getAlignment()));
		}

		if (Objects.nonNull(medcalReportSearchRequest.getReportTakenOn())) {
			criterions.add(Restrictions.between(BaseMedicalreports.FIELD_reportTakenOn,
					medcalReportSearchRequest.getReportTakenOn(), medcalReportSearchRequest.getMaxappointmentdate()));
		}

		if (Objects.nonNull(medcalReportSearchRequest.getAppointmentDate())) {
			criterions.add(Restrictions.between(BaseMedicalreports.FIELD_appointmentDate,
					medcalReportSearchRequest.getAppointmentDate(), medcalReportSearchRequest.getMaxappointmentdate()));
		}

		if (Objects.nonNull(medcalReportSearchRequest.getMedicalTestId())) {
			criterions.add(
					Restrictions.eq(BaseMedicalreports.FIELD_medicalTestId, medcalReportSearchRequest.getMedicalTestId()));
		}

		if (StringUtil.isNullOrEmpty(medcalReportSearchRequest.getReportName())) {
			criterions.add(Restrictions.eq(BaseMedicalreports.FIELD_reportName, medcalReportSearchRequest.getReportName()));
		}
		return findByCrieria(criterions, buildOrders(), medcalReportSearchRequest.getPageNum(), medcalReportSearchRequest.getMaxResults());
	}

	@Override
	@Transactional(readOnly=true)
	public Medicalreports getmedicalreportsById(Long medicalreportsId) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.eq(BaseMedicalreports.FIELD_medicalReportsId, medicalreportsId));
		criterions.add(Restrictions.eq(BaseMedicalreports.FIELD_active, 'Y'));
		List<Medicalreports> medicalreports = findByCrieria(criterions);
		if(CollectionUtils.isEmpty(medicalreports)) return null;
		return medicalreports.get(0);

	}
	
	@Override
	@Transactional(readOnly=true)
	public List<Medicalreports> getmedicalreportsByuserId(Long userId) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.eq(BaseMedicalreports.FIELD_userId, userId));
		criterions.add(Restrictions.eq(BaseMedicalreports.FIELD_active, 'Y'));
		return findByCrieria(criterions, Order.desc(BaseMedicalreports.FIELD_medicalReportsId));

	}



	@Override
	@Transactional
	public List<Medicalreports> getmedicalreportsByuserIds(List<Long> userIds) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.in(BaseMedicalreports.FIELD_userId, userIds));
		criterions.add(Restrictions.eq(BaseMedicalreports.FIELD_active, 'Y'));
		return findByCrieria(criterions, Order.desc(BaseMedicalreports.FIELD_medicalReportsId));
	}
}