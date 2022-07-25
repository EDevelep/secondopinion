package org.secondopinion.patient.service.rest;

import java.util.Objects;

import org.json.simple.JSONObject;
import org.secondopinion.configurations.CustomRestTemlpateConfig;
import org.secondopinion.enums.SecondOpinionModuleEnum;
import org.secondopinion.patient.dto.Invoice;
import org.secondopinion.patient.dto.InvoiceDTO;
import org.secondopinion.patient.dto.Patientratings;
import org.secondopinion.patient.dto.Prescription;
import org.secondopinion.utils.JSONHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import io.netty.util.internal.StringUtil;

@Component
public class PharmacyRestAPIService {

	@Autowired
	private CustomRestTemlpateConfig customRestTemlpateConfig;
	
	@Value("${pharmacy.fillprescription.request.frompatient}")
	private String fillPrescriptionToPharmacyAPI;
	
	@Value("${pharmacy.ratings.save}")
	private String saveRatingToPharmacyAPI;
	
	@Value("${pharmacy.invoice.update}")
	private String updatePharmacyInvoice;
	/*
	 * public Medicalprescription prescriptionRequestToPharmacy(Medicalprescription
	 * medicalprescription) {
	 * if(StringUtil.isNullOrEmpty(medicalprescription.getPatientName()) ||
	 * Objects.isNull(medicalprescription.getPharmacyaddressId())) { throw new
	 * IllegalArgumentException("Pharmacy Id and patient Name can not be null."); }
	 * medicalprescription.setFromModule(SecondOpinionModuleEnum.PATIENT.name());
	 * 
	 * String url = String.format(fillPrescriptionToPharmacyAPI); String
	 * responseJson = customRestTemlpateConfig.callRestAPI(medicalprescription, url,
	 * HttpMethod.POST, String.class).getData(); if
	 * (!StringUtil.isNullOrEmpty(responseJson)) { JSONObject jsonObject =
	 * JSONHelper.buidJSONObject(responseJson); String invoicestatus = (String)
	 * jsonObject.get(Invoice.FIELD_invoicestatus); Long entityInvoiceId = (Long)
	 * jsonObject.get(Invoice.FIELD_invoiceId); Long prescriptionFillRequestId =
	 * (Long) jsonObject.get("prescriptionFillRequestId"); String pharmacyName =
	 * (String) jsonObject.get("pharmacyName");
	 * medicalprescription.setInvoicestatus(invoicestatus);
	 * medicalprescription.setEntityInvoiceId(entityInvoiceId);
	 * medicalprescription.setPharmacyName(pharmacyName);
	 * medicalprescription.setPrescriptionFillRequestId(prescriptionFillRequestId);
	 * } return medicalprescription; }
	 */
	
	
	

	public Prescription prescriptionRequestToPharmacy(Prescription prescription) {
		if(StringUtil.isNullOrEmpty(prescription.getPatientName()) 
				|| Objects.isNull(prescription.getPharmacyaddressId())) {
			throw new IllegalArgumentException("Pharmacy Id and patient Name can not be null.");
		}
		prescription.setFromModule(SecondOpinionModuleEnum.PATIENT.name());
		
		String url = String.format(fillPrescriptionToPharmacyAPI);
		String responseJson = customRestTemlpateConfig.callRestAPI(prescription,
				url, HttpMethod.POST, String.class).getData();
		if (!StringUtil.isNullOrEmpty(responseJson)) {
			JSONObject jsonObject = JSONHelper.buidJSONObject(responseJson);
			String invoicestatus = (String) jsonObject.get(Invoice.FIELD_invoicestatus);
			Long entityInvoiceId = (Long) jsonObject.get(Invoice.FIELD_invoiceId);
			Long prescriptionFillRequestId = (Long) jsonObject.get("prescriptionFillRequestId");
			String pharmacyName = (String) jsonObject.get("pharmacyName");
			prescription.setInvoicestatus(invoicestatus);
			prescription.setEntityInvoiceId(entityInvoiceId);
			prescription.setPharmacyName(pharmacyName);
			prescription.setPrescriptionFillRequestId(prescriptionFillRequestId);
		}
		return prescription;
	}
	
	public void ratingServiceForPharmecy(Patientratings patientratings) {
		customRestTemlpateConfig.callRestAPI(patientratings, saveRatingToPharmacyAPI, HttpMethod.POST, String.class)
				.getData();

	}

	public void updatePharmacyInvoiceStatusAfterPayment(InvoiceDTO invoiceDTO) {
		String jo = customRestTemlpateConfig.callRestAPI(invoiceDTO, updatePharmacyInvoice, HttpMethod.POST, String.class).getData();
		
		JSONHelper.buidJSONObject(jo);
	}
}
