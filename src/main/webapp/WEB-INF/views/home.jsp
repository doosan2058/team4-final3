<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- 글로벌 css -->
    <link rel="stylesheet" href="/resources/css/global.css">
    <link rel="stylesheet" href="/resources/css/home.css">
    <!-- 구글 아이콘 -->
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200"/>
    <!--xeicon-->
    <link rel="stylesheet" href="//cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css">
    <title>시작화면</title>

</head>

<body>
<div class="homeContainer">
    <!--로그인 아이디-->
    <input type="hidden" id="loginCheckHiddenInput" value="${sessionScope.login_id}">
    <!--로그인 권한-->
    <input type="hidden" id="authCheckHiddenInput" value="${sessionScope.login_auth}">

    <div class="homeTop">
        <div class="idDiv">
            <c:if test="${sessionScope.login_id ne null}">
                <span class="loginNameSpan"> ${sessionScope.login_id }</span>
                님 환영합니다.
            </c:if>
        </div>
        <div class="menuDiv">
            <a id="loginBtn" href="login">
                <span class="material-symbols-outlined iconSpan">login</span>
                <span class="headerAnchorTextSpan">로그인</span>
            </a>
            <a id="logOutBtn">
                <span class="material-symbols-outlined iconSpan">logout</span>
                <span class="headerAnchorTextSpan">로그아웃</span>
            </a>
            <a id="joinBtn" href="agree">
                <span class="material-symbols-outlined iconSpan">person_add</span>
                <span class="headerAnchorTextSpan">회원가입</span>
            </a>
        </div>
    </div>

    <div class="homeMain">
        <div class="shopDiv">
            <div class="homeImgWrapDiv">
                <h1>쇼핑</h1>
                <p>캠핑 용품을 구매하세요.</p>
            </div>

        </div>
        <div class="communityDiv">
            <div class="homeImgWrapDiv">
                <h1>커뮤니티</h1>
                <p>사람들과 여행하세요.</p>
            </div>
        </div>
    </div>
</div>


<script src="/resources/js/home.js"></script>
</body>
</html>