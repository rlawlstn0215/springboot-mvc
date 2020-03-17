<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="pageName" value="로그인" />
<%@ include file="../part/head.jspf"%>

<script>
function makeTestData() {
	var form = document.loginForm;

	form.userId.value = 'test01';
	form.userPw.value = 'test01';

	
}
function submitLoginForm(form){
	form.userId.value = form.userId.value.trim();
	if(form.userId.value.length == 0) {
		alert('아이디를 입력해주세요.');
		form.userId.focus();

		return false;
	}
	
	form.userPw.value = form.userPw.value.trim();
	if (form.userPw.value.length == 0) {
		alert('비밀번호를 입력해 주세요.');
		form.userPw.focus();
		return false;
	}
	form.submit();
}
</script>

<style>

</style>

<div class="container">
	<h1 class="title-box">${pageName}</h1>
	<div class="test-btn">
		<button onClick="makeTestData();" class="btn btn-default">테스트 데이터</button>
	</div>
	<form class="form-horizontal formCenter" name="loginForm" 
		onsubmit="submitLoginForm(this); return false;" action="./doLogin" method="post">
		<div class="form-group">
			<label for="userId" class="col-sm-2 control-label">ID</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" id="userId" name="userId"
					placeholder="Id">
			</div>
		</div>
		<div class="form-group">
			<label for="password" class="col-sm-2 control-label">Password</label>
			<div class="col-sm-10">
				<input type="password" class="form-control" id="password" name="userPw"
					placeholder="Password">
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<input type="submit" class="btn btn-default" value="로그인">
			</div>
		</div>
	</form>
</div>

<%@ include file="../part/foot.jspf" %>