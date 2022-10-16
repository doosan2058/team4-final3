'use strict'
//랜덤 코드
const codeInput = document.querySelector('#codeInput');
//입력 코드
const secretEmailText = document.querySelector('#secretEmailText');
//코드 체크 버튼
const formBtns = document.querySelector('.formBtns');
//결과 스팬
const secretEmailSpan = document.querySelector('.secretEmailSpan');
//폼
const joinForm = document.querySelector('#joinForm');
//==================================================================================

formBtns.addEventListener('click' , checkCode);

//==================================================================================

//입력한 코드와 이메일 코드가 맞는지 검증하는 함수
function checkCode(){
	const code = codeInput.value;
	const userCode = secretEmailText.value;
	
	if(code === userCode){
		secretEmailSpan.innerHTML = '성공';
		secretEmailSpan.style.color = 'var(--fontColor)';
		alert('회원가입 되었습니다.');
		joinForm.submit();
	}
	else{
		secretEmailSpan.innerHTML = '인증번호가 일치하지 않습니다.';
		secretEmailSpan.style.color = 'red';
		
	}
}
