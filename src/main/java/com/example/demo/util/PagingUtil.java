package com.example.demo.util;

import java.util.HashMap;
import java.util.Map;

public class PagingUtil {

	public static Map<String, Object> paging(Map<String, Object> param, int total) {
		Map<String, Object> rs = new HashMap<>();
		
		int page = Integer.parseInt((String) param.get("page").toString());//현재 페이지
		int totalCount = total; // 게시물 총 갯수
		int endPage; // 끝 페이지 번호
		int startPage; // 시작 페이지 번호
		int perPageNum = 10; // 페이지당 출력 될 게시물 갯수
		int displayPageNum = 10; // 하단의 페이지 번호의 갯수
		int tempEndPage;
		boolean prev; //이전 링크
		boolean next; //다음 링크
		
		endPage = (int) (Math.ceil(page / (double) displayPageNum) * displayPageNum); // 끝 페이지 번호의 계산
		startPage = endPage - displayPageNum + 1; // 시작 페이지 번호의 계산
		tempEndPage = (int) (Math.ceil(totalCount / (double) perPageNum)); //끝 페이지 번호 보정 계산
		
		if(endPage > tempEndPage) { //이전의 결과 값과 보정된 결과 값을 비교
			endPage = tempEndPage; 	//보정한 결과 값을 페이지 끝 번호 변수에 저장
		}
		
		prev = startPage == 1 ? false : true;	// 이전 링크 계산
		next = endPage * perPageNum >= totalCount ? false : true;	//다음 링크 계산	
		
		rs.put("startPage", startPage);
		rs.put("endPage", endPage);
		rs.put("prev", prev);
		rs.put("next", next);
		rs.put("page", page);
		
		param.put("startNum", (page - 1) * perPageNum); //현재 페이지에 보여질 게시물
		param.put("perPageNum", perPageNum);
		
		return rs;
	}

}
