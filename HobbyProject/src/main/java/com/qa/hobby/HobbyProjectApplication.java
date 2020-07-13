package com.qa.hobby;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class HobbyProjectApplication {

	public static void main(String[] args) {
		ApplicationContext beanBag =SpringApplication.run(HobbyProjectApplication.class, args);
	}

}
