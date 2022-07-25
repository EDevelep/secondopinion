package org.secondopinion.patient.dao.impl.hibernate;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.secondopinion.patient.dto.MedicalprescriptionDTO;
import org.secondopinion.patient.dto.MedicalprescriptionTestDTO;
import org.secondopinion.patient.dto.MedicationUsageNewDTO;
import org.secondopinion.patient.dto.Prescription;
import org.secondopinion.patient.dto.PrescriptionDTO;
import org.springframework.cglib.core.CollectionUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class PrescriptionDAOHibernateImpl extends BasePrescriptionDAOHibernate {

	private static final String GET_ALL_PRESCRIPTION = "select a.patientId, a.prescriptionId,  a.doctorId,  a.containsMedicalPrescription,\n"
			+ "			a.containsTestPrescription, b.medicalPrescriptionId, \n"
			+ "			c.medicaltestprescriptionId, a.appointmentDate, a.doctorName , a.prescriptionFill,  b.numberOfRefills ,a.lastUpdatedTs ,a.prescriptioncontainsImage  \n"
			+ "            from prescription a\n"
			+ "			 left join medicalprescription b on a.prescriptionId = b.prescriptionId\n"
			+ "			 left join medicaltestprescription c on a.prescriptionId = c.prescriptionId\n"
			+ "			 where a.patientId= :patientId ";

	private static final String GET_ALL_MEDICAL_PRESCRIPTION = "select a.patientId, a.prescriptionId,  a.doctorId, a.prescriptioncontainsImage, a.containsMedicalPrescription, \n"
			+ "			a.containsTestPrescription, b.medicalPrescriptionId, \n"
			+ "			c.medicaltestprescriptionId, a.appointmentDate, a.doctorName ,   a.prescriptionFill,  b.numberOfRefills ,a.lastUpdatedTs \n"
			+ "            from prescription a\n"
			+ "			 left join medicalprescription b on a.prescriptionId = b.prescriptionId\n"
			+ "			 left join medicaltestprescription c on a.prescriptionId = c.prescriptionId\n"
			+ "			 where a.patientId= :patientId ";

	private static final String GET_ALL_MEDICAL_PRESCRIPTION_TEST = "select a.patientId, a.prescriptionId,  a.doctorId,  a.prescriptioncontainsImage,  a.containsMedicalPrescription,\n"
			+ "			a.containsTestPrescription, b.medicalPrescriptionId, \n"
			+ "			c.medicaltestprescriptionId, a.appointmentDate, a.doctorName , b.numberOfRefills ,a.lastUpdatedTs \n"
			+ "            from prescription a\n"
			+ "			 left join medicalprescription b on a.prescriptionId = b.prescriptionId\n"
			+ "			 left join medicaltestprescription c on a.prescriptionId = c.prescriptionId\n"
			+ "			 where a.patientId= :patientId ";

	@Override
	@Transactional
	public void save(Prescription prescription) {
		if (Objects.isNull(prescription.getPrescriptionId())) {
			prescription.setActive('Y');
		}
		super.save(prescription);
	}

	@Override
	@Transactional
	public Prescription findByPrescrptionId(Long prescriptionId) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.eq(Prescription.FIELD_prescriptionId, prescriptionId));

		criterions.add(Restrictions.eq(Prescription.FIELD_active, 'Y'));
		List<Prescription> prescriptions = findByCrieria(criterions);
		return prescriptions.get(0);
	}

	@Override
	@Transactional
	public boolean findPrescriptionRequestByDoctorAppointmentId(Long doctorAppointmentId) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.eq(Prescription.FIELD_doctorAppointmentId, doctorAppointmentId));

		criterions.add(Restrictions.eq(Prescription.FIELD_active, 'Y'));
		List<Prescription> prescriptions = findByCrieria(criterions);
		if (org.springframework.util.CollectionUtils.isEmpty(prescriptions)) {
			return false;
		}
		return true;
	}

	@Override
	@Transactional
	public Prescription getPrescriptionById(Long userId, Long prescriptionId) {
		List<Criterion> criterions = new ArrayList<>();
		// criterions.add(Restrictions.eq(Prescription.FIELD_patientId, userId));
		criterions.add(Restrictions.eq(Prescription.FIELD_prescriptionId, prescriptionId));
		criterions.add(Restrictions.eq(Prescription.FIELD_active, 'Y'));
		List<Prescription> prescriptions = findByCrieria(criterions);
		return prescriptions.get(0);
	}

	@Override
	@Transactional
	public List<Prescription> getPrecriptionByUserId(Long userId) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.eq(Prescription.FIELD_patientId, userId));
		criterions.add(Restrictions.eq(Prescription.FIELD_active, 'Y'));
		List<Prescription> prescriptions = findByCrieria(criterions);
		if (org.springframework.util.CollectionUtils.isEmpty(prescriptions)) {
			return null;
		}
		return prescriptions;

	}

	@Override
	@Transactional
	public Collection<PrescriptionDTO> getPrecriptionDetailsByUserId(Long patientId) {
		Map<String, Object> params = new HashMap<String, Object>();

		params.put("patientId", patientId);
		List<Map<String, Object>> medicationData = findBySqlQuery(GET_ALL_PRESCRIPTION, params);
		Map<BigInteger, PrescriptionDTO> medicationUsageData = new HashMap<BigInteger, PrescriptionDTO>();
		medicationData.stream().forEach(n -> {
			BigInteger prescriptionId = (BigInteger) n.get("prescriptionId");
			PrescriptionDTO prescriptionDTO = medicationUsageData.get(prescriptionId);

			if (prescriptionDTO == null) {
				prescriptionDTO = new PrescriptionDTO(prescriptionId, n);
				medicationUsageData.put(prescriptionId, prescriptionDTO);
			}

		});

		return medicationUsageData.values();
	}

	@Override
	@Transactional
	public Collection<MedicalprescriptionDTO> geAlltMedicalPrescription(Long patientId) {
		Map<String, Object> params = new HashMap<String, Object>();

		params.put("patientId", patientId);
		List<Map<String, Object>> medicationData = findBySqlQuery(GET_ALL_MEDICAL_PRESCRIPTION, params);
		Map<BigInteger, MedicalprescriptionDTO> medicationUsageData = new HashMap<BigInteger, MedicalprescriptionDTO>();
		medicationData.stream().forEach(n -> {
			BigInteger medicalPrescriptionId = (BigInteger) n.get("medicalPrescriptionId");
			MedicalprescriptionDTO prescriptionDTO = medicationUsageData.get(medicalPrescriptionId);

			if (prescriptionDTO == null) {
				prescriptionDTO = new MedicalprescriptionDTO(medicalPrescriptionId, n);
				medicationUsageData.put(medicalPrescriptionId, prescriptionDTO);
			}

		});

		return medicationUsageData.values();
	}

	@Override
	@Transactional
	public Collection<MedicalprescriptionTestDTO> geAlltmedicalprescriptionTest(Long patientId) {
		Map<String, Object> params = new HashMap<String, Object>();

		params.put("patientId", patientId);
		List<Map<String, Object>> medicationData = findBySqlQuery(GET_ALL_MEDICAL_PRESCRIPTION, params);
		Map<BigInteger, MedicalprescriptionTestDTO> medicationUsageData = new HashMap<BigInteger, MedicalprescriptionTestDTO>();
		medicationData.stream().forEach(n -> {
			BigInteger medicaltestprescriptionId = (BigInteger) n.get("medicaltestprescriptionId");
			MedicalprescriptionTestDTO prescriptionDTO = medicationUsageData.get(medicaltestprescriptionId);

			if (prescriptionDTO == null) {
				prescriptionDTO = new MedicalprescriptionTestDTO(medicaltestprescriptionId, n);
				medicationUsageData.put(medicaltestprescriptionId, prescriptionDTO);
			}

		});

		return medicationUsageData.values();
	}

	@Override
	@Transactional
	public Prescription getPrescriptionDetailsBydoctorAppointmentIddoctorAppointmentId(Long doctorAppointmentId,
			Long userId) {
		List<Criterion> criterions = new ArrayList<>();

		criterions.add(Restrictions.eq(Prescription.FIELD_doctorAppointmentId, doctorAppointmentId));
		criterions.add(Restrictions.eq(Prescription.FIELD_patientId, userId));
		criterions.add(Restrictions.eq(Prescription.FIELD_active, 'Y'));
		List<Prescription> prescriptions = findByCrieria(criterions);
		if (prescriptions.isEmpty()) {
			return null;
		}
		return prescriptions.get(0);
	}

	@Override
	@Transactional
	public Prescription getPrescriptionDetailsBydoctorAppointmentIddoctorAppointmentId(Long doctorAppointmentId) {
		List<Criterion> criterions = new ArrayList<>();

		criterions.add(Restrictions.eq(Prescription.FIELD_doctorAppointmentId, doctorAppointmentId));

		criterions.add(Restrictions.eq(Prescription.FIELD_active, 'Y'));
		List<Prescription> prescriptions = findByCrieria(criterions);
		if (prescriptions.isEmpty()) {
			return null;
		}
		return prescriptions.get(0);
	}

}
