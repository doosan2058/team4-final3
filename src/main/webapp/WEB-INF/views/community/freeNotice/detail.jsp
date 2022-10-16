<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!--detail css-->
<link rel="stylesheet" href="<c:url value="/resources/css/community/freeNotice/detail.css?ver=1"/>">
<!-- 글로벌 css -->
<link rel="stylesheet" href="/resources/css/global.css">
<!--커뮤니티 글로벌 css-->
<link rel="stylesheet" href="<c:url value="/resources/css/community/community_global.css?ver=2"/>">
<!--xeicon cdn-->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/xeicon@2/xeicon.min.css">
<!-- 제이쿼리 스크립트 -->
<script src="//code.jquery.com/jquery-3.2.1.min.js"></script>

<title>Document</title>
</head>

<body>
	<!--헤더-->
	<%@ include file="../header.jsp"%>
	<!--메인-->
	<main>

		<div class="section">
			<div class="section_header">
				<div class="section_header_profile">
					<img src="${profile}" alt="">
				</div>
				<div class="section_header_info">
					<div class="section_header_info_title">
						<div>
						<p>
							<c:if test="${data.board_tag_name eq '공지'}"> <!-- 태그명이 공지면 빨간색으로 보이게 -->
								<span class="section_header_info_title_tag" style="color:red">[${data.board_tag_name}]</span>
							</c:if>
							<c:if test="${data.board_tag_name ne '공지'}">
								<span class="section_header_info_title_tag">[${data.board_tag_name}]</span>
							</c:if>
							${data.board_title}
						</p>
						</div>
						<div>
						<p class="section_header_info_title_date">
							작성일 :
							<span>
								<fmt:formatDate value="${data.board_regdate}" pattern="yy-MM-dd" />
							</span>
						</p>
						</div>
					</div>
					<div class="section_header_info_id">
						<div>
						<p>
							작성자 :
							<span>${data.member_id}</span>
						</p>
						</div>
						<div>
						<p class="section_header_info_id_recommend">
							추천수 :
							<span class="recommend_count">${data.board_recommend}</span>&nbsp;
							<input type="button" value="추천" class="section_header_info_id_recommend_btn">
						</p>
						</div>
					</div>
					<div class="section_header_info_btns">
						<div>
							<c:if test="${sessionScope.login_auth eq '관리자'}">
								<form class="delete_form" method="POST" action="/community/freeNotice/delete">
									<input type="hidden" name="board_id" value="${data.board_id}">
									<input type="button" value="글 삭제 " class="section_header_info_btns_deleteBtn">
								</form>
							</c:if>
						</div>
						<div>
						<p class="section_header_info_btns_lookup">
							조회수 :
							<span>${data.board_hit}</span>
						</p>
						</div>
					</div>
				</div>
			</div>

			<div class="section_main">${data.board_text}</div>

			<div class="section_list">
				<input type="button" class="section_list_btn" value="목록" onclick="location.href='/community/freeNotice/main'">
			</div>

			<div class="section_footer">
				<h1>
					<c:set var="reply_count" value="${reply_count.board_reply_count}" />
					<!-- view more에 사용할 변수 -->
					<script>
						let reply_count = '<c:out value="${reply_count}"/>';
					</script>
					댓글(
					<span class="reply_count">${reply_count}</span>
					)
				</h1>
				<textarea name="" id="" class="section_footer_commentInput"></textarea>

				<div class="section_footer_btnArea">
					<input type="hidden" class="board_id" value="${data.board_id}">
					<input type="button" class="section_footer_btnArea_btn" value="등록">
				</div>

				<div class="section_footer_commentList">
					<c:if test="${reply_count>5}">
						<div class="section_footer_commentList_viewMore">
							<div class="section_footer_commentList_viewMore_btn">
								<h1>View More</h1>
								<i class="xi-angle-down viewMore_btn"></i>
							</div>
						</div>
					</c:if>

					<c:forEach var="reply" items="${reply_data}">
						<div class="section_footer_commentList_comment">
							<div class="section_footer_commentList_comment_count">
								<c:if test="${reply.member_id eq sessionScope.login_id or sessionScope.login_auth eq '관리자'}">
									<i class="xi-close"></i>
									<input type="hidden" value="${reply.board_reply_id}">
								</c:if>
								<c:if test="${data.member_id ne sessionScope.login_id}">
									<i class="xi-close" style="display: none"></i>
									<input type="hidden" value="${reply.board_reply_id}">
								</c:if>
							</div>
							<p>${reply.member_id}</p>
							<P>
								<fmt:formatDate value="${reply.board_reply_regdate}" pattern="yy-MM-dd HH:mm:ss" />
							</P>
							<div class="section_footer_commentList_comment_text">
								<p>${reply.board_reply_text}</p>
							</div>
						</div>
					</c:forEach>

				</div>
			</div>
		</div>
	</main>

	<!--푸터-->
	<%@ include file="../footer.jsp"%>

	<script src="<c:url value="/resources/js/community/freeNotice/detail.js?ver=5"/>"></script>
</body>

</html>
