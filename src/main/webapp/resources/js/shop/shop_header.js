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
//로그인 체크 히든 스팬
const loginCheckHiddenInput = document.querySelector('#loginCheckHiddenInput');
//로그인 권한 스펜
const authCheckHiddenInput = document.querySelector('#authCheckHiddenInput');
//이벤트 앵커
const draw = document.querySelector('.draw');
//메뉴 설명 앵커
const headerAnchorTextSpan = document.querySelectorAll('.headerAnchorTextSpan');
//로그인 디비전 보기 아이콘
const more_horiz = document.querySelector('.more_horiz');
//헤더 네비 플래그
let IsloginDivShow = false;
//헤더 로그인 구역
const login = document.querySelector('.login');
// ===============================================================================

window.addEventListener('load', mediaQueryHeader);
window.addEventListener('load', initShopHeader);
window.addEventListener('resize', mediaQueryHeader);
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
more_horiz.addEventListener('click', toggleLoginDiv);




// ===============================================================================

function toggleLoginDiv(){
	if(IsloginDivShow == false){
		this.style.transform = 'translateY(-50%) rotateZ(0deg)';
		document.querySelector('.login').style.right = '0px';
		IsloginDivShow = true;
	}
	else{
		this.style.transform = 'translateY(-50%) rotateZ(90deg)';
		document.querySelector('.login').style.right = '-100%';
		IsloginDivShow = false;
	}





}
//로그인 체크 여부
function initShopHeader(){
	//프로필 앵커 초기화
	if(authCheckHiddenInput.value == '회원' || authCheckHiddenInput.value == '관리자'){
		loginName.style.display = 'flex';
		//회원
		if(authCheckHiddenInput.value == '회원'){
			loginName.href = '/user';
			loginName.dataset.tooltip = '내정보 보기';
		}
		else{
			loginName.href = '/admin';
			loginName.dataset.tooltip = '관리자 페이지 이동하기';
		}
	}
	else{
		loginName.style.display = 'none';
	}

	//로그인시 프로필 이미지 설정
	if(loginCheckHiddenInput.value != null || loginCheckHiddenInput.value != '' || loginCheckHiddenInput.value != undefined || loginCheckHiddenInput.value.length != 0){
		let param = {member_id : loginCheckHiddenInput.value}
		$.ajax({
			type : 'post',
			url : '/getUserImg',
			data : JSON.stringify(param),
			contentType : 'application/json',
			dataType : 'json',
			error : function(){
				alert('죄송합니다. 잠시후 다시 시도해 주세요.');
			},
			success : function (data){
				let imgUrlText = data.member_profile_img_url;
				document.querySelector('#headerUserProfileImg').src = imgUrlText;
			}
		});
	}
	//이벤트 페이지 이동 앵커 주소 초기화
	if(authCheckHiddenInput.value != '관리자')
		draw.href = '/shop/draw_customer';
	else
		draw.href = '/shop/draw_admin';

	//장바구니 앵커 초기화
	if(authCheckHiddenInput.value == '회원')
		basket.style.display = 'flex';
	else
		basket.style.display = 'none';

	//로그인, 로그아웃 앵커 초기화
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
//툴팁 박스 오픈
function showToolTipBox() {
	//데스크탑 사이즈 일때만
	if (matchMedia("screen and (min-width:1024px)").matches){
		headerToolTipBox.innerHTML = this.dataset.tooltip;
		headerToolTipBox.style.display = 'block';
		headerToolTipBox.style.top = window.scrollY + this.getBoundingClientRect().bottom + 'px';
		const headerToolTipBoxWidth = headerToolTipBox.offsetWidth;
		//툴팁 박스의 길이가 윈도우 오른쪽을 넘어간다면 오른쪽부터 시작
		if(window.scrollX + this.getBoundingClientRect().right + headerToolTipBoxWidth > window.scrollX + window.innerWidth){
			headerToolTipBox.style.right = 0;
		}
		else{
			headerToolTipBox.style.left = window.scrollX + this.getBoundingClientRect().left + 'px';
		}
	}

}
//툴팁 박스 숨기기
function hideToolTipBox() {
	//데스크탑 사이즈 일때만
	if (matchMedia("screen and (min-width:1024px)").matches){
		headerToolTipBox.innerHTML = '';
		headerToolTipBox.style.display = 'none';
	}


}
//메인 화면 이동 함수
function goMain() {
    location.href = '/';
}

//로그아웃 함수
function doLogOut() {

    const result = confirm('로그아웃 하시겠습니까?');

    if (result == true) {
        location.href = '/logout';
    }
}
function mediaQueryHeader(){

	// 스마트폰
	if (matchMedia("screen and (min-width:1px)").matches && matchMedia("screen and (max-width:767px)").matches) {
		headerAnchorTextSpan.forEach((item) => {
			item.style.display = 'none';
		});
		//로그인 구역 숨기기
		login.style.position = 'absolute';
		login.style.width = '100vw';
		login.style.top = '80px';
		login.style.right = '-100%';
	}
	// 태블릿
	if (matchMedia("screen and (min-width:768px)").matches && matchMedia("screen and (max-width:1023px)").matches) {
		headerAnchorTextSpan.forEach((item) => {
			item.style.display = 'none';
		});
		//로그인 구역 숨기기
		login.style.position = 'absolute';
		login.style.width = '100vw';
		login.style.top = '80px';
		login.style.right = '-100%';


	}
	// 데스크탑
	if (matchMedia("screen and (min-width:1024px)").matches) {
		headerAnchorTextSpan.forEach((item) => {
			item.style.display = 'block';
		});
		//로그인 구역 초기화
		login.style.position = 'static';
		login.style.width = '60%';

	}
}
