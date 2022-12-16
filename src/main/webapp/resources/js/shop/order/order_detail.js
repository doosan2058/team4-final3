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
const orderTextLenghSpan = document.querySelector('.orderTextLenghSpan');
//주소 검색 버튼
const addressSearchBtn = document.querySelector('#addressSearchBtn');
// 주소 직접 입력 라디오
const addressWriteRadio = document.querySelector('#addressWriteRadio');
// 기본 주소 사용 라디오
const addressRadio = document.querySelector('#addressRadio');
// 배송지 인풋
const order_address = document.querySelector('#order_address');
// 서브밋 버튼
const submitBtn = document.querySelector('#submitBtn');
const postcode = document.querySelector('#postcode');
const roadAddress = document.querySelector('#roadAddress');
const extraAddress = document.querySelector('#extraAddress');
const detailAddress = document.querySelector('#detailAddress');
// ================================================================================================
window.addEventListener('load', setTotalPrice);
couponUseCon.addEventListener('click', closeCouponCon);
insertCouponBtn.addEventListener('click', openCouponCon);
requestSelect.addEventListener('change', setOrderMassage);
useCouponBtn.addEventListener('click', useCoupon);
couponCancle.addEventListener('click', cancelCouponUse);
requestTextarea.addEventListener('keyup', changeTextLength);
addressSearchBtn.addEventListener('click', searchAddress);
addressRadio.addEventListener('change', initAddress);
submitBtn.addEventListener('click', checkAddressForm);
// ==================================================================================================

// 서브밋 전 양식 체크 함수
function checkAddressForm() {

    if (addressWriteRadio.checked == true && postcode.value.length == 0) {
        alert('주소란을 작성해 주세요.');
    } else {
        order_address.value = `${postcode.value}, ${roadAddress.value} ${extraAddress.value} ${detailAddress.value}`;
        document.querySelector('#orderForm').submit();
    }
}

// 배송지 설정 함수
function initAddress() {
    if (this.checked) { // 기본 주소지 사용 체크
        order_address.value = document.querySelector('#basicAddressP').innerHTML;
        // 직접 주소 입력 값 초기화
        postcode.value = '';
        roadAddress.value = '';
        extraAddress.value = '';
        detailAddress.value = '';
    }
}

// 다음 주소 검색 함수
function searchAddress() {

    // 직접 입력 체크 시에만 주소 검색 작동
    if (addressWriteRadio.checked) {
        daumSearchAddress();
    }
    else{ // 기본 주소지 사용 체크 상태이면서 주소 찾기 버튼 클릭시
        const result = confirm('주소지를 직접 입력하시겠습니까?');
        if(result){
            addressWriteRadio.checked = true;
            daumSearchAddress();
        }
    }

}

// 다음 우편번호 주소 검색차 띄우기
function daumSearchAddress(){
    new daum.Postcode({
        oncomplete: function (data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
            // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            let roadAddr = data.roadAddress; // 도로명 주소 변수
            let extraRoadAddr = ''; // 참고 항목 변수

            // 법정동명이 있을 경우 추가한다. (법정리는 제외)
            // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
            if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
                extraRoadAddr += data.bname;
            }
            // 건물명이 있고, 공동주택일 경우 추가한다.
            if (data.buildingName !== '' && data.apartment === 'Y') {
                extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
            }
            // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
            if (extraRoadAddr !== '') {
                extraRoadAddr = ' (' + extraRoadAddr + ')';
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('postcode').value = data.zonecode;
            document.getElementById("roadAddress").value = roadAddr;
            // 참고항목 문자열이 있을 경우 해당 필드에 넣는다.
            if (roadAddr !== '') {
                document.getElementById("extraAddress").value = extraRoadAddr;
            } else {
                document.getElementById("extraAddress").value = '';
            }

            let guideTextBox = document.getElementById("guide");
            // 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
            if (data.autoRoadAddress) {
                let expRoadAddr = data.autoRoadAddress + extraRoadAddr;
                guideTextBox.innerHTML = '(예상 도로명 주소 : ' + expRoadAddr + ')';
                guideTextBox.style.display = 'block';

            } else if (data.autoJibunAddress) {
                let expJibunAddr = data.autoJibunAddress;
                guideTextBox.innerHTML = '(예상 지번 주소 : ' + expJibunAddr + ')';
                guideTextBox.style.display = 'block';
            } else {
                guideTextBox.innerHTML = '';
                guideTextBox.style.display = 'none';
            }
        }
    }).open();
}

function changeTextLength() {
    const textLength = requestTextarea.value.length;
    orderTextLenghSpan.innerHTML = `(${textLength}/500)`;
}

//최종 가격 화면에 표시하는 함수
function setTotalPrice() {
    resultPriceSpan.innerHTML = priceToString((parseInt(order_purchase_amount_before.value) - parseInt(discount.value) - parseInt(couponPriceHidden.value)));
    order_purchase_amount.value = ((resultPriceSpan.innerHTML).replace(',', '')).trim();
}

//숫자 정규식 변환 함수
function priceToString(price) {
    couponPriceHidden.value = price;
    return price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
}

//사용한 쿠폰 취소하기 함수
function cancelCouponUse() {
    const result = confirm("할인 쿠폰 적용을 취소 하시겠습니까?");
    if (result) {
        //할인 금액 초기화
        couponPrice.innerHTML = '0';
        couponInfoDiv.style.display = 'none';
        couponPriceHidden.value = '0';
        useCouponNum.value = 'no use coupon';
        insertCouponBtn.style.display = 'block';
        setTotalPrice();
    }
    ;

}


//사용가능 쿠폰인지 체크하기 비동기 전송 함수
function useCoupon() {
    //쿠폰번호
    let param = {
        coupon_num: useCouponText.value,
        product_category_id: productCategory.value,
        product_brand_id: productBrand.value
    }
    //쿠폰번호 서버로 전송
    $.ajax({
        type: 'post',
        dataType: 'json',
        contentType: 'application/json; charset=utf-8',
        url: '/order/coupon',
        data: JSON.stringify(param),
        error: function () {
            alert('죄송합니다. 잠시후 다시 시도해 주세요.');
        },
        success: function (data) {
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
            if (data.coupon) {
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

                couponInfoDiv.style.display = 'flex';
                //버튼 숨기기
                insertCouponBtn.style.display = 'none';
                setTotalPrice();
            }
        }
    });

}

//배송 메시지 셀렉트 박스 값으로 세팅하는 함수
function setOrderMassage() {
    const msg = this.selectedOptions[0].innerHTML;
    if (msg == '배송 메시지를 선택해 주세요.')
        requestTextarea.value = '';
    else
        requestTextarea.value = msg;

    changeTextLength();

}

/*쿠폰 컨테이너 닫는 함수 */
function closeCouponCon(e) {
    if (e.target.className === 'couponUseCon') {
        couponUseCon.children[0].style.animation = 'hiddenModal 0.3s 1 forwards';
        setTimeout(function () {
            couponUseCon.style.display = 'none';
        }, 301);

    }
}

/*쿠폰 컨테이너 여는 함수 */
function openCouponCon() {
    couponUseCon.style.display = 'block'
    couponUseCon.children[0].style.animation = 'showModal 0.3s 1 forwards';
}