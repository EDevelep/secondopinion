package org.secondopinion.patient.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.secondopinion.patient.domain.BaseMedicationusage;
import org.secondopinion.utils.DateUtil;
import org.springframework.util.CollectionUtils;

@SuppressWarnings({ "serial" })
@Entity
@Table(name = "medicationusage")
public class Medicationusage extends BaseMedicationusage {

	public static List<Medicationusage> build(Medication medication) {

		Medicationusage medicationusage = new Medicationusage();
		medicationusage.setMedicationId(medication.getMedicationId());
		medicationusage.setPatientId(medication.getPatientId());
		List<Medicationusage> medicationusages = new ArrayList<>();

		for (int i = 0; i < medication.getNumberOfDays(); i++) {
			try {

				if (medication.getMorning() != null && medication.getMorning() != 0) {
					medicationusages.add(createMedicationUsage(medication, medicationusage, i, "MORNING"));
				}

				if (medication.getAfternoon() != null && medication.getAfternoon() != 0) {
					medicationusages.add(createMedicationUsage(medication, medicationusage, i, "AFTERNOON"));
				}

				if (medication.getEvening() != null && medication.getEvening() != 0) {
					medicationusages.add(createMedicationUsage(medication, medicationusage, i, "EVENING"));
				}

			} catch (CloneNotSupportedException e) {
				throw new RuntimeException("Error creating MedicationUsage.. " + e.getMessage(), e);
			}
		}

		return medicationusages;

	}

	

	public static Medicationusage createMedicationUsage(Medication medication, Medicationusage medicationusage, int i,
			String doseTime) throws CloneNotSupportedException {
		Medicationusage mcCloned = medicationusage.clone();
		mcCloned.setActive('Y');
		mcCloned.setMedacationDate(DateUtil.addDay(medication.getEnddate(), -1 * i));
		mcCloned.setDoseTime(doseTime);

		return mcCloned;
	}
}