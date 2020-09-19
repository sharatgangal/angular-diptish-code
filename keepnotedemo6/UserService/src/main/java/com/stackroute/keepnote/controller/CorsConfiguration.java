package com.stackroute.keepnote.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfiguration {

	
	//GET and POST
	// DELETE, PUT
	
	@Bean
	public WebMvcConfigurer corsConfigurer()
	{
		return new WebMvcConfigurer()
				{
			@Override
			  public void addCorsMappings(CorsRegistry registry)
			  {
				registry.addMapping("/**").allowedOrigins("http://localhost:4200").allowedMethods("PUT","DELETE","GET","POST");  
			  }
	};
	}
}
