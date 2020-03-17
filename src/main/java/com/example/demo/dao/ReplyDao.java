package com.example.demo.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.dto.ArticleReply;

@Mapper
public interface ReplyDao {
	public void writeReply(Map<String, Object> param);

	public List<ArticleReply> getRepliesFrom(@Param("articleId") int articleId, @Param("from") int from);

	public ArticleReply getReply(@Param("id") int id);

	public void deleteReply(@Param("id") int id);

	public void modifyReply(Map<String, Object> param);
}
