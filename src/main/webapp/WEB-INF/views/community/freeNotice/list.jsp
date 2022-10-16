<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

	<!-- 검색 결과가 없으면 보이는 요소 -->
	<c:if test="${empty data}">
		<div class=not_found>
			<i class="xi-search side_nav_search_btn"></i>
			<h1>검색 결과가 없습니다</h1>
		</div>
	</c:if>
	
	<c:forEach var="data" items="${data}">
		<div class="section_notice_main_content">
			<li>${data.board_id}</li>
			
			<c:if test="${data.board_tag_name eq '공지'}"> <!-- 태그명이 공지면 빨간색으로 보이게 -->
				<li style="color:red">[<span class="tag_id">${data.board_tag_name}</span>]</li>
			</c:if>
			<c:if test="${data.board_tag_name ne '공지'}">
				<li style="color:var(--fontColor)">[<span class="tag_id">${data.board_tag_name}</span>]</li>
			</c:if>
			
			<li class="board_title">${data.board_title} [${data.reply_count}]</li>
			<li>${data.member_id}</li>
			<li><fmt:formatDate value="${data.board_regdate}" pattern="yy-MM-dd" /></li>
			<li>${data.board_hit}</li>
			<li>${data.board_recommend}</li>
			
			<c:if test="${member_auth eq '관리자'}">
				<input class="list_check" type="checkbox">
			</c:if>
			<c:if test="${member_auth ne '관리자'}">
				<input class="list_check" type="checkbox" style="display:none">
			</c:if>
			
		</div>
		
		<!-- 게시글 제목 누르면 submit할 폼 -->
		<form method="POST" class="list_form" action="/community/freeNotice/go_detail">
				<input type="hidden" name="user_id" value="${data.member_id}">
				<input type="hidden" name="board_id" value="${data.board_id}">
				<input type="hidden" name="login_id" value="${sessionScope.login_id}">
		</form>
	</c:forEach>
	

	
	