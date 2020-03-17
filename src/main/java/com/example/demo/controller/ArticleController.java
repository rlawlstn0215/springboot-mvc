package com.example.demo.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dto.Article;
import com.example.demo.dto.Board;
import com.example.demo.service.BoardService;

@Controller
public class ArticleController {
	@Autowired
	private BoardService boardService;
	
	@RequestMapping("/article/doDelete")
	public String doDelete(int id, Model md, HttpServletRequest req) {
		Article article = boardService.findByArticleId(id);
		
		if(article == null) {
			md.addAttribute("alertMsg", "해당 게시물은 존재하지 않습니다.");
			md.addAttribute("historyBack", true);
			
			return "common/js";
		}
		
		int boardId = article.getBoardId();
		int memberUserId = (int)req.getAttribute("memberUserId");
		
		Map<String, Object> rs = boardService.deleteArticle(id, memberUserId);
		
		String resultCode = (String) rs.get("resultCode");
		if(resultCode.startsWith("F-")) {
			md.addAttribute("alertMsg", rs.get("msg"));
			md.addAttribute("historyBack", true);
			
			return "common/js";
		}
		md.addAttribute("alertMsg", rs.get("msg"));
		md.addAttribute("redirectUrl", "/article/list?boardId=" + boardId);
		
		return "common/js";
	}
	
	@RequestMapping("/article/modify")
	public String showModify(@RequestParam Map<String, Object> param, Model md) {
		String idStr = (String) param.get("id");
		int id = 0;
		if(idStr == null) {
			md.addAttribute("alertMsg", "게시물 정보가 필요합니다.");
			md.addAttribute("historyBack", true);
			return "common/js";
		} else {
			id = Integer.parseInt(idStr);
		}
		
		Article article = boardService.findByArticleId(id);
		boardService.getMemberUserId(id);
		
		md.addAttribute("article", article);
		
		return "article/modify";
	}
	@RequestMapping("/article/doModify")
	public String doModify(@RequestParam Map<String, Object> param, Model md, HttpServletRequest req) {
		String idStr = (String) param.get("id");
		if(idStr == null) {
			md.addAttribute("alertMsg", "게시물 정보가 필요합니다.");
			md.addAttribute("historyBack", true);
			return "common/js";
		}
		
		int memberUserId = (int)req.getAttribute("memberUserId");
		
		Map<String, Object> rs = boardService.modifyArticle(param, memberUserId);
		
		String resultCode = (String) rs.get("resultCode");
		if(resultCode.startsWith("F-")) {
			md.addAttribute("alertMsg", rs.get("msg"));
			md.addAttribute("historyBack", true);
			
			return "common/js";
		}
		
		md.addAttribute("alertMsg", rs.get("msg"));
		md.addAttribute("redirectUrl", "/article/detail?id=" + idStr);
		
		return "common/js";
	}
	
	@RequestMapping("/article/list")
	public String showList(@RequestParam Map<String, Object> param, Model md) {
		String boardIdStr = (String) param.get("boardId");
		
		int boardId = 0;
		
		if(boardIdStr == null) {
			md.addAttribute("alertMsg", "게시판 정보가 필요합니다.");
			md.addAttribute("historyBack", true);
			
			return "common/js";
		} else {
			boardId = Integer.parseInt(boardIdStr);
		}
		
		Board board = boardService.findByBoardId(boardId);
		
		if(board == null) {
			md.addAttribute("alertMsg", "존재하지 않는 게시판 입니다.");
			md.addAttribute("historyBack", true);
			
			return "common/js";
		}
		
		if(param.get("page") == null || param.get("page").equals("")) {
			param.put("page", 1);
		}
		
		Map<String, Object> rs = boardService.getArticleList(param);
		
		md.addAttribute("board", board);
		md.addAttribute("articleList", rs.get("articleList"));
		md.addAttribute("page", rs.get("pageRs"));
		
		return "article/list";
	}
	
	@RequestMapping("/article/detail")
	public String showDetail(@RequestParam Map<String, Object> param, Model md) {
		String idStr = (String) param.get("id");
		
		int id = 0;
		
		if(idStr == null) {
			md.addAttribute("alertMsg", "게시물 정보가 필요합니다.");
			md.addAttribute("historyBack", true);
			
			return "common/js";
		} else {
			id = Integer.parseInt(idStr);
		}
		
		Article article = boardService.findByArticleId(id);
		if(article == null) {
			md.addAttribute("alertMsg", "해당 게시물은 존재하지 않습니다.");
			md.addAttribute("historyBack", true);
			
			return "common/js";
		}
		
		md.addAttribute("article", article);
		
		return "article/detail";
	}
	
	@RequestMapping("/article/write")
	public String showWrite(@RequestParam Map<String, Object> param, Model md) {
		String boardIdStr = (String) param.get("boardId");
		
		int boardId = 0;
		
		if(boardIdStr == null) {
			md.addAttribute("alertMsg", "게시판 정보가 필요합니다.");
			md.addAttribute("historyBack", true);
			
			return "common/js";
		} else {
			boardId = Integer.parseInt(boardIdStr);
		}
		
		Board board = boardService.findByBoardId(boardId);
		if(board == null) {
			md.addAttribute("alertMsg", "존재하지 않는 게시판 입니다.");
			md.addAttribute("historyBack", true);
			
			return "common/js";
		}
		
		md.addAttribute("board", board);
		
		return "article/write";
	}
	@RequestMapping("/article/doWrite")
	public String doWrite(@RequestParam Map<String, Object> param, Model md, HttpServletRequest req) {
		String boardIdStr = (String) param.get("boardId");
		
		if(boardIdStr == null) {
			md.addAttribute("alertMsg", "게시판 정보가 필요합니다.");
			md.addAttribute("historyBack", true);
			
			return "common/js";
		}
		
		int memberUserId = (int)req.getAttribute("memberUserId");
		param.put("memberId", memberUserId);
		
		Map<String, Object> rs = boardService.writeArticle(param);
		
		md.addAttribute("alertMsg", rs.get("msg"));
		md.addAttribute("redirectUrl", "/article/detail?id=" + rs.get("id"));
		
		return "common/js";
	}
}
