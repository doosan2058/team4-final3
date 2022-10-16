<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!--쇼핑몰 글로벌 css-->
<link rel="stylesheet" href="/resources/css/global.css">
<link rel="stylesheet" href="/resources/css/grade/gradePolicy_admin.css">

<script src="//code.jquery.com/jquery-3.3.1.min.js"></script>
<title>등급 정책</title>
</head>
<body>
	<!--헤더-->
	<%@include file="../shop/shop_header.jsp"%>
	<!--메인-->
	<main>
		<div class="navDiv"></div>
		<div class="mainDiv">
			<h2>등급 수정</h2>
			<hr>
			<form method="post" action="/gradePolicyAler_admin"
				enctype="multipart/form-data">
				<table class="board_detail">
					<tr>
						<th>단계</th>
						<td><input type="text" id="grade_comment"
							name="grade_comment" readonly="readonly"></td>
					</tr>
					<th>등급명</th>
					<td><input type="text" id="grade_name" name="grade_name"></td>
					</tr>
					<tr>
						<th>시작 포인트</th>
						<td><input type="text" id="grade_start_point"
							name="grade_start_point"></td>
					</tr>
					<tr>
						<th>끝 포인트</th>
						<td><input type="text" id="grade_end_point"
							name="grade_end_point"></td>
					</tr>
					<tr>
						<th>할인율</th>
						<td><input type="text" id="grade_discount"
							name="grade_discount"></td>
					</tr>
					<th>적립율</th>
					<td><input type="text" id="grade_accrual_rate"
						name="grade_accrual_rate"></td>
					</tr>
					<tr>
						<th>배경색</th>
						<td><input type="color" id="grade_color" name="grade_color"></td>
					</tr>
					<tr>
						<th>글자색</th>
						<td><input type="color" id="grade_font_color"
							name="grade_font_color"></td>
					</tr>
					<tr>
						<th>대표 이미지</th>
						<td><input type="file" id="grade_img_url" name="img_url"></td>
					</tr>


				</table>
				<input type="submit" value="변경" class="btn add_btn">
				<input type="submit" value="취소" class="btn rollback_btn">

			</form>
		</div>
	</main>
	<!--푸터-->
	<%@include file="../shop/shop_footer.jsp"%>
	<script src="resources/js/gradePolicy_admin.js"></script>

</body>
</html>