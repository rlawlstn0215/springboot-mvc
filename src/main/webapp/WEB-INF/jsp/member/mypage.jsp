<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="pageName" value="${memberUser.name } 님의 회원정보" />
<%@ include file="../part/head.jspf"%>

<script>
	
</script>

<style>
</style>

<div class="container">
	<h1 class="title-box">${pageName}</h1>
	<div class="formCenter">
		<table class="table table-bordered">
			<colgroup>
				<col width="100">
			</colgroup>
			<tbody>
				<tr>
					<th>아이디</th>
					<td><c:out value="${memberUser.userId}" /></td>
				</tr>
				<tr>
					<th>이름</th>
					<td>${memberUser.name}</td>
				</tr>
				<tr>
					<th>이메일</th>
					<td>${memberUser.email}</td>
				</tr>
			</tbody>
		</table>
		<a class="btn btn-default" href="/member/modify">개인정보 수정</a> 
		<a class="btn btn-default"
			onclick="if (confirm('탈퇴 하시겠습니까?') == false) {return false};"
			href="/member/doSecession">회원탈퇴</a>
	</div>
</div>

<%@ include file="../part/foot.jspf"%>