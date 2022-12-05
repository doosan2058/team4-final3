<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- 메인 css -->
    <link rel="stylesheet" href="<c:url value="/resources/css/community/freeNotice/main.css?ver=3"/>">
    <!-- 글로벌 css -->
    <link rel="stylesheet" href="/resources/css/global.css">
    <!--커뮤니티 글로벌 css-->
    <link rel="stylesheet" href="<c:url value="/resources/css/community/community_global.css?ver=1"/>">
    <!-- Jquery cdn -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <!--xeicon-->
    <link rel="stylesheet" href="//cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css">

    <title>Document</title>
</head>

<body>
<!-- 헤더 -->
<%@ include file="../header.jsp" %>
<!--메인-->
<main>
    <div class="side">
        <h1>
            자유
            <span>게시판</span>
        </h1>
        <!-- 사이드 메뉴 -->
        <%@ include file="side.jsp" %>
    </div>

    <div class="section">
        <div class="section_header">
            <li>글번호</li>
            <li>말머리</li>
            <li>제목</li>
            <li>글쓴이</li>
            <li>날짜</li>
            <c:choose>
                <c:when test="${sort_value eq 'h_desc'}">
                    <li class="hit_sort">조회수<i class="xi-caret-up"></i></li>
                </c:when>
                <c:otherwise>
                    <li class="hit_sort">조회수<i class="xi-caret-down"></i></li>
                </c:otherwise>
            </c:choose>

            <c:choose>
                <c:when test="${sort_value eq 'r_desc'}">
                    <li class="recommend_sort">추천수<i class="xi-caret-up"></i></li>
                </c:when>
                <c:otherwise>
                    <li class="recommend_sort">추천수<i class="xi-caret-down"></i></li>
                </c:otherwise>
            </c:choose>

        </div>

        <div class="section_notice">
            <div class="section_notice_main">
                <!--불러올 게시판 목록 -->
                <%@ include file="list.jsp" %>
            </div>

            <script>/* 검색 옵션을 담는 변수들 */
            let searching_keyword = '<c:out value="${keyword}"/>';
            let tag_id = '<c:out value="${tag_id}"/>';
            </script>

            <div class="section_notice_footer">
                <c:set var="board_count" value="${count.board_count}"/>

                <p class="first_btn"><<</p>
                <div class="section_notice_footer_overflow">
                    <div class="section_notice_footer_page"></div>
                </div>
                <p class="end_btn">>></p>

                <!-- 페이징 처리에 필요한 전체 게시글 갯수 -->
                <script>
                    let board_count = '<c:out value="${board_count}"/>';
                </script>
                <c:if test="${member_auth eq '관리자'}">
                    <input type="button" value="글삭제" class="delete_btn">
                </c:if>
                <c:if test="${member_auth ne '관리자'}">
                    <input type="button" value="글삭제" class="delete_btn" style="display: none">
                </c:if>
                <input type="button" value="글쓰기" class="write_btn">
            </div>
        </div>
    </div>
</main>

<!--푸터-->
<%@ include file="../footer.jsp" %>

<script src="<c:url value="/resources/js/community/freeNotice/main.js?ver=3"/>"></script>
</body>

</html>