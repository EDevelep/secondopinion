package org.secondopinion.patient.dao;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.secondopinion.dao.IDAO;
import org.secondopinion.patient.dto.MedicationUsageNewDTO;
import org.secondopinion.patient.dto.Medicationusage;
import org.secondopinion.patient.dto.MedicationusageDTO;
import org.secondopinion.patient.service.helper.DoseTime;

public interface MedicationusageDAO extends IDAO<Medicationusage,Long >{

	List<Medicationusage> getMedactionIsTakenorNot(MedicationusageDTO medicationusageDTO);

	Medicationusage isMedicationTakenOrNot(Long userId, DoseTime doseTime,Long medicationId);

	 Collection<MedicationUsageNewDTO>  getMedicationsForTheDay(MedicationUsageNewDTO medicationusageDTO);

	List<Medicationusage> findMedicationByMedicationId(List<Long> medicationId);

	List<Medicationusage> getMedicationusageBymedicationId(Long medicationId);
}