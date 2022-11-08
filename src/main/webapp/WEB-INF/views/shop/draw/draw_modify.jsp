<%--
  Created by IntelliJ IDEA.
  User: choi
  Date: 2022-11-03
  Time: 오후 5:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/xeicon@2/xeicon.min.css">
    <script src="https://code.jquery.com/jquery-3.6.0.js"></script>

    <!-- 글로벌 css -->
    <link rel="stylesheet" href="/resources/css/global.css">
    <link rel="stylesheet" href="/resources/css/shop/draw/draw_modify.css">
    <title>이벤트 수정 페이지</title>
</head>
<body>
<jsp:include page="../shop_header.jsp"/>
<div class="drawHeaderNavDiv">
    <button class="addDrawBtn">이벤트 등록</button>
</div>
<div class="drawContainer">
    <div class="drawHeaderDiv">
        <span>이벤트 수정</span>
    </div>
    <div class="drawBodyDiv">
        <form method="post" action="modify" id="drawModifyForm">
            <div class="oneDrawDiv">
                <div class="oneDrawTop">
                    <input type="text" value="${item.draw_title}" name="draw_title" id="draw_title" required placeholder="이벤트 제목을 입력하세요.(최대 20자)" maxlength="20">
                </div>
                <div class="drawCenterDiv">
                    <div class="drawImgDiv">
                        <!--이벤트 이미지-->
                        <c:if test="${item.product_img_url1 ne 'no url' }">
                            <img src="/productImg/${item.product_img_url1 }" alt="이미지 준비중 입니다." class="productMainImg">
                        </c:if>
                    </div>
                    <div class="drawBottomDiv">
                        <div class="infoWrapDiv">
                            <div class="infoTop">
                                상품 이름
                            </div>
                            <div class="infoBottom">
                                <a href="/product/detail?product_id=${item.product_id}"
                                   class="productAnchor">${item.product_name}</a>
                            </div>
                        </div>
                        <div class="infoWrapDiv">
                            <div class="infoTop">
                                가격
                            </div>
                            <div class="infoBottom">
                                <fmt:formatNumber value="${item.product_price}"/> 원
                            </div>
                        </div>
                        <div class="infoWrapDiv">
                            <div class="infoTop">
                                인원
                            </div>
                            <div class="infoBottom">
                                <input type="number" value="${item.draw_reqruit}" name="draw_reqruit" min="1" max="99"
                                       required> &nbsp;명
                            </div>
                        </div>
                        <div class="infoWrapDiv">
                            <div class="infoTop">
                                이벤트 등록일
                            </div>
                            <div class="infoBottom">
                                <fmt:parseDate value="${item.draw_regdate}" pattern="yyyy-MM-dd'T'HH:mm"
                                               var="parseRegDate"/>
                                <fmt:formatDate value="${parseRegDate}" pattern="yyyy/MM/dd"/>

                            </div>
                        </div>
                        <div class="infoWrapDiv">
                            <div class="infoTop">
                                이벤트 시작일
                            </div>
                            <div class="infoBottom">
                                <fmt:parseDate value="${item.draw_event_start_date}" pattern="yyyy-MM-dd'T'HH:mm"
                                               var="parseStartDate"/>
                                <input type="hidden"
                                       value="<fmt:formatDate value="${parseStartDate}" pattern="yyyy/MM/dd"/>">
                                <input type="date" id="draw_event_start_date" name="draw_event_start_date"
                                       required>
                            </div>
                        </div>
                        <div class="infoWrapDiv">
                            <div class="infoTop">
                                이벤트 종료일
                            </div>
                            <div class="infoBottom">
                                <fmt:parseDate value="${item.draw_event_end_date}" pattern="yyyy-MM-dd'T'HH:mm"
                                               var="parseEndDate"/>
                                <input type="hidden"
                                       value="<fmt:formatDate value="${parseEndDate}" pattern="yyyy/MM/dd"/>">
                                <input type="date" id="draw_event_end_date" name="draw_event_end_date"
                                       required>
                            </div>
                        </div>
                        <div class="infoWrapDiv">
                            <div class="infoTop">
                                이벤트 내용
                            </div>
                            <div class="infoBottom">
                                <textarea name="draw_comment" id="draw_comment" required placeholder="이벤트 내용을 입력해 주세요.(최대 100자)" maxlength="100">${item.draw_comment}</textarea>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="drawBtnDiv">
                    <input type="hidden" value="${item.draw_id}" name="draw_id">
                    <input type="button" value="저장" class="drawModifyBtn">
                </div>
            </div>
        </form>
    </div>
</div>

<script src="/resources/js/shop/draw/draw_modify.js"></script>
<jsp:include page="../shop_footer.jsp"/>
</body>
</html>
