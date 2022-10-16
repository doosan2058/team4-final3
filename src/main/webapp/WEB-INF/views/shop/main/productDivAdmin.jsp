<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<c:forEach var="item" items="${productList}">
		<div class="section_items">
			<%-- <input type="hidden" value="${item.product_id }"> --%>
			<a href="/product/detail/admin?product_id=${item.product_id }"> ${item.product_name } </a>
			<div class="productInfoDiv">
				<span class="limitedSpan"> ${item.product_limited } 상품 </span>
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
	<input type="text" class="currentPageTemp" value="${pageShop.currentPage}">
	<input type="text" class="totalPageTemp" value="${pageShop.totalPage}">
</body>
</html>