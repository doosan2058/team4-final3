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
    <link rel="stylesheet" href="/resources/css/shop/product/product_detail_admin.css">
    <!-- 글로벌 css -->
    <link rel="stylesheet" href="/resources/css/global.css">
    <!-- 제이쿼리 cdn -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <!-- 구글 폰트 -->
    <link rel="stylesheet"
          href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200"/>
    <title>상품 상세 관리자 페이지</title>
</head>
<body>
<!-- 헤더 -->
<jsp:include page="../shop_header.jsp"/>

<!--메인-->

<!-- 상품 pk -->
<input type="hidden" readonly="readonly" name="product_id" id="product_id" value="${product.product_id }">
<main>

    <!--상품 이미지, 상품 가격, 상품 설명, 수량, 장바구니 담기, 구매하기-->
    <div class="productTop">
        <div class="topLeft">
            <div class="mainImgDiv">
                <!-- 상품 이미지1 -->
                <c:if test="${product.product_img_url1 ne 'no url' }">
                    <img src="/productImg/${product.product_img_url1 }" alt="" class="productMainImg">
                </c:if>
            </div>
            <div class="subImgDiv">
                <!-- 상품 이미지 1~3 -->
                <c:if test="${product.product_img_url1 ne 'no url' }">
                    <img src="/productImg/${product.product_img_url1 }" alt="" class="productSubImg">
                </c:if>

                <c:if test="${product.product_img_url2 ne 'no url' }">
                    <img src="/productImg/${product.product_img_url2 }" alt="" class="productSubImg">
                </c:if>

                <c:if test="${product.product_img_url3 ne 'no url' }">
                    <img src="/productImg/${product.product_img_url3 }" alt="" class="productSubImg">
                </c:if>
            </div>
        </div>
        <div class="topRight">
            <div class="descriptionDiv">
                <table class="descriptionTable">
                    <tr>
                        <td id="productNameTd">${product.product_name }</td>
                    <tr>
                        <td id="productPriceTd"><fmt:formatNumber value="${product.product_price}" type="number"/> 원
                        </td>
                    </tr>
                    <tr>
                        <td>코멘트 : ${product.product_comment }</td>
                    </tr>
                    <tr>
                        <td>배송일 : ${product.product_delivery_day } 일</td>
                    </tr>
                    <tr>
                        <td>카테고리 : ${product.product_category_id }</td>
                    </tr>
                    <tr>
                        <td>제조사 : ${product.product_brand_id }</td>
                    </tr>
                    <tr>
                        <td>유튜브 링크 : ${product.product_youtube_url }</td>
                    </tr>
                </table>
            </div>
            <div class="quantityDiv">
                <div class="quantityLeft">
                    <c:if test="${product.product_limited eq '한정판'}">
                        <span class="EmphasisSpan">한정판</span>
                    </c:if>
                    <c:if test="${product.product_limited eq '일반'}">
                        <span class="EmphasisSpan">일반</span>
                    </c:if>
                    &nbsp;상품 입니다.
                </div>
                <div class="quantityRight">
                    <c:if test="${product.product_delete eq 'y'}">
                        <span class="EmphasisSpan">비공개</span>
                    </c:if>
                    <c:if test="${product.product_delete eq 'n'}">
                        <span class="EmphasisSpan">공개</span>
                    </c:if>
                    &nbsp;상품 입니다.
                </div>
            </div>
            <div class="btnDiv">
                <!--수정하기-->
                <input type="button" class="formBtns" value="수정" id="modifyBtn">
                <!--비공개하기-->
                <c:if test="${product.product_delete eq 'y'}">
                    <input type="button" class="formBtns noneClass" value="비공개" id="deleteBtn">
                </c:if>
                <c:if test="${product.product_delete ne 'y'}">
                    <input type="button" class="formBtns" value="비공개" id="deleteBtn">
                </c:if>
            </div>
        </div>
    </div>
    </div>
    <input type="hidden" id="productNameInput" name="productName">
    <!--상품 상세 설명, 유튜브 광고 링크-->
    <div class="productMiddle">
        <div class="ajaxResultTest"></div>
        <div class="productImgDiv">
            <!-- 상품 상세 이미지 1~2 -->
            <c:if test="${product.product_description_img_url1 ne 'no url'}">
                <img src="/productImg/${product.product_description_img_url1 }" alt="" class="mainImgs">
            </c:if>

            <c:if test="${product.product_description_img_url2 ne 'no url'}">
                <img src="/productImg/${product.product_description_img_url2 }" alt="" class="mainImgs">
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
                    <h3>평점(<span class="averageGradeSpan"></span>)</h3>
                    <c:forEach var="item" items="${gradeData }">
                        <input type="hidden" value="${item.count}" data-grade="${item.product_review_grade }점" class="gradeProduct">
                    </c:forEach>
                    <!-- Horizontal bar chart -->
                    <c:if test="${empty gradeData }">
                        <p>아직 이 상품의 데이터가 없습니다.</p>
                    </c:if>
                    <c:if test="${!empty gradeData }">
                        <canvas id="bar-chart-horizontal" style="" width="250" height="250"></canvas>
                    </c:if>
                </div>
                <div class="reviewGraph">
                    <h3>배송</h3>
                    <c:forEach var="item" items="${data }">
                        <input type="hidden" value="${item.count}" data-speed="${item.product_review_speed }" class="speedProduct">
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
        <!--리뷰 내용들-->
        <input type="hidden" name="currentPage" id="currentPage" value="1">
        <div class="reviewBottom">
            <!-- 리뷰 있다면 -->
            <c:if test="${!empty reviewList }">
                <c:forEach var="item" items="${reviewList }">
                    <div class="reviews">
                        <!-- 아이디, 날짜 -->
                        <div class="reviewsDiv reviewsHeader">
                            <input type="hidden" value="${item.product_review_id }">
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
                                    <p>리뷰 이미지를 업로드 하지 않았습니다.</p>
                                </c:if>
                                <c:if test="${item.product_review_img_url ne 'no url'}">
                                    <img alt="이미지 준비중" src="/reviewFiles/${item.product_review_img_url }" class="reviewImage">
                                </c:if>
                            </div>
                            <div class="reviewsPhotoInner reviewsPhotoInnerVideo">
                                <!-- 조건 처리 작업 -->
                                <c:if test="${item.product_review_video_url eq 'no url' }">
                                    <p>리뷰 동영상을 업로드 하지 않았습니다.</p>
                                </c:if>
                                <c:if test="${item.product_review_video_url ne 'no url' }">
                                    <video controls width="100%" height="100%">
                                        <source src="/reviewFiles/${item.product_review_video_url }" type="video/mp4">
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
        <c:if test="${pageReview.end eq true }">
            <div class="viewMoreReviewsDiv noneClass">리뷰 더보기</div>
        </c:if>
        <c:if test="${pageReview.end eq false }">
            <div class="viewMoreReviewsDiv">리뷰 더보기</div>
        </c:if>
    </div>
    <!--네비게이터-->
    <div class="navigatorContainer">
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
                <input type="hidden" id="product_youtube_url_hidden" value="${product.product_youtube_url}">
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
<script src="/resources/js/shop/product/product_detail_admin.js"></script>
</body>

</html>