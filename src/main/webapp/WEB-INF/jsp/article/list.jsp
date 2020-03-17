<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="pageName" value="${board.name } 리스트" />
<%@ include file="../part/head.jspf"%>

<style>
.table > thead > tr th {
	text-align: center;
}
.table > tbody > tr td {
	text-align: center;
}
.table > tbody > tr td:nth-child(3) {
	text-align: left;
}
.1rem {
	font-size: 1rem;
}
</style>

<div class="container">
	<h1 class="title-box">${pageName}</h1>
	<div class="table-responsive">
	<table class="table table-bordered table-hover">
		<thead>
			<tr>
				<th class="col-sm-1">번호</th>
				<th class="col-sm-2">작성날짜</th>
				<th class="col-sm-7">제목</th>
				<th class="col-sm-1">작성자</th>
				<th class="col-sm-1">조회수</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${articleList }" var="article">
				<tr>
					<td>${article.id }</td>
					<td>${article.regDate }</td>
					<td><a style="display: block;" href="./detail?id=${article.id }">${article.title }</a></td>
					<td>${article.extra.writerName}</td>
					<td>${article.hit }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	</div>
	<div class="float-right">
		<a role="button" class="btn btn-default block" href="/article/write?boardId=${board.id }">글쓰기</a>
	</div>
	
	<nav align="center">
		<ul class="pagination">
			<c:if test="${page.prev }">
				<li>
					<a href="/article/list?boardId=${board.id }&page=${page.startPage - 1 }">prev</a>
				</li>
			</c:if>
			<c:forEach begin="${page.startPage }" end="${page.endPage }" var="i">
				<li <c:out value="${page.page == i ? 'class=active' : '' }"/>>
					<a href="/article/list?boardId=${board.id }&page=${i }">${i }</a>
				</li>
			</c:forEach>
			<c:if test="${page.next && page.endPage > 0 }">
				<li>
					<a href="/article/list?boardId=${board.id }&page=${page.endPage + 1}">next</a>
				</li>
			</c:if>
		</ul>
	</nav>
</div>

<%@ include file="../part/foot.jspf"%>