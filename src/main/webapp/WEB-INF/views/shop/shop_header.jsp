<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!--글로벌 css-->
    <link rel="stylesheet" href="/resources/css/global.css">
    <!--쇼핑몰 글로벌 css-->
    <link rel="stylesheet" href="/resources/css/shop/shop_global.css">
    <!-- Jquery cdn -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <!--쇼핑몰 글로벌 아이콘 cdn-->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/xeicon@2/xeicon.min.css">
    <!-- 구글 폰트 -->
    <link rel="stylesheet"
          href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200"/>
    <!--xeicon-->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/xeicon@2/xeicon.min.css">
    <title>쇼핑몰 헤더</title>
</head>
<body>
<div class="headerToolTipBox">
    테스트
</div>
<!--헤더-->
<header>
    <!--로그인 아이디-->
    <input type="hidden" id="loginCheckHiddenInput" value="${sessionScope.login_id}">
    <!--로그인 권한-->
    <input type="hidden" id="authCheckHiddenInput" value="${sessionScope.login_auth}">
    <div class="logo">
        <h1>Team4</h1>
    </div>
    <div class="menu">
        <a id="shop" href="/shop">Shop</a>
        <a class="draw">Draw</a>
        <a id="qna" href="/shop/qna">Q&A</a>
    </div>
    <div class="login">
        <a id="loginBtn" href="/login" data-tooltip="로그인 하기" class="loginMenuAnchor">
            <span class="material-symbols-outlined">login</span>
            <span class="headerAnchorTextSpan">로그인</span>
        </a>
        <a id="joinBtn" href="/agree" data-tooltip="회원가입 하기" class="loginMenuAnchor">
            <span class="material-symbols-outlined">person_add</span>
            <span class="headerAnchorTextSpan">회원가입</span>
        </a>

        <a id="loginName">
            <img alt="" src="" id="headerUserProfileImg">&nbsp;
            <span class="loginUserIdSpan">${sessionScope.login_id } 님</span>
        </a>
        <a id="basket" href="/user/basket" data-tooltip="장바구니 보기" class="loginMenuAnchor">
            <span class="material-symbols-outlined">shopping_basket</span>
            <span class="headerAnchorTextSpan">장바구니</span>
        </a>
        <a id="logOutBtn" data-tooltip="로그아웃 하기" class="loginMenuAnchor">
            <span class="material-symbols-outlined">logout</span>
            <span class="headerAnchorTextSpan">로그아웃</span>
        </a>

        <a href="/shop" id="loginShop" class="loginMenuAnchor">
            <i class="xi-shop"></i>
            <span class="headerAnchorTextSpan">쇼핑몰</span>
        </a>
        <a href="/community/main" id="loginCommu" class="loginMenuAnchor">
            <i class="xi-forum"></i>
            <span class="headerAnchorTextSpan">커뮤니티</span>
        </a>


    </div>
    <!--스마트폰 사이즈 용 로그인 메뉴 아이콘-->
    <span class="material-symbols-outlined more_horiz">more_horiz</span>
</header>


<script src="/resources/js/shop/shop_header.js"></script>
</body>

</html>