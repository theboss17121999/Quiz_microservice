package com.example.quiz_service;

import com.example.quiz_service.filter.RateLimitingFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableFeignClients
public class QuizServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuizServiceApplication.class, args);
	}
	@Bean
	public FilterRegistrationBean<RateLimitingFilter> rateLimitingFilter() {
		FilterRegistrationBean<RateLimitingFilter> registration = new FilterRegistrationBean<>();
		registration.setFilter(new RateLimitingFilter());
		registration.addUrlPatterns("/quiz/*");
		return registration;
	}

}
