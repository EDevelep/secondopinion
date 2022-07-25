package org.secondopinion.configurations;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import javax.net.ssl.SSLContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.secondopinion.common.dto.RequestDTO;
import org.secondopinion.filter.MutableHttpServletRequest;
import org.secondopinion.request.Response;
import org.secondopinion.utils.UserMgmtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * 
 * @author vishnue
 *
 */
@Component
public class CustomRestTemlpateConfig {

	@Autowired
	private HttpServletRequest httpServletRequest;

	@Autowired
	private RestTemplate restTemplate;

	public HttpComponentsClientHttpRequestFactory getSSLRequestFactory() {
		TrustStrategy trustStrategy = (X509Certificate[] chain, String authType) -> true;
		SSLContext sslContext = null;
		try {
			sslContext = org.apache.http.ssl.SSLContexts.custom().loadTrustMaterial(null, trustStrategy).build();
		} catch (NoSuchAlgorithmException | KeyStoreException | KeyManagementException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);
		CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(csf).build();
		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
		requestFactory.setHttpClient(httpClient);
		return requestFactory;
	}

	public <INPUT, OUTPUT> Response<OUTPUT> callRestAPI(String url, HttpMethod method, Class<OUTPUT> clazz) {
		return callRestAPI(null, url, method, clazz);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public <INPUT, OUTPUT> Response<OUTPUT> callRestAPI(INPUT t, String url, HttpMethod method, Class<OUTPUT> clazz) {

		// restTemplate.setInterceptors(interceptors);
		HttpEntity<?> request = buildHttpEntity(t);
		ResponseEntity<Response> responseEntity = restTemplate.exchange(url, method, request, Response.class);
		Response<OUTPUT> response = responseEntity.getBody();
		if (!responseEntity.getStatusCode().equals(HttpStatus.OK)
				&& !responseEntity.getStatusCode().equals(HttpStatus.CREATED)) {
			throw new RuntimeException(response.getMessage());
		}

		return response;
	}

	private <INPUT> HttpEntity<?> buildHttpEntity(INPUT t) {
		HttpEntity<?> entityRequest = new HttpEntity<>(buildHttpHeader());
		if (Objects.nonNull(t)) {
			entityRequest = new HttpEntity<>(t, buildHttpHeader());
		}
		return entityRequest;
	}

	private HttpHeaders buildHttpHeader() {
		MutableHttpServletRequest mutableHttpServletRequest = MutableHttpServletRequest.INSTANCE(httpServletRequest);
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.set(UserMgmtUtil.AUTHORIZATION, mutableHttpServletRequest.getHeader(UserMgmtUtil.AUTHORIZATION));
		headers.set(UserMgmtUtil.MODULENAME, mutableHttpServletRequest.getHeader(UserMgmtUtil.MODULENAME));
		headers.set(UserMgmtUtil.FORUSERID, mutableHttpServletRequest.getHeader(UserMgmtUtil.FORUSERID));
		return headers;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public <INPUT, OUTPUT> Response<OUTPUT> callRestAPIInternal(INPUT t, String url, HttpMethod method, Class<OUTPUT> clazz) {

		// restTemplate.setInterceptors(interceptors);
		HttpEntity<?> request = buildHttpEntityForInternalCall(t);
		ResponseEntity<Response> responseEntity = restTemplate.exchange(url, method, request, Response.class);
		Response<OUTPUT> response = responseEntity.getBody();
		if (!responseEntity.getStatusCode().equals(HttpStatus.OK)
				&& !responseEntity.getStatusCode().equals(HttpStatus.CREATED)) {
			throw new RuntimeException(response.getMessage());
		}

		return response;
	}

	private <INPUT> HttpEntity<?> buildHttpEntityForInternalCall(INPUT t) {
		HttpEntity<?> entityRequest = new HttpEntity<>(buildHttpHeaderForInternalCall());
		if (Objects.nonNull(t)) {
			entityRequest = new HttpEntity<>(t, buildHttpHeaderForInternalCall());
		}
		return entityRequest;
	}
	
	private HttpHeaders buildHttpHeaderForInternalCall() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.set(UserMgmtUtil.AUTHORIZATION, "NO_AUTH");
		headers.set(UserMgmtUtil.MODULENAME, "INTERNAL_SERVICE_CALL");

		return headers;
	}
}
