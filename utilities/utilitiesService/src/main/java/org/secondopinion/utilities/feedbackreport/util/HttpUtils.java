package org.secondopinion.utilities.feedbackreport.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.HttpClientBuilder;

public class HttpUtils {
	
	public static String retrieveResponseAsString(HttpResponse response) throws IOException {
		BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		StringBuffer line = new StringBuffer();
		
		String ln = "";
		
		while ((ln = rd.readLine()) != null) {
			line.append(ln);
		}
		
		String str = line.toString();
		return str;
	}
	
	public static HttpClient getClient() {
		int timeout = 120;
		RequestConfig config = RequestConfig.custom()
				  .setConnectTimeout(timeout * 1000)
				  .setConnectionRequestTimeout(timeout * 1000)
				  .setSocketTimeout(timeout * 1000).build();

		HttpClient client =  HttpClientBuilder.create().setDefaultRequestConfig(config).build();
		return client;
	}
}
