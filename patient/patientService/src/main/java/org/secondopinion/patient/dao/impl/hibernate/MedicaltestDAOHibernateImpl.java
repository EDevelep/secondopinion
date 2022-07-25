package org.secondopinion.patient.dao.impl.hibernate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.secondopinion.patient.dto.MedicalprescriptionSearchCriteria;
import org.secondopinion.patient.dto.Medicaltest;
import org.secondopinion.patient.dto.Medication;
import org.secondopinion.request.Response;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

@Repository
public class MedicaltestDAOHibernateImpl extends BaseMedicaltestDAOHibernate {

	@Override
	@Transactional
	public void save(Medicaltest medicaltest) {
		if (Objects.isNull(medicaltest.getMedicalTestId())) {
			medicaltest.setActive('Y');
		}
		super.save(medicaltest);
	}

	@Override
	@Transactional(readOnly = true)
	public Response<List<Medicaltest>> getAllMedicaltests(
			MedicalprescriptionSearchCriteria medicalprescriptionSearchCriteria) {
		List<Criterion> criterions = new ArrayList<>();
		if (Objects.nonNull(medicalprescriptionSearchCriteria.getMedicalTestPrescriptionId())) {
			criterions.add(Restrictions.eq(Medicaltest.FIELD_medicalTestPrescriptionId,
					medicalprescriptionSearchCriteria.getMedicalTestPrescriptionId()));
		}
		if (Objects.nonNull(medicalprescriptionSearchCriteria.getMedicalTestPrescriptionId())) {
			criterions.add(Restrictions.eq(Medication.FIELD_patientId,
					medicalprescriptionSearchCriteria.getMedicalTestPrescriptionId()));
		}
		criterions.add(Restrictions.eq(Medication.FIELD_active, 'Y'));
		return findByCrieria(criterions, null, medicalprescriptionSearchCriteria.getPageNum(),
				medicalprescriptionSearchCriteria.getMaxResults());
	}

	@Override
	@Transactional(readOnly = true)
	public List<Medicaltest> findMedicaltestByMedicalTestId(Long medicalTestPrescriptionId) {
		List<Criterion> criterions = new ArrayList<>();
		 criterions.add(Restrictions.eq(Medicaltest.FIELD_medicalTestPrescriptionId, medicalTestPrescriptionId));
		criterions.add(Restrictions.eq(Medicaltest.FIELD_active, 'Y'));
		List<Medicaltest> medicaltests=findByCrieria(criterions);
		if(CollectionUtils.isEmpty(medicaltests)) {
			return null;
		}
		return medicaltests;
		
	}

	private final String CANCEL_MEDICALTEST_FOR_PRESCRIPTION = "update medicaltest set active = 'N', lastUpdatedBy = :LAST_UPDATED_BY, lastUpdatedTs = now() "
			+ " where medicalPresctiptionId = :MEDICAL_PRESCRIPTION_ID";

	@Override
	public void cancel(Long medicalPrescriptionId, String userId) {
		Map<String, Object> params = new HashMap<String, Object>();

		params.put("LAST_UPDATED_BY", userId);
		params.put("MEDICAL_PRESCRIPTION_ID", medicalPrescriptionId);

		executeQuery(CANCEL_MEDICALTEST_FOR_PRESCRIPTION, params);

	}

	@Override
	@Transactional
	public List<Medicaltest> findAllMedicaltestByMedicalTestPresciptionId(List<Long> medicaltestprescriptionId) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.eq(Medication.FIELD_active, 'Y'));
		criterions.add(Restrictions.in(Medicaltest.FIELD_medicalTestPrescriptionId, medicaltestprescriptionId));
		List<Medicaltest> medicaltests=findByCrieria(criterions);
		if(CollectionUtils.isEmpty(medicaltests)) {
			return null;
		}
		return medicaltests;
	}
}