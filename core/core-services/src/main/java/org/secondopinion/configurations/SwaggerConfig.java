
package org.secondopinion.configurations;

import org.secondopinion.utils.UserMgmtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by anil on 24/5/17.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurationSupport {

	@Autowired
	private AppProperties appProperties;

	@Bean
	public Docket productApi() {

		Docket docket = new Docket(DocumentationType.SWAGGER_2)
		// .groupName("")
//                .directModelSubstitute(LocalDateTime.class, String.class)
//               .directModelSubstitute(LocalDate.class, String.class)
//               .directModelSubstitute(LocalTime.class, String.class)
//               .directModelSubstitute(ZonedDateTime.class, String.class)
				.select()
				.apis(springfox.documentation.builders.RequestHandlerSelectors
						.basePackage(appProperties.getDb().getPackagesToScan()))
				.paths(PathSelectors.any()).build().pathMapping("/").apiInfo(apiInfo());

		List<SecurityScheme> securitySchemes = new ArrayList<SecurityScheme>();
		securitySchemes.add(new ApiKey(UserMgmtUtil.AUTHORIZATION, UserMgmtUtil.AUTHORIZATION, UserMgmtUtil.HEADER));
		securitySchemes.add(new ApiKey(UserMgmtUtil.MODULENAME, UserMgmtUtil.MODULENAME, UserMgmtUtil.HEADER));
		securitySchemes.add(new ApiKey(UserMgmtUtil.FORUSERID, UserMgmtUtil.FORUSERID, UserMgmtUtil.HEADER));
		docket.securitySchemes(securitySchemes);

		List<SecurityContext> securityContexts = new ArrayList<SecurityContext>();
		securityContexts.add(SecurityContext.builder()
				.securityReferences(Arrays.asList(new SecurityReference[] {
						new SecurityReference(UserMgmtUtil.AUTHORIZATION, new AuthorizationScope[0]) }))
				.forPaths(PathSelectors.regex("/.*")).build());
		securityContexts.add(SecurityContext.builder()
				.securityReferences(Arrays.asList(new SecurityReference[] {
						new SecurityReference(UserMgmtUtil.MODULENAME, new AuthorizationScope[0]) }))
				.forPaths(PathSelectors.regex("/.*")).build());
		securityContexts.add(SecurityContext.builder()
				.securityReferences(Arrays.asList(new SecurityReference[] {
						new SecurityReference(UserMgmtUtil.FORUSERID, new AuthorizationScope[0]) }))
				.forPaths(PathSelectors.regex("/.*")).build());

		docket.securityContexts(securityContexts);
		return docket;
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("HubVantage REST API")
				.description("HubVantage REST API reference for developers")
				.termsOfServiceUrl("https://hubvantage.io/about/")
				.contact(new Contact("HubVantage", "https://hubvantage.io/about/", "support@hubvantage.com"))
				.license("HubVantage").licenseUrl("https://hubvantage.io/licenses/LICENSE-1.0").version("1.0").build();
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}
}
