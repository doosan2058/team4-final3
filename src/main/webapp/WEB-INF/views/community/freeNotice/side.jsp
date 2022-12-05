<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!--글로벌 css -->
<link rel="stylesheet" href="/resources/css/global.css">
<!--커뮤니티 글로벌 css-->
<link rel="stylesheet" href="<c:url value="/resources/css/community/community_global.css?ver=2"/>">
	<!-- Jquery cdn -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<!--xeicon-->
	<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css">

<title>Document</title>
</head>

<body>
	<div class="side_nav">
		<li class="side_nav_tag all_tag">전체</li>
		<c:forEach var="tag" items="${tag}">
			<li class="side_nav_tag">${tag.board_tag_name}</li>
		</c:forEach>
		
		<form class="side_nav_form1" method="get"> <!-- 태그 클릭 시 넘어 갈 폼 -->
			<input name="board_tag_id" class="side_nav_input" type="hidden" value="">
		</form>
		
		<form class="side_nav_form2" method="get"> <!--  검색 시 넘어 갈 폼 -->
			<input type="hidden" name="board_tag_id" value="${tag_id}">
			<input type="text" class="side_nav_search" name="keyword">
			<i class="xi-search side_nav_search_btn"></i>
		</form>
		
		<form class="side_nav_form3" method="get"> <!-- 조회순 클릭 시 넘어 갈 폼 -->
			<input name="board_tag_id" class="hit_tag" type="hidden" value="${tag_id}">
			<input name="keyword" class="hit_keyword" type="hidden" value="${keyword}">
			<input name="sort_value" class="hit_value" type="hidden" value="h_desc">
		</form>
		
		<form class="side_nav_form4" method="get"> <!-- 추천순 클릭 시 넘어 갈 폼 -->
			<input name="board_tag_id" class="rec_tag" type="hidden" value="${tag_id}">
			<input name="keyword" class="rec_keyword" type="hidden" value="${keyword}">
			<input name="sort_value" class="rec_value" type="hidden" value="r_desc">
		</form>
		<script>/* 조회순 추천순에 사용될 변수 */
			let sort_value = '<c:out value="${sort_value}"/>';
		</script>
	</div>
</body>

</html>