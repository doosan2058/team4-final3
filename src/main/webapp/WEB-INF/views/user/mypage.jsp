<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="/resources/css/user/mypage.css">
<link rel="stylesheet" href="/resources/css/global.css">

<!-- Jquery cdn -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

<!-- 구글 폰트 -->
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
<title>My Page</title>
</head>
<body>
	<!--내정보-->
	<div class="myInfoCon">
		<div class="navDiv">
			<a href="/">메인</a>
			<a href="/shop">쇼핑몰</a>
			<a href="/community/main">커뮤니티</a>
		</div>
		<div class="infoTopDiv">
			<div class="userImgDiv">

				<div class=userImgDivInnerTop>
					<img src="${memberVO.member_profile_img_url }" alt="프로필 이미지가 없습니다." id="userImg">

					<input type="hidden" value="${memberVO.member_profile_img_url }" name="member_profile_img_url" id="member_profile_img_url">
					<form id="profileImgForm">
						<input type="hidden" value="${memberVO.member_id }" name="member_id" id="member_id">
				</div>
				<div class=userImgDivInnerBottom>
					<input type="file" class="file" name="file">
					<a class="imgSubmit">변경</a>
					<a class="imgCancel">취소</a>
				</div>
				</form>

			</div>
			<div class="userInfoDiv">
				<span class="userNameSpan">${sessionScope.login_id }</span>
				님
				<p>Point Of Purchase : ${memberVO.member_purchase_point }</p>
				<p>Point Of Write : ${memberVO.member_write_point }</p>
			</div>
			<div class="userModifyDiv">
				<a id="logOutBtn">로그아웃</a>
				<a class="changePasswordAnchor">비밀번호 변경</a>
				<a class="deleteMemberAnchor">회원탈퇴</a>
			</div>
		</div>

		<div class="infoBottomDiv">
			<div class="userGradeDiv">회원등급</div>
			<!-- <div class="userQuoponHistoryDiv">쿠폰 사용내역</div> -->
			<div class="userOrderHistoryDiv">구매 내역</div>
		</div>
	</div>
	<!--내 활동 내역-->
	<div class="userHistoryCon">

		<div class="deliveryHisroyDiv">
			<c:if test="${!empty orderList }">
				<table class="deliveryHisroyTable">
					<thead>
						<tr>
							<th>주문 번호</th>
							<th>상품 번호</th>
							<th>주문일</th>
							<th>도착 예상일</th>
							<th>구매 금액</th>
							<th>상태</th>
							<th>적립 예정 포인트</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="item" items="${orderList }">
							<tr>
								<td>${item.order_id }</td>
								<td>${item.product_id }</td>
								<td>
									<fmt:formatDate value="${item.order_start_date }" />
								</td>
								<td>
									<fmt:formatDate value="${item.order_end_date }" />
								</td>
								<td>${item.order_purchase_amount }</td>
								<td class="confirmBuy" data-state="${item.order_state }">${item.order_state }</td>
								<td>${item.member_purchase_point }</td>
							</tr>
						</c:forEach>

					</tbody>
				</table>
			</c:if>
			<c:if test="${empty orderList }">
				<h3>현재 주문중인 상품이 없습니다.</h3>
			</c:if>

		</div>
		<div class="etcHistoryDiv">
			<a class="myListAnchor">글 내역</a>


		</div>
	</div>
	<!-- 구매내역 보기 모달 -->
	<div class="orderHistoryModalCon">
		<div class="orderHistoryDiv">

			<c:if test="${!empty salesList }">
				<table class="orderHistoryTable">
					<thead>
						<tr>
							<th>매출번호</th>
							<th>주문번호</th>
							<th>구매일</th>
							<th>리뷰작성여부</th>
							<th>확정일</th>

						</tr>
					</thead>
					<tbody>
						<c:forEach var="item" items="${salesList }">
							<tr>
								<td>${item.sales_id }</td>

								<td>${item.order_id }</td>
								<td>
									<fmt:formatDate value="${item.sales_date }" />
								</td>
								<td class="reviewTd">
									<c:if test="${item.sales_review eq 'n'}">
										<a href="/user/review?sales_id=${item.sales_id }">작성하기</a>
									</c:if>
									<c:if test="${item.sales_review eq 'y'}">
										<a>작성완료</a>
									</c:if>
								</td>
								<td>
									<fmt:formatDate value="${item.sales_review_date }" />
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:if>
			<c:if test="${empty salesList }">
				<h3>회원님의 구매 내역이 없습니다.</h3>
			</c:if>

		</div>
	</div>

	<!-- 회원탈퇴 컨테이너 -->
	<div class="deleteMemberContainer">
		<!-- 회원 탈퇴 디비전 -->
		<div class="deleteMemberDiv">
			<div class="deleteInner">
				<span> 탈퇴 하시겠어요? </span>
				<span class="material-symbols-outlined closeIcon"> close </span>

			</div>
			<div class="deleteInner">만약 이글을 읽지 않으면 엄청난 일이 생길수 있습니다!</div>
			<div class="deleteInner">
				<p>
					탈퇴 신청은
					<span class="boldSpan">취소할수 없습니다</span>
					.
				</p>
				<p>
					탈퇴 완료 후에도
					<span class="boldSpan">${sessionScope.login_id }</span>
					님의 모든 게시글은 삭제되지 않을수 있습니다.
				</p>
				<p>
					동의 하시면
					<span class="deleteSpan">${sessionScope.login_id }/탈퇴신청</span>
					을 입력해 주세요.
				</p>
			</div>
			<div class="deleteInner">
				<input type="text" class="deleteInputs">
				<!-- <input type="button" class="deleteBtns" value="상기 내용에 동의하고, 삭제 신청합니다." disabled="disabled"> -->

				<button class="deleteBtns" disabled="disabled">상기 내용에 동의하고, 삭제 신청합니다.</button>
			</div>
		</div>
	</div>

	<!-- 비밀번호 변경 컨테이너 -->
	<div class="changePasswordContainer">
		<!-- 비밀번호 변경 디비전 -->
		<div class="changePasswordDiv">
			<div class="changePasswordInnerTop">
				<p>확인을 위해 다시 한번 비밀번호를 입력해 주세요.</p>
			</div>
			<div class="changePasswordInnerBottom">
				<input type="password" class="formInputs" id="passwordInput">
				<button class="formBtns" id="passwordBtn">입력</button>
			</div>
			<div class="changePasswordInnerResult">
				<span class="pwCheckSpan"></span>
			</div>
		</div>
	</div>

	<!-- 내 글 목록 -->
	<div class="myListContainer">
		<div class="myListDiv">
			<div class="myListHeader">
				<span class="material-symbols-outlined"> close </span>
			</div>

			<!-- 자유 -->
			<div class="freeBoard">
				<h2>자유 게시판 글목록</h2>
				<c:forEach var="item" items="${freeMapList }">
					<div class="lineDiv">
						<span class="idxSpan">${item.board_id }</span>
						<span class="titleSpan">${item.board_title }</span>
					</div>

				</c:forEach>
			</div>
			<!-- 캠핑 -->
			<div class="camping">
				<h2>캠핑 게시판 글목록</h2>
				<c:forEach var="item" items="${campingMapList }">
					<div class="lineDiv">
						<span class="idxSpan">${item.camping_id }</span>
						<span class="titleSpan">${item.camping_title }</span>
					</div>
				</c:forEach>
			</div>
			<!-- 리뷰 -->
			<div class="review">
				<h2>질문 글목록</h2>
				<c:forEach var="item" items="${qnaMapList }">
					<div class="lineDiv">
						<span class="idxSpan">${item.qna_id }</span>
						<span class="titleSpan">${item.qna_title }</span>
					</div>
				</c:forEach>
			</div>

		</div>
	</div>

	<script src="/resources/js/user/mypage.js"></script>
</body>
</html>