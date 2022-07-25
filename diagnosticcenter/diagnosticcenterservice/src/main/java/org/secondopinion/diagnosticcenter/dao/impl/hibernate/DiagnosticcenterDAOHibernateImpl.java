package org.secondopinion.diagnosticcenter.dao.impl.hibernate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.Type;
import org.secondopinion.common.dto.RatingsDTO;
import org.secondopinion.diagnosticcenter.dao.AppointmentDAO;
import org.secondopinion.diagnosticcenter.dao.DiagnosticcenteraddressDAO;
import org.secondopinion.diagnosticcenter.dao.InvoiceDAO;
import org.secondopinion.diagnosticcenter.dao.MenuDAO;
import org.secondopinion.diagnosticcenter.dao.PackageDAO;
import org.secondopinion.diagnosticcenter.dto.Diagnosticcenter;
import org.secondopinion.diagnosticcenter.dto.DiagnosticcenterSearchRequest;
import org.secondopinion.diagnosticcenter.dto.Diagnosticcenteraddress;
import org.secondopinion.diagnosticcenter.dto.Diagnosticcenteruser;
import org.secondopinion.diagnosticcenter.dto.Menu;
import org.secondopinion.diagnosticcenter.dto.MenuSearchDTO;
import org.secondopinion.diagnosticcenter.dto.Package;
import org.secondopinion.diagnosticcenter.dto.PackageSearchDTO;
import org.secondopinion.diagnosticcenter.dto.Packagemenu;
import org.secondopinion.diagnosticcenter.dto.SearchEnum;
import org.secondopinion.diagnosticcenter.dto.Submenu;
import org.secondopinion.enums.AppointmentStatusEnum;
import org.secondopinion.enums.InvoiceStatusEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import io.netty.util.internal.StringUtil;

@Repository
public class DiagnosticcenterDAOHibernateImpl extends BaseDiagnosticcenterDAOHibernate {

	private static final Logger LOG = LoggerFactory.getLogger(DiagnosticcenterDAOHibernateImpl.class);

	@Autowired
	private MenuDAO menuDAO;

	@Autowired
	private DiagnosticcenteraddressDAO diagnosticcenteraddressDAO;

	@Autowired
	private PackageDAO packageDAO;

	@Autowired
	private AppointmentDAO appointmentDAO;

	@Autowired
	private InvoiceDAO invoiceDAO;

