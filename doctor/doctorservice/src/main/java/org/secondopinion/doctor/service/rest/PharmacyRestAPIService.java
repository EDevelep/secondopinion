package org.secondopinion.doctor.service.rest;

import org.secondopinion.configurations.CustomRestTemlpateConfig;
import org.secondopinion.doctor.dto.Medicalprescription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

@Component
public class PharmacyRestAPIService {

	@Autowired
	private CustomRestTemlpateConfig customRestTemlpateConfig;
	
	@Value("${pharmacy.fillprescription.request.fromdoctor}")
	private String fillPrescriptionToPharmacyAPI;
	
	public void prescriptionRequestToPharmacy(Medicalprescription medicalprescription) {
		customRestTemlpateConfig.callRestAPI(medicalprescription,
				fillPrescriptionToPharmacyAPI, HttpMethod.POST, String.class).getData();
	}
}
