package org.secondopinion.pharmacy.dto; 

import javax.persistence.Entity;
import javax.persistence.Table;

import org.secondopinion.enums.InvoiceStatusEnum;
import org.secondopinion.pharmacy.domain.BaseInvoice;

@Entity 
@Table (name="invoice")
public class Invoice extends BaseInvoice{
	
	/**
	 * Generate Invoice
	 * 
	 * @param prescriptionFillRequestId
	 * @param prescriptionprices
	 * @param request 
	 * @return Invoice
	 */
	public static Invoice buildNewInvoiceForMedicines(String pharmacyName, FillPrescriptionRequestDTO request) {
		Invoice invoice = new Invoice();
		invoice.setPatientid(request.getPatientId());
		invoice.setPatientname(request.getPatientName());
		invoice.setDoctorid(request.getDoctorId());
		invoice.setDoctorname(request.getDoctorName());
		invoice.setSpecialization(request.getDoctorSpecialization());
		invoice.setInvoicestatus(InvoiceStatusEnum.NEW_REQUEST.name());
		invoice.setPharmacyaddressId(request.getPharmacyaddressId());
		invoice.setPharmacyname(pharmacyName);
		invoice.setPaidamount(0.0d);
		invoice.setActive('Y');
		invoice.setMedicalPrescriptionId(request.getMedicalPrescriptionId());
		invoice.setDiscount(0.0d);
		invoice.setTotal(0.0d);
		
		return invoice;
	}
	
	
}