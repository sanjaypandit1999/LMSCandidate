package com.bridgelabz.lmscandidate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ApplicationContext;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
@EnableEurekaClient
public class LmsCandidateApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(LmsCandidateApplication.class, args);
		log.info("LMS Cndidate App Started in {} Environment", context.getEnvironment().getProperty("environment"));
//		log.info("lms DB User is {}", context.getEnvironment().getProperty("spring.datasource.username"));
	}

}
