package org.secondopinion.pharmacy.dto;

import java.util.List;

public class PrescriptionFillRequestUpdateDTO {

	private Long prescriptionFillRequestId;
	private String prescriptiontype;
	private InvoiceUpdateDTO invoiceUpdateDTO;
	private List<PrescriptionPriceUpdateDTO> prescriptionPriceUpdateDTOs;
	public Long getPrescriptionFillRequestId() {
		return prescriptionFillRequestId;
	}

	public void setPrescriptionFillRequestId(Long prescriptionFillRequestId) {
		this.prescriptionFillRequestId = prescriptionFillRequestId;
	}

	public InvoiceUpdateDTO getInvoiceUpdateDTO() {
		return invoiceUpdateDTO;
	}

	public void setInvoiceUpdateDTO(InvoiceUpdateDTO invoiceUpdateDTO) {
		this.invoiceUpdateDTO = invoiceUpdateDTO;
	}

	public List<PrescriptionPriceUpdateDTO> getPrescriptionPriceUpdateDTOs() {
		return prescriptionPriceUpdateDTOs;
	}

	public void setPrescriptionPriceUpdateDTOs(List<PrescriptionPriceUpdateDTO> prescriptionPriceUpdateDTOs) {
		this.prescriptionPriceUpdateDTOs = prescriptionPriceUpdateDTOs;
	}
	
	public String getPrescriptiontype() {
		return prescriptiontype;
	}

	public void setPrescriptiontype(String prescriptiontype) {
		this.prescriptiontype = prescriptiontype;
	}
}
