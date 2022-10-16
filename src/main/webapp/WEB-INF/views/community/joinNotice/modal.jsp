<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- 캠핑장소 검색하면 리스트로 추가되는 요소 -->
<c:forEach var="data" items="${data}">
	<label for="${data.campsite_name}">
		<div class="section_header_modal_result_content">
			<li><input type="radio" name="list" id="${data.campsite_name}">${data.campsite_name}</li>
			<li>${data.campsite_category}</li>
			<li>${data.campsite_address}</li>
		</div>
	</label>
</c:forEach>