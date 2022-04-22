package com.training.employeeservice.config;

import javax.activation.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class corsConfig {
	@Bean
	public WebMvcConfigurer corsConfiguration()
	{
		return new WebMvcConfigurer() {
			
			@Override
			public void addCorsMappings(CorsRegistry registry)
			{
				//registry.addMapping("/**").allowedOrigins("http://www.udemy.com","http://localhost.com");
				registry.addMapping("/**").allowedOrigins("*");
			}
		};
		
	}
}
