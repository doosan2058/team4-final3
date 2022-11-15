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
    <link rel="stylesheet" href="/resources/css/shop/qna/qna_list.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/xeicon@2/xeicon.min.css">
    <title>질문 페이지</title>
</head>
<body>
<jsp:include page="../shop_header.jsp"/>
<div class="qnaListContainer">
    <div class="qnaListHeader">
        <span>QnA</span>
        <p> - 궁금한 점을 물어보세요!</p>
    </div>
    <div class="qnaMeduDiv">
        <a class="writeQnaAnchor">글 작성</a>
        <a href="/qna/sample">
            자주 묻는 질문
        </a>
    </div>
    <div class="qnaListBodyDiv">
        <c:if test="${pageQna.totalCount == 0}">
            <p class="searchResultP">검색 결과가 없습니다.</p>
        </c:if>
        <c:if test="${pageQna.totalCount != 0}">
            <div class="qnaLineHeadDiv">
                <span>제목</span>
                <span>작성자</span>
                <span>작성일</span>
                <span>답변</span>
            </div>
            <c:forEach var="item" items="${list}">
                <div class="qnaLineDiv">
                    <input type="hidden" name="qna_public" value="${item.qna_public}">

                    <div class="qnaLineTitleDiv">
                        <span>${item.qna_id}</span>
                        <span>[${item.qna_category}]</span>

                        <c:choose>
                            <c:when test="${item.qna_public eq 'n' and sessionScope.login_id eq null}">
                                <span>비공개 게시글 입니다.</span>
                            </c:when>
                            <c:when test="${item.qna_public eq 'n' and sessionScope.login_id ne item.member_id}">
                                <span>비공개 게시글 입니다.</span>
                            </c:when>
                            <c:when test="${item.qna_public eq 'n' and sessionScope.login_id eq item.member_id}">
                                <span>${item.qna_title}</span>
                            </c:when>
                            <c:otherwise>
                                <span>${item.qna_title}</span>
                            </c:otherwise>
                        </c:choose>
                        <c:if test="${item.qna_picture_url ne 'not url'}">
                            <i class="xi-paperclip"></i>
                        </c:if>

                    </div>
                    <div class="qnaLineWriterDiv">
                        <span>${item.member_id}</span>
                    </div>
                    <div class="qnaLineDateDiv">
                        <span><fmt:formatDate value="${item.qna_regdate}" pattern="yyyy/MM/dd HH:mm" /></span>
                    </div>
                    <div class="qnaLineAnswerDiv">
                        <c:if test="${item.qna_admin_answer eq 'n'}">
                            <span>대기중</span>
                        </c:if>
                        <c:if test="${item.qna_admin_answer eq 'y'}">
                            <span>완료</span>
                        </c:if>
                    </div>
                </div>
            </c:forEach>
            <div class="qnaListSearchDiv">
                <form>
                    <select name="qna_category">
                        <option value="all">All</option>
                        <option value="배송">배송</option>
                        <option value="취소/환불/교환">취소/환불/교환</option>
                        <option value="회원">회원</option>
                        <option value="주문/결제">주문/결제</option>
                        <option value="기타">기타</option>
                    </select>
                    <input type="text" name="qnaSearchText" placeholder="검색어를 입력하세요.(5자 이하)" maxlength="5">
                    <input type="submit" value="검색" formaction="/qna" formmethod="get">
                </form>

            </div>
            <div class="qnaPageDiv">
                <input type="hidden"  value="${pageQna.clientPageNum}" id="clientPageNumHiddenInput">
                <div class="qnaPagePreDiv">
                    <c:if test="${pageQna.startPageBlock eq true}">
                        <a href="/qna?clientPageNum=1&qna_category=${pageQna.qna_category}&qnaSearchText=${pageQna.qnaSearchText}" style="color: #9b9b9b;">처음</a>
                    </c:if>
                    <c:if test="${pageQna.prePage eq true}">
                        <a href="/qna?clientPageNum=${pageQna.clientPageNum - 1}&qna_category=${pageQna.qna_category}&qnaSearchText=${pageQna.qnaSearchText}" style="color: #9b9b9b;">이전</a>
                    </c:if>
                </div>
                <div class="qnaPageCenterDiv">
                    <c:if test="${pageQna.currentBlockNum eq pageQna.totalBlock}">
                        <c:forEach var="i" begin="${pageQna.currentBlockNum * pageQna.blockCount - 9}" end="${pageQna.totalPage}">
                            <a href="/qna?clientPageNum=${i}&qna_category=${pageQna.qna_category}&qnaSearchText=${pageQna.qnaSearchText}" class="qnaPageAnchor">${i}</a>
                        </c:forEach>
                    </c:if>
                    <c:if test="${pageQna.currentBlockNum ne pageQna.totalBlock}">
                        <c:forEach var="i" begin="${pageQna.currentBlockNum * pageQna.blockCount - 9}" end="${pageQna.currentBlockNum * pageQna.blockCount}">
                            <a href="/qna?clientPageNum=${i}&qna_category=${pageQna.qna_category}&qnaSearchText=${pageQna.qnaSearchText}" class="qnaPageAnchor">${i}</a>
                        </c:forEach>
                    </c:if>
                </div>
                <div class="qnaPageNextDiv">
                    <c:if test="${pageQna.nextPage eq true}">
                        <a href="/qna?clientPageNum=${pageQna.clientPageNum + 1}&qna_category=${pageQna.qna_category}&qnaSearchText=${pageQna.qnaSearchText}" style="color: #9b9b9b;">다음</a>
                    </c:if>
                    <c:if test="${pageQna.endPageBlock eq true}">
                        <a href="/qna?clientPageNum=${pageQna.totalPage}&qna_category=${pageQna.qna_category}&qnaSearchText=${pageQna.qnaSearchText}" style="color: #9b9b9b;">끝</a>
                    </c:if>
                </div>


            </div>

        </c:if>
    </div>

</div>


<jsp:include page="../shop_footer.jsp"/>
<script src="/resources/js/shop/qna/qna_list.js"></script>
</body>
</html>
