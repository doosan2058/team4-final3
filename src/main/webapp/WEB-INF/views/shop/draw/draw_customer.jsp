<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!--xeicon-->
    <link rel="stylesheet" href="//cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css">
    <script src="https://code.jquery.com/jquery-3.6.0.js"></script>

    <!-- 글로벌 css -->
    <link rel="stylesheet" href="/resources/css/global.css">
    <link rel="stylesheet" href="/resources/css/shop/draw/draw_customer.css">

    <title>이벤트 페이지</title>
</head>

<body>
<!-- 헤더 -->
<jsp:include page="../shop_header.jsp"/>
<div class="drawContainer">
    <div class="drawHeaderDiv">
        <span>Lucky Draw</span>
        <span> - 한정판 상품 구매 이벤트에 응모하세요!</span>
    </div>
    <div class="drawBodyDiv">
        <c:forEach var="item" items="${draw}">
            <div class="oneDrawDiv">
                <div class="oneDrawTop">
                    <h3>
                        <c:if test="${item.draw_close eq 'n'}">
                            <span style="color: var(--fontColor);">[진행중]</span>
                        </c:if>
                        <c:if test="${item.draw_close eq 'y'}">
                            <span style="color: rgba(96,96,96,0.66);">[마감]</span>
                        </c:if>
                        <span>${item.draw_title}</span>
                    </h3>
                </div>
                <div class="drawCenterDiv">
                    <div class="drawImgDiv">
                        <!--이벤트 이미지-->
                        <c:if test="${item.product_img_url1 ne 'no url' }">
                            <img src="${item.product_img_url1 }" alt="이미지 준비중 입니다." class="productMainImg">
                        </c:if>
                    </div>
                    <div class="drawBottomDiv">
                        <ul>
                            <li>
                                <span class="font_bottom_td">상품 이름</span>
                                <span>
                                    <a href="/product/detail?product_id=${item.product_id}" class="productAnchor">${item.product_name}</a>
                                </span>
                            </li>
                            <li>
                                <span class="font_bottom_td">가격</span>
                                <span><fmt:formatNumber value="${item.product_price}"/> 원</span>
                            </li>
                            <li>
                                <span class="font_bottom_td">인원</span>
                                <span>${item.draw_reqruit} 명</span>
                            </li>
                            <li>
                                <span class="font_bottom_td">이벤트 등록일</span>
                                <fmt:parseDate value="${item.draw_regdate}" pattern="yyyy-MM-dd'T'HH:mm" var="parseRegDate" />
                                <span>
                                     <fmt:formatDate value="${parseRegDate}" pattern="yyyy/MM/dd"/>
                                </span>
                            </li>
                            <li>
                                <span class="font_bottom_td">응모 기간</span>
                                <span>
                                    <fmt:parseDate value="${item.draw_event_start_date}" pattern="yyyy-MM-dd'T'HH:mm" var="parseStartDate" />
                                    <fmt:formatDate value="${parseStartDate}" pattern="yyyy/MM/dd"/>
                                    ~
                                    <fmt:parseDate value="${item.draw_event_end_date}" pattern="yyyy-MM-dd'T'HH:mm" var="parseEndDate" />
                                    <fmt:formatDate value="${parseEndDate}" pattern="yyyy/MM/dd"/>
                                </span>
                            </li>
                            <li>
                                <span class="font_bottom_td">이벤트 내용</span>
                                <span>${item.draw_comment}</span>
                            </li>
                        </ul>
                    </div>
                </div>

                <div class="drawBtnDiv">
                    <input type="hidden" value="<fmt:formatDate value="${parseStartDate}" pattern="yyyy/MM/dd"/>">
                    <input type="hidden" value="<fmt:formatDate value="${parseEndDate}" pattern="yyyy/MM/dd"/>">
                    <input type="hidden" value="${item.draw_id}">
                    <input type="button" value="응모하기" class="drawApplicationBtn">
                    <input type="button" value="응모 결과 보기" class="showWinnerBtn">
                    <input type="hidden" value="${item.product_id}">
                </div>
            </div>
        </c:forEach>
    </div>
</div>

<div class="winnerListContainer">
    <div class="winnerListDiv">
        <div class="winnerListTop">
            <input type="hidden" id="winnerListDivDrawIdInput">
            <span>당첨자 목록</span>
            <span class="xi-close closeWinnerListContainerIcon"></span>
        </div>

        <div class="winnerListBottom">

        </div>
    </div>
</div>


<!-- 푸터 -->
<jsp:include page="../shop_footer.jsp"/>
<script src="/resources/js/shop/draw/draw_customer.js"></script>

</body>

</html>