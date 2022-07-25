
package org.secondopinions.elasticsearch;

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
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ElasticsearchClientConfig {

  /*
   * @Value("${elasticsearchport}") private Integer elasticsearchport;
   * 
   * @Value("${clustername}") private String clustername;
   * 
   * @Value("${elasticsearchusername}") private String elasticsearchusername;
   * 
   * @Value("${elasticsearchserverurl}") private String elasticsearchserverurl;
   * 
   * @Value("${elasticsearchpswd}") private String elasticsearchpswd;
   */

  @Bean
  public RestHighLevelClient elasticRestClient() {

    final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
    credentialsProvider.setCredentials(AuthScope.ANY,
        new UsernamePasswordCredentials("bhargava", "reddy@123"));

    Settings settings = Settings.builder().put("cluster.name", "hvantage").build();
    RestClientBuilder clientBuilder = RestClient.builder(new HttpHost("147.135.223.84", 9200))
        .setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {

          @Override
          public HttpAsyncClientBuilder customizeHttpClient(
              HttpAsyncClientBuilder httpClientBuilder) {
            return httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
          }
        });
    return new RestHighLevelClient(clientBuilder);

  }
}
