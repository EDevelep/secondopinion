
package org.secondopinion.filter.interceptors;

import java.util.List;

import org.secondopinion.common.dto.BulkRequest;
import org.secondopinion.common.dto.ExceptionIndexDTO;
import org.secondopinion.common.dto.RequestDTO;
import org.secondopinion.configurations.CustomRestTemlpateConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

@Component
public class RequestService {

	@Autowired
	private CustomRestTemlpateConfig customRestTemlpateConfig;

	@Value("${saveReguestResponce}")
	private String saveReguestResponce;


	@Value("${saveExceptionReguest}")
	private String saveExceptionReguest;
	private void saveElasticSearchRequest(String uri, RequestDTO requestDTO) {

		customRestTemlpateConfig.callRestAPIInternal(requestDTO, uri, HttpMethod.POST, String.class).getData();

	}

	public void saveElasticSearchRequest(RequestDTO requestDTO) {
		String uri = String.format(saveReguestResponce, requestDTO);
		saveElasticSearchRequest(uri, requestDTO);
	}
	
	public void saveElasticSearchRequestListOfObject( BulkRequest request) {
		String uri = String.format(saveReguestResponce, request);
		saveElasticSearchRequestListOfObject(uri, request);
	}
	
	private void saveElasticSearchRequestListOfObject(String uri, BulkRequest request) {

		customRestTemlpateConfig.callRestAPIInternal(request, uri, HttpMethod.POST, String.class).getData();

	}

	public void saveElasticSearchExceptionObject(ExceptionIndexDTO request) {
		String uri = String.format(saveExceptionReguest, request);
		saveElasticSearchExceptionObject(uri, request);
	}
	
	private void saveElasticSearchExceptionObject(String uri, ExceptionIndexDTO request) {

		customRestTemlpateConfig.callRestAPIInternal(request, uri, HttpMethod.POST, String.class).getData();

	}
}
