package org.secondopinion.ssclient;


import java.net.URLEncoder;
import java.util.Map;
import java.util.Map.Entry;


import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.google.gson.Gson;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.WebResource.Builder;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;

/**
 * @author viswav
 */
public class RestUtil {

	private static final Logger logger = Logger.getLogger(RestUtil.class);


	/**
	 * 
	 * @param uri
	 * @param clazz
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static <T> T securityServiceRequest(String uri, RequestMethodType methodType, Map<String, String> params) throws Exception {

		WebResource webResource = getJerseyClient(uri);
		Gson gson = new Gson();
		ClientResponse response = null;
		String responseType = "application/json";
		
		if(params != null && !params.isEmpty()) {
			for (Entry<String, String> entry : params.entrySet()) {
				String value = URLEncoder.encode(entry.getValue(), "UTF-8").replaceAll("\\+", "%20");
				webResource = webResource.queryParam(entry.getKey(), value);
			}
		}
		Builder builder = webResource.accept(responseType).type(responseType);
		switch (methodType) {
		case POST:
			response = builder.post(ClientResponse.class);
			break;
		case GET:
			response = builder.get(ClientResponse.class);
			break;
		case PUT:
			response = builder.put(ClientResponse.class);
			break;
		case DELETE:
			response = builder.delete(ClientResponse.class);
			break;
		default:
			break;
		}

		if(response == null) {
			throw new Exception("Security response null");
		}
		String res = response.getEntity(String.class);
		SecurityResponse<T> securityResponse = gson.fromJson(res, SecurityResponse.class);
		if(securityResponse.getStatusCode() != 201
				&& securityResponse.getStatusCode() != 200) {
			throw new Exception(securityResponse.getErrorMsg());
		}
		return (T) securityResponse.getValue();
	}

	/**
	 * 
	 * @param url
	 * @return
	 * @throws IllegalArgumentException
	 */
	public static WebResource getJerseyClient(String url) throws Exception{
		if(StringUtils.isEmpty(url )){
			throw new IllegalArgumentException("url can not be empty");
		}
		logger.info("Call to ssecurity service : " + url);
		ClientConfig clientConfigRegisterUser = new DefaultClientConfig();

		clientConfigRegisterUser.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING,Boolean.TRUE);

		Client client = Client.create(clientConfigRegisterUser);
		logger.info("URL : " + url);
		return client.resource(url);
	}

	/**
	 * 
	 * @param url
	 * @return
	 * @throws Exception
	 */
	public static WebResource getJerseyClientWithTimeOut(String url)  throws Exception{
		if(StringUtils.isEmpty(url )){
			throw new IllegalArgumentException("url can not be empty");
		}
		logger.info("Call to ssecurity service : " + url);

		ClientConfig clientConfigRegisterUser = new DefaultClientConfig();

		clientConfigRegisterUser.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING,Boolean.TRUE);

		Client client = Client.create(clientConfigRegisterUser);
		client.setReadTimeout(10000);
		logger.info("URL : " + url);
		return client.resource(url);
	}

	public enum RequestMethodType {
		POST, GET, PUT, DELETE
	}

}
