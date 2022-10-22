<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>전체 회원 목록 페이지</title>
</head>
<body>
	<c:forEach var="item" items="${list }">
		<div class="memberDiv">
			<div class="memberImgDiv">
				<img alt="" src="${item.member_profile_img_url }" class="memberImg">
			</div>
			<div class="memberIdDiv">
				<a href="/admin/detail?member_id=${item.member_id }">
					<span class="memberIdSpan">${item.member_id }</span> 
				</a>

			</div>
		</div>
	</c:forEach>
</body>
</html>