<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="/resources/css/user/review.css">
<link rel="stylesheet" href="/resources/css/global.css">
<!--구글 폰트-->
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
<title>Document</title>
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
			<!--상품 이름-->
			<div class="reviewGradeDiv">
				<div class="gradeTop">
					<span> 주문 번호 : ${sales_id }</span> 
					
					<span class="productGradeSpan">점수를 선택해 주세요.</span>
					<!--상품 별점-->
				</div>
				<div class="gradeBottom">
					<div class="stars">
						<span class="material-symbols-outlined star star1"> star </span>
					</div>
					<div class="stars">
						<span class="material-symbols-outlined star star2"> star </span>
					</div>
					<div class="stars">
						<span class="material-symbols-outlined star star3"> star </span>
					</div>
					<div class="stars">
						<span class="material-symbols-outlined star star4"> star </span>
					</div>
					<div class="stars">
						<span class="material-symbols-outlined star star5"> star </span>
					</div>

				</div>
			</div>
			<!--배송-->
			<div class="reviewDeliveryDiv">
				<div class="speedDiv">느려요</div>
				<div class="speedDiv">보통이에요</div>
				<div class="speedDiv">빨라요</div>
				
				<!--배송속도-->
			</div>
			<!--리뷰작성-->
			<div class="reviewWriteDiv">
				<textarea maxlength="500" name="product_review_text" id="reviewTextArea" cols="30" rows="10" placeholder="리뷰를 작성해 주세요. (500자)"></textarea>
			</div>
			<!--첨부-->
			<div class="reviewAttachDiv">
				<input type="file" name="fileImg" id="fileImg" class="formInputs">
				<input type="file" name="fileVideo" id="fileVideo" class="formInputs">
			</div>
			<!--저장하기-->
			<div class="reviewBtnDiv">
				<input type="submit" value="저장하기" id="reviewBtn" class="formBtns">
			</div>
		</div>
	</form>

	<script src="/resources/js/user/review.js"></script>
</body>
</html>