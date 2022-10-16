<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<!-- 글로벌 css -->
<link rel="stylesheet" href="/resources/css/global.css">
<link rel="stylesheet" href="/resources/css/shop/draw/draw_admin_count.css">
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<!--쇼핑몰 글로벌 아이콘 cdn-->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/xeicon@2/xeicon.min.css">
<title>Document</title>
</head>

<body>
	<!-- 헤더 -->
	<jsp:include page="../shop_header.jsp" />
	<!--메인-->
	<main>
		<div class="draw_container">
			<form class="count_container" method="POST"
				action="/shop/draw_customer_count?draw_id=${map.draw_id}">
				<div class="count_main">
					<table class="count_body">
						<thead>
							<tr class="count_Title_title">
								<th colspan="4">이벤트 1 응모회원 목록</th>
							</tr>
						</thead>
						<tbody>

							<tr class="count_Title">
								<c:forEach var="row" items="${data}">
									<td><label class="all-chckbox"> 
									<input type="text" name="chk" class="chk" value=${row.draw_id}>
									</label>
									<td> ${row.member_id}</td>
								

								</c:forEach>
							</tr> 
						</tbody>
					</table>

					<input value="닫기" class="file_submit"
						type="button" onclick="checkForm()">
					<input value="적용하기" class="_submit"
					type="button" onclick="checkpurchaseForm()">
				
				</div>


			</form>
		</div>

	</main>
	<!-- 푸터 -->
	<jsp:include page="../shop_footer.jsp" />
	<script src="/resources/js/shop/draw/draw_admin_count.js"></script>

</body>

</html>