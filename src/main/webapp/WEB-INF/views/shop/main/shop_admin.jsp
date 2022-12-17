<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!-- shop css -->
    <link rel="stylesheet" href="/resources/css/shop/main/shop_admin.css">
    <!-- 글로벌 css -->
    <link rel="stylesheet" href="/resources/css/global.css">
    <!-- Jquery cdn -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <!--xeicon-->
    <link rel="stylesheet" href="//cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css">
    <!-- 폰트어썸 -->
    <script src="https://kit.fontawesome.com/10a37546a9.js" crossorigin="anonymous"></script>
    <title>쇼핑몰 관리자 메인</title>
</head>

<body>
<!-- 헤더 -->
<jsp:include page="../shop_header.jsp"/>
<!--광고-->
<div class="carousel">
    <div class="adContainer">
        <div class="adSubCon">
            <div class="carouselImgDiv">
                <img src="https://team4projectbucket.s3.ap-northeast-2.amazonaws.com/utilImg/3ed70dbe5c741730b08d02b2664bd0ad.jpg" alt="이미지 준비중 입니다.">
            </div>
        </div>
        <div class="adSubCon">
            <div class="carouselImgDiv">
                <img src="https://team4projectbucket.s3.ap-northeast-2.amazonaws.com/utilImg/426036bc4d8e49a589788a2730d3ecf5.jpg" alt="이미지 준비중 입니다.">
            </div>
        </div>
        <div class="adSubCon">
            <div class="carouselImgDiv">
                <img src="https://team4projectbucket.s3.ap-northeast-2.amazonaws.com/utilImg/56e8f0bd7af5e3fa52f7ac76dd346b8f.jpg" alt="이미지 준비중 입니다.">
            </div>
        </div>
        <div class="adSubCon">
            <div class="carouselImgDiv">
                <img src="https://team4projectbucket.s3.ap-northeast-2.amazonaws.com/utilImg/ba3b9d639544b7b7e35a1c6173a52fbb.jpg" alt="이미지 준비중 입니다.">
            </div>
        </div>
        <div class="adSubCon">
            <div class="carouselImgDiv">
                <img src="https://team4projectbucket.s3.ap-northeast-2.amazonaws.com/utilImg/c5a20ba490f33ba0301b5cbc46fbf4b9.jpg" alt="이미지 준비중 입니다.">
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

<div class="footerNoticeContainer">
    <div class="footerNoticeDiv">
        <div class="footerNoticeTitleDiv">
            <span class="footerNoticeTitleSpan">공지</span>
        </div>
        <div class="footerNoticeWrapDiv">
            <div class="footerNoticeSpanDiv">
                <c:forEach var="item" items="${noticeList}">
                    <a href="/community/freeNotice/detail?board_id=${item.board_id}">
                        <span class="footerNoticeSpan">${item.board_title}</span>
                    </a>
                </c:forEach>

            </div>
        </div>
    </div>
</div>

