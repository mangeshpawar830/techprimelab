package com.project;

import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.*;
 

@org.springframework.context.annotation.Configuration
public class CorsConfig {

		  @Bean
		  public WebMvcConfigurer Configuration() {
		    return new WebMvcConfigurer() {
		    	
		      @Override	      
		      public void addCorsMappings(CorsRegistry registry) {
		        registry.addMapping("/**")
		        .allowedMethods("GET", "POST", "PUT", "DELETE")
		        .allowedHeaders("*")
		        .allowedOrigins("https://localhost:4200");
		      }
		    };
		  }
}
