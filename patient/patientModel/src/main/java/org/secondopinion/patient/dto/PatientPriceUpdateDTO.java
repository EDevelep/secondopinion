package org.secondopinion.patient.dto;

import java.util.Date;
import java.util.List;



public class PatientPriceUpdateDTO {
	private List<PrescriptionPriceUpdateDTOPharmacy> prescriptionPriceUpdateDTOs;

	public List<PrescriptionPriceUpdateDTOPharmacy> getPrescriptionPriceUpdateDTOs() {
		return prescriptionPriceUpdateDTOs;
	}

	public void setPrescriptionPriceUpdateDTOs(List<PrescriptionPriceUpdateDTOPharmacy> prescriptionPriceUpdateDTOs) {
		this.prescriptionPriceUpdateDTOs = prescriptionPriceUpdateDTOs;
	}

}
