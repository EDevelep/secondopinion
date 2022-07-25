package org.secondopinion.patient.dao.impl.hibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.secondopinion.patient.dto.Medicalprescription;
import org.secondopinion.patient.dto.Medicaltestprescription;
import org.secondopinion.patient.dto.Prescription;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class MedicaltestprescriptionDAOHibernateImpl extends BaseMedicaltestprescriptionDAOHibernate {

	@Override
	@Transactional
	public void save(Medicaltestprescription medicalprescription) {
		if (Objects.isNull(medicalprescription.getMedicalTestPrescriptionId())) {
			medicalprescription.setActive('Y');
		}
		super.save(medicalprescription);
	}

	@Override
	@Transactional
	public List<Medicaltestprescription> findAllMedicaltestprescriptionyPrescriptionId(Long prescriptionId) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.eq(Medicaltestprescription.FIELD_prescriptionId, prescriptionId));

		criterions.add(Restrictions.eq(Medicaltestprescription.FIELD_active, 'Y'));
		List<Medicaltestprescription> prescriptions = findByCrieria(criterions);
		if (org.springframework.util.CollectionUtils.isEmpty(prescriptions)) {
			return null;
		}
		return prescriptions;
	}

	@Override
	public List<Medicaltestprescription> findAllMedicaltestprescriptionByMedicalTestPresciptionId(
			Long medicalTestPresciptionId) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.eq(Medicaltestprescription.FIELD_medicalTestPrescriptionId, medicalTestPresciptionId));

		criterions.add(Restrictions.eq(Medicaltestprescription.FIELD_active, 'Y'));
		List<Medicaltestprescription> prescriptions = findByCrieria(criterions);
		if (org.springframework.util.CollectionUtils.isEmpty(prescriptions)) {
			return null;
		}
		return prescriptions;
	}

	@Override
	@Transactional
	public Medicaltestprescription findMedicaltestprescriptionyPrescriptionId(Long prescriptionId) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.eq(Medicaltestprescription.FIELD_prescriptionId, prescriptionId));

		criterions.add(Restrictions.eq(Medicaltestprescription.FIELD_active, 'Y'));
		List<Medicaltestprescription> prescriptions = findByCrieria(criterions);
		if (org.springframework.util.CollectionUtils.isEmpty(prescriptions)) {
			return null;
		}
		return prescriptions.get(0);
	}
}
