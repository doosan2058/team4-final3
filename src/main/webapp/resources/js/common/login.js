/**
 * 로그인 페이지 자바스크립트
 */

//회원가입 앵커
const joinAnchor = document.querySelector('#joinAnchor');
//아이디 인풋
const member_id = document.querySelector('#member_id');
//비밀번호 인풋
const member_pw = document.querySelector('#member_pw');
//===========================================================
joinAnchor.addEventListener('mouseenter' , changeText);
joinAnchor.addEventListener('mouseleave' , rollbackText);
member_id.addEventListener('blur' , setPlaceholderSpanVisible);
member_pw.addEventListener('blur' , setPlaceholderSpanVisible);

//===========================================================
//플레이스홀더 스팬 이벤트
function setPlaceholderSpanVisible(){
	if(this.value.length > 0){

		this.nextElementSibling.style.opacity = '0';
	}
	else
		this.nextElementSibling.style.opacity = '1';
}
//회원가입 권유 텍스트 바뀌는 함수
function changeText(){
	if(matchMedia("screen and (min-width:1024px)").matches){
		this.innerHTML = '회원가입 하러 가기';
	}
}

function rollbackText(){
	if(matchMedia("screen and (min-width:1024px)").matches){
		this.innerHTML = '아직 회원이 아니신가요?';
	}
}
