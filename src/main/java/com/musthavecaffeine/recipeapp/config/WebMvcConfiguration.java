package com.musthavecaffeine.recipeapp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class WebMvcConfiguration extends WebMvcConfigurationSupport {

	@Override
	protected void addCorsMappings(CorsRegistry registry) {
	    
	    registry.addMapping("/**")
	        .allowedMethods("GET", "POST", "PUT", "DELETE")
	        .allowedOrigins("*")
	        .allowedHeaders("*")
	        .allowCredentials(false);
	    }
}
