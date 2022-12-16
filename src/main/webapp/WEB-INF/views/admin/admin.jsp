<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>관리자 페이지</title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!--글로벌 css-->
    <link rel="stylesheet" href="/resources/css/global.css">
    <link rel="stylesheet" href="/resources/css/admin/admin.css">
	<!--xeicon-->
	<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css">
    <!-- Jquery cdn -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <!-- chart.js -->
    <script src="https://cdn.jsdelivr.net/npm/chart.js@3.9.1/dist/chart.min.js"></script>
</head>
<body>
<div class="container">
    <!-- 왼쪽 네비 -->
    <div class="nav">
        <div class="navInnerTop">
            <span class="xi-bars navIcon menuIcons"></span>
        </div>
		<div class="navInnerBottom">
			<a class="allMemberAnchor">
				<span class="xi-users menuIcons"></span>
				<span class="textSpan">회원 관리</span>
			</a>
			<a class="mailAnchor">
				<span class="xi-mail-o menuIcons"></span>
				<span class="textSpan">프로모션 메일</span>
			</a>
			<a class="graphAnchor">
				<span class="xi-chart-bar-square menuIcons"></span>
				<span class="textSpan">모니터링</span>
			</a>
			<a class="productAddAnchor">
				<span class="xi-cart-add menuIcons"></span>
				<span class="textSpan">상품 등록</span>
			</a>
			<a class="gradeAnchor">
				<span class="xi-cog menuIcons"></span>
				<span class="textSpan">등급 관리</span>
			</a>
			<a class="homeAnchor">
				<span class="xi-home menuIcons"></span>
				<span class="textSpan">나가기</span>
			</a>
		</div>
	</div>
    <!-- 오른쪽 메인 -->
    <div class="main">
        <div class="mainTop">
            <h1>관리자 페이지</h1>
        </div>
		<div class="mainBottom"></div>
	</div>
</div>


<script src="/resources/js/admin/admin.js"></script>
</body>
</html>