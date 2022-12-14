<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!--글로벌 css-->
    <link rel="stylesheet" href="/resources/css/community_global.css">
    <!--xeicon-->
    <link rel="stylesheet" href="//cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css">
    <title>Document</title>
</head>
<body>
<!--헤더-->
<header>
    <div class="logo">
        <a href="/"><h1>Camp<span>ing</span></h1></a>
    </div>

    <div class="menu">
        <a href="/community/freeNotice/main">자유게시판</a>
        <a href="/community/joinNotice/main">캠핑모임게시판</a>
    </div>

    <div class="login">
        <a href="/community/main">
            <i class="xi-forum"></i><span class="loginSpan">Community</span>
        </a>
        <a href="/shop">
            <i class="xi-shop"></i><span class="loginSpan">Shop</span>
        </a>

        <c:if test="${sessionScope.login_id eq null}">
            <a href="/login">로그인</a>
            <a href="/agree">회원가입</a>
            <a id="loginName" class="noneClass" href="/user">${sessionScope.login_id }님</a>
            <a id="logOutBtn" class="noneClass">로그아웃</a>
        </c:if>

        <c:if test="${sessionScope.login_id ne null}">
            <a href="/login" class="noneClass">로그인</a>
            <a href="/agree" class="noneClass">회원가입</a>
            <a id="loginName" href="/user">${sessionScope.login_id }님</a>
            <a id="logOutBtn" href="/logout">로그아웃</a>
        </c:if>

        <input type="hidden" id="user_id" value="${sessionScope.login_id}">
        <input type="hidden" id="user_auth" value="${sessionScope.login_auth}">
    </div>


</header>

</body>
</html>