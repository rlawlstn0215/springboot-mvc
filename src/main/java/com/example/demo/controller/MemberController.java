package com.example.demo.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dto.Member;
import com.example.demo.service.MemberService;

@Controller
public class MemberController {
	@Autowired
	private MemberService memberService;
	
	@RequestMapping("/member/doSecession")
	public String doSescession(HttpSession session, Model model) {
		int secession = Integer.parseInt((String)session.getAttribute("memberUserId"));
		
		memberService.secession(secession);
		
		session.removeAttribute("memberUserId");
		
		model.addAttribute("alertMsg", "회원 탈퇴 되었습니다.");
		model.addAttribute("redirectUrl", "/home/main");
		
		return "common/js";
	}
	
	@RequestMapping("/member/modify")
	public String showModify(HttpSession session) {
		return "member/modify";
	}
	@RequestMapping("/member/doModify")
	public String doModify(@RequestParam Map<String, Object> param, Model model) {
		Map<String, Object> rs = memberService.doModify(param);
		
		model.addAttribute("alertMsg", rs.get("msg"));
		model.addAttribute("redirectUrl", "/home/main");
		
		return "common/js";
	}
	@RequestMapping("/member/mypage")
	public String showMypage() {
		return "member/mypage";
	}
	
	//비밀번호 찾기
	@RequestMapping("/member/findUserPw")
	public String showFindUserPw() {
		return "member/findUserPw";
	}
	@RequestMapping("/member/doFindUserPw")
	public String doFindUserPw(@RequestParam Map<String, Object> param, Model model) {
		Map<String, Object> rs = memberService.findUserPw(param);
		
		String resultCode = (String) rs.get("resultCode");
		if(resultCode.startsWith("F-")) {
			model.addAttribute("alertMsg", rs.get("msg"));
			model.addAttribute("historyBack", true);
			
			return "common/js";
		}
		model.addAttribute("alertMsg", rs.get("msg"));
		model.addAttribute("redirectUrl", "/member/login");
		
		return "common/js";
	}
	//아이디 찾기
	@RequestMapping("/member/findUserId")
	public String showFindUserId() {
		return "member/findUserId";
	}
	@RequestMapping("/member/doFindUserId")
	public String doFindUserId(@RequestParam Map<String, Object> param, Model model) {
		Map<String, Object> rs = memberService.findUserId(param);
		
		String resultCode = (String) rs.get("resultCode");
		if(resultCode.startsWith("F-")) {
			model.addAttribute("alertMsg", rs.get("msg"));
			model.addAttribute("historyBack", true);
			
			return "common/js";
		}
		
		model.addAttribute("alertMsg", rs.get("msg"));
		model.addAttribute("redirectUrl", "/member/login");
		
		return "common/js";
	}
	
	@RequestMapping("/member/doLogout")
	public String doLogout(HttpSession session) {
		session.removeAttribute("memberUserId");
		
		return "redirect:/home/main";
	}
	
	@RequestMapping("/member/login")
	public String showLogin() {
		return "member/login";
	}
	@RequestMapping("/member/doLogin")
	public String doLogin(@RequestParam Map<String, Object> param, Model model, HttpSession session) {
		Map<String, Object> rs = memberService.login(param);
		
		String resultCode = (String) rs.get("resultCode");
		Member memberUser = (Member) rs.get("member");
		
		if(resultCode.startsWith("F-")) {
			model.addAttribute("alertMsg", rs.get("msg"));
			model.addAttribute("historyBack", true);
			
			return "common/js";
		}
		
		session.setAttribute("memberUserId", memberUser.getId() + "");
		
		model.addAttribute("alertMsg", rs.get("msg"));
		model.addAttribute("redirectUrl", "/home/main");
		
		return "common/js";
	}
	
	@RequestMapping("/member/join")
	public String showJoin() {
		return "member/join";
	}
	@RequestMapping("/member/doJoin")
	public String doJoin(@RequestParam Map<String, Object> param, Model model) {
		Map<String, Object> rs = memberService.join(param);
		
		String resultCode = (String) rs.get("resultCode");
		if(resultCode.startsWith("F-")) {
			model.addAttribute("alertMsg", rs.get("msg"));
			model.addAttribute("historyBack", true);
			
			return "common/js";
		}
		model.addAttribute("alertMsg", rs.get("msg"));
		model.addAttribute("redirectUrl", "/member/login");
		
		return "common/js";
	}
}
