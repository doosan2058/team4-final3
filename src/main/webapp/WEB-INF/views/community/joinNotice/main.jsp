<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- 글로벌 css -->
    <link rel="stylesheet" href="/resources/css/global.css">
    <!--커뮤니티 글로벌 css-->
    <link rel="stylesheet" href="<c:url value="/resources/css/community/community_global.css?ver=2"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/community/joinNotice/main.css?ver=1"/>">

    <!-- Jquery cdn -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <!--xeicon-->
    <link rel="stylesheet" href="//cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css">

    <title>Document</title>

</head>

<body>
<!--헤더-->
<%@ include file="../header.jsp" %>

<!--메인-->
<main>
    <!-- 사이드 메뉴 -->
    <%@ include file="side.jsp" %>

    <div class="section">
        <div class="section_header">
            <li>글번호</li>
            <li>제목</li>
            <li>글쓴이</li>
            <li>지역</li>
            <li>모집인원</li>
            <li>출발일</li>
            <li>기간</li>
            <c:choose>
                <c:when test="${sort_value eq 'desc'}">
                    <li class="sort">조회수<i class="xi-caret-up"></i></li>
                </c:when>
                <c:otherwise>
                    <li class="sort">조회수<i class="xi-caret-down"></i></li>
                </c:otherwise>
            </c:choose>
            <li>마감여부</li>
        </div>

        <div class="section_notice">
            <div class="section_notice_main">
                <!-- 게시글 목록 -->
                <%@ include file="list.jsp" %>
            </div>

            <script>/* 현재 검색중인 옵션을 담는 변수들 */
            let camping_region = '<c:out value="${option.camping_region}"/>';
            let camping_recruitment = '<c:out value="${option.camping_recruitment}"/>';
            let camping_departure = '<c:out value="${option.camping_departure}"/>';
            let camping_period = '<c:out value="${option.camping_period}"/>';
            let camping_close = '<c:out value="${option.camping_close}"/>';
            let keyword = '<c:out value="${option.keyword}"/>';
            let sort_value = '<c:out value="${sort_value}"/>';
            </script>

            <div class="section_notice_footer">
                <c:set var="camping_count" value="${count.camping_count}"/>

                <p class="first_btn"><<</p>
                <div class="section_notice_footer_overflow">
                    <div class="section_notice_footer_page"></div>
                </div>
                <p class="end_btn">>></p>

                <!-- 페이징 처리에 필요한 전체 게시글 갯수 -->
                <script>
                    let camping_count = '<c:out value="${camping_count}"/>';
                </script>
                <c:if test="${member_auth eq '관리자'}"><!-- 관리자라면 글삭제 버튼이 보이게 -->
                <input type="button" value="글삭제" class="delete_btn">
                </c:if>
                <c:if test="${member_auth ne '관리자'}"><!-- 관리자가 아니면 글삭제 버튼이 안 보이게 -->
                <input type="button" value="글삭제" class="delete_btn" style="display: none">
                </c:if>
                <input type="button" value="글쓰기" class="write_btn">
            </div>
        </div>
    </div>
</main>

<!--푸터-->
<%@ include file="../footer.jsp" %>
<script src="<c:url value="/resources/js/community/joinNotice/main.js?ver=1"/>"></script>
</body>
</html>