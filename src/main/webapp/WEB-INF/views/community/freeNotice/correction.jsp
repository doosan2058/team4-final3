<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!--쇼핑몰 글로벌 css-->
    <link rel="stylesheet" href="<c:url value="/resources/css/community/freeNotice/input.css?ver=4"/>">
    <!-- 글로벌 css -->
    <link rel="stylesheet" href="/resources/css/global.css">
    <!--쇼핑몰 글로벌 css-->
    <link rel="stylesheet" href="<c:url value="/resources/css/community/community_global.css?ver=2"/>">

    <!--xeicon-->
    <link rel="stylesheet" href="//cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css">
    <!-- 에디터 스크립트 -->
    <script type="text/javascript" src="<c:url value="/resources/editor/ckeditor/ckeditor.js"/>"></script>

    <title>Document</title>
</head>

<body>
<!--헤더-->
<%@ include file="../header.jsp" %>
<!--메인-->
<main>
    <form class="section" method="POST">
        <div class="section_header">
            <h1>제목</h1>
            <select name="board_tag_id" id="" class="section_header_brackets">
                <option value="">말머리 선택</option>
                <option value=1 <c:out value="${data.board_tag_name == '잡담' ? 'selected' : ''}"/>>[잡담]</option>
                <c:if test="${sessionScope.login_auth eq '관리자'}">
                    <option value=2 <c:out value="${data.board_tag_name == '공지' ? 'selected' : ''}"/>>[공지]</option>
                </c:if>
                <option value=3 <c:out value="${data.board_tag_name == '캠핑팁' ? 'selected' : ''}"/>>[캠핑팁]</option>
                <option value=4 <c:out value="${data.board_tag_name == '상품후기' ? 'selected' : ''}"/>>[상품후기]</option>
                <option value=5 <c:out value="${data.board_tag_name == '캠핑후기' ? 'selected' : ''}"/>>[캠핑후기]</option>
            </select>
            <textarea name="board_title" id="" class="section_header_headline"
                      placeholder="제목을 입력하세요.">${data.board_title}</textarea>
        </div>

        <div class="section_main">
            <h1>내용</h1>
            <textarea name="board_text" id="content" class="section_main_write"
                      placeholder="내용을 입력하세요.">${data.board_text}</textarea>
            <!-- 에디터 스크립트 -->
            <script type="text/javascript">
                CKEDITOR.replace('content', {
                    filebrowserUploadUrl: "/community/imgUpload",
                    height: 1200
                });
            </script>
        </div>

        <div class="section_footer">
            <input type="hidden" value="양화대교세걸음" name="member_id">
            <input type="button" value="글 등록" class="section_footer_registrationBtn">
        </div>
    </form>
</main>
<!--푸터-->
<%@ include file="../footer.jsp" %>

<script src="<c:url value="/resources/js/community/freeNotice/correction.js?ver=1"/>"></script>
</body>

</html>