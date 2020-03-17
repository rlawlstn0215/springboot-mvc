package com.example.demo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.ReplyDao;
import com.example.demo.dto.ArticleReply;

@Service
public class ReplyServiceImpl implements ReplyService{
	@Autowired
	private ReplyDao replyDao;

	@Override
	public Map<String, Object> writeReply(Map<String, Object> param) {
		Map<String, Object> rs = new HashMap<>();
		
		replyDao.writeReply(param);
		
		rs.put("resultCode", "S-1");
		rs.put("msg", "작성을 완료했습니다.");
		
		return rs;
	}

	@Override
	public List<ArticleReply> getRepliesFrom(int articleId, int from) {
		return replyDao.getRepliesFrom(articleId, from);
	}

	@Override
	public Map<String, Object> deleteReply(int id, int memberUserId) {
		Map<String, Object> rs = new HashMap<>();
		
		ArticleReply articleReply = replyDao.getReply(id);
		
		if(memberUserId != articleReply.getMemberId()) {
			rs.put("resultCode", "F-1");
			rs.put("msg", "권한이 없습니다.");
			
			return rs;
		}
		
		replyDao.deleteReply(id);
		
		rs.put("resultCode", "S-1");
		rs.put("msg", id + "번 글이 삭제되었습니다.");
		rs.put("id", id);
		
		return rs;
	}

	@Override
	public Map<String, Object> modifyReply(Map<String, Object> param, int memberUserId) {
		Map<String, Object> rs = new HashMap<>();
		
		int id = (int) param.get("id");
		
		ArticleReply articleReply = replyDao.getReply(id);
		
		if(memberUserId != articleReply.getMemberId()) {
			rs.put("resultCode", "F-1");
			rs.put("msg", "권한이 없습니다.");
			
			return rs;
		}
		
		replyDao.modifyReply(param);
		
		rs.put("resultCode", "S-1");
		rs.put("msg", id + "번 글이 수정되었습니다.");
		rs.put("id", id);
		
		return rs;
	}

}
