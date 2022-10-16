<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- 폰트, 색상 공통 -->
<link rel="stylesheet" href="/resources/css/global.css">

<link rel="stylesheet" href="/resources/css/shop/qna/aad.css">

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/xeicon@2/xeicon.min.css">
<title>Document</title>
</head>
<body>
	<!-- header -->
	<%@include file="../shop_header.jsp" %>
	
	
	<!--메인-->
	<main>
		<div class="Container">
			<div class="list">
				<button class="list_btn">목록</button>
			</div>
			<div class="box">
				<div class="member_idBox">
					<p class="boxtext">아이디</p>
					<p class="boxData">${data.member_id}</p>
				</div>
				<div class="qna_titleBox">
					<p class="boxtext">제목</p>
					<p class="boxData">${data.qna_title}</p>
				</div>
				<div class="qna_categoryBox">
					<p class="boxtext">문의유형</p>
					<p class="boxData">${data.qna_category}</p>
				</div>
				<div class="qna_regdate">
					<p class="boxtext">작성일자</p>
					<p class="boxData"><fmt:formatDate value="${data.qna_regdate}" pattern="yyyy-MM-dd ,a, HH:mm:ss" /></p>
				</div>
				<div class="qna_textBox">
					<p>내용</p>
					<div>
					${data.qna_text}
					</div>
				</div>
				<div class="qna_answer_textBox">
			 		<p>답변 내용</p>
			 		<div>
			 		${data.qna_answer_text}
			 		</div>
			 	</div>
			</div>
		</div>
		<div class="btnContainer">
			<input type="hidden" name="qna_id" value="${data.qna_id}">
			<c:if test="${sessionScope.login_auth eq '관리자' && data.qna_answer_text eq null}">
			<form action="question_admin" method="get">
   			 <input type="hidden" id="qna_id" name="qna_id" value='${data.qna_id}'/>
   			 <input type="submit" class="btn_ask" value="답변" />
			</form>
			</c:if>
			<c:if test="${sessionScope.login_auth eq '관리자' && data.qna_answer_text ne null}">
			<form action="question_admin_update" method="get">
   			 <input type="hidden" id="qna_id" name="qna_id" value='${data.qna_id}'/>
   			 <input type="submit" class="btn_update" value="답변수정" />
			</form>
			</c:if>
			<c:if test="${sessionScope.login_id eq data.member_id}">
			<form action="question_update" method="get">
   			 <input type="hidden" id="qna_id" name="qna_id" value='${data.qna_id}'/>
   			 <input type="submit" class="btn_update" value="수정" />
			</form>
			</c:if>
			<c:if test="${sessionScope.login_id eq data.member_id || sessionScope.login_auth eq '관리자'}">
			<form method="POST" action="delete">
				<input type="hidden" name="qna_id" value="${data.qna_id}" />
				<input type="submit" class="btn_delete" value="삭제" />
			</form>
			</c:if>
		</div>
	</main>
	
	
	<!-- footer -->
	<%@include file="../shop_footer.jsp" %>

	<script src="/resources/js/shop/qna/qna_detail.js"></script>
	
</body>
</html>