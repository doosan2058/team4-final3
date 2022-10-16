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
				action="/shop/draw_admin_count?draw_id=${draw_id}">


				<div class="count_main">
					<table class="count_body">
						<thead>
							<tr id="count_Title_title">
								<th class="title_th_head">
								<label class="checkbox-inline"> 
								<input type="checkbox" id="allCheckBox" onclick="allChecked()">
								</label>
								</th>
								<th class="title_td">이벤트 1 응모회원 목록</th>
							</tr> 
						</thead> 
						<tbody>
						<c:forEach var="row" items="${data}">
							<tr class="count_Title">
									<td>
									<label class="all-chckbox"> 
									<input type="checkbox" name="chk" class="chk" onclick="chkClicked()" value=${row.member_id}>
									</label>
									</td>
									<td class="member_id_td">${row.member_id}</td>
							</tr>
							</c:forEach>
						</tbody>
					</table>
					<input type="hidden" name="draw_id" id="hidden" value="${map.draw_id}">
					<input value="적용하기" class="file_submit"
						type="button" onclick="checkForm()">
				</div>


			</form>
		</div>

	</main>
	<!-- 푸터 -->
	<jsp:include page="../shop_footer.jsp" />
	<script src="/resources/js/shop/draw/draw_admin_count.js"></script>
	<script>
	function checkForm(){
		var draw_id= document.querySelector("#hidden").value;
		var checkArray = new Array();
		// let testt = JSON.parse('${filter}');
		checkArray.push(draw_id);
		$('input[name=chk]:checked').each(function(){
			checkArray.push($(this).val());
		});
		console.log(checkArray);
	$.ajax({
			url : "/shop/draw_admin_count.os",
			type : "POST",
			dataType : "text",
			data : {
				valueArrTest:checkArray
			},
			success : function(){
				alert("응모 추첨이 완료되었습니다");
				  
				 location.href = '/shop/draw_admin';
		
				 
		     }

				
				
			}) 
		}
	</script>

</body>

</html>