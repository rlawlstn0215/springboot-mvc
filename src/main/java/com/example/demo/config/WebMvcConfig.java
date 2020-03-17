package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	@Autowired
	@Qualifier("beforeActionInterceptor")
	HandlerInterceptor beforeActionInterceptor;

	@Autowired
	@Qualifier("needToLoginInterceptor")
	HandlerInterceptor needToLoginInterceptor;

	@Autowired
	@Qualifier("needToLogoutInterceptor")
	HandlerInterceptor needToLogoutInterceptor;

	public void addInterceptors(InterceptorRegistry registry) {
		///resource로 시작하는 액션을 제외한 모든 액션에 연결
		registry.addInterceptor(beforeActionInterceptor).addPathPatterns("/**").excludePathPatterns("/resource/**");
		
		//로그인이 필요없는 url은 제외
		registry.addInterceptor(needToLoginInterceptor).addPathPatterns("/**").excludePathPatterns("/resource/**")
				.excludePathPatterns("/home/main").excludePathPatterns("/").excludePathPatterns("/member/login")
				.excludePathPatterns("/member/doLogin").excludePathPatterns("/member/join")
				.excludePathPatterns("/member/doJoin").excludePathPatterns("/article/list")
				.excludePathPatterns("/article/detail").excludePathPatterns("/member/findUserId")
				.excludePathPatterns("/member/doFindUserId").excludePathPatterns("/member/findUserPw")
				.excludePathPatterns("/member/doFindUserPw").excludePathPatterns("/chat/list");

		//로그인 상태에 접근할수 없는 url 설정
		registry.addInterceptor(needToLogoutInterceptor).addPathPatterns("/member/login")
				.addPathPatterns("/member/doLogin").addPathPatterns("/member/join").addPathPatterns("/member/doJoin");
	}
}
