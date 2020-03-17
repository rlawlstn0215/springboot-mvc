package com.example.demo.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.PageViewLogItemDao;
import com.example.demo.dto.PageViewLogItem;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PageViewLogItemServiceImpl implements PageViewLogItemService {

	@Autowired
	PageViewLogItemDao pageViewLogItemDao;

	@Override
	public void addLog(HttpServletRequest req) {
		HttpSession session = req.getSession();

		String path = req.getServletPath();
		String queryStr = req.getQueryString();

		if (queryStr == null) {
			queryStr = "";
		}

		String domain = req.getServerName();
		int port = req.getServerPort();
		String url = req.getRequestURL().toString();
		int memberUserId = 0;

		if (session.getAttribute("memberUserId") != null) {
			memberUserId = Integer.parseInt((String) session.getAttribute("memberUserId"));
		}

		PageViewLogItem pageViewLogItem = new PageViewLogItem(0, "", path, queryStr, domain, port, url,
				memberUserId);

		log.info("pageViewLogItem : " + pageViewLogItem);

		pageViewLogItemDao.addLog(pageViewLogItem);
	}

}
