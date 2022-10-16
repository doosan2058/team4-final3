'use strict'

/*쿠폰사용 컨테이너 */
const couponUseCon = document.querySelector('.couponUseCon');
/*쿠폰 컨테이너 활성화 버튼 */
const insertCouponBtn = document.querySelector('#insertCouponBtn');
/*배송 메세지 셀렉트 */
const requestSelect = document.querySelector('#requestSelect');
//배송 메세지 텍스트박스
const requestTextarea = document.querySelector('#requestTextarea');
//쿠폰 사용하기 버튼
const useCouponBtn = document.querySelector('#useCouponBtn');
//쿠폰 번호 입력 텍스트
const useCouponText = document.querySelector('#useCouponText');
//상품 카테고리 히든 인풋
const productCategory = document.querySelector('#productCategory');
//상품 브랜드 히든 인풋
const productBrand = document.querySelector('#productBrand');
//쿠폰 할인 금액 인풋
const couponPrice = document.querySelector('#couponPrice');
//할인전 상품 금액 히든 인풋
const order_purchase_amount_before = document.querySelector('#order_purchase_amount_before');
//쿠폰 정보 구역
const couponInfoDiv = document.querySelector('.couponInfoDiv');
//쿠폰 지우기 스팬
const couponCancle = document.querySelector('.couponCancle');
//최종 가격 스팬
const resultPriceSpan = document.querySelector('#resultPriceSpan');
//등급 할인 금앱 스팬
const discount = document.querySelector('#discount');
//쿠폰 할인 금액 히든 인풋
const couponPriceHidden = document.querySelector('#couponPriceHidden');
//사용한 쿠폰 번호
const useCouponNum = document.querySelector('#useCouponNum');
//최종 결제 금액
const order_purchase_amount = document.querySelector('#order_purchase_amount');
//================================================================================================
couponUseCon.addEventListener('click' , closeCouponCon);
insertCouponBtn.addEventListener('click' , openCouponCon);
requestSelect.addEventListener('change' , setOrderMassage);
useCouponBtn.addEventListener('click' , useCoupon);
couponCancle.addEventListener('click' , cancelCouponUse);
//==================================================================================================

window.onload = function(){
	setTotalPrice();
}

//최종 가격 화면에 표시하는 함수
function setTotalPrice(){
	
	resultPriceSpan.innerHTML = priceToString( (parseInt(order_purchase_amount_before.value) - parseInt(discount.value) - parseInt(couponPriceHidden.value)) );
	order_purchase_amount.value = ((resultPriceSpan.innerHTML).replace(',','')).trim();
}

//숫자 정규식 변환 함수
function priceToString(price) {
	couponPriceHidden.value = price;
    return price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
}

//사용한 쿠폰 취소하기 함수
function cancelCouponUse(){
	const result = confirm("할인 쿠폰 적용을 취소 하시겠습니까?");
		

	
	if(result){
		//할인 금액 초기화
		couponPrice.innerHTML = '0';
		couponInfoDiv.style.display = 'none';
		couponPriceHidden.value = '0';
		useCouponNum.value = 'no use coupon';
		setTotalPrice();
	};
	
	insertCouponBtn.style.display = 'block';
	//인풋 쿠폰 번호 확인
//	console.log(useCouponNum.value);
	 	
}


//사용가능 쿠폰인지 체크하기 비동기 전송 함수
function useCoupon(){
	//쿠폰번호
	let param = { 
					coupon_num : useCouponText.value,
					product_category_id : productCategory.value,
					product_brand_id : productBrand.value
				}
	//쿠폰번호 서버로 전송
	$.ajax({
		type: 'post',
		dataType: 'json',
		contentType : 'application/json; charset=utf-8',
		url: '/order/coupon',
		data: JSON.stringify(param),
		error: function(){
			alert('죄송합니다. 잠시후 다시 시도해 주세요.');
		},
		success: function(data){
			//서버에서 쿠폰 결과 처리후 텍스트 리턴
			// 1. 사용 가능한 쿠폰입니다.
			// 2. 이미 사용된 쿠폰입니다.
			// 3. 사용기한이 만료된 쿠폰입니다.
			// 4. 쿠폰 번호를 잘못 입력 하였습니다.
			// 5. 이 상품에는 사용할 수 없는 쿠폰 입니다.
			
			//결과 띄우기
			//data.result : 쿠폰 사용 결과
			//data.coupon : 쿠폰 정보
			alert(data.result);
			
			
			//쿠폰이 존재하면 할인 금액 띄우기
			if(data.coupon){
				//쿠폰 컨테이너 닫기
				couponUseCon.style.display = 'none';
				
				//상품 금액 * 개수 금액
				let price = order_purchase_amount_before.value;
				//쿠폰 할인 금액
				let resultCouponPrice = Math.ceil(data.coupon.coupon_discount * price);
				//금액 정규식 변환
				couponPrice.innerHTML = priceToString(resultCouponPrice);
				//사용할 쿠폰 번호 히든인풋 저장
				useCouponNum.value = data.coupon.coupon_num;
				//인풋 쿠폰 번호 확인
//				console.log(useCouponNum.value);
				couponInfoDiv.style.display = 'flex';
				//버튼 숨기기
				insertCouponBtn.style.display = 'none';
				setTotalPrice();
			}
		}
	});
	
}

//배송 메시지 셀렉트 박스 값으로 세팅하는 함수
function setOrderMassage(){
	const msg = this.selectedOptions[0].innerHTML;
	if(msg == '배송 메시지를 선택해 주세요.')
		return;
	requestTextarea.value = msg;
	
}

/*쿠폰 컨테이너 닫는 함수 */
function closeCouponCon(e){
    if(e.target.className === 'couponUseCon')
        this.style.display = 'none';
}
/*쿠폰 컨테이너 여는 함수 */
function openCouponCon(){
    couponUseCon.style.display = 'block'
}