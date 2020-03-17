<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="pageName" value="${board.name } 상세내용" />
<%@ include file="../part/head.jspf"%>

<script>
var articleId = parseInt('${article.id}');
var memberUserId = parseInt('${memberUserId}');
var memberUserName = parseInt('${memberUser.name}');
var isLogined = '${isLogined}' == 'true';

var Article_lastReceivedReplyId = 0;

function Article_loadNewReplies(){
	$.get('./getRepliesFrom', {
		articleId : articleId,
		from : Article_lastReceivedReplyId + 1
	}, function(data){
			if(data.messages.length > 0){
				Article_lastReceivedReplyId = 
				data.messages[data.messages.length - 1].id;
			}
			for(var i = 0; i < data.messages.length; i++){
				Article_drawReply(data.messages[i]);
			}

			setTimeout(Article_loadNewReplies, 1000);
	   }, 'json'
	 );
}

$(function(){
	Article_loadNewReplies();
});

function Article_writeReply(form){
	if(isLogined == false){
		alert('로그인 후 이용해주세요.');
		
		return;
	}

	form.body.value = form.body.value.trim();

	if(form.body.value.length == 0){
		alert('내용을 입력해주세요.');
		form.body.focus();

		return;
	}
	
	var $form = $(form);

	var body = form.body.value;

	form.body.value = '';

	$form.find('input[type="submit"]').val('작성중..');
	$form.find('input[type="submit"]').prop('disabled', true);
	$form.find('input[type="reset"]').prop('disabled', true);

	$.post('./doWriteReply', {
		articleId : articleId,
		body : body
	}, function(data){
			if(data.resultCode.substr(0, 2) == 'F-1'){
				alert(data.msg);
			} else {
				$form.find('input[type="submit"]').val('작성');
				$form.find('input[type="submit"]').prop('disabled', false);
				$form.find('input[type="reset"]').prop('disabled', false);
			}
		}
	);
}

function Article_drawReply(reply){
	var $tbody = $('.reply-list table > tbody');

	var templateHtml = $('.template-box-2 tbody').html();

	var 번호 = reply.id;
	var 작성자 = reply.extra.writerName;
	var 내용 = reply.body;
	var 날짜 = reply.regDate.substr(2, 14);
	var 작성자번호 = reply.memberId;

	var trHtml = templateHtml;

	trHtml = replaceAll(trHtml, "{$번호}", 번호);
	trHtml = replaceAll(trHtml, "{$작성자}", 작성자);
	trHtml = replaceAll(trHtml, "{$내용}", 내용);
	trHtml = replaceAll(trHtml, "{$날짜}", 날짜);
	trHtml = replaceAll(trHtml, "{$작성자번호}", 작성자번호);
	
	$tbody.prepend(trHtml);
}

function Article_modifyReply(form) {
	form.body.value = form.body.value.trim();
	if (form.body.value.length == 0) {
		form.body.focus();
		alert('내용을 입력해주세요.');
		return;
	}
	var $tr = $(form).closest('tr');
	$tr.attr('data-modify-mode', 'N');
	var newBody = form.body.value;
	var id = parseInt(form.id.value);

	$tr.find(' > .reply-body-td > .modify-mode-invisible')
		.empty().append('변경중..');
	
	$.post('./doModifyReply', {
		id : id,
		body : newBody
	}, function(data) {
		if (data.resultCode.substr(0, 2) == 'S-') {
			$tr.find(' > .reply-body-td > .modify-mode-invisible')
			.empty().append(newBody);
		} else {
			alert(data.msg);
		}
	}, 'json');
}

function Article_deleteReply(el) {
	if (isLogined == false) {
		alert('권한이 없습니다.');
		return;
	}
	
	var $tr = $(el).closest('tr');
	var id = parseInt($tr.attr('data-reply-id'));
	var writerId = parseInt($tr.attr('data-reply-writer-id'));
	
	if (memberUserId != writerId) {
		alert('권한이 없습니다.');
		return;
	}
	
	if (confirm('댓글을 삭제하시겠습니까?') == false) {
		return
		;
	}
	$.post('./doDeleteReply', {
		id : id
	}, function(data) {
		if (data.resultCode.substr(0, 2) == 'S-') {
			$tr.remove();
		} else {
			alert(data.msg);
		}
	});
}

