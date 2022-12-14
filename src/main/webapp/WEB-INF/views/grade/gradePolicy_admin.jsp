<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!--쇼핑몰 글로벌 css-->
    <link rel="stylesheet" href="/resources/css/global.css">
    <link rel="stylesheet" href="/resources/css/grade/gradePolicy_admin.css">

    <!-- Jquery cdn -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <!--xeicon-->
    <link rel="stylesheet" href="//cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css">
    <title>등급 정책 관리 페이지</title>
</head>
<body>
<!--헤더-->
<%@include file="../shop/shop_header.jsp" %>
<!--메인-->
<div class="gradePolicyContainer">
    <h1>등급 정책 관리</h1>
    <div class="gradePolicyTop">
        <p>확인 사항 : 총 ${gradeCount}개의 등급이 있습니다.</p>
        <div class="gradePolicyBtnDiv">
            <input type="button" value="추가" class="gradeBtn  insert_btn" onclick="location.href='/gradePolicyAdd_admin'"/>
        </div>
    </div>

    <div class="gradePolicyBottom">
        <div class="gradePolicyLineHeaderDiv">
            <span>번호</span>
            <span>등급명</span>
            <span>시작 포인트</span>
            <span>끝 포인트</span>
            <span>할인율</span>
            <span>적립율</span>
            <span>배경색</span>
            <span>글자색</span>
            <span>이미지</span>
        </div>
        <c:forEach items="${gradePolicy}" var="item" varStatus="count">
            <div class="gradePolicyLineDiv">
                <span class="gradePolicyLineDivSpan">${count.count}</span>
                <span class="gradePolicyLineDivSpan">${item.grade_name}</span>
                <span class="gradePolicyLineDivSpan"><fmt:formatNumber type="Number" pattern="#,###" value="${item.grade_start_point}"/>P</span>
                <span class="gradePolicyLineDivSpan"><fmt:formatNumber type="Number" pattern="#,###" value="${item.grade_end_point}"/>P</span>
                <span class="gradePolicyLineDivSpan"><fmt:formatNumber type="percent" value="${item.grade_discount}"/></span>
                <span class="gradePolicyLineDivSpan"><fmt:formatNumber type="percent" value="${item.grade_accrual_rate}"/></span>
                <div class="gradePolicyColorDiv" style="background-color:${item.grade_color}"></div>
                <div class="gradePolicyColorDiv" style="background-color:${item.grade_font_color}"></div>
                <img src="${item.grade_img_url}" class="gradePolicyImg">
            </div>
        </c:forEach>
    </div>
</div>
<div class="explainGradePolicyDiv">
    <i class="xi-close closeIcon"></i>
    <div>
        <span class="explainGradePolicyDivTitleSpan">번호</span>
        <span class="explainGradePolicyDivSpan"></span>
    </div>
    <div>
        <span class="explainGradePolicyDivTitleSpan">등급명</span>
        <span class="explainGradePolicyDivSpan"></span>
    </div>
    <div>
        <span class="explainGradePolicyDivTitleSpan">시작 포인트</span>
        <span class="explainGradePolicyDivSpan"></span>
    </div>
    <div>
        <span class="explainGradePolicyDivTitleSpan">끝 포인트</span>
        <span class="explainGradePolicyDivSpan"></span>
    </div>
    <div>
        <span class="explainGradePolicyDivTitleSpan">할인율</span>
        <span class="explainGradePolicyDivSpan"></span>
    </div>
    <div>
        <span class="explainGradePolicyDivTitleSpan">적립율</span>
        <span class="explainGradePolicyDivSpan"></span>
    </div>

</div>

<!--푸터-->
<%@include file="../shop/shop_footer.jsp" %>
<script src="/resources/js/grade/gradePolicy_admin.js"></script>

</body>
</html>