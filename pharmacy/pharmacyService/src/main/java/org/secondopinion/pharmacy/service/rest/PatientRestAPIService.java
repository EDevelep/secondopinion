package org.secondopinion.pharmacy.service.rest;

import org.secondopinion.configurations.CustomRestTemlpateConfig;
import org.secondopinion.pharmacy.dto.PatientInvoiceUpdateDTO;
import org.secondopinion.pharmacy.dto.PrescriptionPriceUpdateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

@Component
public class PatientRestAPIService {
	@Autowired
	private CustomRestTemlpateConfig customRestTemlpateConfig;
	
	@Value("${patient.invoice.update.requestpayment.api}")
	private String patientInvoiceUpdateForRequestPaymentAPI;
	
	
	@Value("${patient.medcine.update.requestpayment.api}")
	private String patientUpdateMedcineForRequestPaymentAPI;
	
	@Value("${patient.invoice.update.status.api}")
	private String patientInvoiceUpdateWithStatusAPI;

	@Value("${patient.invoice.updateShipping.status.api}")
	private String patientInvoiceShippingUpdateWithStatusAPI;
	
	
	@Value("${patient.invoice.updateShipping.update.api}")
	private String patientInvoiceShippingUpdate;
	
	
	public void updateInvoiceByEntityInvoiceIdWithStatus(PatientInvoiceUpdateDTO patientInvoiceUpdateDTO) {
		customRestTemlpateConfig.callRestAPI(patientInvoiceUpdateDTO,
				patientInvoiceUpdateForRequestPaymentAPI, HttpMethod.POST, String.class).getData();
	}


	public void updateMedcineByEntityInvoiceIdWithStatus(PatientPriceUpdateDTO patientInvoiceUpdateDTO) {
		customRestTemlpateConfig.callRestAPI(patientInvoiceUpdateDTO,
				patientUpdateMedcineForRequestPaymentAPI, HttpMethod.POST, String.class).getData();
	}
	public void patientInvoiceUpdateWithStatusAPI(PatientInvoiceUpdateDTO patientInvoiceUpdateDTO) {
		customRestTemlpateConfig.callRestAPI(patientInvoiceUpdateDTO,
				patientInvoiceUpdateWithStatusAPI, HttpMethod.POST, String.class).getData();
	}
	

	public void patientInvoiceShippingUpdateWithStatusAPI(PatientInvoiceUpdateDTO patientInvoiceUpdateDTO) {
		customRestTemlpateConfig.callRestAPI(patientInvoiceUpdateDTO,
				patientInvoiceShippingUpdateWithStatusAPI, HttpMethod.POST, String.class).getData();
	}
	
	
	public void patientInvoiceShippingUpdate(PatientShippingUpdateDTO patientInvoiceUpdateDTO) {
		customRestTemlpateConfig.callRestAPI(patientInvoiceUpdateDTO,
				patientInvoiceShippingUpdate, HttpMethod.POST, String.class).getData();
	}
}
