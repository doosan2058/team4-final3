<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- 글로벌 -->
    <link rel="stylesheet" href="/resources/css/global.css">
    <!--커뮤니티 글로벌 css-->
    <link rel="stylesheet" href="<c:url value="/resources/css/community/community_global.css?ver=1"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/community/main.css?ver=1"/>">
    <!--xeicon-->
    <link rel="stylesheet" href="//cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css">
    <!--제이쿼리 -->
    <script src="//code.jquery.com/jquery-3.2.1.min.js"></script>

    <title>Document</title>
</head>
<body>
<!--헤더-->
<%@ include file="header.jsp" %>

<!--메인-->
<main>
    <!-- 화제의 사진 -->
    <div class="recommendation">
        <div class="recommendation_header">
            <span class="mainTitleSpan">화제의 사진</span>
            <a class="mainViewMoreAnchor">+ 더보기</a>
        </div>

        <div class="recommendation_main">
            <div class="recommendation_main_imgBox">
                <div class="recommendation_img">
                    <img src="https://team4projectbucket.s3.ap-northeast-2.amazonaws.com/community/%EC%BA%A0%ED%95%91%EB%A9%94%EC%9D%B81.jpg"
                         alt="">
                </div>
                <div class="recommendation_img">
                    <img src="https://team4projectbucket.s3.ap-northeast-2.amazonaws.com/community/%EC%BA%A0%ED%95%91%EB%A9%94%EC%9D%B82.jpg"
                         alt="">
                </div>
                <div class="recommendation_img">
                    <img src="https://team4projectbucket.s3.ap-northeast-2.amazonaws.com/community/%EC%BA%A0%ED%95%91%EB%A9%94%EC%9D%B83.jpg"
                         alt="">
                </div>
                <div class="recommendation_img">
                    <img src="https://team4projectbucket.s3.ap-northeast-2.amazonaws.com/community/%EC%BA%A0%ED%95%91%EB%A9%94%EC%9D%B84.jpg"
                         alt="">
                </div>

            </div>
        </div>
    </div>

    <section>
        <div class="section1">
            <!-- 자유게시판 -->
            <div class="section1_noticeBoard">
                <div class="section1_noticeBoard_header">
                    <span class="mainTitleSpan">자유게시판 Top 10</span>
                    <a href="/community/freeNotice/main" class="mainViewMoreAnchor">+ 더보기</a>
                </div>

                <div class="section1_noticeBoard_main">
                    <div class="section1_noticeBoard_title">
                        <li>글번호</li>
                        <li>말머리</li>
                        <li>제목</li>
                        <li>글쓴이</li>
                        <li>날짜</li>
                        <li>조회수</li>
                        <li>추천수</li>
                    </div>

                    <div class="section1_noticeBoard_content">
                        <%@ include file="freeNotice/list.jsp" %>
                    </div>
                </div>
            </div>

            <!-- 캠핑모임 -->
            <div class="section1_notice">
                <div class="section1_notice_header">
                    <span class="mainTitleSpan">모집중 캠핑모임</span>
                    <a href="/community/joinNotice/main" class="mainViewMoreAnchor">+ 더보기</a>
                </div>

                <div class="section1_notice_main">
                    <div class="section1_notice_title">
                        <li>글번호</li>
                        <li>제목</li>
                        <li>글쓴이</li>
                        <li>지역</li>
                        <li>인원</li>
                        <li>출발일</li>
                        <li>기간</li>
                        <li>조회수</li>
                        <li>마감여부</li>
                    </div>

                    <div class="section1_notice_content">
                        <%@ include file="joinNotice/list.jsp" %>
                    </div>
                </div>
            </div>
        </div>

        <div class="section2">

            <!-- 이벤트,프로모션 -->
            <div class="section2_event">
                <div class="section2_event_header">
                    <span class="mainTitleSpan">이벤트</span>
                    <a href="/draw/draw_customer" class="mainViewMoreAnchor">+ 더보기</a>
                </div>

                <div class="section2_event_main">
                    <div class="section2_event_content">

                            <div class="section2_event_content_img">
                                <img src="${draw.product_img_url1}">
                            </div>
                            <div class="section2_event_content_info">
                                <p><span>한정</span> 판매</p>
                                <p>${draw.draw_title}</p>
                                <fmt:parseDate value="${draw.draw_event_start_date}" pattern="yyyy-MM-dd'T'HH:mm"
                                               var="date" type="both"/>
                                <p>시작일 <span><fmt:formatDate value="${date}" pattern="yy-MM-dd"/></span></p>
                                <p>${draw.draw_comment}</p>
                                <a href="/draw/draw_customer">이벤트 바로가기</a>
                            </div>

                    </div>
                </div>
            </div>

            <!-- 날씨 정보 -->
            <div class="section2_map">
                <div class="section2_map_header">
                    <span class="mainTitleSpan">날씨정보</span>
                    <select name="camping_region" class="section2_map_list">
                        <option class="location_list" value="서울">서울</option>
                        <option class="location_list" value="경기도">경기도</option>
                        <option class="location_list" value="강원도">강원도</option>
                        <option class="location_list" value="전라북도">전라북도</option>
                        <option class="location_list" value="전라남도">전라남도</option>
                        <option class="location_list" value="경상북도">경상북도</option>
                        <option class="location_list" value="경상남도">경상남도</option>
                        <option class="location_list" value="충청북도">충청북도</option>
                        <option class="location_list" value="충청남도">충청남도</option>
                        <option class="location_list" value="제주도">제주도</option>
                    </select>
                </div>
                <div class="section2_map_main">
                    <div class="section2_map_content">
                        <div class="section2_map_content_main">
                            <div class="section2_map_content_main_icon">
                                <video autoplay loop muted class="weather_img">
                                    <source src="https://cdn-icons-mp4.flaticon.com/512/6455/6455017.mp4">
                                    <!-- 날씨 아이콘 -->
                                </video>
                            </div>
                            <div class="section2_map_content_main_info">
                                <p><span class="temp"></span>&deg;C</p>
                                <p class="weather"></p>
                                <p class="location"></p>
                            </div>
                        </div>
                        <div class="section2_map_content_footer">
                            <div class="section2_map_content_footer_wind">
                                <p>Wind speed</p>
                                <p><span class="wind_speed"></span> m/s</p>
                            </div>
                            <div class="section2_map_content_footer_hum">
                                <p>Humidity</p>
                                <p><span class="humidity"></span> %</p>
                            </div>
                            <div class="section2_map_content_footer_cloud">
                                <p>Clouds</p>
                                <p><span class="clouds"></span> %</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
</main>

<!--푸터-->
<%@ include file="footer.jsp" %>

</body>

<script src="<c:url value="/resources/js/community/main.js?ver=1"/>"></script>
</html>