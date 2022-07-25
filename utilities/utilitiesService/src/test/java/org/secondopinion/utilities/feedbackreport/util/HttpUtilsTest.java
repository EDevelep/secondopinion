package org.secondopinion.utilities.feedbackreport.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.hibernate.validator.internal.util.privilegedactions.GetMethod;
import org.junit.Test;

public class HttpUtilsTest {

	@Test
	public void test() {
		
		//‘https://api.worldpostallocations.com/pincode?postalcode='+postal+'&countrycode=IN’

		CloseableHttpClient client = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost("https://api.worldpostallocations.com/pincode");

		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("postalcode", "500017"));
		params.add(new BasicNameValuePair("countrycode", "IN"));
		try {
			httpPost.setEntity(new UrlEncodedFormEntity(params));

			CloseableHttpResponse response = client.execute(httpPost);
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
