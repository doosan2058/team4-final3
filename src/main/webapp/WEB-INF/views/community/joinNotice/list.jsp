<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:if test="${empty list}"> <!-- 검색 결과가 없을 경우 보여줄 요소 -->
		<div class=not_found>
			<i class="xi-search side_nav_search_btn"></i>
			<h1>검색 결과가 없습니다</h1>
		</div>
</c:if>

<c:forEach var="data" items="${list}">
<div class="section_notice_main_content">
	<li>${data.camping_id}</li>
	<li class="camping_title">${data.camping_title} [${data.reply_count}]</li>
	<li>${data.member_id}</li>
	<li>${data.camping_region}</li>
	<li>${data.camping_applicant}/${data.camping_recruitment}</li>
	<li><fmt:formatDate value="${data.camping_departure}" pattern="yy-MM-dd" /></li>
	<li>${data.camping_period}</li>
	<li>${data.camping_hit}</li>
	<c:if test="${data.camping_close eq '마감'}"> <!-- 마감된 글은 마감 텍스트 색이 빨간색 -->
		<li style="color:red">${data.camping_close}</li>
	</c:if>
	<c:if test="${data.camping_close ne '마감'}"> <!-- 마감된 글이 아니면 파란색 -->
		<li style="color:rgb(142, 206, 231)">${data.camping_close}</li>
	</c:if>
		<c:if test="${member_auth eq '관리자'}"> <!-- 관리자라면 글 삭제에 사용할 체크박스  보이게-->
			<input class="list_check" type="checkbox">
		</c:if>
		<c:if test="${member_auth ne '관리자'}"> <!-- 관리자가 아니라면 체크박스  안 보이게 -->
			<input class="list_check" type="checkbox" style="display: none">
		</c:if>
	</div> 
		<form method="POST" class="list_form" action="/community/joinNotice/go_detail"> <!-- 게시글 제목 누를 때 submit 시킬 폼 -->
				<input type="hidden" name="user_id" value="${data.member_id}">
				<input type="hidden" name="camping_id" value="${data.camping_id}">
				<input type="hidden" name="login_id" value="${sessionScope.login_id}">
		</form>
</c:forEach>