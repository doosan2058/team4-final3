<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!-- shop css -->
    <link rel="stylesheet" href="/resources/css/shop/main/shop_user.css">
    <!-- 글로벌 css -->
    <link rel="stylesheet" href="/resources/css/global.css">

    <!-- Jquery cdn -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <!-- 구글 폰트 -->
    <link rel="stylesheet"
          href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200"/>
    <title>쇼핑몰 메인</title>
</head>

<body>
<!-- 헤더 -->
<jsp:include page="../shop_header.jsp"/>


<!--광고-->
<div class="carousel">
    <!--광고 컨테이너-->
    <div class="adContainer">
        <div class="adSubCon">
            <div class="carouselImgDiv">
                <img src="/resources/img/shop/ad/3ed70dbe5c741730b08d02b2664bd0ad.jpg" alt="이미지 준비중 입니다.">
            </div>
        </div>
        <div class="adSubCon">
            <div class="carouselImgDiv">
                <img src="/resources/img/shop/ad/426036bc4d8e49a589788a2730d3ecf5.jpg" alt="이미지 준비중 입니다.">
            </div>
        </div>
        <div class="adSubCon">
            <div class="carouselImgDiv">
                <img src="/resources/img/shop/ad/56e8f0bd7af5e3fa52f7ac76dd346b8f.jpg" alt="이미지 준비중 입니다.">
            </div>
        </div>
        <div class="adSubCon">
            <div class="carouselImgDiv">
                <img src="/resources/img/shop/ad/ba3b9d639544b7b7e35a1c6173a52fbb.jpg" alt="이미지 준비중 입니다.">
            </div>
        </div>
        <div class="adSubCon">
            <div class="carouselImgDiv">
                <img src="/resources/img/shop/ad/c5a20ba490f33ba0301b5cbc46fbf4b9.jpg" alt="이미지 준비중 입니다.">
            </div>
        </div>
    </div>
    <div class="adNaviCon">
        <div class="adNavi" data-index="0"></div>
        <div class="adNavi" data-index="1"></div>
        <div class="adNavi" data-index="2"></div>
        <div class="adNavi" data-index="3"></div>
        <div class="adNavi" data-index="4"></div>
    </div>
</div>
<!--carousel-->


<!--상품, 카테고리 컨테이너-->
<main>
    <!--왼쪽 섹션-->
    <div class="itemContainer">
        <!--상품 탑 10 구역-->
        <div class="itemBestDivCon">
            <!--상품 탑 10 헤드-->
            <div class="itemBestHeader">
                <h1>
                    인기상품
                    <span>10</span>
                </h1>
                <!-- 인기 상품 -->
            </div>
            <!--상품 탑 10 내용-->
            <div class="itemBestBody">
                <span class="material-symbols-outlined backSpan">
                    arrow_back_ios
                </span>
                <span class="material-symbols-outlined forwardSpan">
                    arrow_forward_ios
                </span>
                <!--탑 10 상품 디비전-->
                <div class="itemBestDiv">
                    <c:forEach var="item" items="${productList}" varStatus="status">
                        <div class="productBest">
                            <input type="hidden" value="${item.product_id }">
                            <div class="nameDivBest">
                                <a href="/product/detail?product_id=${item.product_id }"> ${item.product_name } </a>
                            </div>
                            <div class="thumbnailDivBest">
                                <img src="/productImg/${item.product_thumbnail_img_url }" class="thumbnailImg"
                                     alt="이미지 준비중 입니다.">
                            </div>
                            <!-- 스코어 -->
                            <div class="scoreDiv">
                                <span class="numberSpan${status.count }">${status.count }</span>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>

        <!--전체 상품 목록 구역-->
        <div class="itemAllDivCon">
            <!--상품 헤더-->
            <div class="itemAllHeader">
                <h1>상품 목록</h1>
            </div>

            <!-- 현재 페이지 정보 -->
            <input type="hidden" id="currentPageInput" value="${pageShop.currentPage}">
            <!-- 전체 페이지 정보 -->
            <input type="hidden" id="totalPageInput" value="${pageShop.totalPage}">

            <!--상품 내용-->
            <div class="itemAllBody">
                <!--상품 디비전-->
                <div class="itemAllDiv">
                    <!-- 처음 상품 10개  -->
                    <c:forEach var="item" items="${productList}">
                        <div class="product">
                            <input type="hidden" value="${item.product_id }">
                            <div class="nameDiv">
                                <a href="/product/detail?product_id=${item.product_id }"> ${item.product_name } </a>
                            </div>
                            <div class="thumbnailDiv">
                                <img src="/productImg/${item.product_thumbnail_img_url }" class="thumbnailImg"
                                     alt="이미지 준비중 입니다.">
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>

        </div>

        <div class="viewMoreDiv">
            <span class="moreSpan">더보기</span>
        </div>
    </div>



</main>
<!--사이드-->
<div class="side">
    <div class="side_navBar">
        <div class="categorySpanAnchor">
            <span class="material-symbols-outlined">youtube_searched_for</span>
        </div>

        <!-- 등급 구역 -->
        <div class="gradeDiv" style="background-color: ${userInfo.grade_color};">
            <div class="gradeInnerTop">
                <c:if test="${empty userInfo }">
                    <p>로그인후 이용하실수 있습니다.</p>
                </c:if>
                <c:if test="${!empty userInfo }">
                    <img alt="이미지 준비중 입니다." src="/gradeImg/${userInfo.grade_img_url}" id="userProfileImg" data-profile="${userInfo.member_profile_img_url }">
                </c:if>
            </div>
            <div class="gradeInnerBottom">
                <c:if test="${!empty userInfo }">
                        <span class="userGradeSpan"
                              style="color: ${userInfo.grade_font_color}">${userInfo.grade_name }
                        </span> 등급
                    <span class="userGradeSpan" style="color: ${userInfo.grade_font_color}">
                            ${userInfo.grade_discount * 100}%
                        </span> 할인
                    <span class="userGradeSpan" style="color: ${userInfo.grade_font_color}">
                            ${userInfo.grade_accrual_rate * 100}%
                        </span> 적립
                </c:if>
            </div>
        </div>
        <!-- 카테고리 구역 -->
        <div class="categoryDiv">
            <div class="sortMenuDiv">
                <div class="sortMenuSpanDiv">
                    <span class="sortMenuSpan">카테고리</span>
                </div>

                <div class="sortInnerDiv">
                    <span class="categorySpan" data-category-id="0">All Category</span>
                    <c:forEach var="item" items="${categoryList }">
                        <span class="categorySpan"
                              data-category-id="${item.product_category_id }">${item.product_category_name }
                        </span>
                    </c:forEach>
                </div>
            </div>
            <div class="sortMenuDiv">
                <div class="sortMenuSpanDiv">
                    <span class="sortMenuSpan">브랜드</span>
                </div>
                <div class="sortInnerDiv">
                    <span class="brandSpan" data-brand-id="0">All Brand</span>
                    <c:forEach var="item" items="${brandList }">
                        <span class="brandSpan"
                              data-brand-id="${item.product_brand_id }">${item.product_brand_name }
                        </span>
                    </c:forEach>
                </div>
            </div>
        </div>

    </div>
</div>

<!-- 푸터 -->
<jsp:include page="../shop_footer.jsp"/>

<!-- shop js -->
<script src="/resources/js/shop/main/shop_user.js"></script>
</body>

</html>