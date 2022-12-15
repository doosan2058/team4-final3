<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">

    <title>모니터링 페이지</title>
</head>
<body>
<div class="monitorContainer">
    <div class="monitorSubDiv">
        <div class="monitorTextDiv">
            <h3>방문자 수</h3>
            <p>오늘 : ${countMap.todayCount } | 이번주 : ${countMap.weekCount} | 전체 : ${countMap.totalCount }</p>
            <c:forEach var="item" items="${countTotalDayMap }">
                <input type="hidden" data-date="${item.date }" data-count="${item.count }" class="dailyCount">
            </c:forEach>
            <c:forEach var="item" items="${countTotalWeekMap }">
                <input type="hidden" data-date="${item.start}" data-count="${item.count }" class="weekCount">
            </c:forEach>
        </div>
        <div class="canvasDiv">
			<div class="canvasWrapDiv">
				<canvas id="line-chart"></canvas>
			</div>
			<div class="canvasWrapDiv">
				<canvas id="line-chart2"></canvas>
			</div>


        </div>
    </div>
    <div class="monitorSubDiv">
        <div class="monitorTextDiv">
            <h3>커뮤니티 게시글 수</h3>
            <p>오늘 : ${countMap.todayBoardCount } | 이번주 : ${countMap.weekBoardCount} | 전체 : ${countMap.totalBoardCount }</p>
			<c:forEach var="item" items="${countTotalDayMapBoard }">
                <input type="hidden" data-date="${item.date }" data-count="${item.count }" class="dailyCountBoard">

            </c:forEach>
            <c:forEach var="item" items="${countTotalWeekMapBoard }">
                <input type="hidden" data-date="${item.start}" data-count="${item.count }" class="weekCountBoard">

            </c:forEach>
        </div>
        <div class="canvasDiv">
			<div class="canvasWrapDiv">
				<canvas id="line-chart3"></canvas>
			</div>
			<div class="canvasWrapDiv">
				<canvas id="line-chart4"></canvas>
			</div>


		</div>
    </div>
    <div class="monitorSubDiv">
        <div class="monitorTextDiv">
            <h3>쇼핑몰 질문글 수</h3>
            <p>일간 : ${countMap.todayQnaCount } | 이번주 : ${countMap.weekQnaCount} | 전체 : ${countMap.totalQnaCount }</p>

            <c:forEach var="item" items="${countTotalDayMapQna }">
                <input type="hidden" data-date="${item.date }" data-count="${item.count }" class="dailyCountQna">

            </c:forEach>
            <c:forEach var="item" items="${countTotalWeekMapQna }">
                <input type="hidden" data-date="${item.start}" data-count="${item.count }" class="weekCountQna">

            </c:forEach>
        </div>
        <div class="canvasDiv">
			<div class="canvasWrapDiv">
				<canvas id="line-chart5"></canvas>
			</div>
			<div class="canvasWrapDiv">
				<canvas id="line-chart6"></canvas>
			</div>


		</div>

    </div>
    <div class="monitorSubDiv">
        <div class="monitorTextDiv">
            <h3>쇼핑몰 매출</h3>
            <p>일간 : <fmt:formatNumber value="${countMap.todaySalesCount }"/> | 주간 : <fmt:formatNumber value="${countMap.weekSalesCount}" /> | 전체 : <fmt:formatNumber
                    value="${countMap.totalSalesCount }"/></p>

            <c:forEach var="item" items="${countTotalDayMapShop }">
                <input type="hidden" data-date="${item.date }" data-count="${item.count }" class="dailyCountShop">

            </c:forEach>
            <c:forEach var="item" items="${countTotalWeekMapShop }">
                <input type="hidden" data-date="${item.start}" data-count="${item.count }" class="weekCountShop">

            </c:forEach>
        </div>
        <div class="canvasDiv">
			<div class="canvasWrapDiv">
				<canvas id="line-chart7"></canvas>
			</div>
			<div class="canvasWrapDiv">
				<canvas id="line-chart8"></canvas>
			</div>



        </div>

    </div>
</div>


<script src="/resources/js/admin/count.js"></script>
</body>
</html>