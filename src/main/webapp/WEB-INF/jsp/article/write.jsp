<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="title" value="${board.name } - 게시물 -  ${article.title}" />
<c:set var="pageName" value="${board.name } 글쓰기" />
<%@ include file="../part/head.jspf"%>

<script>
	function submitWriteForm(form) {
		form.title.value = form.title.value.trim();

		if (form.title.value.length == 0) {
			alert('제목을 입력해 주세요.');
			form.title.focus();

			return false;
		}

		form.body.value = form.body.value.trim();

		if (form.body.value.length == 0) {
			alert('내용을 입력해 주세요.');
			form.body.focus();

			return false;
		}
		form.submit();
	}
</script>

<style>
.formCenter {
	width: 80%;
}
</style>

<div class="container">
	<h1 class="title-box">${pageName}</h1>
	<form class="form-horizontal formCenter"
		onsubmit="submitWriteForm(this); return false;" action="./doWrite"
		method="post">
		<a class="text-right block" href="/article/list?boardId=${board.id }">리스트</a>
		<input type="hidden" name="boardId" value="${board.id }" />
		<div class="form-group">
			<label for="title" class="col-sm-2 control-label">제목</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" id="title" name="title" maxlength="100"
					autocomplete="off" autofocus="autofocus" placeholder="제목을 입력해 주세요.">
			</div>
		</div>
		<div class="form-group">
			<label for="body" class="col-sm-2 control-label">내용</label>
			<div class="col-sm-10">
				<textarea class="form-control" autocomplete="off"
					name="body" id="body" cols="30" rows="10"
					placeholder="내용을 입력해 주세요."></textarea>
			</div>
		</div>
		<div class="col-sm-offset-2 col-sm-10">
			<input type="submit" class="btn btn-default" value="작성" />
		</div>
	</form>
</div>


<%@ include file="../part/foot.jspf"%>