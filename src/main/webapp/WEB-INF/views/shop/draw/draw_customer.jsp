<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/xeicon@2/xeicon.min.css">
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>

<!-- 글로벌 css -->
<link rel="stylesheet" href="/resources/css/global.css">
<link rel="stylesheet" href="/resources/css/shop/draw/draw_customer.css">

<title>Document</title>
</head>

<body>
	<!-- 헤더 -->
	<jsp:include page="../shop_header.jsp" />

	<main>
		<div class="draw_container">
			<%--  <c:if test="${sessionScope.login_auth eq '회원'} || ${sessionScope.login_auth eq null}" >  --%>

			<%--     </c:if>  --%>
			<div class="">
				<div class="section1">
					<h1>
						Camping
						<span> Draw</span>
					</h1>
					<div class="section1_img">
						<img src="/resources/img/shop/draw/캠핑2.jpg" alt="" id=section1_img_img>
					</div>
				</div>
				<div class="section2">
					<div class="section2-top">
						<span>드로우 목록</span>
					</div>

					<div class="section2-bottom">
						<!--foreach 반복 디비전-->
						<c:forEach var="row" items="${data}">
							<div class="section2_img">
								<!--이벤트 이미지-->
								<c:if test="${row.product_img_url1 ne 'no url' }">
									<img src="/productImg/${row.product_img_url1 }" alt="" class="productMainImg">f
								</c:if>
							</div>
							<div class="section2_font">
								<!--이벤트 텍스트-->
								<div class="font_top">
									<h3>
										<span class="deadlineSpan">${row.draw_deadline }</span>
										<span>${row.draw_title}</span>
									</h3>
									<!--이벤트 제목-->
									<div class="adminBtnsContainer">
										<input type="hidden" value="수정" class="adminBtns">
										<input type="hidden" value="삭제" class="adminBtns">
									</div>
								</div>
								<div class="font_bottom">
									<table>
										<tbody>
											<tr>
												<td class="font_bottom_td">상품 제목</td>
												<td>${row.draw_title}</td>
											</tr>
											<tr>
												<td class="font_bottom_td">가격</td>
												<td>${row.draw_price}</td>
											</tr>
											<tr>
												<td class="font_bottom_td">인원</td>
												<td>${row.draw_reqruit}</td>
											</tr>
											<tr>
												<td class="font_bottom_td">이벤트 시작일</td>
												<td>
													<fmt:formatDate value="${row.draw_event_start_date}" />
													<%-- <fmt:parseDate value="${row.draw_event_start_date}" pattern="yyyy-MM-dd'T'HH:mm" var="date1" type="both" />
                                     <fmt:formatDate value="${date1}" pattern="yyyy-MM-dd a HH:mm:ss" /> --%>
												</td>
											</tr>
											<tr>
												<td class="font_bottom_td">이벤트 종료일</td>
												<td>
													<fmt:formatDate value="${row.draw_event_end_date}" />
												</td>

											</tr>
											<tr>
												<td class="font_bottom_td">구매 시작일</td>
												<td>
													<fmt:formatDate value="${row.draw_pur_start_date}" />
												</td>
											<tr>
												<td class="font_bottom_td">구매 종료일</td>
												<td>
													<fmt:formatDate value="${row.draw_pur_end_date}" />
												</td>
											</tr>

											<tr>
												<td class="font_bottom_td">코멘트</td>
												<td>${row.draw_comment}</td>
											</tr>

										</tbody>
									</table>
									<div class="button_container">
										
										<input type="button" value="${row.draw_deadline }" name="draw_deadline" class="font_top_button" id="font_top_button">
										<input type="hidden" name="draw_id" id="draw_id" value="${row.draw_id}">
										&nbsp; &nbsp;
										<input type="button" value="응모결과보기" class="font_top_button_end">
										<input type="hidden" value="${row.product_id}">
									</div>

								</div>
							</div>
							<form method="POST" class="list_form" action="shop/draw_customer_draw" class="draw_modal_form">
								<%-- <input type="hidden" name="member_id" id="user_id" value="${sessionScope.member_id}"> --%>
								<input type="hidden" name="login_id" id="login_id" value="${sessionScope.login_id}">
							</form>
						</c:forEach>
					</div>

					<!-- 응모 성공 모달 -->
					<!-- <div class="modal_container">
						<div class="modal_body">
							<div class="modal_top">응모에 성공하셨습니다</div>
							<div class="modal_bottom">
								<button class="modal_bottom_btn">확인</button>
							</div>
						</div>
					</div> -->


					<div class="modal_final_container">
						<!-- 상단 -->
						<div class="modal_final_body">
							<!-- 제목 -->
							<div class="modal_final_top">추첨결과</div>
							<!-- 당첨자 목록 -->
							<div class="modal_final_middle">
								<%-- <c:forEach var="row" items="${draw_customer_button}">
									<div class="modal_final_middle_ajax"></div>
								</c:forEach> --%>
							</div>



							<!-- 하단 -->
							<div class="modal_final_bottom">

								<button class="modal_final_bottom_btn">확인</button>
								<button class="modal_final_bottom_btn" id="limitedBtns">구매하기</button>

							</div>

						</div>
						<input type="hidden" id="limiteProduct">
					</div>


				</div>
			</div>
		</div>

		</div>

	</main>

	<!-- 푸터 -->
	<jsp:include page="../shop_footer.jsp" />
	<script src="/resources/js/shop/draw/draw_customer.js"></script>
	
</body>

</html>