package org.secondopinion.patient.service.impl;

import java.util.List;

import org.secondopinion.patient.dto.CountryCodes;
import org.secondopinion.patient.dto.CountrycodeDTO;
import org.secondopinion.utils.HttpsClient;
import org.secondopinion.utils.JSONHelper;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

@Service
public class CountryCodeService {

	public List<CountrycodeDTO> getCountrycode(String postalcode) {
		String value = HttpsClient.allPostalDataByPostalCode(postalcode);
		Gson gson = JSONHelper.getGsonWithDate();
		// gson.toJson
		CountryCodes countryCodes = gson.fromJson(value, CountryCodes.class);
		return countryCodes.getResult();

	}
}
