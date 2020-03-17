package com.example.demo.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.dto.Member;

@Mapper
public interface MemberDao {
	public void join(Map<String, Object> param);

	public Member findByMemberId(@Param("userId") String userId);

	public Member findByNameAndEmail(@Param("name") String name, 
			@Param("email") String email);

	public Member findByUserIdAndNameAndEmail(@Param("userId") String userId, 
			@Param("name") String name, @Param("email") String email);

	public void updateUserPw(@Param("id") int id, @Param("userPw") String userPw);

	public Member findById(@Param("id") int id);

	public void doModify(Map<String, Object> param);

	public Member findByIdAndPw(@Param("userId") String userId, 
			@Param("userPw") String userPw);

	public void secession(int secession);
}
