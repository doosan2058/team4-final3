<%--
  Created by IntelliJ IDEA.
  User: choi
  Date: 2022-11-04
  Time: 오후 4:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/xeicon@2/xeicon.min.css">
    <script src="https://code.jquery.com/jquery-3.6.0.js"></script>

    <!-- 글로벌 css -->
    <link rel="stylesheet" href="/resources/css/global.css">
    <link rel="stylesheet" href="/resources/css/shop/draw/draw_add.css">
    <title>이벤트 등록 페이지</title>
</head>
<body>
<!-- 헤더 -->
<jsp:include page="../shop_header.jsp"/>
<div class="drawHeaderNavDiv">
    <button class="addDrawBtn">이벤트 등록</button>
</div>
<form method="post" action="/draw/add" id="drawAddForm" onsubmit="return checkDrawAdd();">
    <div class="drawContainer">
        <div class="drawHeaderDiv">
            <span>Lucky Draw</span>
            <input type="hidden" name="product_id" id="product_id">
        </div>
        <div class="drawBodyDiv">
            <div class="oneDrawDiv">
                <div class="oneDrawTop">
                    <input type="text" name="draw_title" id="draw_title" required placeholder="이벤트 제목을 입력하세요.(최대 20자)"
                           maxlength="20">
                </div>
                <div class="drawCenterDiv">
                    <div class="drawImgDiv">
                        <div class="productMainImgWrapDiv">
                            <div class="productMainImgWrapDivCloseDiv">
                                <i class="xi-close closeImgWrapDivIcon"></i>
                            </div>

                            <img src="" alt="이미지 준비중 입니다." class="productMainImg">
                        </div>
                        <div class="addDrawProductDiv">
                            <i class="xi-plus plusIcon"></i>
                        </div>
                    </div>
                    <div class="drawBottomDiv">
                        <div class="infoWrapDiv">
                            <div class="infoTop">
                                상품 이름
                            </div>
                            <div class="infoBottom">
                                <span class="selectedProductNameSpan"></span>
                            </div>
                        </div>
                        <div class="infoWrapDiv">
                            <div class="infoTop">
                                가격
                            </div>
                            <div class="infoBottom">
                                <span class="selectedProductPriceSpan"></span>
                            </div>
                        </div>
                        <div class="infoWrapDiv">
                            <div class="infoTop">
                                인원
                            </div>
                            <div class="infoBottom">
                                <input type="number" value="" name="draw_reqruit" min="1" max="99"
                                       required> &nbsp;명
                            </div>
                        </div>
                        <div class="infoWrapDiv">
                            <div class="infoTop">
                                시작일
                            </div>
                            <div class="infoBottom">
                                <input type="date" id="draw_event_start_date" name="draw_event_start_date"
                                       required>
                            </div>
                        </div>
                        <div class="infoWrapDiv">
                            <div class="infoTop">
                                종료일
                            </div>
                            <div class="infoBottom">
                                <input type="date" id="draw_event_end_date" name="draw_event_end_date"
                                       required>
                            </div>
                        </div>
                        <div class="infoWrapDiv">
                            <div class="infoTop">
                                이벤트 내용
                            </div>
                            <div class="infoBottom">
                                <textarea name="draw_comment" id="draw_comment" required
                                          placeholder="이벤트 내용을 입력해 주세요.(최대 100자)" maxlength="100"></textarea>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="drawBtnDiv">
                    <input type="submit" value="등록" class="drawAddBtn">
                </div>
            </div>
        </div>
    </div>
</form>

<!-- 이벤트 상품 등록 컨테이너 -->
<div class="selectProductContainer">
    <div class="selectProductDiv">
        <div class="selectProductDivLeft">
            <img class="drawProductImg">
        </div>
        <div class="selectProductDivRight">
            <i class="xi-close closeIcon"></i>
            <div class="selectProductDivRightHeader">
                <select id="brandSelect">
                    <option value="0">브랜드</option>
                    <c:forEach var="item" items="${brandList}">
                        <option value="${item.product_brand_id}">${item.product_brand_name}</option>
                    </c:forEach>
                </select>
                <select id="categorySelect">
                    <option value="0">카테고리</option>
                    <c:forEach var="item" items="${categoryList}">
                        <option value="${item.product_category_id}">${item.product_category_name}</option>
                    </c:forEach>
                </select>

                <span class="selectSpan">검색</span>

            </div>
            <div class="selectProductDivRightMain">

            </div>
        </div>
    </div>
</div>
<!-- 푸터 -->
<jsp:include page="../shop_footer.jsp"/>
<script src="/resources/js/shop/draw/draw_add.js"></script>
</body>
</html>
