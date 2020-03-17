package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dto.ArticleReply;
import com.example.demo.service.ReplyService;

@Controller
public class ReplyController {
	@Autowired
	private ReplyService replyService;
	
	@RequestMapping("/article/doModifyReply")
	@ResponseBody
	public Map<String, Object> doDeleteReply(@RequestParam Map<String, Object> param, HttpServletRequest req){
		param.put("id", Integer.parseInt((String) param.get("id")));
		
		int memberUserId = (int) req.getAttribute("memberUserId");
		
		Map<String, Object> modifyReplyRs = replyService.modifyReply(param, memberUserId);
		
		Map<String, Object> rs = new HashMap<>();
		
		rs.put("resultCode", modifyReplyRs.get("resultCode"));
		rs.put("msg", modifyReplyRs.get("msg"));
		
		return rs;
	}
	
	@RequestMapping("/article/doDeleteReply")
	@ResponseBody
	public Map<String, Object> doDeleteReply(int id, HttpServletRequest req){
		int memberUserId = (int) req.getAttribute("memberUserId");
		
		Map<String, Object> deleteReplyRs = replyService.deleteReply(id, memberUserId);
		
		Map<String, Object> rs = new HashMap<>();
		
		rs.put("resultCode", deleteReplyRs.get("resultCode"));
		rs.put("msg", deleteReplyRs.get("msg"));
		
		return rs;
	}
	
	@RequestMapping("/article/getRepliesFrom")
	@ResponseBody
	public Map<String, Object> getRepliesFrom(int articleId, int from){
		Map<String, Object> rs = new HashMap<>();
		
		List<ArticleReply> messages = replyService.getRepliesFrom(articleId, from);
		
		rs.put("resultCode", "S-1");
		rs.put("msg", "성공");
		rs.put("messages", messages);
		
		return rs;
	}

	@RequestMapping("/article/doWriteReply")
	@ResponseBody
	public Map<String, Object> doWriteReply(@RequestParam Map<String, Object>param, HttpServletRequest req){
		String articleIdStr = (String) param.get("articleId");
		
		Map<String, Object> rs = new HashMap<>();
		
		if(articleIdStr == null) {
			rs.put("resultCode", "F-1");
			rs.put("msg", "게시물 정보가 필요합니다.");
			
			return rs;
		}
		
		int memberUserId = (int) req.getAttribute("memberUserId");
		
		param.put("memberId", memberUserId);
		
		Map<String, Object> writeReplyRs = replyService.writeReply(param);
		
		rs.put("resultCode", writeReplyRs.get("resultCode"));
		rs.put("msg", writeReplyRs.get("msg"));
		
		return rs;
	}
}
