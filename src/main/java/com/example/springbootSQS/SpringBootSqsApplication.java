package com.example.springbootSQS;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.aws.autoconfigure.context.ContextStackAutoConfiguration;

@SpringBootApplication(exclude = {ContextStackAutoConfiguration.class})
public class SpringBootSqsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootSqsApplication.class, args);
	}

}
