<%--
  Created by IntelliJ IDEA.
  User: choi
  Date: 2022-11-09
  Time: 오후 6:47
  To change this template use File | Settings | File Templates.
--%>
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
    <link rel="stylesheet" href="/resources/css/shop/qna/qna_detail.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/xeicon@2/xeicon.min.css">
    <title>질문 상세 페이지</title>
</head>
<body>
<jsp:include page="../shop_header.jsp"/>
<input type="hidden" id="qna_public" value="${qnaVO.qna_public}">
<input type="hidden" id="qna_delete" value="${qnaVO.qna_delete}">
<c:if test="${qnaVO.qna_delete ne 'y'}">
    <div class="qnaDetailContainer">
        <div class="detailTopDiv">
            <div class="detailProfileImgDiv">
                <img src="${member_profile_img_url}" class="profileImg">
            </div>
            <div class="detailInfoDiv">
                <div>
                <span class="qnaIdSpan">${qnaVO.qna_id}.
                    <span class="qnaTagSpan">[${qnaVO.qna_category}]</span>
                    <c:if test="${qnaVO.qna_admin_answer eq 'n'}">
                        <span>[대기중]</span>
                    </c:if>
                    <c:if test="${qnaVO.qna_admin_answer eq 'y'}">
                        <span>[완료]</span>
                    </c:if>
                </span>

                    <span class="qnaTitleSpan">${qnaVO.qna_title}</span>

                </div>
                <div>
                    <span class="qnaSubjectSpan qna_member_id">${qnaVO.member_id}</span>
                </div>
                <div>
                <span class="qnaSubjectSpan">
                    작성일
                    <span class="qnaDateSpan"><fmt:formatDate
                            value="${qnaVO.qna_regdate}" pattern="yyyy.MM.dd HH:mm"/>
                    </span>
                    <span class="dateDashSpan">|</span>
                </span>

                    <span class="qnaSubjectSpan">
                    수정일
                     <span class="qnaDateSpan"><fmt:formatDate
                             value="${qnaVO.qna_update_date}" pattern="yyyy.MM.dd HH:mm"/>
                     </span>
                </span>
                    <span class="dateDashSpan">|</span>
                    <span class="qnaSubjectSpan">
                    첨부파일
                    <c:if test="${qnaVO.qna_picture_url ne 'not url'}">
                        <i class="xi-paperclip attachmentIcon"></i>
                    </c:if>
                    <c:if test="${qnaVO.qna_picture_url eq 'not url'}">
                        <i class="xi-paperclip attachmentIcon noneClass"></i>
                        <span style="color: rgba(122,122,122,0.87); font-size: 0.8rem">없습니다.</span>
                    </c:if>
                </span>
                    <div>
                        <a id="qnaDetailModifyAnchor" href="/qna/modify?qna_id=${qnaVO.qna_id}">수정</a>
                        <form>
                            <input type="hidden" name="qna_id" value="${qnaVO.qna_id}">
                            <input type="submit" formmethod="post" formaction="/qna/delete" id="qnaDetailDeleteAnchor" value="삭제">
                        </form>

                    </div>



                </div>

            </div>
        </div>
        <div class="detailBodyDiv">
            <div class="detailText">
                    ${qnaVO.qna_text}
            </div>
        </div>
        <div class="answerDiv">
            <div class="answerText">

                <form>
                    <c:choose>
                        <c:when test="${qnaVO.qna_admin_answer eq 'y'}">
                            <h3>[답변 내용]</h3>
                            <input type="hidden" name="answer_id" value="  ${answerVO.answer_id}" readonly>
                            <input type="hidden" name="qna_id" value="  ${answerVO.qna_id}" readonly>
                            <div class="answerTextDateDiv">
                            <span class="answerTextDateDivInner">
                                <span class="answerSubjectSpan">등록일</span>
                                <span class="answerDateSpan">
                                    <fmt:formatDate value="${answerVO.answer_regdate}" pattern="yyyy/MM/dd HH:mm:ss"/>
                                </span>
                            </span>
                                <span class="dateDashSpan test">|</span>
                                <span class="answerTextDateDivInner">
                                <span class="answerSubjectSpan">수정일</span>
                                <span class="answerDateSpan"><fmt:formatDate value="${answerVO.answer_update_date}" pattern="yyyy/MM/dd HH:mm:ss"/>
                                </span>
                            </span>
                            </div>
                            <div class="answerTextDetailDiv"> ${answerVO.answer_text}</div>



                        </c:when>
                        <c:when test="${qnaVO.qna_admin_answer eq 'n'}">

                        </c:when>
                    </c:choose>
                    <input type="hidden" name="member_id" value="${qnaVO.member_id}">
                    <input type="hidden" name="qna_id" value="${qnaVO.qna_id}">
                </form>

            </div>
        </div>

    </div>
</c:if>

<div class="qnaImgContainer">
    <div>
        <i class="xi-close closeQnaImgContainer"></i>
    </div>
    <div class="qnaImgWrapDiv">
        <img src="${qnaVO.qna_picture_url}">
    </div>

</div>


<jsp:include page="../shop_footer.jsp"/>
<script src="/resources/js/shop/qna/qna_detail.js"></script>
</body>
</html>
