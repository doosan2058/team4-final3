<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!-- 폰트, 색상 공통 -->
    <link rel="stylesheet" href="/resources/css/global.css">


    <link rel="stylesheet" href="/resources/css/shop/qna/Question_admin.css">

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/xeicon@2/xeicon.min.css">
    <script src="https://cdn.ckeditor.com/ckeditor5/35.1.0/classic/ckeditor.js"></script>
    <style>
        .ck.ck-editor {
            width: 760px;
        }

        .ck-editor__editable {
            height: 340px;
        }
    </style>
    <title>Document</title>
</head>

<body>
<!-- header -->
<%@include file="../shop_header.jsp" %>
<!--메인-->
<main>
    <div class="Answer_Header">
        <h1>문의 답변</h1>
    </div>

    <div class="Answer_Name">
        <h3>작성자 아이디</h3>
        <input id="user_Name_Text" name="member_id" value="${data.member_id}" type="text" readonly>
    </div>
    <div class="Answer_Date">
        <h3>작성일</h3>
        <input id="user_Name_Text" name="qna_regdate" value="${data.qna_regdate}" type="text" readonly>
    </div>
    <div class="Answer_Type">
        <h3>문의유형</h3>
        <select name="qna_category" id="question_Type_Select" disabled="disabled">
            <option value="주문/결제">주문/결제</option>
            <option value="배송">배송</option>
            <option value="취소/환불/교환">취소/환불/교환</option>
            <option value="회원">회원</option>
            <option value="기타">기타</option>
        </select>
    </div>
    <div class="Answer_Title">
        <h3>게시글 번호</h3>
        <input id="question_Title_Text" name="qna_id" type="text" value="${data.qna_id}" readonly>
    </div>
    <div class="Answer_Content">
        <h3>내용</h3>
        <input id="question_Title_Text" name="qna_title" type="text" value="${data.qna_title}" readonly>
    </div>
    <form method="POST" action="/shop/question_admin_update">
        <div class="admin_Content">
            <h3>답변 내용</h3>
            <input type="hidden" name="qna_id" value="${data.qna_id}">
            <textarea id="question_Title_Text4" name="qna_answer_text" cols="30"
                      rows="10">${data.qna_answer_text}</textarea>
            <script>
                ClassicEditor
                    .create(document.querySelector('#question_Title_Text4'));
            </script>
        </div>
        <div class="Answer_Check">
            <input type="submit" value="답변수정">
        </div>
    </form>
</main>
<!-- footer -->
<%@include file="../shop_footer.jsp" %>
<script src="/resources/js/shop/qna/question_admin.js"></script>

</body>

</html>