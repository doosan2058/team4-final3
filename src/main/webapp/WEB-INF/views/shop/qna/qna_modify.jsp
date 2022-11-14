<%--
  Created by IntelliJ IDEA.
  User: choi
  Date: 2022-11-09
  Time: 오후 6:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!-- 폰트, 색상 공통 -->
    <link rel="stylesheet" href="/resources/css/global.css">
    <link rel="stylesheet" href="/resources/css/shop/qna/qna_modify.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/xeicon@2/xeicon.min.css">
    <title>질문 수정 페이지</title>
</head>
<body>
<jsp:include page="../shop_header.jsp"/>

<form method="post" action="/qna/modify" enctype="multipart/form-data">
    <input type="hidden" name="member_id" value="${qnaVO.member_id}" id="qna_member_id">
    <input type="hidden" name="qna_id" value="${qnaVO.qna_id}">
    <select name="qna_category" id="qna_category" data-ex-value="${qnaVO.qna_category}">
        <option value="주문/결제">주문/결제</option>
        <option value="취소/환불/교환">취소/환불/교환</option>
        <option value="회원">회원</option>
        <option value="기타">기타</option>
    </select>
    <c:choose>
        <c:when test="${qnaVO.qna_public eq 'y'}">
            <input type="radio" value="y" name="qna_public" checked>공개
            <input type="radio" value="n" name="qna_public">비공개
        </c:when>
        <c:when test="${qnaVO.qna_public eq 'n'}">
            <input type="radio" value="y" name="qna_public">공개
            <input type="radio" value="n" name="qna_public" checked>비공개
        </c:when>
    </c:choose>
    <input type="text" name="qna_title" maxlength="30" placeholder="제목을 입력하세요.(최대 30자)" required id="qna_title" value="${qnaVO.qna_title}">
    <textarea name="qna_text" required id="qna_text">${qnaVO.qna_text}</textarea>
    <input type="file" name="file" id="qna_picture_url">
    <input type="submit" value="수정하기" id="qnaSubmitBtn">
</form>


<jsp:include page="../shop_footer.jsp"/>
<script src="/resources/js/shop/qna/qna_modify.js"></script>
</body>
</html>