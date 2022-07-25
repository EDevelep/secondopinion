package org.secondopinion.patient.dao.impl.hibernate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.secondopinion.configurations.UtilComponent;
import org.secondopinion.patient.dto.MedicalprescriptionSearchCriteria;
import org.secondopinion.patient.dto.Medication;
import org.secondopinion.request.Response;
import org.secondopinion.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

@Repository
public class MedicationDAOHibernateImpl extends BaseMedicationDAOHibernate {

	@Autowired
	private UtilComponent utilComponent;

	@Override
	@Transactional
	public void save(Medication medication) {
		if (Objects.isNull(medication.getMedicationId())) {
			medication.setActive('Y');
		}
		super.save(medication);
	}

	@Override
	@Transactional(readOnly = true)
	public Response<List<Medication>> getAllMedications(
			MedicalprescriptionSearchCriteria medicalprescriptionSearchCriteria) {
		List<Criterion> criterions = new ArrayList<>();

		if (Objects.nonNull(medicalprescriptionSearchCriteria.getForUserId())) {
			criterions
					.add(Restrictions.eq(Medication.FIELD_patientId, medicalprescriptionSearchCriteria.getForUserId()));
		}
		if (medicalprescriptionSearchCriteria.getIsOnlyActive() != null
				&& medicalprescriptionSearchCriteria.getIsOnlyActive()) {
			criterions.add(Restrictions.ge(Medication.FIELD_enddate,
					DateUtil.convertLocalDateToUtilDate(LocalDate.now(), utilComponent.getTimeZone())));
		}

		if (Objects.nonNull(medicalprescriptionSearchCriteria.getMedicineName())) {
			criterions.add(Restrictions.ge(Medication.FIELD_medicineName,
					medicalprescriptionSearchCriteria.getMedicineName()));
		}

		if (Objects.nonNull(medicalprescriptionSearchCriteria.getFromDate())) {
			criterions.add(Restrictions.between(Medication.FIELD_enddate,
					DateUtil.getMinDateTimeOfDate(medicalprescriptionSearchCriteria.getFromDate()),
					DateUtil.getMinDateTimeOfDate(medicalprescriptionSearchCriteria.getToDate())));
		}
		if (Objects.nonNull(medicalprescriptionSearchCriteria.getMedicalTestPrescriptionId())) {
			criterions.add(Restrictions.eq(Medication.FIELD_medicalPrescriptionId,
					medicalprescriptionSearchCriteria.getMedicalTestPrescriptionId()));
		}
		criterions.add(Restrictions.eq(Medication.FIELD_active, 'Y'));
		List<Order> orders = Arrays.asList(Order.desc(Medication.FIELD_medicationId));
		return findByCrieria(criterions, orders, medicalprescriptionSearchCriteria.getPageNum(),
				medicalprescriptionSearchCriteria.getMaxResults());

	}

	@Override
	@Transactional(readOnly = true)
	public List<Medication> findMedicationByPrescriptionId(Long medicalPrescriptionId) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.eq(Medication.FIELD_medicalPrescriptionId, medicalPrescriptionId));
		criterions.add(Restrictions.eq(Medication.FIELD_active, 'Y'));
		return findByCrieria(criterions);
	}

	private final String CANCEL_MEDICATION_FOR_PRESCRIPTION = "update medication set active = 'N', lastUpdatedBy = :LAST_UPDATED_BY, lastUpdatedTs = now() "
			+ " where medicalPresctiptionId = :MEDICAL_PRESCRIPTION_ID";

	@Override
	public void cancel(Long medicalPrescriptionId, String userId) {
		Map<String, Object> params = new HashMap<String, Object>();

		params.put("LAST_UPDATED_BY", userId);
		params.put("MEDICAL_PRESCRIPTION_ID", medicalPrescriptionId);

		executeQuery(CANCEL_MEDICATION_FOR_PRESCRIPTION, params);

	}

	@Override
	@Transactional
	public List<Medication> findMedicationByUserIds(List<Long> userIds) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.in(Medication.FIELD_patientId, userIds));
		criterions.add(Restrictions.eq(Medication.FIELD_active, 'Y'));
		return findByCrieria(criterions);
	}

	@Override
	@Transactional
	public List<Medication> getMedicalPrescriptionDetailsWithMedication(List<Long> medicalprescriptionId) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.in(Medication.FIELD_medicalPrescriptionId, medicalprescriptionId));
		criterions.add(Restrictions.eq(Medication.FIELD_active, 'Y'));
		List<Medication> medications=	 findByCrieria(criterions);
		if(CollectionUtils.isEmpty(medications)) {
			return null;
		}
		return medications;
	}
}
