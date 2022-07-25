package org.secondopinion.patient.dto;

import java.util.List;

public class PrescriptionFillRequestUpdateDTO {

	private List<PrescriptionPriceUpdateDTO> prescriptionPriceUpdateDTOs;

	public List<PrescriptionPriceUpdateDTO> getPrescriptionPriceUpdateDTOs() {
		return prescriptionPriceUpdateDTOs;
	}

	public void setPrescriptionPriceUpdateDTOs(List<PrescriptionPriceUpdateDTO> prescriptionPriceUpdateDTOs) {
		this.prescriptionPriceUpdateDTOs = prescriptionPriceUpdateDTOs;
	}

}
