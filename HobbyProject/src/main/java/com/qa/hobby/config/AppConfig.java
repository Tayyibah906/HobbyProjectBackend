package com.qa.hobby.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;

public class AppConfig {
	
	@Bean 
	public ModelMapper mapper() {
		return new ModelMapper();
	}

}
