/*
 * package org.secondopinions.elasticsearch;
 * 
 * import java.util.List;
 * 
 * import org.apache.http.HttpHost; import org.apache.http.auth.AuthScope;
 * import org.apache.http.auth.UsernamePasswordCredentials; import
 * org.apache.http.client.CredentialsProvider; import
 * org.apache.http.impl.client.BasicCredentialsProvider; import
 * org.apache.http.impl.nio.client.HttpAsyncClientBuilder; import
 * org.elasticsearch.client.RestClient; import
 * org.elasticsearch.client.RestClientBuilder; import
 * org.elasticsearch.client.RestHighLevelClient; import
 * org.elasticsearch.common.settings.Settings; import org.junit.Test; import
 * org.junit.runner.RunWith;
 * 
 * 
 * 
 * import org.secondopinions.elasticsearch.model.Medicine; import
 * org.secondopinions.elasticsearch.model.MedicineLineParser; import
 * org.secondopinions.elasticsearch.repository.ElasticsearchMedicineRepository;
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.boot.test.context.SpringBootTest; import
 * org.springframework.test.context.junit4.SpringRunner;
 * 
 * @RunWith(SpringRunner.class)
 * 
 * @SpringBootTest(classes = { ElasticsearchApplication.class }) public class
 * FileReaderTest {
 * 
 * @Autowired private ElasticsearchMedicineRepository
 * elasticsearchMedicineRepository;
 * 
 * public RestHighLevelClient elasticRestClient() {
 * 
 * final CredentialsProvider credentialsProvider = new
 * BasicCredentialsProvider(); credentialsProvider.setCredentials(AuthScope.ANY,
 * new UsernamePasswordCredentials("bhargava", "reddy@123"));
 * 
 * Settings settings = Settings.builder().put("cluster.name",
 * "hvantage").build(); RestClientBuilder clientBuilder = RestClient.builder(new
 * HttpHost("147.135.223.84", 9200)) .setHttpClientConfigCallback(new
 * RestClientBuilder.HttpClientConfigCallback() {
 * 
 * @Override public HttpAsyncClientBuilder
 * customizeHttpClient(HttpAsyncClientBuilder httpClientBuilder) { return
 * httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider); } });
 * return new RestHighLevelClient(clientBuilder);
 * 
 * }
 * 
 * @Test public void contextLoads() { elasticRestClient(); }
 * 
 * @Test public void test() { FileReader<Medicine, MedicineLineParser> reader =
 * new FileReader<>("E:\\drug_file_1.csv"); // reader.setPageSize(500); // This
 * line is not needed
 * 
 * while (!reader.isEof()) { List<Medicine> medicines = reader.getNext();
 * elasticsearchMedicineRepository.saveAll(medicines);
 * 
 * } } }
 */