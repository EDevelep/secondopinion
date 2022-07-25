package org.secondopinion.diagnosticcenter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.context.request.RequestContextListener;
import org.secondopinion.configurations.AppProperties;
import org.secondopinion.configurations.AppProperties.Smtp;
import org.secondopinion.utils.MailProperties;
@SpringBootApplication
@EnableConfigurationProperties
@ComponentScan(basePackages = {"org.secondopinion"})
@EnableTransactionManagement
@EnableAsync(proxyTargetClass = true)
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableScheduling
public class DiagnosticcenterServiceApplication {

	
	@Autowired
	private AppProperties appProperties;
	
	public static void main(String[] args) {
		SpringApplication.run(DiagnosticcenterServiceApplication.class, args);
	}

	@Bean
	public HttpMessageConverters customConverters() {
		ByteArrayHttpMessageConverter arrayHttpMessageConverter = new ByteArrayHttpMessageConverter();
		return new HttpMessageConverters(arrayHttpMessageConverter);
	}

	@Bean
	public MailProperties mailProperties() {
		Smtp smtp = appProperties.getSmtp();
		return new MailProperties(smtp.getPort(), smtp.getUser(), 
				smtp.getPassword(), smtp.getHost(),
				smtp.getFromaddress());
	}

	@Bean 
	public RequestContextListener requestContextListener(){
		return new RequestContextListener();
	} 

	
}
