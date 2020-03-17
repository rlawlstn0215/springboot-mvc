package com.example.demo.service;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.BoardDao;
import com.example.demo.dto.Article;
import com.example.demo.dto.Board;
import com.example.demo.util.PagingUtil;

@Service
public class BoardServiceImpl implements BoardService{
	@Autowired
	private BoardDao boardDao;
	
	@Override
	public Board findByBoardId(int boardId) {
		return boardDao.findByBoardId(boardId);
	}

	@Override
	public Map<String, Object> writeArticle(Map<String, Object> param) {
		Map<String, Object> rs = new HashMap<>();
		
		boardDao.writeArticle(param);
		
		int id = ((BigInteger) param.get("id")).intValue();
		
		rs.put("resultCode", "S-1");
		rs.put("msg", id + "번 글이 생성되었습니다.");
		rs.put("id", id);
		
		return rs;
	}

	@Override
	public Article findByArticleId(int id) {
		return boardDao.findByArticleId(id);
	}

	@Override
	public Map<String, Object> getArticleList(Map<String, Object> param) {
		Map<String, Object> rs = new HashMap<>();
		
		int total = boardDao.articleCnt(param);
		Map<String, Object> pageRs = PagingUtil.paging(param, total);
		List<Article> articleList = boardDao.getArticleList(param);
		
		rs.put("articleList", articleList);
		rs.put("pageRs", pageRs);
		
		return rs;
	}

	@Override
	public Map<String, Object> modifyArticle(Map<String, Object> param, int memberUserId) {
		Map<String, Object> rs = new HashMap<>();
		
		int id = Integer.parseInt((String) param.get("id"));
		
		Article article = boardDao.getMemberUserId(id);
		
		if(memberUserId != article.getMemberId()) {
			rs.put("resultCode", "F-1");
			rs.put("msg", "해당 글 수정에 대한 권한이 없습니다.");
			
			return rs;
		}
		
		boardDao.modifyArticle(param);
		
		rs.put("resultCode", "S-1");
		rs.put("msg", "해당 글이 수정되었습니다.");
		
		return rs;
	}

	@Override
	public Map<String, Object> deleteArticle(int id, int memberUserId) {
		Map<String, Object> rs = new HashMap<>();
		
		Article article = boardDao.getMemberUserId(id);
		
		if(memberUserId != article.getMemberId()) {
			rs.put("resultCode", "F-1");
			rs.put("msg", "해당 글 삭제에 대한 권한이 없습니다.");
			
			return rs;
		}
		boardDao.deleteArticle(id);
		
		rs.put("resultCode", "S-1");
		rs.put("msg", "해당 글이 삭제되었습니다.");
		
		return rs;
	}

	@Override
	public void getMemberUserId(int id) {
		boardDao.getMemberUserId(id);
	}



}
