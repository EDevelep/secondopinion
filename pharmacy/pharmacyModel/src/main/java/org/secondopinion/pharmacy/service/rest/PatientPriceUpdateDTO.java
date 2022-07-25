package org.secondopinion.pharmacy.service.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.secondopinion.pharmacy.dto.PrescriptionFillRequestUpdateDTO;
import org.secondopinion.pharmacy.dto.PrescriptionPriceUpdateDTO;

public class PatientPriceUpdateDTO {

	private List<PrescriptionPriceUpdateDTOPharmacy> prescriptionPriceUpdateDTOs;

	public List<PrescriptionPriceUpdateDTOPharmacy> getPrescriptionPriceUpdateDTOs() {
		return prescriptionPriceUpdateDTOs;
	}

	public void setPrescriptionPriceUpdateDTOs(List<PrescriptionPriceUpdateDTOPharmacy> prescriptionPriceUpdateDTOs) {
		this.prescriptionPriceUpdateDTOs = prescriptionPriceUpdateDTOs;
	}

	public static PatientPriceUpdateDTO buildObjectForPatient(PrescriptionFillRequestUpdateDTO fillRequestUpdateDTO) {
		PatientPriceUpdateDTO patientPriceUpdateDTO = new PatientPriceUpdateDTO();

		List<PrescriptionPriceUpdateDTO> prescriptionPriceUpdateDTOs = fillRequestUpdateDTO
				.getPrescriptionPriceUpdateDTOs();

		List<PrescriptionPriceUpdateDTOPharmacy> prescriptionPriceUpdateDTOPharmacy = prescriptionPriceUpdateDTOs
				.stream()
				.map(p -> new PrescriptionPriceUpdateDTOPharmacy(p.getPatientId(), p.getPatientAppointmentId(),
						p.getMedicalPrescriptionId(), p.getMedicine(), p.getNumberOfDays(), p.getEndDate()))
				.collect(Collectors.toList());

		patientPriceUpdateDTO.setPrescriptionPriceUpdateDTOs(prescriptionPriceUpdateDTOPharmacy);
		return patientPriceUpdateDTO;
	}
}