<!--왼쪽 섹션-->
<div class="itemContainer">
    <!--판매순 10-->
    <div class="itemBestDivCon">
        <div class="itemBestHeader">
            <h1>인기 상품<span> 10</span></h1>
        </div>
        <div class="itemBestBody">
            <i class="xi-angle-left backSpan"></i>
            <i class="xi-angle-right forwardSpan"></i>
            <div class="itemBestDiv">
                <c:forEach var="item" items="${topProduct}" varStatus="status">
                    <div class="productBest">
                        <input type="hidden" value="${item.product_id }">
                        <div class="nameDivBest">
                            <a href="/product/detail?product_id=${item.product_id }">${item.product_name }(${item.product_sales_rate}개
                                판매)</a>
                        </div>
                        <div class="thumbnailDivBest">
                            <img src="${item.product_thumbnail_img_url }" class="thumbnailImg"
                                 alt="이미지 준비중 입니다.">
                        </div>
                        <div class="scoreDiv">
                            <span class="numberSpan${status.count }">${status.count }</span>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
    <!-- 광고 5 -->
    <div class="youtubeListContainer">
        <div class="youtubeListHeader">
            <h1>신규 광고</h1>
        </div>
        <div class="youtubeListBody">
            <c:forEach var="item" items="${youtubeList}">
                <div class="youtubeListLineDiv">
                    <input type="hidden" value="${item.product_id}">
                    <img src="${item.product_thumbnail_img_url}" class="youtubeListLineDivThumbImg">
                    <div class="youtubeListLineInnerDiv">
                        <a href="/product/detail?product_id=${item.product_id}"><span class="youtubeListLineDivTitleSpan">${item.product_name}</span></a>
                        <i class="fa-brands fa-youtube youtubeListIcon" data-url="${item.product_youtube_url}"></i>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>

    <!--상품 목록-->
    <div class="itemAllDivCon">
        <div class="itemAllHeader">
            <h1>상품 목록</h1>
        </div>
        <div class="itemAllOptionDiv">
            <div class="itemAllOptionSelectDiv">
                <select class="brandSelect">
                    <option value="0">브랜드</option>
                    <c:forEach var="item" items="${brandList}">
                        <option value="${item.product_brand_id}">${item.product_brand_name}</option>
                    </c:forEach>
                </select>
                <select class="categorySelect">
                    <option value="0">카테고리</option>
                    <c:forEach var="item" items="${categoryList}">
                        <option value="${item.product_category_id}">${item.product_category_name}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="itemAllOptionSpanDiv">
                <div class="optionLabelWrapDiv">
                    <label class="optionLabel" for="searchOptionRadioBoxNew">입고</label>
                    <input type="radio" name="searchOptionRadioBox" class="searchOptionRadioBox"
                           id="searchOptionRadioBoxNew" value="regist" checked>
                    <label class="optionLabel" for="searchOptionRadioBoxSale">판매</label>
                    <input type="radio" name="searchOptionRadioBox" class="searchOptionRadioBox"
                           id="searchOptionRadioBoxSale" value="sale">
                    <label class="optionLabel" for="searchOptionRadioBoxPrice">가격</label>
                    <input type="radio" name="searchOptionRadioBox" class="searchOptionRadioBox"
                           id="searchOptionRadioBoxPrice" value="price">
                    <div id="optionLabelUnderLineDiv"></div>
                </div>
                <span id="optionSearchSpan">보기</span>
            </div>
        </div>
        <!-- 현재 페이지 -->
        <input type="hidden" id="currentPageInput" value="${pageShop.currentPage}">
        <!-- 전체 페이지 -->
        <input type="hidden" id="totalPageInput" value="${pageShop.totalPage}">


        <div class="itemAllBody">
            <div class="itemAllDiv">
                <c:forEach var="item" items="${productList}">
                    <div class="product">
                        <input type="hidden" value="${item.product_id }">
                        <div class="nameDiv">
                            <a href="/product/detail/admin?product_id=${item.product_id }">${item.product_name }</a>
                        </div>
                        <div class="productInfoDiv">
                            <span class="limitedSpan">${item.product_limited } 상품</span>
                            <span class="deletedSpan">
									<c:if test="${item.product_delete eq 'y'}">
                                        비공개
                                    </c:if>
									<c:if test="${item.product_delete eq 'n'}">
                                        공개
                                    </c:if>
									상품
								</span>
                        </div>
                        <div class="thumbnailDiv">
                            <img src="${item.product_thumbnail_img_url }" class="thumbnailImg"
                                 alt="이미지 준비중 입니다.">
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
</div>
<div class="viewMoreDiv">
    <span class="viewMoreSpan">더보기</span>
</div>
<!-- 유튜브 광고 컨테이너 -->
<div class="youtubeContainer">
        <i class="xi-close closeYoutubeContainerIcon"></i>
    <div class="youtubeInnerDiv">
        <iframe class="adIframe" allowfullscreen></iframe>
    </div>
</div>



<!-- 푸터 -->
<jsp:include page="../shop_footer.jsp"/>

<!-- shop js -->
<script src="/resources/js/shop/main/shop_admin.js"></script>
</body>
</html>