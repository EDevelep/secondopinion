package org.secondopinions.elasticsearch;

import java.util.Arrays;
import java.util.Date;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.settings.Settings;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.secondopinions.elasticsearch.model.Request;
import org.secondopinions.elasticsearch.serviceImpl.RequestResponseServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { ElasticsearchApplication.class })
public class ElasticsearchApplicationTest {

	public RestHighLevelClient elasticRestClient() {

		final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
		credentialsProvider.setCredentials(AuthScope.ANY,
				new UsernamePasswordCredentials("elastic", "7CqIxQw8qnTB0ovWhU4a"));

		Settings settings = Settings.builder().put("cluster.name", "elasticsearch").build();
		RestClientBuilder clientBuilder = RestClient.builder(new HttpHost("192.168.1.158", 9200))
				.setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {

					@Override
					public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpClientBuilder) {
						return httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
					}
				});
		return new RestHighLevelClient(clientBuilder);

	}

	@Test
	public void contextLoads() {
		 elasticRestClient();
	}

	@Autowired
	private RequestResponseServiceImp requestResponseServiceImp;

	@Test
	public void saveRequestResponce() {
		Request request = new Request();
		request.setIpAddress("127.09.98.98");
		request.setExcutionUrl("localhost");
		request.setUserId(1L);
		request.setTotalTimeTaken(12L);
		request.setRequestEndTime(new Date());
		request.setModuleType("PATIENT");
		request.setRequestStartTime(new Date());
		requestResponseServiceImp.saveRequestResponse(request);
	}
}
