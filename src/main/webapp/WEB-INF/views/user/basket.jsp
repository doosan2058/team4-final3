<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!--쇼핑몰 글로벌 css-->
    <link rel="stylesheet" href="/resources/css/global.css">
    <!--장바구니 css-->
    <link rel="stylesheet" href="/resources/css/user/basket.css">
    <!--쇼핑몰 글로벌 아이콘 cdn-->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/xeicon@2/xeicon.min.css">
    <!--장바구니 아이콘 cdn-->
    <link rel="stylesheet"
          href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200"/>
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

            <!--장바구니 상품 정보 구역-->
            <div class="basketMiddle">
                <c:if test="${empty list }">
                    <h1>장바구니가 비었습니다.</h1>
                </c:if>
                <c:if test="${!empty list }">
                    <table id="basketTable">
                        <thead>
                        <tr>
                            <th></th>
                            <th>상품 이미지</th>
                            <th>상품 이름</th>
                            <th>수량</th>

                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="item" items="${list }">
                            <tr>
                                <td>
                                    <input type="radio" name="basketRadio" value="${item.basket_id }"
                                           class="basketRadio">
                                </td>
                                <td>
                                    <img src="/productImg/${item.product_img_url1 }" width="50" height="50">
                                </td>
                                <td>
                                    <a href="/product/detail?product_id=${item.product_id}" class="productAnchor">
                                            ${item.product_name }
                                    </a>
                                </td>
                                <td>${item.basket_amount }</td>
                            </tr>
                        </c:forEach>

                        <tr>
                            <td colspan="4" class="btnTd">
                                <input type="button" id="deleteBtn" value="삭제하기" class="formBtns">
                                <input type="button" id="buyBtn" value="구매하기" class="formBtns">
                            </td>
                        </tr>


                        </tbody>
                    </table>

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