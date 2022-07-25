package org.secondopinion.configurations;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class CustomBeanConfigurations {

	@Autowired
	private CustomRestTemlpateConfig customRestTemlpateConfig;
	
	@Bean
	public RestTemplate restTemplate() {
		
		if(Objects.isNull(customRestTemlpateConfig)) {
			return new RestTemplate();
		}
		
		RestTemplate restTemplate = new RestTemplate(customRestTemlpateConfig.getSSLRequestFactory());
		//restTemplate.setInterceptors(interceptors);
		return restTemplate;
	}
	
}
