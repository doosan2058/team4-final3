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

    <link rel="stylesheet" href="/resources/css/shop/qna/asked_Question.css">

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/xeicon@2/xeicon.min.css">
    <title>Document</title>
</head>

<body>
<%@include file="../shop_header.jsp" %>
<main>
    <div class="asked_Question_Title">
        <h1>자주 묻는 질문</h1>
    </div>

    <div class="asked_Question_Main">
        <div class="asked_Question_accordion">
            <p>Q. 주문 취소 처리 후, 환불까지 얼마나 소요되나요?</p>
        </div>
        <div class="asked_Question_accordion_panel">
            <p>A. 결제 유형에 따른 환불 소요 기간은 다음과 같습니다.</p>
            <p>(1) 카드 결제 : 카드매출 취소는 즉시 접수되나, 카드사 사정에 따라 매출취소가 이루어지기 까지 약 3~7영업일 정도 소요됩니다.</p>
            <p>(2) 할인쿠폰 : 주문 후 취소, 반품하신 경우 이미 사용하신 쿠폰은 복구되어 재사용 하실 수 있습니다. (단, 쿠폰의 유효기간이 경과한 경우에는 재사용이 불가능합니다.)</p>
            <p>(3) 피해 보상 규정 : 피해보상기준은 소비자피해보상규정(재정경제부 고시)을 적용합니다.</p>
        </div>
    </div>

    <div class="asked_Question_Main">
        <div class="asked_Question_accordion">
            <p>Q. 탈퇴할 때 사용했던 아이디로 재가입이 가능한가요?</p>
        </div>
        <div class="asked_Question_accordion_panel">
            <p>A. 탈퇴 후 재가입 시에는 새로운 아이디를 사용하셔야 합니다.</p>
        </div>
    </div>

    <div class="asked_Question_Main">
        <div class="asked_Question_accordion">
            <p>Q. 상품을 반송할 때 주의사항은 무엇인가요?</p>
        </div>
        <div class="asked_Question_accordion_panel">
            <p>A. 회수방법에 따른 주의사항</p>
            <p>(1) 자동회수 : 택배기사가 반품회수를 위해 2-3일이내 회수지로 방문할 예정</p>
            <p>(2) 직접반송 : 원송장을 통한 착불반송 or 타택배 선불반송 中 선택 후 반송</p>
            <p>- 원송장통한 착불반송은 택배사 APP, WEB, ARS 자동접수 후 취소 시 재접수 불가하니 택배사 상담원통해 유선으로 재접수바랍니다.</p>
            <p>- 택배사 정책 및 사정에 따른 문제는 더블유컨셉과는 무관하오니 택배사로 컨택하여 확인부탁드립니다.</p>
            <p>- 반송 후 반송장번호는 필히 소지하시기 바랍니다.</p>
        </div>
    </div>

    <div class="asked_Question_Main">
        <div class="asked_Question_accordion">
            <p>Q. 회원가입의 제한이 있나요?</p>
        </div>
        <div class="asked_Question_accordion_panel">
            <p>A. 회원가입시,별도의 조건은 없으며 무료인 점 참고 바랍니다.</p>
        </div>
    </div>
    <div class="asked_Question_Main">
        <div class="asked_Question_accordion">
            <p>Q. 배송 사고(파손,상품&구성품 누락, 분실)는 어떻게 처리 해야 하나요?</p>
        </div>
        <div class="asked_Question_accordion_panel">
            <p>A. 배송사고 처리 방법</p>
            <p>(1) 상품파손 : 택배 박스, 제품 포장재 등을 받은 상태 그대로 보관 후 1:1문의로 사진과 함께 접수해 주세요.</p>
            <p>(2) 상품분실 : 제품 내용을 받은 상태 그대로 보관 후 1:1문의로 사진과 함께 접수해 주세요.</p>
            <p>(3) 상품분실 : 분실했다는 택배사 연락을 받았다면 해당 문자를 캡처 후 1:1문의로 사진과 함께 접수해 주세요.</p>
        </div>
    </div>
    <div class="null">
    </div>
</main>

<%@include file="../shop_footer.jsp" %>
<script src="/resources/js/shop/qna/asked_question.js"></script>
</body>

</html>