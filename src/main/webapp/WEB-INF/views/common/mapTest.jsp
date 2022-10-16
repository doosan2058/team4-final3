<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="/resources/css/common/mapTest.css">
<!-- 글로벌 css -->
<link rel="stylesheet" href="/resources/css/global.css">
<!-- Jquery cdn -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<title>Map Test</title>

</head>
<body>
	<!--지도 컨테이너-->
	<div class="mapContainer">
		<!--지도-->
		<div id="mapDiv"></div>
		<!--캠핑장 선택 구역-->
		<div class="selectDiv">
			<select name="" id="selectMap">
				<option value="">지도 초기화</option>
				<option value="서울">서울</option>
				<option value="부산">부산</option>
				<option value="대구">대구</option>
				<option value="인천">인천</option>
				<option value="광주">광주</option>
				<option value="대전">대전</option>
				<option value="울산">울산</option>
				<option value="세종">세종</option>
				<option value="경기도">경기도</option>
				<option value="강원도">강원도</option>
				<option value="충청북도">충청북도</option>
				<option value="충청남도">충청남도</option>
				<option value="전라북도">전라북도</option>
				<option value="전라남도">전라남도</option>
				<option value="경상북도">경상북도</option>
				<option value="경상남도">경상남도</option>
				<option value="제주도">제주도</option>

			</select>
			<button id="closeInfoWindowBtn">인포윈도우 닫기</button>
		</div>
		<!--결과 표시 구역-->
		<div class="resultDiv"></div>
	</div>

	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=b3fddae03c72f192c7b74da13d2a55b0&libraries=services"></script>
	<script src="/resources/js/common/mapTest.js"></script>
</body>
</html>