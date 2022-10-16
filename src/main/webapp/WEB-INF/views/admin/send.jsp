<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>프로모션 메일 보내기</title>
</head>
<body>
<form action="/admin/send" method="post">
	<div class="mailCon">
		<input type="text" name="subject" class="formInputs" placeholder="제목을 입력하세요." style="border-bottom: 3px solid var(--mainColor);">
		<textarea rows="" cols="" name="content" id="mailTextArea" placeholder="내용을 입력하세요." style="border-bottom: 3px solid var(--mainColor);"></textarea>
		<input type="submit" class="formBtns" value="전송">	
	</div>
</form>
</body>
</html>