function Article_turnOnModifyMode(el) {
	if (isLogined == false) {
		alert('권한이 없습니다.');
		return;
	}
	
	var $tr = $(el).closest('tr');
	var writerId = parseInt($tr.attr('data-reply-writer-id'));
	
	if (memberUserId != writerId) {
		alert('권한이 없습니다.');
		return;
	}
	
	var body = $tr.find(' > .reply-body-td > .modify-mode-invisible')
					.html().trim();
	
	$tr.find(' > .reply-body-td > .modify-mode-visible > form > textarea')
		.val(body);
	$tr.attr('data-modify-mode', 'Y');
	$tr.siblings('[data-modify-mode="Y"]').attr('data-modify-mode', 'N');
}

function Article_turnOffModifyMode(el) {
	var $tr = $(el).closest('tr');
	$tr.attr('data-modify-mode', 'N');
}

function replaceAll(str, searchStr, replaceStr) {
	return str.split(searchStr).join(replaceStr);
}



</script>

<style>
.template-box {
	display: none;
}
.reply-list>table>tbody>tr[data-modify-mode="N"] .modify-mode-visible {
	display: none;
}
.reply-list>table>tbody>tr[data-modify-mode="Y"] .modify-mode-invisible {
	display: none;
}

</style>

<div class="container">
	<div class="">
		<h1 class="title-box">${pageName}</h1>
		<div class="float-right">
			<ul class="row-after">
				<c:if test="${isLogined }">
					<c:if test="${memberUserId == article.memberId }">
						<li class="float-left"><a class="block" href="/article/modify?id=${article.id }">수정</a></li>
						<li class="float-left"><a class="block" onclick="if(confirm('정말 삭제하시겠습니까?') == false) {return false;}"
						href="/article/doDelete?id=${article.id }">삭제</a></li>
					</c:if>
				</c:if>
				
				<li class="float-left"><a class="block"
					href="/article/list?boardId=${article.boardId }">리스트</a></li>
			</ul>
		</div>
		<table class="table table-bordered">
			<colgroup>
				<col width="100">
				<col />
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
					<td>${article.title}</td>
				</tr>
				<tr>
					<th>내용</th>
					<td style="word-break: break-all;">${article.bodyForPrint}</td>
				</tr>

			</tbody>
		</table>
	</div>
	<div class="reply-list">
		<h4>댓글목록</h4>
		<table class="table table-responsive">
			<thead>
				<tr>
					<th class="col-sm-1">날짜</th>
					<th class="col-sm-1">글쓴이</th>
					<th class="col-sm-7">내용</th>
					<th class="col-sm-1"></th>
				</tr>
			</thead>
			<tbody>
			</tbody>
		</table>
	</div>

	<div class="template-box template-box-2">
		<table class="table">
			<tbody>
				<tr data-modify-mode="N" data-reply-id="{$번호}"
					data-reply-writer-id="{$작성자번호}">
					<td style="font-size:12px;">{$날짜}</td>
					<td style="font-size:12px;">{$작성자}</td>
					<td class="reply-body-td" style="font-size:12px;" >
						<div class="modify-mode-invisible" style="word-break: break-all;">{$내용}</div>
						<div class="modify-mode-visible">
							<form onsubmit="Article_modifyReply(this); return false;">
								<input type="hidden" name="id" value="{$번호}">
								<textarea class="form-control" maxlength="300" name="body"></textarea>
								<input type="submit" value="수정완료"> <a 
									href="javascript:;" onclick="Article_turnOffModifyMode(this);">수정취소</a>
							</form>
						</div>
					</td>
					<td style="font-size:12px;"><a href="javascript:;"
						onclick="Article_deleteReply(this);">삭제</a> <a
						class="modify-mode-invisible" href="javascript:;"
						onclick="Article_turnOnModifyMode(this);">수정</a></td>
				</tr>
			</tbody>
		</table>
	</div>

	<div class="reply-write">
		<form action="" onsubmit="Article_writeReply(this); return false;">
			<table class="table">
				<tbody>
					<tr>
						<td colspan="2">
							<textarea class="form-control" placeholder="내용을 입력해주세요." name="body" maxlength="300"></textarea>
						</td>
					</tr>
					<tr>
						<th></th>
						<td><input class="float-right" type="submit" value="작성"></td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
</div>

<%@ include file="../part/foot.jspf"%>