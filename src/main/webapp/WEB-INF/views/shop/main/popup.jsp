<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/resources/css/global.css">
    <link rel="stylesheet" href="/resources/css/shop/main/popup.css">
    <title>신상품</title>
    <!--xeicon-->
    <link rel="stylesheet" href="//cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css">
</head>
<body>
<!-- 팝업 컨테이너 -->
<div class="popupCon">
    <!-- 팝업 제목 -->
    <div class="titleDiv">
        <form name="popupForm" action="/product/detail" method="get">
            <h2>
                <span class="animateText">빅 - 뉴스!</span>
                <span class="xi-central-signal animateText"></span>
            </h2>
            <input type="hidden" value="${item.product_id }" id="product_id" name="product_id">
        </form>
    </div>
    <!-- 팝업 이미지 -->
    <div class="imgDiv">
        <img alt="" src="${item.product_img_url1 }" id="popupImg">
        <div class="popupTextDiv">
            <p>신상품 ${item.product_name } 이 입고 되었어요 ~ </p>
        </div>
    </div>
    <!-- 팝업 버튼 -->
    <div class="btnDiv">
        <button class="formBtns todayExit">오늘하루 보지 않기</button>
        <button class="formBtns justExit">닫기</button>
    </div>
</div>
<script src="/resources/js/shop/main/popup.js"></script>
</body>
</html>