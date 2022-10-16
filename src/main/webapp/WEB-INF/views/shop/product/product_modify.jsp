<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- css 링크 -->
<link rel="stylesheet" href="/resources/css/shop/product/product_modify.css">
<!-- 글로벌 css -->
<link rel="stylesheet" href="/resources/css/global.css">
<title>Product_Modify</title>
</head>
<body>
	<!-- 헤더 -->
	<jsp:include page="../shop_header.jsp" />
	<!--메인-->

	<main>
		<form action="modify" method="post" enctype="multipart/form-data">
			<input type="hidden" value="${product.product_id}" name="product_id">
			<!--상품 수정 컨테이너-->
			<div class="registProductCon">
				<div class="registProductDiv">
					<table>
						<thead>
							<tr>
								<th></th>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>카테고리 선택</td>
								<td>
									<select name="product_category_id" id="" class="formInputs">
										<c:forEach var="item" items="${categoryList }">
											<c:if test="${product.product_category_id eq item.product_category_id }">
												<option value="${item.product_category_id }" selected="selected">${item.product_category_name }</option>
											</c:if>
											<c:if test="${product.product_category_id ne item.product_category_id }">
												<option value="${item.product_category_id }">${item.product_category_name }</option>
											</c:if>


										</c:forEach>

									</select>
								</td>
							</tr>
							<tr>
								<td>브랜드 선택</td>
								<td>
									<select name="product_brand_id" id="" class="formInputs">
										<c:forEach var="item" items="${brandList }">
											<option value="${item.product_brand_id }">${item.product_brand_name }</option>
										</c:forEach>


									</select>
								</td>
							</tr>
							<tr>
								<td>한정판 여부</td>
								<td>
									<c:if test="${product.product_limited eq '한정판' }">
										<input type="radio" name="product_limited" id="" value="한정판" checked="checked">한정판 
										<input type="radio" name="product_limited" id="" value="일반">일반
									</c:if>
									<c:if test="${product.product_limited eq '일반' }">
										<input type="radio" name="product_limited" id="" value="한정판">한정판 
									<input type="radio" name="product_limited" id="" value="일반" checked="checked">일반
									</c:if>
								</td>
							</tr>
							<tr>
								<td>상품 재고량</td>
								<td>
									<input type="number" class="formInputs" name="product_stock" id="" required="required" min="1" max="99" value=${product.product_stock }>
									개
								</td>
							</tr>
							<tr>
								<td>상품 이름</td>
								<td>
									<input type="text" class="formInputs" name="product_name" id="" required="required" maxlength="20" value="${product.product_name }">
								</td>
							</tr>
							<tr>
								<td>상품 코멘트</td>
								<td>
									<textarea name="product_comment" id="formText" placeholder="상품 설명 작성(200자)" maxlength="200">${product.product_comment }</textarea>
								</td>
							</tr>
							<tr>
								<td>상품 가격</td>
								<td>
									<input type="number" class="formInputs" name="product_price" id="" required="required" min="1" max="99999999" value="${product.product_price }">
									원
								</td>
							</tr>
							<tr>
								<td>상품 이미지(최대 3개)</td>
								<!-- <td><input type="file" name="imgs1" multiple></td> -->
								<td>
									<input type="file" name="imgs1" id="mainFile" class="formInputs" multiple>
									<div class="mainFileListDiv"></div>
								</td>
							</tr>
							<tr>
								<td>상품 설명 이미지(최대 2개)</td>
								<!-- <td><input type="file" name="imgs2" multiple></td> -->
								<td>
									<input type="file" name="imgs2" id="subFile" class="formInputs" multiple>
									<div class="subFileListDiv"></div>
								</td>
							</tr>
							<tr>
								<td>상품 유튜브 주소</td>
								<td>
									<c:if test="${product.product_youtube_url eq 'no url' }">
										<input type="text" name="product_youtube_url" id="" maxlength="100" class="formInputs">
									</c:if>
									<c:if test="${product.product_youtube_url ne 'no url' }">
										<input type="text" name="product_youtube_url" id="" maxlength="100" value="${product.product_youtube_url }" class="formInputs">
									</c:if>
								</td>
							</tr>
							<tr>
								<td>상품 배송일</td>
								<td>
									<input type="number" class="formInputs" name="product_delivery_day" id="" required="required" min="1" max="10" value="${product.product_delivery_day }">
									일
								</td>
							</tr>
							<tr>
								<td>상품 공개 여부</td>
								<td>
									<c:if test="${product.product_delete eq 'y' }">
										<input type="radio" name="product_delete" id="" value="n">공개
										<input type="radio" name="product_delete" id="" value="y" checked="checked">비공개
									</c:if>
									<c:if test="${product.product_delete eq 'n' }">
										<input type="radio" name="product_delete" id="" value="n" checked="checked">공개
										<input type="radio" name="product_delete" id="" value="y">비공개
									</c:if>
								</td>
							</tr>

							<tr>
								<td colspan="2">
									<input type="submit" id="submitBtn" class="formBtns" value="수정하기">
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				<!--미리보기-->
				<div class="prePageDiv">

					<div class="prePageDivTop">
						<div class="prePageDivTopLeft">
							<div class="productImgMain">상품 메인 이미지</div>
							<div class="productImgFirst">
								<img class="mainImgs">
							</div>
							<div class="productImgSecond">
								<img class="mainImgs">
							</div>
							<div class="productImgThird">
								<img class="mainImgs">
							</div>
						</div>
						<div class="prePageDivTopRight"></div>
					</div>
					<div class="prePageDivBottom">
						<div class="subImgFirst">
							<img class="subImgs">
						</div>
						<div class="subImgSecond">
							<img class="subImgs">
						</div>
					</div>

				</div>
			</div>
		</form>

	</main>
	<!-- 푸터 -->
	<jsp:include page="../shop_footer.jsp" />
	<!-- js -->
	<script src="/resources/js/shop/product/product_modify.js"></script>
</body>
</html>