/**
 * 
 */

'use strict'
//비밀번호들
const member_pw = document.querySelector('#member_pw');
const member_pw2 = document.querySelector('#member_pw2');
//비밀번호 결과 스팬
const passwordSpan = document.querySelector('.resultSpan1');
const password2Span = document.querySelector('.resultSpan2');
//취소버튼
const cancelBtn = document.querySelector('#cancelBtn');


//공백 정규표현식
const WS_REG = /\s/g;

// ======================================================================
member_pw.addEventListener('keyup', checkPw);
member_pw2.addEventListener('keyup', checkPw2);
cancelBtn.addEventListener('click', cancelChangePw)
// ======================================================================

//비밀번호 변경 취소
function cancelChangePw(){
	location.href = '/user';
}
//서브밋 전 폼 체크
function checkForm(){
	//비밀번호 양식 확인
    if(passwordSpan.innerHTML != '성공'){
        alert('비밀번호 양식을 확인해 주세요.');
        return false;
    }
    //비밀번호 체크 확인
    else if(password2Span.innerHTML != '성공'){
        alert('비밀번호 확인을 작성해 주세요.');
        return false;
    }
	else{
		const result = confirm('비밀번호를 변경 하시겠습니까?');
		if(result)
			return true;
		else
			return false;
		
	}
}

////비밀번호 입력시 정규식 + 비밀번호 확인 인풋 초기화 하는 함수
function checkPw(){
	//비밀번호 정규식
	//9~20 영문 소문자 + 숫자 + 특수문자의 조합
	const PW_REG = /^(?=.*[a-z])(?=.*\d)(?=.*[@$!%*#?&])[a-z\d@$!%*#?&]{9,20}$/;
	    
	if(member_pw.value.length == 0){
	    passwordSpan.innerHTML = '';
	}
	else if(WS_REG.test(member_pw.value)){
	    passwordSpan.innerHTML = '비밀번호에는 공백이 포함될 수 없습니다.';
	    passwordSpan.style.color = 'red';
	}
	else if(!PW_REG.test(member_pw.value)){
	    passwordSpan.innerHTML = '비밀번호 양식이 틀렸습니다.';
	    passwordSpan.style.color = 'red';
	}
	else{
	    passwordSpan.innerHTML = '성공';
	    passwordSpan.style.color = 'var(--fontColor)';
	}
	//비밀번호 변경시 비밀번호 확인 인풋 초기화
	member_pw2.value = '';
	password2Span.innerHTML = '';
	
}

//비밀번호 확인 체크
function checkPw2(){
	
	if(passwordSpan.innerHTML != '성공'){
		password2Span.innerHTML = '올바른 비밀번호를 입력해 주세요.';
		password2Span.style.color = 'red';
	}
	else if(member_pw2.value.length == 0){
        password2Span.innerHTML = '';
    }
    else if(WS_REG.test(member_pw2.value)){
        password2Span.innerHTML = '비밀번호에는 공백이 포함될 수 없습니다.';
        password2Span.style.color = 'red';
     }
     else if(member_pw2.value != member_pw.value){
        password2Span.innerHTML = '비밀번호가 틀립니다.';
        password2Span.style.color = 'red';
     }
     else if(member_pw2.value == null || member_pw2.value == undefined){
        password2Span.innerHTML = '죄송합니다. 다시 입력해 주세요.';
        password2Span.style.color = 'red';
     }
     else if(member_pw2.value === member_pw.value){
        password2Span.innerHTML = '성공';
        password2Span.style.color = 'var(--fontColor)';
     }
     else{
        password2Span.innerHTML = '심각한 오류 발생. 다시 입력해 주세요.';
        password2Span.style.color = 'red';
     }
}