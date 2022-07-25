package org.secondopinion.patient.dao.impl.hibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.secondopinion.patient.dto.Medicalprescription;
import org.secondopinion.patient.dto.MedicationDTO;
import org.secondopinion.patient.dto.Prescription;
import org.springframework.cglib.core.CollectionUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class MedicalprescriptionDAOHibernateImpl extends BaseMedicalprescriptionDAOHibernate {

	@Override
	@Transactional
	public void save(Medicalprescription medicalprescription) {
		if (Objects.isNull(medicalprescription.getMedicalPrescriptionId())) {
			medicalprescription.setActive('Y');
		}
		medicalprescription.setActive('Y');
		super.save(medicalprescription);
	}

// need to remove
	@Override
	public Medicalprescription findByPrescrptionId(Long referenceId) {
		// TODO Auto-generated method stub
		return null;
	}

//// need to remove
	@Override
	public boolean findPrescriptionRequestByDoctorAppointmentId(Long doctorAppointmentId) {

		return false;
	}
	//// need to remove

	@Override
	@Transactional
	public List<MedicationDTO> getMedicationsAllByuserId(Long userId) {
		
		return null;
	}

	@Override
	@Transactional
	public Medicalprescription getprecriptionByDoctorIdAndUserId(Long userId, Long medicalPrescriptionId) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.eq(Medicalprescription.FIELD_medicalPrescriptionId, medicalPrescriptionId));
		criterions.add(Restrictions.eq(Medicalprescription.FIELD_patientId, userId));
		criterions.add(Restrictions.eq(Prescription.FIELD_active, 'Y'));
		List<Medicalprescription> prescriptions = findByCrieria(criterions);
		if (org.springframework.util.CollectionUtils.isEmpty(prescriptions)) {
			return null;
		}
		return prescriptions.get(0);
	}

	@Override
	@Transactional
	public List<Medicalprescription> findAllMedicalprescriptionByPrescriptionId(Long medicalPrescriptionId) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.eq(Medicalprescription.FIELD_medicalPrescriptionId, medicalPrescriptionId));

		criterions.add(Restrictions.eq(Prescription.FIELD_active, 'Y'));
		List<Medicalprescription> prescriptions = findByCrieria(criterions);
		if (org.springframework.util.CollectionUtils.isEmpty(prescriptions)) {
			return null;
		}
		return prescriptions;
	}

	@Override
	@Transactional
	public List<Medicalprescription> findMedicationBymedicalprescriptionId(List<Long> medicalprescriptionId) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.in(Medicalprescription.FIELD_medicalPrescriptionId, medicalprescriptionId));

		criterions.add(Restrictions.eq(Prescription.FIELD_active, 'Y'));
		List<Medicalprescription> prescriptions = findByCrieria(criterions);
		if (org.springframework.util.CollectionUtils.isEmpty(prescriptions)) {
			return null;
		}
		return prescriptions;
	}

	@Override
	@Transactional
	public List<Medicalprescription> findAllMedicalprescriptionByUserId(Long userId) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.eq(Medicalprescription.FIELD_patientId, userId));

		criterions.add(Restrictions.eq(Prescription.FIELD_active, 'Y'));
		List<Medicalprescription> prescriptions = findByCrieria(criterions);
		if (org.springframework.util.CollectionUtils.isEmpty(prescriptions)) {
			return null;
		}
		return prescriptions;
	}

	@Override
	@Transactional
	public Medicalprescription findMedicalprescriptionByPrescriptionId(Long prescriptionId) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.eq(Medicalprescription.FIELD_prescriptionId, prescriptionId));

		criterions.add(Restrictions.eq(Prescription.FIELD_active, 'Y'));
		List<Medicalprescription> prescriptions = findByCrieria(criterions);
		if (org.springframework.util.CollectionUtils.isEmpty(prescriptions)) {
			return null;
		}
		return prescriptions.get(0);
	}

}
