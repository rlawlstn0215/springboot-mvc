package com.example.demo.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.groovy.util.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.MemberDao;
import com.example.demo.dto.Member;

@Service
public class MemberServiceImpl implements MemberService {
	@Autowired
	private MemberDao memberDao;
	@Autowired
	private MailService mailService;

	@Override
	public Map<String, Object> join(Map<String, Object> param) {
		Map<String, Object> rs = new HashMap<>();

		String userId = (String) param.get("userId");

		Member oldMember = memberDao.findByMemberId(userId);
		if (oldMember != null) {
			if (oldMember.isOutStatus()) {
				rs.put("resultCode", "F-1");
				rs.put("msg", "탈퇴한 회원의 아이디 입니다.");
			} else {
				rs.put("resultCode", "F-2");
				rs.put("msg", "이미 사용중인 아이디 입니다.");
			}
			return rs;
		}

		memberDao.join(param);

		rs.put("resultCode", "S-1");
		rs.put("msg", param.get("name") + "님 가입을 축하드립니다.");

		return rs;
	}

	@Override
	public Map<String, Object> login(Map<String, Object> param) {
		Map<String, Object> rs = new HashMap<>();

		String userId = (String) param.get("userId");
		String userPw = (String) param.get("userPw");

		Member member = memberDao.findByIdAndPw(userId, userPw);
		if (member == null) {
			rs.put("resultCode", "F-2");
			rs.put("msg", "아이디 또는 비밀번호가 틀렸습니다.");
			
			return rs;
		} else if(member.isOutStatus()){
			rs.put("resultCode", "F-1");
			rs.put("msg", "탈퇴한 회원입니다.");
			
			return rs;
		}

		rs.put("resultCode", "S-1");
		rs.put("msg", member.getName() + "님 환영합니다.");
		rs.put("member", member);

		return rs;
	}

	@Override
	public Map<String, Object> findUserId(Map<String, Object> param) {
		Map<String, Object> rs = new HashMap<>();

		String name = (String) param.get("name");
		String email = (String) param.get("email");

		Member member = memberDao.findByNameAndEmail(name, email);
		if (member == null) {
			rs.put("resultCode", "F-1");
			rs.put("msg", "일치하는 회원이 없습니다.");
			return rs;
		}
		rs.put("resultCode", "S-1");
		rs.put("msg", "회원님의 아이디는 ( " + member.getUserId() + " ) 입니다.");
		return rs;
	}

	@Override
	public Map<String, Object> findUserPw(Map<String, Object> param) {
		String userId = (String) param.get("userId");
		String name = (String) param.get("name");
		String email = (String) param.get("email");

		Member member = memberDao.findByUserIdAndNameAndEmail(userId, name, email);
		if (member == null) {
			return Maps.of("resultCode", "F-1", "msg", "일치하는 회원이 없습니다.");
		}

		String tempUserPw = (int) (Math.random() * 10000) + "";
		member.setUserPw(tempUserPw);
		memberDao.updateUserPw(member.getId(), tempUserPw);

		String title = name + "님의 임시 패스워드 입니다.";
		String body = "임시 패스워드 : " + tempUserPw;

		mailService.send(email, title, body);

		return Maps.of("resultCode", "F-1", "msg", "회원님의 메일로 임시 패스워드를 보내드렸습니다.");
	}

	@Override
	public Member findById(int id) {
		return memberDao.findById(id);
	}

	@Override
	public Map<String, Object> doModify(Map<String, Object> param) {
		memberDao.doModify(param);

		return Maps.of("resultCode", "S-1", "msg", "개인정보 수정이 완료되었습니다.");
	}

	@Override
	public void secession(int secession) {
		memberDao.secession(secession);

	}

}
