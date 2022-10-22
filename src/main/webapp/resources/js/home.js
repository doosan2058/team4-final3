/**
 * home
 */

'use strict'
//쇼핑몰 구역
const shopDiv = document.querySelector('.shopDiv');
//커뮤니티 구역
const communityDiv = document.querySelector('.communityDiv');
//로그인 앵커
const loginBtn = document.querySelector('#loginBtn');
//로그아웃 앵커
const logOutBtn = document.querySelector('#logOutBtn');
//회원가입 앵커
const joinBtn = document.querySelector('#joinBtn');
//로그인 체크 히든 스팬
const loginCheckHiddenInput = document.querySelector('#loginCheckHiddenInput');
//로그인 권한 스펜
const authCheckHiddenInput = document.querySelector('#authCheckHiddenInput');
//메뉴 설명 앵커
const headerAnchorTextSpan = document.querySelectorAll('.headerAnchorTextSpan');
// ===============================================================================
window.addEventListener('load', mediaQueryHome);
window.addEventListener('load', initHome);
window.addEventListener('resize', mediaQueryHome);

logOutBtn.addEventListener('click', doLogOut);
shopDiv.addEventListener('click', goShop);
communityDiv.addEventListener('click', goCommunity);


// ===============================================================================
function initHome(){
	//로그인
	if(authCheckHiddenInput.value == '회원' || authCheckHiddenInput.value == '관리자'){
		loginBtn.style.display = 'none';
		logOutBtn.style.display = 'flex';
		joinBtn.style.display = 'none';
	}
	else{
		loginBtn.style.display = 'flex';
		logOutBtn.style.display = 'none';
		joinBtn.style.display = 'flex';
	}
}

//쇼핑몰 가기
function goShop() {
    location.href = '/shop';
}

//커뮤니티 가기
function goCommunity() {
    location.href = '/community/main';
}

//로그아웃 함수
function doLogOut() {
    const result = confirm('로그아웃 하시겠습니까?');
    if (result) {
        location.href = '/logout';
    }
}

// ===================================================================================

function mediaQueryHome(){

	// 스마트폰
	if (matchMedia("screen and (min-width:320px)").matches && matchMedia("screen and (max-width:767px)").matches) {
		headerAnchorTextSpan.forEach((item) => {
			item.style.display = 'none';
		});

	}
	// 태블릿
	if (matchMedia("screen and (min-width:768px)").matches && matchMedia("screen and (max-width:1023px)").matches) {
		headerAnchorTextSpan.forEach((item) => {
			item.style.display = 'none';
		});

	}
	// 데스크탑
	if (matchMedia("screen and (min-width:1024px)").matches) {
		headerAnchorTextSpan.forEach((item) => {
			item.style.display = 'block';
		});
	}
}



