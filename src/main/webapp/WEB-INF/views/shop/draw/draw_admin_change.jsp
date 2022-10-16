<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@  taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<!-- 글로벌 css -->
<link rel="stylesheet" href="/resources/css/global.css">
<link rel="stylesheet" href="/resources/css/shop/draw/draw_admin_change.css">
<!--쇼핑몰 글로벌 아이콘 cdn-->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/xeicon@2/xeicon.min.css">
<title>Document</title>
</head>

<body>
	<!-- 헤더 -->
	<jsp:include page="../shop_header.jsp" />

	<!--메인-->
	<main>
		<form class="admin_container" id="admin_container" name="admin_container" method="POST" action="/shop/draw_admin_change?draw_id=${map.draw_id}">
			<div class="draw_container">

				<div class="admin_main">
					<!--  input을 줘서 히든을 줘라 -->
					<table class="admin_body">
						<c:forEach var="data" items="${data}">
							<tr class="admin_body_file">
								<td class="file_top_fir">이벤트 이미지</td>
								<td>
									<input type="text" name="product_img_url1" class="drawInputs" value="${data.product_img_url1}" readonly="readonly">
								</td>
							</tr>
							<!-- 상품키 모달 버튼 -->
							<tr class="admin_title">
								<td>드로우키</td>
								<td class="admin_product_id">
									<input type="text" name="draw_id" value=" ${data.draw_id}" readonly="readonly" />

								</td>
							</tr>
							<!-- 상품이름 -->
							<tr class="admin_title">
								<td>상품 이름</td>
								<td class="admin_title_name">
									<input type="text" name="draw_title" value="${data.product_name}" readonly="readonly" />

								</td>
								<!-- 상품가격 -->
							<tr class=" admin_title">
								<td>상품 가격</td>
								<td class="admin_title_price">
									<input type="text" name="draw_price" value="${data.product_price}" readonly="readonly" />
								</td>
							</tr>

							<!-- 모집인원 -->
							<tr class="admin_title">
								<td>인원</td>
								<td>
									<input type="number" id="selectDrawBtn" name="draw_reqruit" value="${data.draw_reqruit}" />
								</td>
							</tr>
							<!-- 이벤트 시작일 -->
							<tr class="admin_title">
								<td>이벤트 시작일</td>
								<td>
									<fmt:parseDate value="${data.draw_event_start_date}" pattern="yyyy-MM-dd'T'HH:mm" var="date1" type="both" />
									<input type="date" name="draw_event_start_date" value="<fmt:formatDate value="${date1}" pattern="yyyy-MM-dd" />">

								</td>
							</tr>
							<tr class="admin_title">
								<td>이벤트 종료일</td>
								<td>
									<fmt:parseDate value="${data.draw_event_end_date}" pattern="yyyy-MM-dd'T'HH:mm" var="date1" type="both" />
									<input type="date" name="draw_event_end_date" value="<fmt:formatDate value="${date1}" pattern="yyyy-MM-dd" />">
								</td>

							</tr>

							<tr class="admin_title">
								<td>구매 시작일</td>
								<td>
									<fmt:parseDate value="${data.draw_pur_start_date}" pattern="yyyy-MM-dd'T'HH:mm" var="date1" type="both" />
									<input type="date" name="draw_pur_start_date" value="<fmt:formatDate value="${date1}" pattern="yyyy-MM-dd" />">
								</td>

							</tr>

							<tr class="admin_title">
								<td>구매 종료일</td>
								<td>
									<fmt:parseDate value="${data.draw_pur_end_date}" pattern="yyyy-MM-dd'T'HH:mm" var="date1" type="both" />
									<input type="date" name="draw_pur_end_date" value="<fmt:formatDate value="${date1}" pattern="yyyy-MM-dd" />">
								</td>
							</tr>

							<tr class="admin_title">
								<td>코멘트</td>
								<td>
									<textarea name="draw_comment" id="draw_comment" cols="55" rows="15">${data.draw_comment}</textarea>

								</td>
							</tr>
							<tr>
								<td>상품 공개 여부</td>
								<td>
									<c:if test="${data.draw_deadline eq '이벤트 진행' }">
										<input type="radio" name="draw_deadline" id="draw_dealine" value="이벤트 진행" checked="checked">
										<h3 class="open">이벤트 진행</h3>
										<input type="radio" name="draw_deadline" id="draw_dealine" value="이벤트 마감">
										<h3 class="open">이벤트 마감</h3>
									</c:if>
									<c:if test="${data.draw_deadline eq '이벤트 마감' }">
										<input type="radio" name="draw_deadline" id="draw_dealine" value="이벤트 마감" checked="checked">
										<h3 class="open">이벤트 마감</h3>
										<input type="radio" name="draw_deadline" id="draw_dealine" value="이벤트 진행">
										<h3 class="open">이벤트 진행</h3>
									</c:if>
								</td>
							</tr>
						</c:forEach>
					</table>

				</div>
				<input type="submit" value="완료" class="file_submit">
			</div>
		</form>
	</main>
	<!-- 푸터 -->
	<jsp:include page="../shop_footer.jsp" />

	<script src="/resources/js/shop/draw_customer_add.js"></script>
</body>

</html>