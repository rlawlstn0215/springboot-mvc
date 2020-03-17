package com.example.demo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
	
	@RequestMapping("/part/test")
	public String test() {
		return "part/test";
	}
	
	@RequestMapping("/")
	public String showRedirect() {
		return "redirect:/home/main";
	}
	
	@RequestMapping("/home/main")
	public String ShowMain(Model model) {
		return "home/main";
	}
	@RequestMapping("/home/testRequestInfo")
	@ResponseBody
	public String testRequestInfo(HttpServletRequest req, HttpSession session) {
		String info = "";
		info += "path : " + req.getServletPath();
		info += "<br>";
		info += "queryStr : " + req.getQueryString();
		info += "<br>";
		info += "domain : " + req.getServerName();
		info += "<br>";
		info += "port : " + req.getServerPort();
		info += "<br>";
		info += "url : " + req.getRequestURL();
		info += "<br>";
		info += "loginedMemberId : " + session.getAttribute("memberUserId");
		info += "<br>";
		
		return info;
	}
}
