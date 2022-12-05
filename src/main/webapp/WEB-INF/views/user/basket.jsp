<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!--글로벌 css-->
    <link rel="stylesheet" href="/resources/css/global.css">
    <!--장바구니 css-->
    <link rel="stylesheet" href="/resources/css/user/basket.css">
    <!--xeicon-->
    <link rel="stylesheet" href="//cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css">
    <!--장바구니 아이콘 cdn-->
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200"/>
    <!-- 제이쿼리 cdn -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <title>장바구니 페이지</title>
</head>

<body>
<%@include file="../shop/shop_header.jsp" %>


<!--메인-->
<form name="basketForm">
    <main>
        <!--장바구니 컨테이너-->
        <div class="basketContainer">
            <div class="basketHeader">
                <h2>나의 장바구니</h2>
            </div>
            <!--장바구니 상품 정보 구역-->
            <div class="basketMiddle">
                <c:if test="${empty list }">
                    <p style="color: var(--subFontColor4); margin: 1rem;">장바구니가 비었습니다.</p>
                </c:if>
                <c:if test="${!empty list }">
                    <div id="basketTable">
                        <div class="basketTableTop">
                            <span></span>
                            <span>상품사진</span>
                            <span>상품명</span>
                            <span>수량</span>
                        </div>
                        <div class="basketTableBottom">
                            <c:forEach var="item" items="${list }">
                                <div class="basketLineDiv">
                                    <div class="basketLineInnerDiv">
                                        <input type="radio" name="basketRadio" value="${item.basket_id }"
                                               class="basketRadio">
                                    </div>
                                    <div class="basketLineInnerDiv">
                                        <img src="${item.product_img_url1 }" class="productImg">
                                    </div>
                                    <div class="basketLineInnerDiv">
                                        <a href="/product/detail?product_id=${item.product_id}" class="productAnchor">
                                                ${item.product_name }
                                        </a>
                                    </div>
                                    <div class="basketLineInnerDiv">
                                            ${item.basket_amount }
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                    <div class="btnDiv">
                        <input type="button" id="deleteBtn" value="삭제하기" class="formBtns">
                        <input type="button" id="buyBtn" value="구매하기" class="formBtns">
                    </div>
                </c:if>
            </div>
        </div>
    </main>
</form>

<div class="ajaxResultTest"></div>
<%@include file="../shop/shop_footer.jsp" %>

<script src="/resources/js/user/basket.js"></script>
</body>

</html>