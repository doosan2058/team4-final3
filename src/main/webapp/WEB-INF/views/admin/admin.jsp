<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 페이지</title>

<!--글로벌 css-->
<link rel="stylesheet" href="/resources/css/global.css">
<link rel="stylesheet" href="/resources/css/admin/admin.css">
<!-- 구글 폰트 -->
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
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
				<span class="material-symbols-outlined navIcon"> menu </span>
			</div>

			<div class="navInnerBottom">
				<a class="allMemberAnchor">
					<span class="material-symbols-outlined"> group </span>
				</a>
				<a class="mailAnchor">
					<span class="material-symbols-outlined"> mail </span>
				</a>
				<a class="graphAnchor">
					<span class="material-symbols-outlined"> monitoring </span>
				</a>
				<a class="homeAnchor">
					<span class="material-symbols-outlined"> home </span>
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