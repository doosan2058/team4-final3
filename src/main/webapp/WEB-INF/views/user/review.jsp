<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/resources/css/user/review.css">
    <link rel="stylesheet" href="/resources/css/global.css">
	<!--xeicon-->
	<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css">
	<title>리뷰 작성 페이지</title>
</head>
<body>
<div class="navDiv">
    <a href="/user">내정보</a>
    <a href="/">메인</a>
</div>
<form action="review" method="post" enctype="multipart/form-data" onsubmit="return checkForm();">
    <div class="reviewCon">

        <!--전송목록-->
        <input type="hidden" name="sales_id" value="${sales_id }">
        <input type="hidden" name="product_review_grade" id="productGradeInput">
        <input type="hidden" name="product_review_speed" id="productSpeedInput">

        <div class="reviewDiv">
			<h4 class="reviewSubTitle">점수 등록하기<span class="essentialSpan"> *</span></h4>
			<div class="gradeTop">
                <span class="orderNumberSpan"> 주문 번호 : ${sales_id }</span>
                <span><span class="productGradeSpan">( )</span>점</span>
			</div>
            <div class="gradeBottom">
				<i class="xi-star-o star" data-num="star1"></i>
				<i class="xi-star-o star" data-num="star2"></i>
				<i class="xi-star-o star" data-num="star3"></i>
				<i class="xi-star-o star" data-num="star4"></i>
				<i class="xi-star-o star" data-num="star5"></i>
			</div>
        </div>
        <!--배송-->
        <div class="reviewDiv">
            <h4 class="reviewSubTitle">배송속도 선택하기<span class="essentialSpan"> *</span></h4>
			<div class="speedWrapDiv">
				<span class="speedSpan">느려요</span>
				<span class="speedSpan">보통이에요</span>
				<span class="speedSpan">빨라요</span>
			</div>
		</div>
        <!--리뷰작성-->
        <div class="reviewDiv">
            <h4 class="reviewSubTitle">내용 작성하기<span class="essentialSpan"> *</span></h4>
			<div class="reviewTextWrapDiv">
				<textarea maxlength="500" name="product_review_text" id="reviewTextArea" cols="30" rows="10" placeholder="리뷰를 작성해 주세요. (500자)" required></textarea>
				<span class="reviewTextLengthSpan">(0/500)</span>
			</div>
		</div>
        <!--첨부-->
        <div class="reviewDiv">
            <h4 class="reviewSubTitle">첨부파일 등록하기</h4>
			<div>
				<div class="reviewAttachInnerDiv">
					<label for="fileImg">사진을 올려주세요.</label>
					<span class="uploadImgSpan"></span>
					<input type="file" name="fileImg" id="fileImg" class="reviewFileInput">
				</div>
				<div class="reviewAttachInnerDiv">
					<label for="fileVideo">영상을 올려주세요.</label>
					<span class="uploadVedioSpan"></span>
					<input type="file" name="fileVideo" id="fileVideo" class="reviewFileInput">
				</div>
			</div>
		</div>
        <!--저장하기-->
        <div class="reviewDiv">
            <h4 class="reviewSubTitle">저장하기</h4>
			<div class="reviewBtnDiv">
				<input type="submit" value="저장하기" id="reviewBtn" class="formBtns">
			</div>
		</div>
    </div>
</form>

<script src="/resources/js/user/review.js"></script>
</body>
</html>