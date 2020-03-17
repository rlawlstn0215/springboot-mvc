package com.example.demo.service;

import java.util.Map;

import com.example.demo.dto.Member;

public interface MemberService {
	public Map<String, Object> join(Map<String, Object> param);
	public Map<String, Object> login(Map<String, Object> param);
	public Map<String, Object> findUserId(Map<String, Object> param);
	public Map<String, Object> findUserPw(Map<String, Object> param);
	public Member findById(int memberUserId);
	public Map<String, Object> doModify(Map<String, Object> param);
	public void secession(int secession);
}
