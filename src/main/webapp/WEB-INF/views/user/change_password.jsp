<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- 글로벌 css -->
<link rel="stylesheet" href="/resources/css/global.css">
<link rel="stylesheet" href="/resources/css/user/change_password.css">

<!-- Jquery cdn -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>


<title>비밀번호 변경</title>
</head>
<body>
	
	<form action="change_password" method="post" onsubmit="return checkForm();">
		<!-- 비밀번호 변경 -->
		<div class="changePasswordCon">
			<div class="innerTop">
				<span class="explainSpan">9~20 영문 소문자 + 숫자 + 특수문자의 조합</span>
				<input type="password" class="formInputs" name="member_pw" id="member_pw" placeholder="비밀번호 입력">
				<span class="resultSpan1"></span>
				<input type="password" class="formInputs" id="member_pw2" placeholder="비밀번호 확인"> 
				<span class="resultSpan2"></span>
			</div>
			<div class="innerBottom">
				<input type="submit" class="formBtns" value="변경">
				<input type="button" class="formBtns" id="cancelBtn" value="취소">
				
			</div>
		</div>
	</form>
	<script src="/resources/js/user/change_password.js"></script>
</body>
</html>