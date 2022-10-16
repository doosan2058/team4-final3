<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- 댓글 입력하면 추가될 요소 -->
		<div class="section_footer_commentList_comment">
			<div class="section_footer_commentList_comment_count">
				<c:if test="${data.member_id eq sessionScope.login_id}"> <!-- 글쓴이와 현재 아이디가 동일하면 삭제 버튼 보이게 -->
					<i class="xi-close" ></i>
					<input type="hidden" value="${data.camping_reply_id}">
				</c:if>
				<c:if test="${data.member_id ne sessionScope.login_id}"> <!-- 글쓴이와 현재 아이디가 다르면 삭제 버튼 안 보이게 -->
					<i class="xi-close" style="display:none"></i>
					<input type="hidden" value="${data.camping_reply_id}">
				</c:if>
			</div>
			<p>${data.member_id}</p>
			<P>
				<fmt:formatDate value="${data.camping_reply_regdate}"
					pattern="yy-MM-dd HH:mm:ss" />
			</P>
			<div class="section_footer_commentList_comment_text">
				<p>${data.camping_reply_text}</p>
			</div>
		
		</div>

