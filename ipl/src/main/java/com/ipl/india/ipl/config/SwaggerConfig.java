package com.ipl.india.ipl.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.ipl.india.ipl.resource"))
				.paths(PathSelectors.ant("/ipl/**")).build().pathMapping("")
				.apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		return new ApiInfo("IPL - SERVICE API DOCUMENTATION",
				"handles api's of IPL management system operations", "1.0", "Terms of service",
				new Contact("ADITYA NIRMALA", "https://www.linkedin.com/in/aditya-nirmala-340709106/", "aditya.nirmala12@gmail.com"), "License of API", "https://www.linkedin.com/in/aditya-nirmala-340709106/",
				Collections.emptyList());
	}
}
