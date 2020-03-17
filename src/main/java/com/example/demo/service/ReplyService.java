package com.example.demo.service;

import java.util.List;
import java.util.Map;

import com.example.demo.dto.ArticleReply;

public interface ReplyService {
	public Map<String, Object> writeReply(Map<String, Object> param);
	public List<ArticleReply> getRepliesFrom(int articleId, int from);
	public Map<String, Object> deleteReply(int id, int memberUserId);
	public Map<String, Object> modifyReply(Map<String, Object> param, int memberUserId);
}
