<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="pageName" value="개인정보수정" />
<%@ include file="../part/head.jspf"%>

<script>

function submitModifyForm(form){
	form.name.value = form.name.value.trim();
	if(form.name.value.length == 0) {
		alert('이름을 입력해주세요.');
		form.name.focus();

		return false;
	}
	
	form.userPw.value = form.userPw.value.trim();
	if (form.userPw.value.length == 0) {
		alert('비밀번호를 입력해 주세요.');
		form.userPw.focus();
		return false;
	}

	form.userPwConfirm.value = form.userPwConfirm.value.trim();
	if (form.userPw.value != form.userPwConfirm.value) {
		alert('비밀번호가 일치하지 않습니다.');
		form.userPwConfirm.focus();
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
	
	<form class="form-horizontal formCenter" name="modifyForm" 
		onsubmit="submitModifyForm(this); return false;" action="./doModify" method="post">
		<input type="hidden" name="id" value="${memberUser.id}"> 
		<div class="form-group">
			<label for="name" class="col-sm-2 control-label">Name</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" id="userId" name="name" 
					placeholder="Name" autofocus="autofocus">
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
			<label for="passwordConfrim" class="col-sm-2 control-label">Password</label>
			<div class="col-sm-10">
				<input type="password" class="form-control" id="passwordConfrim" name="userPwConfirm" 
					placeholder="Password">
			</div>
		</div>
		<div class="form-group">
			<label for="email" class="col-sm-2 control-label">Email</label>
			<div class="col-sm-10">
				<input type="email" class="form-control" id="email" name="email"
					placeholder="Email">
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<input type="submit" class="btn btn-default" value="완료">
			</div>
		</div>
	</form>
</div>

<%@ include file="../part/foot.jspf" %>