<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
<title>Shop_Admin</title>
</head>

<body>
	<!-- 헤더 -->
	<jsp:include page="../shop_header.jsp" />


	<!--광고-->
	<div class="carousel">
		<!-- <i class="xi-angle-left carousel_button_L"></i> -->
		<!--광고 컨테이너-->
		<div class="adContainer">

			<div class="adSubCon">

				<div class="carouselImgDiv">
					<img src="/resources/img/shop/ad/3ed70dbe5c741730b08d02b2664bd0ad.jpg" alt="">
				</div>
				<!-- <div class="carouselContentDiv">광고 1</div> -->

			</div>
			<div class="adSubCon">

				<div class="carouselImgDiv">
					<img src="/resources/img/shop/ad/426036bc4d8e49a589788a2730d3ecf5.jpg" alt="">
				</div>
				<!-- <div class="carouselContentDiv">광고 2</div> -->

			</div>
			<div class="adSubCon">

				<div class="carouselImgDiv">
					<img src="/resources/img/shop/ad/56e8f0bd7af5e3fa52f7ac76dd346b8f.jpg" alt="">
				</div>
				<!-- <div class="carouselContentDiv">광고 3</div> -->

			</div>
			<div class="adSubCon">

				<div class="carouselImgDiv">
					<img src="/resources/img/shop/ad/ba3b9d639544b7b7e35a1c6173a52fbb.jpg" alt="">
				</div>
				<!-- <div class="carouselContentDiv">광고 4</div> -->

			</div>
			<div class="adSubCon">

				<div class="carouselImgDiv">
					<img src="/resources/img/shop/ad/c5a20ba490f33ba0301b5cbc46fbf4b9.jpg" alt="">
				</div>
				<!-- <div class="carouselContentDiv">광고 5</div> -->

			</div>



		</div>

		<!-- <i class="xi-angle-right carousel_button_R"></i> -->
	</div>
	<!--carousel-->

	<!--메인-->
	<main>
		<!--왼쪽 섹션-->
		<div class="section">
			<!--상품 탑 10 헤드-->
			<div class="section_best_header">
				<h1>
					Best
					<span>10</span>
				</h1>
			</div>
			<!--상품 탑 10 내용-->
			<div class="section_best">
				<i class="xi-angle-left best_button_L"></i>

				<div class="section_best_items">
					<c:forEach var="item" items="${productList}" varStatus="status">
						<div class="section_item">
							<div class="nameDiv">
								
								<a href="/product/detail?product_id=${item.product_id }"> ${item.product_name } </a>

							</div>
							<div class="thumbnailDiv">
								<img src="/productImg/${item.product_thumbnail_img_url }" class="thumbnailImg" art="이미지 준비중 입니다.">
							</div>
							<!-- 스코어 -->
							<div class="scoreDiv">
								<span class="numberSpan${status.count }">${status.count }</span>
							</div>
						</div>
					</c:forEach>
				</div>


				<i class="xi-angle-right best_button_R"></i>
			</div>
			<!--상품 헤더-->
			<div class="section_items_header">
				<h1>Item
				<span>
					<a href="/product/register">상품 추가</a>
				</span>
				
				</h1>
			</div>
			<!--상품 컨테이너-->
			<div class="section_items_main">
				<!-- 현재 페이지 정보 -->
				<input type="hidden" id="currentPageInput" value="${pageShop.currentPage}">
				<!-- 전체 페이지 정보 -->
				<input type="hidden" id="totalPageInput" value="${pageShop.totalPage}">
				<!--상품 더보기 클릭시 상품 그릴 디비전-->
				<div class="productDiv">
					<!-- 처음 상품 10개  -->
					<c:forEach var="item" items="${productList}">
						<div class="section_items">
							<%-- <input type="hidden" value="${item.product_id }"> --%>
							<a href="/product/detail/admin?product_id=${item.product_id }"> ${item.product_name } </a>
							<div class="productInfoDiv">
								<span class="limitedSpan">
									${item.product_limited } 상품
								</span>
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
								<img src="/productImg/${item.product_thumbnail_img_url}" class="thumbnailImg" art="이미지 준비중 입니다.">
							</div>
						</div>
					</c:forEach>



				</div>
			</div>

			<div class="section_view_more">
				<!-- <i class="xi-angle-down view_more_btn"></i> -->
				<span class="moreSpan">더보기</span>
			</div>
		</div>

		<div class="side">
			<div class="side_navBar">
				<!-- 등급 구역 -->
				<div class="gradeDiv">
					
				
					<div class="gradeInnerTop">
						
						<c:if test="${!empty userInfo }">
							<img alt="" src="${userInfo.member_profile_img_url }" id="userProfileImg">
						</c:if>
						
						
					</div>
					<div class="gradeInnerBottom">
						
						<c:if test="${!empty userInfo }">
							<span class="userGradeSpan" style="color: var(--fontColor);">관리자</span>
							<a href="/gradePolicy_admin" class="gradeAnchor">등급 편집</a>
						</c:if>
						
					</div>
					
					
				</div>
				<!-- 카테고리 구역 -->
				<div class="categoryDiv">
					<div class="categoryInnerDiv">
						<span class="categorySpan" data-category-id = "0">All</span>
					</div>
					
					<c:forEach var="item" items="${categoryList }">
						<div class="categoryInnerDiv">
							<span class="categorySpan" data-category-id = "${item.product_category_id }">${item.product_category_name }</span>
						
						</div>
					</c:forEach>
					
				</div>
			</div>
		</div>
		
	</main>
	<!-- 푸터 -->
	<jsp:include page="../shop_footer.jsp" />

	<!-- shop js -->
	<script src="/resources/js/shop/main/shop_admin.js"></script>
</body>

</html>