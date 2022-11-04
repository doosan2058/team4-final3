<%--
  Created by IntelliJ IDEA.
  User: choi
  Date: 2022-11-04
  Time: 오후 4:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
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
<div class="drawContainer">
    <div class="drawHeaderDiv">
        <span>이벤트 수정</span>
    </div>
    <div class="drawBodyDiv">
        <form method="post" action="modify" id="drawModifyForm">
            <div class="oneDrawDiv">
                <div class="oneDrawTop">
                    <h3>
                        [이벤트 제목]
                    </h3>
                    <input type="text" value="" name="draw_title" required>
                </div>
                <div class="drawCenterDiv">
                    <div class="drawImgDiv">
                        <img src="" alt="이미지 준비중 입니다." class="productMainImg">
                    </div>
                    <div class="drawBottomDiv">
                        <div class="infoWrapDiv">
                            <div class="infoTop">
                                상품 이름
                            </div>
                            <div class="infoBottom">

                            </div>
                        </div>
                        <div class="infoWrapDiv">
                            <div class="infoTop">
                                가격
                            </div>
                            <div class="infoBottom">

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
                                이벤트 등록일
                            </div>
                            <div class="infoBottom">

                            </div>
                        </div>
                        <div class="infoWrapDiv">
                            <div class="infoTop">
                                이벤트 시작일
                            </div>
                            <div class="infoBottom">
                                <input type="date" id="draw_event_start_date" name="draw_event_start_date"
                                       required>
                            </div>
                        </div>
                        <div class="infoWrapDiv">
                            <div class="infoTop">
                                이벤트 종료일
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
                                <textarea name="draw_comment" required></textarea>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="drawBtnDiv">
                    <input type="button" value="등록" class="drawAddBtn">
                </div>
            </div>
        </form>
    </div>
</div>
<!-- 푸터 -->
<jsp:include page="../shop_footer.jsp"/>
<script src="/resources/js/shop/draw/draw_add.js"></script>
</body>
</html>
