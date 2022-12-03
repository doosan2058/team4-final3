<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:forEach var="item" items="${reviewList }">
		<div class="reviews">
			<!-- 아이디, 날짜 -->
			<div class="reviewsDiv reviewsHeader">
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
					<input type="hidden" value="${item.product_review_id }">
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
						<p></p>
					</c:if>
					<c:if test="${item.product_review_img_url ne 'no url'}">
						<img alt="이미지 준비중" src="${item.product_review_img_url }" class="reviewImage">
					</c:if>



				</div>
				<div class="reviewsPhotoInner reviewsPhotoInnerVideo">
					<!-- 조건 처리 작업 -->
					<c:if test="${item.product_review_video_url eq 'no url' }">
						<p></p>
					</c:if>
					<c:if test="${item.product_review_video_url ne 'no url' }">
						<video controls width="100%" height="100%">
							<source src="${item.product_review_video_url }" type="video/mp4">
						</video>
					</c:if>


				</div>


			</div>





		</div>

	</c:forEach>
	<input type="text" value="${pageReview.end }" id="pageEnd">
</body>
</html>