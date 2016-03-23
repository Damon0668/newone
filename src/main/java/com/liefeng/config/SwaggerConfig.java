package com.liefeng.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.async.DeferredResult;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	/**
	 * 指点
	 * @return
	 */
	@Bean
	public Docket finger() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("finger")
				.genericModelSubstitutes(DeferredResult.class)
				.useDefaultResponseMessages(false)
				.forCodeGeneration(true)
				.pathMapping("/")
				.select()
				.paths(PathSelectors.regex("/api/.*"))
				.build()
				.apiInfo(apiInfo());
	}
	
	/**
	 * 工作台
	 * @return
	 */
	@Bean
	public Docket work() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("work")
				.genericModelSubstitutes(DeferredResult.class)
				.useDefaultResponseMessages(false)
				.forCodeGeneration(true)
				.pathMapping("/")
				.select()
				.paths(PathSelectors.regex("/api/auth/.*"))
				.build()
				.apiInfo(apiInfo());
	}
	
	private ApiInfo apiInfo(){
		return new ApiInfo("列丰-物业系统API", "物业系统API", "1.0", "cock burst!", "@liefeng.com", "", "http://liefeng.com");
	}
}
