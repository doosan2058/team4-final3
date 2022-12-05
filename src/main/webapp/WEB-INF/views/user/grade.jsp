<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!-- 글로벌 css -->
    <link rel="stylesheet" href="/resources/css/global.css">
    <link rel="stylesheet" href="/resources/css/user/grade.css">
    <!--xeicon-->
    <link rel="stylesheet" href="//cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css">
    <title>등급 정책</title>
</head>
<body>
<jsp:include page="../shop/shop_header.jsp" />
<div class="gradeInfoContainer">
    <!-- 메인 윗 부분 : 이번달 내 등급 -->
    <div class="myGradeState" style="background-color: ${Info.grade_color}">
        <div class="myGradeState_top">
			<h3>${Info.member_id}님의 현재 등급은 <span style="color: ${Info.grade_font_color}"> ${Info.grade_name} </span> 입니다. </h3>
			<p>누적 ${Info.member_purchase_point + Info.member_write_point} p</p>
		</div>
        <div class="myGradeState_bottom" style="border: solid 1px ${Info.grade_font_color}">
            <div id="myGradeState_bottom_title">등급혜택</div>
            <div id="myGradeState_bottom_text">
                제품 구매 시 전 제품
				<span style="color: ${Info.grade_font_color};">
				<fmt:formatNumber type="percent" value="${Info.grade_discount}"/> 할인</span> /
				<span style="color: ${Info.grade_font_color};">
				<fmt:formatNumber type="percent" value="${Info.grade_accrual_rate}"/> 적립</span>

            </div>
        </div>
    </div>


    <!-- 메인 아랫부분 : 등급별 혜택 안내 -->
    <div class="gradeAdventage">
        <div class="grade_info">
			<h3>등급별 혜택 안내</h3>
            <ul>
				<li>누적 포인트 합산 후, 자동으로 등급을 산정합니다.</li>
                <li>등급 관련 변경사항이 있을 시 공지사항을 통해 공지합니다.</li>
            </ul>
        </div>
        <c:forEach var="Info" items="${map.InfoAll}">
            <div class="gradeDiv" data-hover="${Info.grade_font_color}" style="background-color: ${Info.grade_color}; border:solid 2px ${Info.grade_font_color};">
                <div class="grade_left">
                    <h3 style="color: ${Info.grade_font_color}">${Info.grade_name }</h3>
                    <p>누적 포인트 <fmt:formatNumber type="Number" pattern="#,###" value="${Info.grade_start_point}"/>P 이상</p>
                    <p>
                        상품 구매 시
						<span style="color: ${Info.grade_font_color}"><fmt:formatNumber type="percent" value="${Info.grade_discount}"/> 할인</span>
                        <span style="color: ${Info.grade_font_color}"><fmt:formatNumber type="percent" value="${Info.grade_accrual_rate}"/> 적립 </span>
                    </p>
                </div>
                <div class="grade_right">
                    <img src="${Info.grade_img_url }">
                </div>
            </div>
        </c:forEach>
    </div>

</div>
<jsp:include page="../shop/shop_footer.jsp" />
<script src="/resources/js/user/grade.js"></script>

</body>
</html>