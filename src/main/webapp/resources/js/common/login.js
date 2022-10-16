/**
 * 로그인 페이지 자바스크립트
 */

//회원가입 앵커
const joinAnchor = document.querySelector('#joinAnchor');

//===========================================================
joinAnchor.addEventListener('mouseenter' , changeText);
joinAnchor.addEventListener('mouseleave' , rollbackText);

//===========================================================

//회원가입 권유 텍스트 바뀌는 함수
function changeText(){
	this.innerHTML = '회원가입 하러 가기';
}

function rollbackText(){
	this.innerHTML = '아직 회원이 아니신가요?';
}