	@Override
	@Transactional(readOnly = true)
	public Diagnosticcenter readByEmailId(String emailId) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.eq(Diagnosticcenter.FIELD_primaryUserEmailId, emailId));
		criterions.add(Restrictions.eq(Diagnosticcenter.FIELD_active, 'Y'));

		return getDiagnosticcenterFromList(findByCrieria(criterions));
	}

	private Diagnosticcenter getDiagnosticcenterFromList(List<Diagnosticcenter> diagnosticcenters) {
		if (CollectionUtils.isEmpty(diagnosticcenters)) {
			return null;
		}
		return diagnosticcenters.get(0);
	}

	@Override
	@Transactional(readOnly = true)
	public Diagnosticcenter readByDiagnosticcenterId(Long diagnosticcenterId) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.eq(Diagnosticcenter.FIELD_diagnosticcenterId, diagnosticcenterId));
		criterions.add(Restrictions.eq(Diagnosticcenter.FIELD_active, 'Y'));

		return getDiagnosticcenterFromList(findByCrieria(criterions));
	}

	@Override
	@Transactional(readOnly = true)
	public Diagnosticcenter readByLicenceNumber(String licenseNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public void save(Diagnosticcenter diagnosticcenter) {
		if (Objects.isNull(diagnosticcenter.getDiagnosticcenterId())) {
			diagnosticcenter.setActive('Y');
		}
		super.save(diagnosticcenter);
	}

	@Override
	@Transactional
	public void updatediagnosticcenterratings(Long diagnosticcenterid, RatingsDTO values) {

		Diagnosticcenter diagnosticcenter = findById(diagnosticcenterid);
		diagnosticcenter.setAveragerating(values.getAverage());
		super.save(diagnosticcenter);

	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Diagnosticcenteraddress> getAllDiagnosticcenterBySearchCritieria(
			DiagnosticcenterSearchRequest diagnosticcenterSearchRequest) {

		StringBuilder selectClause = new StringBuilder(
				"SELECT distinct dca." + Diagnosticcenteraddress.FIELD_diagnosticCenterAddressId);
		StringBuilder fromClause = new StringBuilder(" FROM diagnosticcenter dc");
		String dcName = diagnosticcenterSearchRequest.getDiagnosticCenterName();
		StringBuilder joinClause = new StringBuilder(
				" INNER JOIN diagnosticcenteraddress dca on dca." + Diagnosticcenteraddress.FIELD_diagnosticcenterId
						+ " = dc." + Diagnosticcenter.FIELD_diagnosticcenterId);
		joinClause.append(
				" INNER JOIN diagnosticcenteruser dcu on dcu." + Diagnosticcenteruser.FIELD_diagnosticCenterAddressId
						+ " = dca." + Diagnosticcenteraddress.FIELD_diagnosticCenterAddressId);

		StringBuilder whereClause = new StringBuilder(" WHERE dc." + Diagnosticcenter.FIELD_active + " = 'Y'");
		if (!StringUtil.isNullOrEmpty(dcName)) {
			whereClause.append(" AND LOWER(dc." + Diagnosticcenter.FIELD_name + ") = '" + dcName + "'");
		}
		whereClause.append(" AND LOWER(dcu." + Diagnosticcenteruser.FIELD_active + ") = 'Y'");

		String addressJoin = " INNER JOIN diagnosticcenteraddress ad on ad."
				+ Diagnosticcenteraddress.FIELD_diagnosticcenterId + " = d."
				+ Diagnosticcenteraddress.FIELD_diagnosticcenterId;
		Double latitude = diagnosticcenterSearchRequest.getLatitude();
		Double longitude = diagnosticcenterSearchRequest.getLongitude();
		if (!Objects.isNull(latitude) && !Objects.isNull(longitude)) {
			joinClause.append(addressJoin);
			whereClause.append(" AND ");
			whereClause.append("(dca." + Diagnosticcenteraddress.FIELD_latitude + " >= '" + latitude + "' AND dca."
					+ Diagnosticcenteraddress.FIELD_longitude + " <= '" + longitude + "')");
		}

		String finalQuery = selectClause.append(fromClause).append(joinClause).append(whereClause).toString();
		LOG.info("diagnosticcenter search criteria : {}", finalQuery);

		Map<String, Type> scalarMapping = new HashMap<>();
		scalarMapping.put(Diagnosticcenteraddress.FIELD_diagnosticCenterAddressId, StandardBasicTypes.LONG);

		List<Long> diagnosticcenteraddressIds = querySqlMapTransformer(finalQuery, null, null, null, scalarMapping);

		if (diagnosticcenteraddressIds.isEmpty()) {
			return new ArrayList<>();
		}

		List<Diagnosticcenteraddress> diagnosticcenteraddresss = diagnosticcenteraddressDAO
				.getByIds(diagnosticcenteraddressIds);

		return diagnosticcenteraddresss;
	}

	@Override
	@Transactional(readOnly = true)
	public Map<String, Object> getMyReports(Long diagnosticCenterAddressid) {
		// 1. Total patients: number of patients (distinct patientids from appointment
		// where doctorid)
		Long totalAcceptedAppointmentsByDoctor = appointmentDAO.getTotalPatientsOfDiagnosticCenter(
				diagnosticCenterAddressid, AppointmentStatusEnum.ENTITY_ACCEPTED.name());
		// 2. Total Cancel:(distinct patientids from appointment where doctorid and
		// status = cancel)
		Long totalCancelledAppointmentsByDoctor = appointmentDAO.getTotalPatientsOfDiagnosticCenter(
				diagnosticCenterAddressid, AppointmentStatusEnum.ENTITY_CANCELLED.name());
		Long totalCancelledAppointmentsByPatient = appointmentDAO.getTotalPatientsOfDiagnosticCenter(
				diagnosticCenterAddressid, AppointmentStatusEnum.PATIENT_CANCELLED.name());
		// 3. Total reschedules: (distinct patientids from appointment where doctorid
		// and status = reschedule)
		Long totalRescheduledAppointmentsByDoctor = appointmentDAO.getTotalPatientsOfDiagnosticCenter(
				diagnosticCenterAddressid, AppointmentStatusEnum.ENTITY_RESCHEDULED.name());
		// 4. Total Revenues:(distinct patientids from invoice where doctorid and
		// invoicestatus=booked)
		Double totalRevenueByBookedStatus = invoiceDAO.getTotalRevenue(diagnosticCenterAddressid,
				InvoiceStatusEnum.PAYMENT_DONE.name());

		Map<String, Object> myReportsMap = new HashMap<>();
		myReportsMap.put("Total_Accepted_Appointments", totalAcceptedAppointmentsByDoctor);
		myReportsMap.put("Total_Cancelled_Appointments",
				Long.sum(totalCancelledAppointmentsByDoctor, totalCancelledAppointmentsByPatient));
		myReportsMap.put("Total_Rescheduled_Appointments", totalRescheduledAppointmentsByDoctor);
		myReportsMap.put("Total_Booked_Revenue", totalRevenueByBookedStatus);
		return myReportsMap;
	}

	@Override
	@Transactional
	public List<Diagnosticcenter> getAllDiagnosticcenterBySearchCritieria(List<Long> diagnosticcenterId) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.in(Diagnosticcenter.FIELD_primaryDataCenterAddressId, diagnosticcenterId));
		criterions.add(Restrictions.eq(Diagnosticcenter.FIELD_active, 'Y'));
		return findByCrieria(criterions);
	}

	@Override
	@Transactional
	public List<Diagnosticcenter> getAssoctedDiagnosticCenter(Long diagnosticCenteradminId) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.eq(Diagnosticcenter.FIELD_diagnosticCenteradminId,diagnosticCenteradminId));
	//	criterions.add(Restrictions.eq(Diagnosticcenter.FIELD_active, 'Y'));
		List<Diagnosticcenter> diagnosticcenter=findByCrieria(criterions);
		if(diagnosticcenter==null) {
			return null;
		}
		return diagnosticcenter;
	}

	
}