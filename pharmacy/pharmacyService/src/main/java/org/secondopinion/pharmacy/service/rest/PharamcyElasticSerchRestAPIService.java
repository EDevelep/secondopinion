
package org.secondopinion.pharmacy.service.rest;

import org.json.simple.JSONObject;
import org.secondopinion.configurations.CustomRestTemlpateConfig;
import org.secondopinion.pharmacy.dto.Inventory;
import org.secondopinion.utils.JSONHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

@Component
public class PharamcyElasticSerchRestAPIService {

	@Autowired
	private CustomRestTemlpateConfig customRestTemlpateConfig;

	@Value("${elastic.pharmacy.url}")
	private String pharmacyElasticSerchSave;

	@Value("${save.elastic.medaction}")
	private String saveElasticMedication;

	public Inventory getMedication(String drugName) {
		Inventory inventory=new Inventory();
		String uri = String.format(pharmacyElasticSerchSave, drugName);
		String jo = customRestTemlpateConfig.callRestAPI(uri, HttpMethod.GET, String.class).getData();
		JSONObject gson = JSONHelper.buidJSONObject(jo);
		String name=(String) gson.get("name");
		String manufacturers=(String) gson.get("manufacturers");
		inventory.setDrugname(name);
		inventory.setManufacturer(manufacturers);
		return inventory;
	}

	public void saveMedication(Inventory inventory) {

		String jo = customRestTemlpateConfig.callRestAPI(inventory, saveElasticMedication, HttpMethod.POST, String.class)
				.getData();
		JSONObject gson = JSONHelper.buidJSONObject(jo);
		System.out.println(gson);

	}
}
