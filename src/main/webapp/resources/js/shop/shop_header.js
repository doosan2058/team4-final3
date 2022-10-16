/**
 * shop header
 */

'use strict'

//로그아웃 앵커
const logOutBtn = document.querySelector('#logOutBtn');
//로그인 앵커
const loginBtn = document.querySelector('#loginBtn');
//회원가입 앵커
const joinBtn = document.querySelector('#joinBtn');
//로고
const logo = document.querySelector('.logo');
//장바구니
const basket = document.querySelector('#basket');
//내정보
const loginName = document.querySelector('#loginName');
//툴팁 박스
const headerToolTipBox = document.querySelector('.headerToolTipBox');
// ===============================================================================

window.addEventListener('load', mediaQuery);
window.addEventListener('resize', mediaQuery);



logOutBtn.addEventListener('click', doLogOut);
logo.addEventListener('click', goMain);
basket.addEventListener('mouseenter', showToolTipBox);
basket.addEventListener('mouseleave', hideToolTipBox);
logOutBtn.addEventListener('mouseenter', showToolTipBox);
logOutBtn.addEventListener('mouseleave', hideToolTipBox);
loginBtn.addEventListener('mouseenter', showToolTipBox);
loginBtn.addEventListener('mouseleave', hideToolTipBox);
joinBtn.addEventListener('mouseenter', showToolTipBox);
joinBtn.addEventListener('mouseleave', hideToolTipBox);
loginName.addEventListener('mouseenter', showToolTipBox);
loginName.addEventListener('mouseleave', hideToolTipBox);





// ===============================================================================
//툴팁 박스 오픈
function showToolTipBox() {
	headerToolTipBox.innerHTML = this.dataset.tooltip;
	headerToolTipBox.style.display = 'block';
	headerToolTipBox.style.top = window.scrollY + this.getBoundingClientRect().bottom + 'px';
	const headerToolTipBoxWidth = headerToolTipBox.offsetWidth;

	if(window.scrollX + this.getBoundingClientRect().right + headerToolTipBoxWidth > window.scrollX + window.innerWidth){
		headerToolTipBox.style.right = 0;
	}
	else{
		headerToolTipBox.style.left = window.scrollX + this.getBoundingClientRect().left + 'px';
	}
}
//툴팁 박스 숨기기
function hideToolTipBox() {
	headerToolTipBox.innerHTML = '';
	headerToolTipBox.style.display = 'none';

}
//메인 화면 이동 함수
function goMain() {
    location.href = '/';
}

//로그아웃 함수
function doLogOut() {

    const result = confirm('로그아웃 하시겠습니까?ㅇㅇ');

    if (result == true) {
        location.href = '/logout';
    }
}
function mediaQuery(){
	//로그인 유저 이미지
	const userProfileImg = document.querySelector('#userProfileImg').dataset.profile;
	if (matchMedia("screen and (min-width:320px)").matches && matchMedia("screen and (max-width:767px)").matches) {
		// 스마트폰

		loginBtn.innerHTML = '<span class="material-symbols-outlined">login</span>';
		logOutBtn.innerHTML = '<span class="material-symbols-outlined">logout</span>';
		joinBtn.innerHTML = '<span class="material-symbols-outlined">person_add</span>';
		//유저 프로필 이미지
		if(userProfileImg != undefined || userProfileImg != null)
			loginName.innerHTML = `<img alt="" src="${userProfileImg}" id="headerUserProfileImg">`;


	}
	if (matchMedia("screen and (min-width:768px)").matches && matchMedia("screen and (max-width:1023px)").matches) {
		// 태블릿
		
		loginBtn.innerHTML = '<span class="material-symbols-outlined">login</span>';
		logOutBtn.innerHTML = '<span class="material-symbols-outlined">logout</span>';
		joinBtn.innerHTML = '<span class="material-symbols-outlined">person_add</span>';
		//유저 프로필 이미지
		if(userProfileImg != undefined || userProfileImg != null)
			loginName.innerHTML = `<img alt="" src="${userProfileImg}" id="headerUserProfileImg">`;

	}
	if (matchMedia("screen and (min-width:1024px)").matches) {
		// 데스크탑
		loginBtn.innerHTML = '로그인';
		logOutBtn.innerHTML = '로그아웃';
		joinBtn.innerHTML = '회원가입';
		if(userProfileImg != undefined || userProfileImg != null)
			loginName.innerHTML = `<img alt="" src="${userProfileImg}" id="headerUserProfileImg">&nbsp;` + loginName.dataset.login + '님';
	}
}
