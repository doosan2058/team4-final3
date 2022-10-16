<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- 글로벌 css -->
<link rel="stylesheet" href="/resources/css/global.css">
<link rel="stylesheet" href="/resources/css/common/forgot.css">

<!-- Jquery cdn -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<title>비밀번호 찾기</title>
</head>
<body>
	<form method="post" action="forgot">
		<!-- 비밀번호 찾기 컨테이너 -->
		<div class="forgotContainer">
			<!-- 아이디 -->
			<div class="idDiv">
				<input type="text" name="member_id" id="member_id" class="formInputs" placeholder="아이디를 입력하세요.">
			</div>
			<!-- 이메일 -->
			<div class="emailDiv">
				<input type="email" name="member_email" id="member_email" class="formInputs" placeholder="이메일을 입력하세요.">
			</div>
			<!-- 버튼 -->
			<div class="btnDiv">
				<input type="submit" class="formBtns" value="비밀번호 초기화">
				<input type="button" class="formBtns" id="backBtn" value="돌아가기" >
			</div>
		</div>
	
	</form>

<script src="/resources/js/common/forgot.js"></script>
</body>
</html>