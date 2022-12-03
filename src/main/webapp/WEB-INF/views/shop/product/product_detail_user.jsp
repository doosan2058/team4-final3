<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!--상품 상제 css-->
    <link rel="stylesheet" href="/resources/css/shop/product/product_detail_user.css">
    <!-- 글로벌 css -->
    <link rel="stylesheet" href="/resources/css/global.css">
    <!-- 제이쿼리 cdn -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <!-- 구글 폰트 -->
    <link rel="stylesheet"
          href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200"/>
    <!-- 폰트 어썸 -->
    <script src="https://kit.fontawesome.com/10a37546a9.js" crossorigin="anonymous"></script>
    <title>상품 상세 페이지</title>
</head>
<body>
<!-- 헤더 -->
<jsp:include page="../shop_header.jsp"/>
<input type="hidden" name="member_id" id="loginInput" value="${sessionScope.login_id}">
<!--메인-->

<!-- 상품 pk -->
<main>
    <!--상품 이미지, 상품 가격, 상품 설명, 수량, 장바구니 담기, 구매하기-->
    <form method="post" action="/order">
        <div class="productTop">
            <input type="hidden" name="product_id" id="product_id" value="${product.product_id }">
            <div class="topLeft">
                <div class="mainImgDiv">
                    <!-- 상품 이미지1 -->
                    <c:if test="${product.product_img_url1 ne 'no url' }">
                        <img src="${product.product_img_url1 }" alt="" class="productMainImg">
                    </c:if>
                </div>
                <div class="subImgDiv">
                    <!-- 상품 이미지 1~3 -->
                    <c:if test="${product.product_img_url1 ne 'no url' }">
                        <div class="subImgWrapDiv">
                            <img src="${product.product_img_url1 }" alt="" class="productSubImg">
                        </div>
                    </c:if>

                    <c:if test="${product.product_img_url2 ne 'no url' }">
                        <div class="subImgWrapDiv">
                            <img src="${product.product_img_url2 }" alt="" class="productSubImg">
                        </div>
                    </c:if>

                    <c:if test="${product.product_img_url3 ne 'no url' }">
                        <div class="subImgWrapDiv">
                            <img src="${product.product_img_url3 }" alt="" class="productSubImg">
                        </div>
                    </c:if>
                </div>
            </div>
            <div class="topRight">

                <div class="topRightTop">
                    <div class="topRightTopInfoDiv">
                        <h3 class="productNameH3">${product.product_name }</h3>
                        <div class="productInfoSpanDiv">
                            <span id="productNameTd" class="productInfoSpan">${product.product_name }</span>
                            <input type="hidden" id="priceHiddenInput" value="${product.product_price}">
                            <span id="productPriceTd" class="productInfoSpan">
                                <fmt:formatNumber value="${product.product_price}" type="number"/>원
                            </span>
                            <span class="productInfoSpan">배송에 ${product.product_delivery_day }일 걸려요</span>
                            <span class="productInfoSpan">${product.product_category_name }</span>
                            <span class="productInfoSpan">${product.product_brand_name }</span>
                            <span class="productInfoSpan">${product.product_stock }개 남았어요</span>
                            <span class="productInfoSpan">${product.product_comment }</span>
                            <span class="productInfoSpan">${product.product_limited } 상품이에요</span>

                        </div>

                    </div>


                    <div class="shareDiv">
                        <div class="shareIconsWrapDiv">
                            <i class="fa-brands fa-youtube shareIcons"></i>
                        </div>
                        <div class="shareIconsWrapDiv">
                            <i class="fa-brands fa-facebook shareIcons"></i>
                        </div>
                        <div class="shareIconsWrapDiv">
                            <i class="fa-brands fa-square-twitter shareIcons"></i>
                        </div>
                        <div class="shareIconsWrapDiv">
                            <img class="shareIconsImg" src="https://team4projectbucket.s3.ap-northeast-2.amazonaws.com/utilImg/KakaoTalk_logo.svg">
                        </div>
                        <div class="shareIconsWrapDiv">
                            <i class="fa-solid fa-copy shareIcons"></i>
                        </div>
                    </div>
                </div>

                <div class="topRightBottom">
                    <!-- 상품 수량 -->
                    <div class="quantityDiv">
                        <input type="text" id="quantityText" value="1" name="order_quantity">
                        <div class="quantitySubDiv">
                            <input type="button" value="추가" class="quantityBtn" id="quantityUpBtn">
                            <input type="button" value="제거" class="quantityBtn" id="quantityDownBtn">
                        </div>
                    </div>
                    <!--기타 구역-->
                    <div class="orderDiv">
                        <!--상품 가격 -->
                        <span id="productPriceSpan"></span>

                        <div class="orderDivBtnDiv">
                            <c:choose>
                                <c:when test="${product.product_delete eq 'y' or product.product_limited eq '한정판'}">
                                    <p>구매할 수 없는 상품입니다.</p>
                                    <!--장바구니 담기는 ajax-->
                                    <i class="xi-cart-o" id="basketBtn" style="display: none;"></i>
                                </c:when>
                                <c:when test="${product.product_delete eq 'n' and product.product_limited eq '일반'}">
                                    <!--장바구니 담기는 ajax-->
                                    <i class="xi-cart-o" id="basketBtn"></i>
                                    <!--구매하기는 submit-->
                                    <input type="submit" value="구매하기" class="formBtns" id="submitBtn">
                                </c:when>
                            </c:choose>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </form>

    <!--상품 상세 설명, 유튜브 광고 링크-->
    <div class="productMiddle">
        <div class="ajaxResultTest"></div>
        <div class="productImgDiv">
            <!-- 상품 상세 이미지 1~2 -->
            <c:if test="${product.product_description_img_url1 ne 'no url'}">
                <img src="${product.product_description_img_url1 }" alt="" class="mainImgs">
            </c:if>
            <c:if test="${product.product_description_img_url2 ne 'no url'}">
                <img src="${product.product_description_img_url2 }" alt="" class="mainImgs">
            </c:if>
            <div class="viewMoreDiv">펼처 보기</div>
        </div>
    </div>
    <!--상품 리뷰-->
    <div class="productBottom">
        <!--리뷰 통계-->
        <div class="reviewTop">
            <div class="reviewTitle">
                상품리뷰(
                <span class="colorSpans">${pageReview.totalNum }</span>
                )
            </div>
            <div class="reviewGraphDiv">
                <div class="reviewGrade">
                    <h3>
                        평점(
                        <span class="averageGradeSpan"></span>
                        )
                    </h3>
                    <!--상품 리뷰 평점 (count : 점수 개수, product_review_grade : 점수)-->
                    <c:forEach var="item" items="${gradeData }">
                        <input type="hidden" value="${item.count}" data-grade="${item.product_review_grade }점"
                               class="gradeProduct">
                    </c:forEach>
                    <!-- Horizontal bar chart -->
                    <c:if test="${empty gradeData }">
                        <p>아직 이 상품의 데이터가 없습니다.</p>
                    </c:if>
                    <c:if test="${!empty gradeData }">
                        <canvas id="bar-chart-horizontal" width="250" height="250"></canvas>
                    </c:if>
                </div>
                <div class="reviewGraph">
                    <h3>배송</h3>
                    <!--상품 리뷰 배송 속도 (count : 속도 개수, product_review_speed : 속도)-->
                    <c:forEach var="item" items="${data }">
                        <input type="hidden" value="${item.count}" data-speed="${item.product_review_speed }"
                               class="speedProduct">
                    </c:forEach>
                    <!-- 파이 차트 -->
                    <c:if test="${empty data }">
                        <p>아직 이 상품의 데이터가 없습니다.</p>
                    </c:if>
                    <c:if test="${!empty data }">
                        <canvas id="pie-chart"></canvas>
                    </c:if>
                </div>
            </div>
        </div>
        <!--리뷰-->
        <!--리뷰 현재 페이지 번호-->
        <input type="hidden" name="currentPage" id="currentPage" value="1">
        <div class="reviewBottom">
            <!-- 리뷰 있다면 -->
            <c:if test="${!empty reviewList }">
                <c:forEach var="item" items="${reviewList }">
                    <div class="reviews">
                        <!-- 아이디, 날짜 -->
                        <div class="reviewsDiv reviewsHeader">
                            <input type="hidden" value="${item.product_id }">
                            <span>
                                <img src="${item.member_profile_img_url}" class="reviewProfileImg">
                                <span class="reviewWriterSpan">${item.member_id }</span>
                                &nbsp;님
                            </span>
                            <span>
									<fmt:formatDate value="${item.product_review_regdate }"/>
                            </span>
                        </div>
                        <!-- 별점 -->
                        <div class="reviewsDiv reviewsGrade">
                            <input type="hidden" value="${item.product_review_speed }">
                            <div class="reviewsGradeInner">
                                <c:forEach begin="1" end="${item.product_review_grade }">
                                    <span class="material-symbols-outlined star"> grade </span>
                                </c:forEach>
                                <span>${item.product_review_grade }점</span>
                            </div>
                            <div class="reviewsGradeInner reviewsGradeInnerThumb">
                                <!--리뷰 번호(PK)-->
                                <input type="hidden" value="${item.product_review_id }">
                                <span class="material-symbols-outlined thumb"> thumb_up </span>
                                <span class="helpfulSpan">${item.product_review_helpful }</span>
                            </div>
                        </div>
                        <!-- 내용 -->
                        <div class="reviewsDiv reviewsContent">
                            <p>${item.product_review_text }</p>
                        </div>
                        <!-- 사진, 영상 -->
                        <div class="reviewsDiv reviewsPhoto">
                            <div class="reviewsPhotoInner reviewsPhotoInnerPhoto">
                                <!-- 조건 처리 작업 -->
                                <c:if test="${item.product_review_img_url eq 'no url'}">
                                    <p></p>
                                </c:if>
                                <c:if test="${item.product_review_img_url ne 'no url'}">
                                    <img alt="이미지 준비중" src="${item.product_review_img_url }"
                                         class="reviewImage">
                                </c:if>
                            </div>
                            <div class="reviewsPhotoInner reviewsPhotoInnerVideo">
                                <!-- 조건 처리 작업 -->
                                <c:if test="${item.product_review_video_url eq 'no url' }">
                                    <p></p>
                                </c:if>
                                <c:if test="${item.product_review_video_url ne 'no url' }">
                                    <video controls width="100%" height="100%">
                                        <source src="${item.product_review_video_url }" type="video/mp4">
                                    </video>
                                </c:if>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </c:if>
            <!-- 리뷰 없다면 -->
            <c:if test="${empty reviewList }">
                <p>작성된 리뷰가 없습니다.</p>
            </c:if>
        </div>
        <!--리뷰 더보기 버튼 초기화-->
        <c:if test="${pageReview.end eq true }">
            <div class="viewMoreReviewsDiv noneClass">리뷰 더보기</div>
        </c:if>
        <c:if test="${pageReview.end eq false }">
            <div class="viewMoreReviewsDiv">리뷰 더보기</div>
        </c:if>
    </div>
    <!--네비게이터-->
    <div class="navigatorContainer">
        <div class="naviDiv">
            <i class="fa-regular fa-compass"></i>
        </div>
        <div class="toTopDiv">상단</div>
        <div class="toReviewCon">리뷰</div>
        <div class="toBottomDiv">하단</div>
        <div class="youtubeDiv">
            <span class="material-symbols-outlined playIconSpan">play_circle</span>
        </div>
    </div>
    <!-- 유튜브 광고 컨테이너 -->
    <div class="youtubeContainer">
        <span class="material-symbols-outlined closeYoutubeContainerIcon">
            close
        </span>
        <div class="youtubeInnerDiv">
            <c:if test="${product.product_youtube_url eq 'no url'}">
                <input type="hidden" class="product_youtube_url_hidden" value="${product.product_youtube_url}">
                <iframe class="adIframe noneClass" allowfullscreen></iframe>
                <h1>유튜브 광고가 없는 제품 입니다.</h1>
            </c:if>
            <c:if test="${product.product_youtube_url ne 'no url'}">
                <input type="hidden" class="product_youtube_url_hidden" value="${product.product_youtube_url}">
                <iframe class="adIframe" allowfullscreen></iframe>
            </c:if>
        </div>
    </div>
</main>
<div class="fullImageContainer">
    <span class="material-symbols-outlined closeFullImageContainerIcon">
        close
    </span>
    <img src="">
</div>
<!-- 푸터 -->
<jsp:include page="../shop_footer.jsp"/>

<!-- chart.js -->
<script src="https://cdn.jsdelivr.net/npm/chart.js@3.9.1/dist/chart.min.js"></script>
<!-- js -->
<script src="/resources/js/shop/product/product_detail_user.js"></script>
</body>

</html>