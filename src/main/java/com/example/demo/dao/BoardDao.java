package com.example.demo.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.dto.Article;
import com.example.demo.dto.Board;

@Mapper
public interface BoardDao {
	public Board findByBoardId(@Param("boardId") int boardId);

	public void writeArticle(Map<String, Object> param);

	public Article findByArticleId(@Param("articleId") int articleId);

	public int articleCnt(Map<String, Object> param);

	public List<Article> getArticleList(Map<String, Object> param);

	public void modifyArticle(Map<String, Object> param);

	public Article getMemberUserId(@Param("id") int id);

	public void deleteArticle(@Param("id") int id);

}
