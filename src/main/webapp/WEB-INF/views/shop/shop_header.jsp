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

    <!--쇼핑몰 글로벌 아이콘 cdn-->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/xeicon@2/xeicon.min.css">
    <!-- 구글 폰트 -->
    <link rel="stylesheet"
          href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200"/>
    <title>쇼핑몰 헤더</title>
</head>
<body>
<div class="headerToolTipBox">
    테스트
</div>
<!--헤더-->
<header>
    <div class="logo">
        <h1>Camping</h1>
    </div>
    <div class="menu">
        <a id="shop" href="/shop">Shop</a>
        <c:if test="${sessionScope.login_auth eq '관리자'}">
            <a class="draw" href="/shop/draw_admin">Draw</a>
        </c:if>
        <c:if test="${sessionScope.login_auth eq '회원' || sessionScope.login_auth eq null}">
            <a class="draw" href="/shop/draw_customer">Draw</a>
        </c:if>
        <a id="qna" href="/shop/qna">Q&A</a>
    </div>
    <div class="login">

        <c:if test="${sessionScope.login_id eq null}">
            <a id="loginBtn" href="/login" data-tooltip="로그인 하기">로그인</a>
            <a id="joinBtn" href="/agree" data-tooltip="회원가입 하기">회원가입</a>
            <a id="loginName" class="noneClass" href="/user">${sessionScope.login_id }</a>
            <c:if test="${sessionScope.login_auth eq '회원'}"></c:if>
            <a id="basket" class="noneClass">
                <span class="material-symbols-outlined basketSpan">
                shopping_basket
                </span>
            </a>
            <a id="logOutBtn" class="noneClass" >로그아웃</a>
        </c:if>

        <c:if test="${sessionScope.login_id ne null}">
            <a id="loginBtn" href="/login" class="noneClass">로그인</a>
            <a id="joinBtn" href="/agree" class="noneClass">회원가입</a>
            <c:if test="${sessionScope.login_auth eq '관리자' }">
                <a id="loginName" href="/admin" data-tooltip="관리자 페이지 보기" data-login="${sessionScope.login_id }" ></a>
            </c:if>
            <c:if test="${sessionScope.login_auth eq '회원' }">
                <a id="loginName" class="loginInfo" href="/user" data-tooltip="내정보 페이지 보기" data-login="${sessionScope.login_id }">
                        </a>
            </c:if>
            <c:if test="${sessionScope.login_auth ne '회원'}">
                <a id="basket" href="user/basket" data-tooltip="장바구니 보기" class="noneClass">
                    <span class="material-symbols-outlined basketSpan">
                        shopping_basket
                    </span>
                </a>
            </c:if>
            <c:if test="${sessionScope.login_auth eq '회원'}">
                <a id="basket" href="user/basket" data-tooltip="장바구니 보기">
                    <span class="material-symbols-outlined basketSpan">
                        shopping_basket
                    </span>
                </a>
            </c:if>
            <a id="logOutBtn" data-tooltip="로그아웃 하기">로그아웃</a>
        </c:if>


    </div>
</header>


<script src="/resources/js/shop/shop_header.js"></script>
</body>

</html>