<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- 글로벌 css -->
    <link rel="stylesheet" href="/resources/css/global.css">
    <link rel="stylesheet" href="/resources/css/common/auth_mail.css">
    <!-- 구글 아이콘 닷닷닷 -->
    <link rel="stylesheet"
          href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200"/>

    <title>인증번호 체크</title>
</head>
<body>
<!-- 진행상태 -->
<div class="progressDiv">
    <span class="material-symbols-outlined backOne"> looks_one </span>
    <span class="material-symbols-outlined backOne"> more_horiz </span>
    <span class="material-symbols-outlined backOne"> looks_two </span>
    <span class="material-symbols-outlined backOne"> more_horiz </span>
    <span class="material-symbols-outlined one"> looks_3 </span>
</div>

<form action="joinSuccess" method="post" id="joinForm">
    <input type="hidden" name="member_id" value="${member.member_id }">
    <input type="hidden" name="member_pw" value="${member.member_pw }">
    <input type="hidden" name="member_email" value="${member.member_email }">
    <input type="hidden" name="member_name" value="${member.member_name }">
    <input type="hidden" name="member_phone" value="${member.member_phone }">
    <input type="hidden" name="member_age" value="${member.member_age }">
    <input type="hidden" name="member_gender" value="${member.member_gender }">
    <input type="hidden" name="member_postal_code" value="${member.member_postal_code }">
    <input type="hidden" name="member_address" value="${member.member_address }">
    <input type="hidden" name="member_email_agree" value="${member.member_email_agree }">
    <input type="hidden" name="member_profile_img_url" value="${member.member_profile_img_url }">
</form>
<div class="codeDiv">
	<h2>랜덤 코드 : ${code }</h2>
</div>



<!-- 이메일 인증번호 -->
<div class="secretEmailDiv">
    <input type="hidden" id="codeInput" value=${code }>
    <input type="text" name="secretEmailText" id="secretEmailText" class="formInputs" placeholder="인증번호를 입력해 주세요">
    <button class="formBtns">인증번호 체크</button>
    <span class="secretEmailSpan"></span>
</div>


<script src="/resources/js/common/auth_mail.js"></script>
</body>
</html>