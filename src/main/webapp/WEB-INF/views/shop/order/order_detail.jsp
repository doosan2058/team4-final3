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
    <!-- 구글 폰트 -->
    <link rel="stylesheet"
          href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200"/>
    <title>주문 상세 페이지</title>
</head>
<body>
<%@include file="../shop_header.jsp" %>

<main>
    <form>

        <!--주문 상세 컨테이너-->
        <div class="orderContainer">
            <!--주소-->
            <div class="orderAddressDiv">
                <div class="orderAddressTop">
                    <h2>배송지</h2>
                </div>

                <div class="orderAddressBottom">
                    <div class="addressDiv">
                        <p>${list.member_postal_code },${list.member_address }</p>

                        <!-- 주소 -->
                        <input type="hidden" name="order_address"
                               value="${list.member_postal_code}, ${list.member_address}">

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
                                  rows="10" maxlength="500" placeholder="배송 메시지를 입력해 주세요."></textarea>


                    </div>
                </div>
            </div>
            <!--상품정보-->
            <div class="orderProductDiv">
                <div class="orderProductTop">
                    <h2>주문상품</h2>
                </div>
                <div class="orderProductBottom">
                    <div class="productImgDiv">
						<a href="/product/detail?product_id=${product.product_id}">
							<img src="/productImg/${product.product_thumbnail_img_url }" alt="이미지 준비중 입니다."
								 class="productImg">
						</a>

                    </div>
                    <div class="productDetail">
						<a href="/product/detail?product_id=${product.product_id}" class="productAnchor">
							<p class="productNameP">${product.product_name}</p>
						</a>
                        <p>
                            <fmt:formatNumber type="number" value="${product.product_price}"/>
                            원
                        </p>
                        <p>
                            평균
                            <span class="EmphasisSpan">${product.product_delivery_day }</span>
                            일 배송일
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
                                    <span class="material-symbols-outlined couponCancle"> delete </span>
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
                    <input type="submit" value="결제하기" class="formBtns" formaction="buy" formmethod="POST">
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

<script src="/resources/js/shop/order/order_detail.js"></script>
</body>
</html>