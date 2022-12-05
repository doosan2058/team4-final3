<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- 글로벌 css -->
    <link rel="stylesheet" href="/resources/css/global.css">
    <!--커뮤니티 글로벌 css-->
    <link rel="stylesheet" href="<c:url value="/resources/css/community/community_global.css?ver=2"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/community/joinNotice/input.css?ver=1"/>">


    <!-- 에디터 스크립트 -->
    <script type="text/javascript" src="<c:url value="/resources/editor/ckeditor/ckeditor.js"/>"></script>

    <!-- Jquery cdn -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <!--xeicon-->
    <link rel="stylesheet" href="//cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css">
    <script type="text/javascript"
            src="//dapi.kakao.com/v2/maps/sdk.js?appkey=b3fddae03c72f192c7b74da13d2a55b0&libraries=services"></script>

    <title>Document</title>

</head>

<body>
<!--헤더-->
<%@ include file="../header.jsp" %>

<!--메인-->
<main>
    <form class="section" method="post">
        <div class="section_header">
            <h1>제목</h1>

            <textarea name="camping_title" id="" class="section_header_headline"
                      placeholder="제목을 입력하세요.">${data.camping_title}</textarea>

            <div class="section_header_select">
                <p>출발일</p>

                <input type="date" name="camping_departure" class="section_header_select_date"
                       value="<fmt:formatDate value="${data.camping_departure}" pattern="yyyy-MM-dd" />">

                <select name="camping_period" class="section_header_select_fewDays">
                    <option value="">기간</option>
                    <option value="당일" <c:out value="${data.camping_period == '당일' ? 'selected' : ''}"/>>당일</option>
                    <option value="1박 2일" <c:out value="${data.camping_period == '1박 2일' ? 'selected' : ''}"/>>1박 2일
                    </option>
                    <option value="2박 3일" <c:out value="${data.camping_period == '2박 3일' ? 'selected' : ''}"/>>2박 3일
                    </option>
                    <option value="3박 4일" <c:out value="${data.camping_period == '3박 4일' ? 'selected' : ''}"/>>3박 4일
                    </option>
                    <option value="4박 5일" <c:out value="${data.camping_period == '4박 5일' ? 'selected' : ''}"/>>4박 5일
                    </option>
                </select> <select name="camping_region" id="" class="section_header_select_area">
                <option value="">지역 선택</option>
                <option value="서울" <c:out value="${data.camping_region == '서울' ? 'selected' : ''}"/>>서울</option>
                <option value="경기도" <c:out value="${data.camping_region == '경기도' ? 'selected' : ''}"/>>경기도</option>
                <option value="강원도" <c:out value="${data.camping_region == '강원도' ? 'selected' : ''}"/>>강원도</option>
                <option value="전라북도" <c:out value="${data.camping_region == '전라북도' ? 'selected' : ''}"/>>전라북도</option>
                <option value="전라남도" <c:out value="${data.camping_region == '전라남도' ? 'selected' : ''}"/>>전라남도</option>
                <option value="경상북도" <c:out value="${data.camping_region == '경상북도' ? 'selected' : ''}"/>>경상북도</option>
                <option value="경상남도" <c:out value="${data.camping_region == '경상남도' ? 'selected' : ''}"/>>경상남도</option>
                <option value="충청북도" <c:out value="${data.camping_region == '충청북도' ? 'selected' : ''}"/>>충청북도</option>
                <option value="충청남도" <c:out value="${data.camping_region == '충청남도' ? 'selected' : ''}"/>>충청남도</option>
                <option value="제주도" <c:out value="${data.camping_region == '제주도' ? 'selected' : ''}"/>>제주도</option>
            </select> <select name="camping_recruitment" id="" class="section_header_select_count">
                <option value="">캠핑 인원</option>
                <option value="2" <c:out value="${data.camping_recruitment == '2' ? 'selected' : ''}"/>>2</option>
                <option value="3" <c:out value="${data.camping_recruitment == '3' ? 'selected' : ''}"/>>3</option>
                <option value="4" <c:out value="${data.camping_recruitment == '4' ? 'selected' : ''}"/>>4</option>
                <option value="5" <c:out value="${data.camping_recruitment == '5' ? 'selected' : ''}"/>>5</option>
                <option value="6" <c:out value="${data.camping_recruitment == '6' ? 'selected' : ''}"/>>6</option>
                <option value="7" <c:out value="${data.camping_recruitment == '7' ? 'selected' : ''}"/>>7</option>
                <option value="8" <c:out value="${data.camping_recruitment == '8' ? 'selected' : ''}"/>>8</option>
            </select>

                <script>/* 현재 참여인원 select박스 option 갯수 설정에 사용할 변수들 */
                let camping_recruitment = '<c:out value="${data.camping_recruitment}"/>'; // 총 모집 인원
                let applicant_count = '<c:out value="${data.camping_applicant}"/>'; // 현재 참여 인원
                </script>
                <select name="camping_applicant" class="section_header_select_applicant">
                    <option value="">현재 참여 인원</option>
                </select> <select name="camping_close" id="" class="section_header_select_close">
                <option value="진행중" <c:out value="${data.camping_close == '진행중' ? 'selected' : ''}"/>>진행중</option>
                <option value="마감" <c:out value="${data.camping_close == '마감' ? 'selected' : ''}"/>>마감</option>
            </select>

            </div>

            <div class="section_header_search">
                <p>캠핑장 검색</p>
                <input type="text" name="camping_campsite" value="${data.camping_campsite}" readonly>
                <input type="button" class="campsite_search" value="주소 검색">
            </div>

            <div class="section_header_back">
                <div class="section_header_modal">
                    <!-- 주소 검색 시 사용할 모달 -->
                    <h1>캠핑장 검색</h1>
                    <select name="campsite_category" class="section_header_modal_category">
                        <option value="">캠핑장 구분</option>
                        <option value="일반야영장">일반야영장</option>
                        <option value="자동차야영장">자동차야영장</option>
                        <option value="카라반">카라반</option>
                        <option value="글램핑">글램핑</option>
                    </select>
                    <input class="search_region" type="text" placeholder="지역 입력">
                    <input class="search_keyword" type="text" placeholder="검색어 입력">
                    <input type="button" class="modal_search" value="검색">
                    <div class="section_header_modal_result">
                        <div class="section_header_modal_result_header">
                            <li>캠핑장 이름</li>
                            <li>캠핑장 구분</li>
                            <li>캠핑장 주소</li>
                        </div>
                        <div class="section_header_modal_result_box"></div>
                    </div>
                    <input type="button" class="modal_submit" value="확인">
                    <input type="button" class="modal_close" value="닫기">
                </div>
            </div>
        </div>

        <div class="section_main">
            <h1>내용</h1>
            <textarea name="camping_text" id="content" class="section_main_write" placeholder="내용을 입력하세요.">
                ${data.camping_text}
            </textarea>
            <!-- 에디터 스크립트 -->
            <script type="text/javascript">
                CKEDITOR.replace('content', {
                    filebrowserUploadUrl: "/community/imgUpload",
                    height: 1015
                });
            </script>
        </div>

        <div class="section_footer">
            <input type="hidden" value="${sessionScope.login_id}" name="member_id">
            <input type="button" value="글 등록" class="section_footer_registrationBtn">
        </div>
    </form>
</main>

<!--푸터-->
<%@ include file="../footer.jsp" %>

</body>
<script src="<c:url value="/resources/js/community/joinNotice/correction.js?ver=1"/>"></script>
</html>