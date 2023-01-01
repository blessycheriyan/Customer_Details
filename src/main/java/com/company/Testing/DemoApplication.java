package com.company.Testing;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@EnableSwagger2
@RestController
@SpringBootApplication
public class DemoApplication {
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.company.Testing"))
//				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any())
				.build()
				.apiInfo(getName());
	}
	@GetMapping
	ResponseEntity<String> hello() {
	    return new ResponseEntity<>("Hello World its me!", HttpStatus.OK);
	}
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);

	}
	
	 private ApiInfo getName()
	    {
	        return new ApiInfo( "Demo For Swagger 2",
	                "Return Name",
	                "V1",
	                null,
	                new Contact("Checkout-","bless","bless1@gmail.com"),
	                null,
	                null,
	                Collections.emptyList());
	               
		}  
		

}
