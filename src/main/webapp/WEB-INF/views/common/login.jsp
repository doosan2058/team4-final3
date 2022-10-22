<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="/resources/css/common/login.css">
<!-- 글로벌 css -->
<link rel="stylesheet" href="/resources/css/global.css">

<title>로그인</title>
<style>
</style>
</head>

<body>
	
	<!--로그인 컨테이너-->
	<div class="loginContainer">
		<form action="login" method="post">
			<!--아이디 컨테이너-->
			<div class="idDiv">
				<div class="placeholderDiv">
					<input type="text" name="member_id" class="formInputs" required="required">
					<span class="placeholderSpan">아이디</span>
				</div>

			</div>
			<!--비밀번호 컨테이너-->
			<div class="pwDiv">
				<div class="placeholderDiv">
					<input type="password" name="member_pw" class="formInputs" required="required">
					<span class="placeholderSpan">비밀번호</span>
				</div>
			</div>
			<!--서브밋-->
			<div class="btnDiv">
				<div class="btnDivLeft">
					<input type="submit" value="로그인" class="formBtns">
				</div>
				<div class="btnDivRight">
					<a href="/agree" id="joinAnchor">아직 회원이 아니신가요?</a>
					<a href="/forgot" id="forgotAnchor">비밀번호 찾기</a>				
				</div>				
			</div>
		</form>
	</div>
	<script src="/resources/js/common/login.js"></script>
</body>

</html>