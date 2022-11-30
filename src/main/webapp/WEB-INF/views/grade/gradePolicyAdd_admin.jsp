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

    <script src="//code.jquery.com/jquery-3.3.1.min.js"></script>
    <title>등급 정책</title>
</head>
<body>
<!--헤더-->
<%@include file="../shop/shop_header.jsp" %>
<div class="gradeAddContainer">
    <h1>등급 추가</h1>
    <form method="post" action="/gradePolicyAdd_admin" enctype="multipart/form-data">

        <div class="gradeAddLineDiv">
            <span>단계</span>
            <input type="text" id="grade_comment" name="grade_comment" value="${gradeCount}단계" readonly="readonly">
		</div>
        <div class="gradeAddLineDiv">
            <span>등급명</span>
           <input type="text" id="grade_name" name="grade_name" required="required">
        </div>
        <div class="gradeAddLineDiv">
            <span>시작 포인트</span>
            <input type="text" id="grade_start_point" name="grade_start_point" required="required">
        </div>
        <div class="gradeAddLineDiv">
            <span>끝 포인트</span>
           <input type="text" id="grade_end_point" name="grade_end_point" required="required">
        </div>
        <div class="gradeAddLineDiv">
            <span>할인율</span>
           <input type="text" id="grade_discount" name="grade_discount" required="required">
        </div>
        <div class="gradeAddLineDiv">
            <span>적립율</span>
            <input type="text" id="grade_accrual_rate" name="grade_accrual_rate" required="required">
        </div>
        <div class="gradeAddLineDiv">
            <span>배경색</span>
           <input type="color" id="grade_color" name="grade_color" required="required">
        </div>
        <div class="gradeAddLineDiv">
            <span>글자색</span>
           <input type="color" id="grade_font_color" name="grade_font_color">
        </div>
        <div class="gradeAddLineDiv">
            <span>대표 이미지</span>
            <input type="file" id="grade_img_url" name="img_url" required="required">
        </div>
		<div class="gradeAddBtnDiv">
			<input type="submit" id="submit" value="저장" class="gradeAddBtn">
		</div>

    </form>
</div>

<!--푸터-->
<%@include file="../shop/shop_footer.jsp" %>
<script src="resources/js/gradePolicy_admin.js"></script>

</body>
</html>