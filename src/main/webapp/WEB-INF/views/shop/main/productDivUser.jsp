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
		<div class="product">
			<input type="hidden" value="${item.product_id }">
			<div class="nameDiv">
				<a href="/product/detail?product_id=${item.product_id }"> ${item.product_name } </a>
			</div>

			<div class="thumbnailDiv">
				<img src="${item.product_thumbnail_img_url}" class="thumbnailImg" alt="이미지 준비중 입니다.">
			</div>
		</div>

	</c:forEach>
	<input type="text" class="currentPageTemp" value="${pageShop.currentPage}">
	<input type="text" class="totalPageTemp" value="${pageShop.totalPage}">
</body>
</html>