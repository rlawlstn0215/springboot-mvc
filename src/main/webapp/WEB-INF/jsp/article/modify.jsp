<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="pageName" value="${board.name } 상세내용" />
<%@ include file="../part/head.jspf"%>

<style>
</style>

<div class="container">
	<form class="" action="./doModify" method="post">
		<input type="hidden" name="id" value="${article.id}" />
		<h1 class="title-box">${pageName}</h1>
		<table class="table table-bordered">
			<colgroup>
				<col width="100">
			</colgroup>
			<tbody>
				<tr>
					<th>번호</th>
					<td>${article.id}</td>
				</tr>
				<tr>
					<th>작성자</th>
					<td>${article.extra.writerName}</td>
				</tr>
				<tr>
					<th>조회수</th>
					<td>${article.hit}</td>
				</tr>
				<tr>
					<th>제목</th>
					<td><input class="form-control" autofocus="autofocus" type="text" maxlength="100" name="title"
						value="${article.title}"></td>
				</tr>
				<tr>
					<th>내용</th>
					<td><textarea rows="20" class="form-control" name="body">${article.body}</textarea>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<ul class="row-after">
							<c:if test="${isLogined}">
								<c:if test="${memberUserId == article.memberId}">
									<li class="float-right"><a class="btn btn-default" onclick="history.back();">취소</a></li>
									<li class="float-right"><input class="btn btn-default" type="submit" value="수정" /></li>
								</c:if>
							</c:if>
							<li class="float-left"><a class="btn btn-default"
								href="/article/list?boardId=${article.boardId}">리스트</a></li>
						</ul>
					</td>
			</tbody>
		</table>
	</form>
	
</div>

<%@ include file="../part/foot.jspf"%>