package com.example.demo.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.example.demo.dto.Member;
import com.example.demo.service.MemberService;
import com.example.demo.service.PageViewLogItemService;

@Component("beforeActionInterceptor")
public class BeforeActionInterceptor implements HandlerInterceptor{
	@Autowired
	MemberService memberService;
	
	@Autowired
	PageViewLogItemService pageViewLogItemService;

	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception {
		
		boolean isLogined = false;
		int memberUserId = 0;
		Member memberUser = null;
		
		HttpSession session = req.getSession();
		
		if ( session.getAttribute("memberUserId") != null ) {
			isLogined = true;
			memberUserId = Integer.parseInt((String)session.getAttribute("memberUserId"));
			memberUser = memberService.findById(memberUserId);
		}
		
		req.setAttribute("isLogined", isLogined);
		req.setAttribute("memberUserId", memberUserId);
		req.setAttribute("memberUser", memberUser);
		
		pageViewLogItemService.addLog(req);
		
		return HandlerInterceptor.super.preHandle(req, res, handler);
	}
}
