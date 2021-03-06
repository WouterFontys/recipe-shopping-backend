package com.musthavecaffeine.recipeapp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class CustomWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
			.authorizeRequests()
			.antMatchers("/","/swagger-resources")
			.permitAll();
		
        httpSecurity
        	.csrf()
        	.disable();
        
        httpSecurity
        	.headers()
        	.frameOptions()
        	.disable();
	}
	
}
