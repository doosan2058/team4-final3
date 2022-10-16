<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<!-- 글로벌 css -->
<link rel="stylesheet" href="/resources/css/global.css">
<link rel="stylesheet" href="/resources/css/user/grade.css">
<!--쇼핑몰 글로벌 아이콘 cdn-->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/xeicon@2/xeicon.min.css">
<title>Document</title>
</head>
<body>



	<!--메인-->
	<main>
		<div class="mainDiv">
			<!-- 메인 윗 부분 : 이번달 내 등급 -->
<!-- 			<div class="myGrade"> -->
				<div class="myGradeState"
					style="background-color: ${Info.grade_color}">
					<div class="myGradeState_top">
						<h3>${Info.member_id}님의등급은 ${Info.grade_name}</h3>
					</div>
					<div class="myGradeState_bottom" style= "border: solid 1px ${Info.grade_font_color}">
						<div id="circle">등급혜택</div>
						<div id="text1">
							제품 구매 시 전 제품 <b ${Info.grade_font_color}> <fmt:formatNumber type="percent"  value="${Info.grade_accrual_rate}" /> 적립</b>
			
<%-- 						<div id="text2">
							제품 구매 시 전 제품 <b ${Info.grade_font_color}> ${Info.grade_discount*100}% 할인</b>
						</div> --%>
					</div>
					</div>
					<div>
					
					</div>
				<!-- </div> -->
<%-- 					<div class="NextMonthGrade"
						style="background-color: ${nextInfo.grade_color}">
						<p class="p1">
							<span id="cus_money"><fmt:formatNumber type="Number" pattern = "#,###"  value="${Info.grade_end_point+1-Info.grade_start_point}" /></span>
							P 더 모으면 레벨업!
						</p>
					</div> --%>
			</div>



			<!-- 메인 아랫부분 : 등급별 혜택 안내 -->
			<div class="gradeAdventage">

				<div class="grade_info">
					<ul>
						<h3>등급별 혜택 안내</h3>
						<li>누적 포인트 합산 후, 자동으로 등급을 산정합니다.</li>
						<li>등급 관련 변경사항이 있을 시 공지사항을 통해 공지합니다.</li>
					</ul>
				</div>
					<c:forEach var="Info" items="${map.InfoAll}" >
		
					
					<div class="grade" data-hover="${Info.grade_font_color}" style="background-color: ${Info.grade_color}; border:solid 2px ${Info.grade_font_color};">
						<div class="grade_left">
							<h3 style="color: ${Info.grade_font_color}">${Info.grade_name }</h3>
							<p>누적 포인트 <fmt:formatNumber type="Number" pattern = "#,###"  value="${Info.grade_start_point}" />P 이상</p>
							<p>
								적립 시 <span style="color: ${Info.grade_font_color}"><fmt:formatNumber type="percent"  value="${Info.grade_discount}" /> 할인</span>
								<span style="color: ${Info.grade_font_color}"><fmt:formatNumber type="percent"  value="${Info.grade_accrual_rate}" /> 적립 </span>
							</p>
						</div>
						<div class="grade_right">
							<img src="/gradeImg/${Info.grade_img_url }">
						</div>
					</div>
					</c:forEach>



			</div>
		</div>
	</main>
	<script src="resources/js/user/grade.js"></script>

</body>
</html>