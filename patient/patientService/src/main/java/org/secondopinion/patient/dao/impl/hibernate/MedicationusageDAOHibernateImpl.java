package org.secondopinion.patient.dao.impl.hibernate;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.secondopinion.patient.dto.MedicationUsageNewDTO;
import org.secondopinion.patient.dto.Medicationusage;
import org.secondopinion.patient.dto.MedicationusageDTO;
import org.secondopinion.patient.service.helper.DoseTime;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import io.jsonwebtoken.lang.Collections;

@Repository
public class MedicationusageDAOHibernateImpl extends BaseMedicationusageDAOHibernate {

	@Override
	public List<Medicationusage> getMedactionIsTakenorNot(MedicationusageDTO medicationusageDTO) {
		List<Criterion> criteria = new ArrayList<>();
		criteria.add(Restrictions.eq(Medicationusage.FIELD_patientId, medicationusageDTO.getPatientId()));
		criteria.add(Restrictions.eq(Medicationusage.FIELD_medicationId, medicationusageDTO.getMedicineId()));
		if (medicationusageDTO.getMedicinedate() != null) {
			criteria.add(Restrictions.eq(Medicationusage.FIELD_medacationDate, medicationusageDTO.getMedicinedate()));
		}

		criteria.add(Restrictions.eq(Medicationusage.FIELD_doseTime, medicationusageDTO.getDoseTime()));
		List<Medicationusage> medicationusages = findByCrieria(criteria);
		return medicationusages;
	}

	@Override
	@Transactional
	public Medicationusage isMedicationTakenOrNot(Long userId, DoseTime doseTime, Long medactionId) {
		List<Criterion> criteria = new ArrayList<>();
		criteria.add(Restrictions.eq(Medicationusage.FIELD_patientId, userId));
		criteria.add(Restrictions.eq(Medicationusage.FIELD_medicationId, medactionId));
		criteria.add(Restrictions.eq(Medicationusage.FIELD_doseTime, doseTime.name()));
		List<Medicationusage> medicationusages = findByCrieria(criteria);

		return medicationusages.get(0);
	}

	private static final String MEDICATION_INFORMATION ="select medicationusageId, a.patientId, a.medicationId, a.dosageConsumed, mc.doctorName,   ap.referenceEntitySpecialization  as doctorSpecialization,  "
			+ " mc.appointmentDate,doseTime, medicineName from\r\n"
			+ "			 medicationusage a, medication b , prescription mc ,  appointment ap \r\n"
			+ "						where a.patientId =:PATIENT_ID  and a.medacationDate =:MEDICATION_DATE  and b.medicationId = a.medicationId;";
	
	
	@Override
	@Transactional
	public Collection<MedicationUsageNewDTO> getMedicationsForTheDay(MedicationUsageNewDTO medicationusageDTO) {
		Map<String, Object> params = new HashMap<String, Object>();

		params.put("PATIENT_ID", medicationusageDTO.getPatientId());
		params.put("MEDICATION_DATE", medicationusageDTO.getMedicinedate());

		List<Map<String, Object>> medicationData = findBySqlQuery(MEDICATION_INFORMATION, params);

		Map<BigInteger, MedicationUsageNewDTO> medicationUsageData = new HashMap<BigInteger, MedicationUsageNewDTO>();

		medicationData.stream().forEach(n -> {
			BigInteger medicationId = (BigInteger) n.get("medicationId");
			MedicationUsageNewDTO medicationUsageDTO = medicationUsageData.get(medicationId);

			if (medicationUsageDTO == null) {
				medicationUsageDTO = new MedicationUsageNewDTO(medicationId, n);
				medicationUsageData.put(medicationId, medicationUsageDTO);
			}

			medicationUsageDTO.update(n);
		});
		return medicationUsageData.values();
	}

	@Override
	@Transactional
	public List<Medicationusage> findMedicationByMedicationId(List<Long> medicationId) {
		List<Criterion> criteria = new ArrayList<>();

		criteria.add(Restrictions.in(Medicationusage.FIELD_medicationId, medicationId));
		criteria.add(Restrictions.eq(Medicationusage.FIELD_active, 'Y'));
		List<Medicationusage> medicationusages = findByCrieria(criteria);
		return medicationusages;
	}

	@Override
	@Transactional
	public List<Medicationusage> getMedicationusageBymedicationId(Long medicationId) {
		List<Criterion> criteria = new ArrayList<>();
		criteria.add(Restrictions.eq(Medicationusage.FIELD_medicationId, medicationId));
		criteria.add(Restrictions.eq(Medicationusage.FIELD_active, 'Y'));
		List<Medicationusage> medicationusages = findByCrieria(criteria);
		if (Collections.isEmpty(medicationusages)) {
			return new ArrayList<>();
		}
		return medicationusages;
	}

}