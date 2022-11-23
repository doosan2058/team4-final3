<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!--글로벌 css-->
    <link rel="stylesheet" href="/resources/css/global.css">
    <link rel="stylesheet" href="/resources/css/admin/detail.css">
    <!-- 구글 폰트 -->
    <link rel="stylesheet"
          href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200"/>
    <!-- Jquery cdn -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <!-- 구글 폰트 -->
    <link rel="stylesheet"
          href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200"/>
    <!-- xicon -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/xeicon@2/xeicon.min.css">
    <title>전체 회원 목록 페이지</title>
</head>
<body>
<div class="detailCon">
    <!-- 상단 -->
    <div class="innerTop">
        <div class="topLeft">
            <div class="profileImgWrapDiv">
                <img alt="" src="${member.member_profile_img_url }" class="profileImg">
            </div>


        </div>
        <div class="topRight">
            <span class="subjectSpan">아이디</span>
            <p>${member.member_id }</p>
            <span class="subjectSpan">이메일</span>
            <p>${member.member_email }</p>
            <span class="subjectSpan">이름</span>
            <p>${member.member_name }</p>
            <span class="subjectSpan">휴대폰</span>
            <p>${member.member_phone }</p>
            <span class="subjectSpan">나이</span>
            <p>${member.member_age }</p>
            <span class="subjectSpan">성별</span>
            <p>${member.member_gender }</p>
            <span class="subjectSpan">주소</span>
            <p>${member.member_postal_code },${member.member_address }</p>


            <i class="xi-caret-down arrowIcon"></i>
        </div>


    </div>
    <!-- 하단 -->
    <div class="innerBottom">
        <div class="bottomLeft">
            <span class="subjectSpan">가입 날짜</span>
            <p><fmt:formatDate value="${member.member_join_date }" pattern="yyyy-MM-dd hh:mm:ss"/></p>
            <span class="subjectSpan">마지막 로그인 날짜</span>
            <p><fmt:formatDate value="${member.member_last_login_date}" pattern="yyyy-MM-dd hh:mm:ss"/></p>

            <span class="subjectSpan">회원 상태</span>
            <c:if test="${member.member_state eq '정상'}">
                <p>정상 계정</p>
            </c:if>
            <c:if test="${member.member_state eq '휴면'}">
                <p>휴면 계정</p>
            </c:if>

            <span class="subjectSpan">탈퇴 여부</span>
            <c:if test="${member.member_withdrawal eq 'n'}">
                <p>가입 계정</p>
            </c:if>
            <c:if test="${member.member_withdrawal eq 'y'}">
                <p>탈퇴 계정</p>
            </c:if>

            <span class="subjectSpan">이메일 수신 동의 여부</span>
            <c:if test="${member.member_email_agree eq 'n'}">
                <p>수신 거부</p>
            </c:if>
            <c:if test="${member.member_email_agree eq 'y'}">
                <p>수신 동의</p>
            </c:if>

            <span class="subjectSpan">회원 등급</span>
            <p>${member.grade_name }</p>
			<span class="subjectSpan">구매 포인트</span>
            <p>${member.member_purchase_point } p</p>
            <span class="subjectSpan">작성 포인트</span>
            <p>${member.member_write_point } p</p>
            <span class="subjectSpan">로그인 실패 횟수</span>
            <p>${member.member_login_count } 번</p>
            <span class="subjectSpan">로그인 제한 일</span>
            <c:if test="${empty member.member_off_limits_date}">
                <p>없습니다.</p>
            </c:if>
            <c:if test="${!empty member.member_off_limits_date}">
                <p><fmt:formatDate value="${member.member_off_limits_date }" pattern="yyyy-MM-dd hh:mm:ss"/></p>
            </c:if>

            <span class="subjectSpan">비밀번호 초기화 여부</span>
            <c:if test="${member.member_init_pw eq 'y'}">
                <p>재설정이 필요한 회원입니다.</p>
            </c:if>
            <c:if test="${member.member_init_pw eq 'n'}">
                <p>재설정 완료 회원입니다.</p>
            </c:if>


        </div>
        <div class="bottomRight">
            <!-- 자유 -->
            <div class="freeBoard">
                <h2>자유 게시판 글목록</h2>
                <c:if test="${empty freeMapList }">
                    <p>작성한 게시글이 없습니다.</p>
                </c:if>

                <c:if test="${!empty freeMapList }">
                    <c:forEach var="item" items="${freeMapList }">
                        <div class="lineDiv">
                            <span class="idxSpan">${item.board_id }</span>
							<a href="/community/freeNotice/detail_user?board_id=${item.board_id}">
								<span class="titleSpan">${item.board_title }</span>
							</a>

                        </div>
					</c:forEach>
                </c:if>

            </div>
            <!-- 캠핑 -->
            <div class="camping">
                <h2>캠핑 게시판 글목록</h2>
				<c:if test="${empty campingMapList }">
                    <p>작성한 게시글이 없습니다.</p>
                </c:if>
                <c:if test="${!empty campingMapList }">
                    <c:forEach var="item" items="${campingMapList }">
                        <div class="lineDiv">
                            <span class="idxSpan">${item.camping_id }</span>
							<a href="/community/freeNotice/detail_user?board_id=${item.camping_id }">
								<span class="titleSpan">${item.camping_title }</span>
							</a>

                        </div>
                    </c:forEach>
                </c:if>


            </div>
            <!-- 리뷰 -->
            <div class="qna">
                <h2>질문 글목록</h2>
                <c:if test="${empty qnaMapList }">
                    <p>작성한 게시글이 없습니다.</p>
                </c:if>
                <c:if test="${!empty qnaMapList }">
                    <c:forEach var="item" items="${qnaMapList }">
                        <div class="lineDiv">
                            <input type="hidden" value="${item.qna_delete}">
                            <span class="idxSpan">${item.qna_id }</span>
                            <c:choose>
                                <c:when test="${item.qna_delete eq 'y'}">
                                    <span class="titleSpan">작성자에 의해 삭제된 게시글 입니다.</span>
                                </c:when>
                                <c:when test="${item.qna_delete eq 'n'}">
                                    <a href="/qna/detail/admin?qna_id=${item.qna_id }">
                                        <span class="titleSpan">${item.qna_title }</span>
                                    </a>
                                </c:when>
                            </c:choose>
                            <c:if test="${item.qna_picture_url ne 'not url'}">
                                <i class="xi-paperclip"></i>
                            </c:if>
                        </div>
                    </c:forEach>
                </c:if>

            </div>
            <!-- 구매 -->
            <div class="buy">
                <h2>구매 목록</h2>
                <c:if test="${empty orderMapList }">
                    <p>구매한 상품이 없습니다.</p>
                </c:if>
                <c:if test="${!empty orderMapList }">
                    <c:forEach var="item" items="${orderMapList }">
                        <div class="lineDiv">
                            <span class="idxSpan">${item.order_id }</span>
                            <span class="titleSpan">${item.product_name }</span>
                            <span class="titleSpan">${item.order_state }</span>
                        </div>
                    </c:forEach>
                </c:if>

            </div>
        </div>

    </div>
</div>


<script src="/resources/js/admin/detail.js"></script>
</body>
</html>