package com.example.demo.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component("needToLoginInterceptor")
public class NeedToLoginInterceptor implements HandlerInterceptor {

	public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception {
		boolean isLogined = (boolean) req.getAttribute("isLogined");

		if (isLogined == false) {
			res.setContentType("text/html; charset=UTF-8");
			res.getWriter().append("<script>");
			res.getWriter().append("alert('로그인 후 이용해주세요.');");
			res.getWriter().append("location.replace('/member/login');");
			res.getWriter().append("</script>");

			return false;
		}
		return HandlerInterceptor.super.preHandle(req, res, handler);
	}
}
