package com.example.demo.service;

import java.util.Map;

import com.example.demo.dto.Article;
import com.example.demo.dto.Board;

public interface BoardService {

	public Board findByBoardId(int boardId);

	public Map<String, Object> writeArticle(Map<String, Object> param);

	public Article findByArticleId(int id);

	public Map<String, Object> getArticleList(Map<String, Object> param);

	public Map<String, Object> modifyArticle(Map<String, Object> param, int memberUserId);

	public Map<String, Object> deleteArticle(int id, int memberUserId);

	public void getMemberUserId(int id);



}
