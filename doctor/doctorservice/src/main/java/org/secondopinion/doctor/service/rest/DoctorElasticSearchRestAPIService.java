package org.secondopinion.doctor.service.rest;

import org.secondopinion.configurations.CustomRestTemlpateConfig;

import org.secondopinion.doctor.dto.Doctor;
import org.secondopinion.doctor.dto.DoctorAddress;
import org.secondopinion.doctor.dto.DoctorSaarchDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

@Component
public class DoctorElasticSearchRestAPIService {
	@Autowired
	private CustomRestTemlpateConfig customRestTemlpateConfig;

	@Value("${elasticdoctorurl}")
	private String updateDoctorElasticSearch;

	@Value("${updateDoctorAddress}")
	private String updateDoctorAddress;
	private void updateDoctorElasticSearchData(String uri, DoctorSaarchDTO doctorSaarchDTO) {

		customRestTemlpateConfig.callRestAPI(doctorSaarchDTO, uri, HttpMethod.POST, String.class).getData();

	}

	public void updateDoctorElasticSearchData(DoctorSaarchDTO doctor) {
		
		String uri = String.format(updateDoctorElasticSearch, doctor);
		updateDoctorElasticSearchData(uri, doctor);
	}

	public void updateDoctorElasticSearchData(DoctorAddress doctorAddress) {
		DoctorSaarchDTO doctorSaarchDTO = DoctorSaarchDTO.buildDoctorDTOObject(doctorAddress);

		String uri = String.format(updateDoctorAddress, doctorSaarchDTO);
		updateDoctorElasticSearchData(uri, doctorSaarchDTO);
	}

}
