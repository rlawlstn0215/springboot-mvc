<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="pageName" value="아이디찾기" />
<%@ include file="../part/head.jspf"%>

<script>
function makeTestData() {
	var form = document.joinForm;

	form.name.value = '테스트01';
	form.email.value = 'test01@test01.com';

	
}
function submitFindUserIdForm(form){
	form.name.value = form.name.value.trim();
	if(form.name.value.length == 0) {
		alert('이름을 입력해주세요.');
		form.name.focus();

		return false;
	}
	
	form.email.value = form.email.value.trim();
	if (form.email.value.length == 0) {
		alert('이메일을 입력해 주세요.');
		form.email.focus();
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
	<form class="form-horizontal formCenter" name="joinForm" 
		onsubmit="submitFindUserIdForm(this); return false;" action="./doFindUserId" method="post">
		<div class="form-group">
			<label for="name" class="col-sm-2 control-label">이름</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" id="name" name="name"
					placeholder="Name">
			</div>
		</div>
		<div class="form-group">
			<label for="email" class="col-sm-2 control-label">email</label>
			<div class="col-sm-10">
				<input type="email" class="form-control" id="email" name="email"
					placeholder="Email">
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<input type="submit" class="btn btn-default" value="아이디찾기">
			</div>
		</div>
	</form>
</div>

<%@ include file="../part/foot.jspf" %>