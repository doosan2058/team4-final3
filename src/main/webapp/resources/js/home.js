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


// ===============================================================================
window.addEventListener('load', mediaQueryHome);
window.addEventListener('resize', mediaQueryHome);

logOutBtn.addEventListener('click', doLogOut);
shopDiv.addEventListener('click', goShop);
communityDiv.addEventListener('click', goCommunity);


// ===============================================================================

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

	if (matchMedia("screen and (min-width:320px)").matches && matchMedia("screen and (max-width:767px)").matches) {
		// 스마트폰
		loginBtn.innerHTML = '<span class="material-symbols-outlined">login</span>';
		logOutBtn.innerHTML = '<span class="material-symbols-outlined">logout</span>';
		joinBtn.innerHTML = '<span class="material-symbols-outlined">person_add </span>';
	}
	if (matchMedia("screen and (min-width:768px)").matches && matchMedia("screen and (max-width:1023px)").matches) {
		// 태블릿
		loginBtn.innerHTML = '로그인';
		logOutBtn.innerHTML = '로그아웃';
		joinBtn.innerHTML = '회원가입';
	}
	if (matchMedia("screen and (min-width:1024px)").matches) {
		// 데스크탑
		loginBtn.innerHTML = '로그인';
		logOutBtn.innerHTML = '로그아웃';
		joinBtn.innerHTML = '회원가입';
	}
}



