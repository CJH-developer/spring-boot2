package com.coding404.myweb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.coding404.myweb.interceptor.UserAuthHandler;

// 스프링 설정 파일
@Configuration
public class WebConfig implements WebMvcConfigurer{

	//@Autowired
	//private UserAuthHandler userAuthHandler;
	
	// 인터셉터를 등록
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		registry.addInterceptor(userAuthHandler())
		.addPathPatterns("/product/**")
		.addPathPatterns("/user/**")
		.excludePathPatterns("/user/login")
		.excludePathPatterns("/user/loginForm")
		.excludePathPatterns("/user/join");
	}
	
	@Bean
	public UserAuthHandler userAuthHandler() {
		return new UserAuthHandler();
	}
}
