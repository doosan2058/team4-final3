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
    <link rel="stylesheet" href="/resources/css/grade/gradePolicyAdd_admin.css">

    <!-- Jquery cdn -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <title>등급 정책</title>
</head>
<body>
<!--헤더-->
<%@include file="../shop/shop_header.jsp" %>
<div class="gradeAddContainer">
    <span class="gradeCountSpan">${gradeCount + 1}</span><span class="gradeSpan">th 등급.</span>
    <form method="post" action="/gradePolicyAdd_admin" enctype="multipart/form-data" id="gradeForm" onsubmit="return checkGradeAddForm();">
        <div class="gradeAddLineDiv">
            <span>설명</span>
            <input type="text" id="grade_comment" name="grade_comment" placeholder="최대 50자" maxlength="50" required>
        </div>
        <div class="gradeAddLineDiv">
            <span>등급명</span>
            <input type="text" id="grade_name" name="grade_name" placeholder="최대 10자" maxlength="10" required>
        </div>
        <div class="gradeAddLineDiv">
            <span>시작 포인트</span>
            <input type="number" id="grade_start_point" name="grade_start_point" readonly
                   value="${lastGradeEndPoint + 1}">
        </div>
        <div class="gradeAddLineDiv">
            <span>끝 포인트</span>
            <input type="number" id="grade_end_point" name="grade_end_point"
                   placeholder="${lastGradeEndPoint + 1} 보다 큰 수를 입력하세요." min="${lastGradeEndPoint + 2}" required>
        </div>
        <div class="gradeAddLineDiv">
            <span>할인율(0.xx)</span>
            <input type="text" id="grade_discount" name="grade_discount" placeholder="${LastDiscountAndAccrualPoint.grade_discount} 보다 큰수를 입력하세요." required>
        </div>
        <div class="gradeAddLineDiv">
            <span>적립율(0.xx)</span>
            <input type="text" id="grade_accrual_rate" name="grade_accrual_rate" placeholder="${LastDiscountAndAccrualPoint.grade_accrual_rate} 보다 큰수를 입력하세요." required>
        </div>
        <div class="gradeAddLineDiv">
            <span>배경색</span>
            <input type="color" id="grade_color" name="grade_color" required>
        </div>
        <div class="gradeAddLineDiv">
            <span>글자색</span>
            <input type="color" id="grade_font_color" name="grade_font_color" required>
        </div>
        <div class="gradeAddLineDiv">
            <span>이미지</span>
            <input type="file" id="grade_img_url" name="img_url" required>
        </div>
        <div class="gradeAddBtnDiv">
            <input type="submit" id="submitBtn" value="저장" class="gradeAddBtn">
        </div>
    </form>
</div>

<!--푸터-->
<%@include file="../shop/shop_footer.jsp" %>
<script src="/resources/js/grade/gradePolicyAdd_admin.js"></script>

</body>
</html>