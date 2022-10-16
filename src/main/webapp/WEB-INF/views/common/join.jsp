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
<link rel="stylesheet" href="/resources/css/common/join.css">
<!-- Jquery cdn -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<!-- 구글 아이콘 닷닷닷 -->
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />

<title>회원가입</title>
</head>
<body>
	<!-- 진행상태 -->
	<div class="progressDiv">
		<span class="material-symbols-outlined backOne"> looks_one </span> 
		<span class="material-symbols-outlined backOne"> more_horiz </span> 
		<span class="material-symbols-outlined one"> looks_two </span> 
		<span class="material-symbols-outlined oneDot"> more_horiz </span> 
		<span class="material-symbols-outlined"> looks_3 </span>
	</div>
	<!--회원가입 컨테이너-->
	<div class="joinContainer">
		<form action="join" method="post" onsubmit="return preDoJoinCheck();">
			<!--프로모션 이메일 수신 동의-->
			<input type="hidden" name="member_email_agree" value="${member_email_agree }">
			<!--이메일-->
			<div class="joinDivs">
				<span class="joinSpans">이메일(4~20 영문으로 시작, 숫자 포함 가능, 특수문자 포함 불허, 영문 소문자만 가능)</span>
				<div class="subDiv">
					<input type="text" name="" id="emailName" class="formInputs" placeholder="Enter Your Email" required> <span class="EmphasisSpan at">@</span> <input type="text" name="" id="domain" class="formInputs" placeholder="Select Your Domain" required readonly> <select name="" id="emailSelect" class="formInputs">
						<option value="init">선택하세요</option>
						<option value="naver.com">naver.com</option>
						<option value="gmail.com">gmail.com</option>
						<option value="daum.net">daum.net</option>
						<option value="nate.com">nate.com</option>
						<option value="self">직접 입력</option>
					</select> <input type="button" class="formBtns" id="checkDupEmailBtn" value="Email Check">
				</div>
				<!--이메일 완본 주소-->
				<input type="hidden" name="member_email"> <span class="emailSpan checkSpans"></span> <span class="domainSpan checkSpans"></span> <span class="emailDupCheckSpan checkSpans"></span>
			</div>
			<!--아이디-->
			<div class="joinDivs">
				<span class="joinSpans">아이디(4~20 영문으로 시작, 숫자 포함 가능, 특수문자 포함 불허, 중복확인시 대소문자 구분 x)</span> <input type="text" name="member_id" id="member_id" class="formInputs" placeholder="Enter Your Id" required> <span class="idSpan checkSpans"></span> <span class="idDupCheckSpan checkSpans"></span>
			</div>
			<!--비밀번호-->
			<div class="joinDivs">
				<span class="joinSpans">비밀번호(9~20 영문 소문자 + 숫자 + 특수문자의 조합)</span>
				<!-- <input type="password" name="member_pw" id="member_pw" class="joinInputs" placeholder="Enter Your Password" required> -->
				<input type="text" name="member_pw" id="member_pw" class="formInputs" placeholder="Enter Your Password" required> <span class="passwordSpan checkSpans"></span>
			</div>
			<!--비밀번호 확인-->
			<div class="joinDivs">
				<span class="joinSpans">비밀번호 확인</span> <input type="password" name="member_pw2" id="member_pw2" class="formInputs" placeholder="Enter Your Password For Confirm"> <span class="password2Span checkSpans"></span>
			</div>
			<!--이름-->
			<div class="joinDivs">
				<span class="joinSpans">이름(한글 1글자 이상)</span> <input type="text" name="member_name" id="member_name" class="formInputs" placeholder="Enter Your Name" required> <span class="nameSpan checkSpans"></span>
			</div>
			<!--핸드폰-->
			<div class="joinDivs">
				<span class="joinSpans">핸드폰(01로 시작, 10~11자리, '-' 생략)</span> <input type="number" name="member_phone" id="member_phone" class="formInputs" placeholder="Enter Your Phone" required> <span class="phoneSpan checkSpans"></span>
			</div>
			<!--나이-->
			<div class="joinDivs">
				<span class="joinSpans">나이(1~130)</span> <input type="number" name="member_age" id="member_age" class="formInputs" placeholder="Enter Your Age" required max="130" min="1"> <span class="ageSpan checkSpans"></span>
			</div>
			<!--성별-->
			<div class="joinDivs">
				<span class="joinSpans">성별</span>
				<div class="subDiv">
					<label for="male">남자</label> <input type="radio" name="member_gender" id="male" value="남자" checked> <label for="female">여자</label> <input type="radio" name="member_gender" id="female" value="여자">
				</div>
			</div>
			<!--주소-->
			<div class="joinDivs">
				<span class="joinSpans">주소</span> <input type="button" value="우편번호 찾기" id="addressSearchBtn" class="formBtns"> <input type="text" id="postcode" placeholder="우편번호" class="formInputs" name="member_postal_code" readonly> <input type="text" id="roadAddress" placeholder="도로명주소" class="formInputs addressInputs" readonly>
				<!-- 지번 주소 필요 없어서 생략 -->
				<!-- <input type="text" id="jibunAddress" placeholder="지번주소" class="joinInputs addressInputs" readonly> -->
				<span id="guide" style="color: #999; display: none"></span> <input type="text" id="detailAddress" placeholder="상세주소" class="formInputs addressInputs"> <input type="text" id="extraAddress" placeholder="참고항목" class="formInputs addressInputs" readonly> <span class="addressSpan checkSpans"></span> <input type="hidden" name="member_address">

			</div>

			<!--가입-->
			<div class="joinDivs">
				<input type="submit" value="가입하기" class="formBtns">
			</div>
		</form>
	</div>
	<!--회원가입 컨테이너 끝-->
	<!--카카오 주소찾기 api-->
	<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<script src="/resources/js/common/join.js"></script>
</body>
</html>