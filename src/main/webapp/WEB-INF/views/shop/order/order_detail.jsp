<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- css -->
    <link rel="stylesheet" href="/resources/css/shop/order/order_detail.css">
    <!-- 글로벌 css -->
    <link rel="stylesheet" href="/resources/css/global.css">
    <!-- 제이쿼리 cdn -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <!--xeicon-->
    <link rel="stylesheet" href="//cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css">
    <title>주문 상세 페이지</title>
</head>
<body>
<%@include file="../shop_header.jsp" %>

<main>
    <form action="/buy" method="post" id="orderForm">
        <!--주문 상세 컨테이너-->
        <div class="orderContainer">
            <!--주소-->
            <div class="orderAddressDiv">
                <div class="orderAddressTop">
                    <h2>배송지</h2>
                </div>

                <div class="orderAddressBottom">
                    <div class="addressSelectDiv">
                        <input type="radio" id="addressRadio" name="addressRadio" checked>
                        <label for="addressRadio" class="addressRadioLabel">기본 주소지</label>
                    </div>
                    <div class="addressDiv">
                        <p id="basicAddressP">${list.member_postal_code },${list.member_address }</p>
                    </div>
                    <div class="addressWriteSelectDiv">
                        <input type="radio" id="addressWriteRadio" name="addressRadio">
                        <label for="addressWriteRadio" class="addressRadioLabel">직접 입력</label>
                    </div>
                    <div class="addressWriteDiv">
                        <input type="button" value="우편번호 찾기" id="addressSearchBtn" class="formBtns">
                        <input type="text" id="postcode" placeholder="우편번호" class="formInputs" name="member_postal_code" readonly>
                        <input type="text" id="roadAddress" placeholder="도로명주소" class="formInputs addressInputs" readonly>
                        <input type="text" id="extraAddress" placeholder="참고항목" class="formInputs addressInputs" readonly>
                        <input type="text" id="detailAddress" placeholder="상세주소" class="formInputs addressInputs">
                        <span id="guide" style="color: #999; display: none"></span>
                    </div>
                    <div class="requestDiv">
                        <select id="requestSelect" class="orderInputs orderSelect">
                            <option value="" selected>배송 메시지를 선택해 주세요.</option>
                            <option value="">배송전 연락 바랍니다.</option>
                            <option value="">부재시 경비실에 맡겨주세요.</option>
                            <option value="">부재시 연락주세요.</option>

                        </select>
                        <!-- 메시지 -->
                        <textarea name="order_comment" class="orderInputs orderText" id="requestTextarea" cols="30"
                                  rows="10" maxlength="500" ></textarea>
                        <span class="orderTextLenghSpan">(0/500)</span>
                    </div>
                </div>
                <!-- 주소 -->
                <input type="hidden" name="order_address" id="order_address"
                       value="${list.member_postal_code}, ${list.member_address}">
            </div>
            <!--상품정보-->
            <div class="orderProductDiv">
                <div class="orderProductTop">
                    <h2>주문상품</h2>
                </div>
                <div class="orderProductBottom">
                    <div class="productImgDiv">
						<a href="/product/detail?product_id=${product.product_id}">
							<img src="${product.product_thumbnail_img_url }" alt="이미지 준비중 입니다."
								 class="productImg">
						</a>

                    </div>
                    <div class="productDetail">
						<a href="/product/detail?product_id=${product.product_id}" class="productAnchor">
							${product.product_name}
						</a>
                        <p>
                            <fmt:formatNumber type="number" value="${product.product_price}"/>
                            원
                        </p>
                        <p>
                            평균
                            <span class="EmphasisSpan">${product.product_delivery_day }</span>
                            일 배송
                        </p>
                        <input type="hidden" id="productCategory" value="${product.product_category_id }">
                        <input type="hidden" id="productBrand" value="${product.product_brand_id }">
                        <input type="hidden" name="product_id" value="${product.product_id}">

                    </div>
                </div>
            </div>
            <!--결제정보-->
            <div class="orderPriceDiv">
                <div class="orderPriceTop">
                    <h2>할인/적립</h2>
                </div>
                <div class="orderPriceBottom">
                    <table>
                        <tr>
                            <td class="tdLeft">
								<p>

									<span class="productQuantitySpan">${order_quantity }</span>
									 개
									<input type="hidden" name="order_quantity" value="${order_quantity }">
									금액
								</p>

                            </td>
                            <td class="tdRight">
								<p>
									<fmt:formatNumber value="${product.product_price * order_quantity }" type="number"/>
									<input type="hidden" value="${product.product_price * order_quantity }"
										   id="order_purchase_amount_before">
									원
								</p>

                            </td>
                        </tr>


                        <tr>
                            <td class="tdLeft">
								<p>
									<span class="gradeNameSpan">${grade.grade_name }</span>
									 등급
								</p>

                            </td>
                            <td class="tdRight">
								<p>
									-
									<span class="discountSpan">
										<fmt:formatNumber type="number" minFractionDigits="0"
														  value="${grade.grade_discount * product.product_price }"/>
										<input type="hidden" value="${grade.grade_discount * product.product_price }"
											   id="discount" name="discount">
									</span>
									원
								</p>

                            </td>
                        </tr>
                        <tr>
                            <td class="tdLeft">
								<p>
									적립포인트
								</p>
							</td>
                            <td class="tdRight">
								<p>
									+
									<fmt:formatNumber type="number" minFractionDigits="0"
													  value="${grade.grade_accrual_rate * product.product_price }"/>
									<input type="hidden" name="getPoint"
										   value="${grade.grade_accrual_rate * product.product_price }">
									P
								</p>

                            </td>
                        </tr>
                        <tr>
                            <td class="tdLeft">
								<p>
									쿠폰할인
								</p>
								<input type="hidden" name="">
                            </td>
                            <td class="tdRight">
                                <!-- 쿠폰 정도 -->
                                <div class="couponInfoDiv">
                                    -&nbsp;
                                    <span id="couponPrice" class="discountSpan">0</span>
                                    <input type="hidden" id="couponPriceHidden" value="0">
                                    원
                                    <span class="xi-trash-o couponCancle"></span>
                                    <input type="hidden" name="order_coupon_num" value="no use coupon"
                                           id="useCouponNum">
                                </div>
                                <input type="button" value="쿠폰번호입력" class="formBtns" id="insertCouponBtn">

                            </td>
                        </tr>

                        <tr>
                            <td class="tdLeft">
								<p>
									결제금액
								</p>
							</td>
                            <td class="tdRight">
								<p>
									<span id="resultPriceSpan">0</span>
									원
								</p>

                            </td>
                            <input type="hidden" name="order_purchase_amount" id="order_purchase_amount">
                            <!--form 전송 데이터-->
                        </tr>
                    </table>
                </div>
            </div>
            <!--결제하기버튼-->
            <div class="orderPaymentDiv">
                <div class="orderPaymentTop">
                    <h2>계산</h2>
                </div>
                <div class="orderPaymentBottom">
                    <input type="button" value="결제하기" class="formBtns" id="submitBtn">
                </div>
            </div>
        </div>
    </form>
    <!--쿠폰사용컨테이너-->
    <div class="couponUseCon">
        <div class="couponDiv">
            <input type="text" class="formInputs" id="useCouponText" maxlength="16" placeholder="Use Coupon">
            <button id="useCouponBtn" class="formBtns">사용하기</button>
        </div>
    </div>
</main>

<%@include file="../shop_footer.jsp" %>
<!--카카오 주소찾기 api-->
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="/resources/js/shop/order/order_detail.js"></script>
</body>
</html>