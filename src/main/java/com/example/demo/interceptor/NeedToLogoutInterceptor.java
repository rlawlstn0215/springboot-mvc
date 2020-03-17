package com.example.demo.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component("needToLogoutInterceptor")
public class NeedToLogoutInterceptor implements HandlerInterceptor{
	
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception {
		boolean isLogined = (boolean) req.getAttribute("isLogined");
		
		if(isLogined) {
			res.setContentType("text/html; charset=UTF-8");
			res.getWriter().append("<script>");
			res.getWriter().append("alert('이미 로그인이 되어있습니다.');");
			res.getWriter().append("history.back();");
			res.getWriter().append("</script>");
			
			return false;
		}
		
		return HandlerInterceptor.super.preHandle(req, res, handler);
	}
}
