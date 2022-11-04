'use strict'
//주소 검색 버튼
const addressSearchBtn = document.querySelector('#addressSearchBtn');
//비밀번호 인풋
const member_pw = document.querySelector('#member_pw');
//비밀번호 스팬
const passwordSpan = document.querySelector('.passwordSpan');
//비밀번호 확인 인풋
const member_pw2 = document.querySelector('#member_pw2');
//비밀번호 확인 스팬
const password2Span = document.querySelector('.password2Span');
//아이디 인풋
const member_id = document.querySelector('#member_id');
//아이디 스팬
const idSpan = document.querySelector('.idSpan');
//아이디 중복 확인 스팬
const idDupCheckSpan = document.querySelector('.idDupCheckSpan');
//이름 인풋
const member_name = document.querySelector('#member_name');
//이름 스팬
const nameSpan = document.querySelector('.nameSpan');
//핸드폰 인풋
const member_phone = document.querySelector('#member_phone');
//핸드폰 스팬
const phoneSpan = document.querySelector('.phoneSpan');
//도메인 셀렉트
const emailSelect = document.querySelector('#emailSelect'); 
//도메인 인풋
const domain = document.querySelector('#domain');
//도메인 스팬
const domainSpan = document.querySelector('.domainSpan');
//이메일 이름 인풋
const emailName = document.querySelector('#emailName');
//이메일 이름 스팬
const emailSpan = document.querySelector('.emailSpan');
//이메일 중복 확인 버튼
const checkDupEmailBtn = document.querySelector('#checkDupEmailBtn');
//이메일 중복 확인 결과 스팬
const emailDupCheckSpan = document.querySelector('.emailDupCheckSpan');
//나이 인풋
const member_age = document.querySelector('#member_age');
//나이 스팬
const ageSpan = document.querySelector('.ageSpan');
//주소 인풋들
const addressInputs = document.querySelectorAll('.addressInputs');
//주소 스팬
const addressSpan = document.querySelector('.addressSpan');
//공백 정규표현식
const WS_REG = /\s/g;
//==================================================================================
addressSearchBtn.addEventListener('click' , searchAddress);
member_pw.addEventListener('keyup' , checkRegAndInitPw2);
member_id.addEventListener('keyup' , checkId);
member_id.addEventListener('blur' , checkDupId);
member_name.addEventListener('keyup' , checkName);
member_phone.addEventListener('keyup' , checkphone);
emailSelect.addEventListener('change' , selectDomain);
emailName.addEventListener('keyup' , checkEmailName);
domain.addEventListener('keyup' , checkDomain);
checkDupEmailBtn.addEventListener('click' , checkDupEmail);
member_pw2.addEventListener('keyup' , checkPw2);
member_age.addEventListener('change' , checkAge);
member_age.addEventListener('keyup' , checkAge);

//==================================================================================
//이메일 중복 확인 함수
function checkDupEmail(){
	//이메일 양식 확인
	if(emailSpan.innerHTML != '성공'){
        alert('이메일 이름양식을 확인해 주세요');
        return;
    }
    else if(domainSpan.innerHTML != '성공'){
        alert('도메인 양식을 확인해 주세요');
        return;
    }
	
	let emailText = (emailName.value + '@' + domain.value).trim();
	const param = {
			"email" : emailText
	}

	$.ajax({
		
		url : 'checkDupEmailAjax',
		data : JSON.stringify(param),
		type : 'post',
		contentType : 'application/json; charset=utf-8',
		error : function(){
			alert('죄송합니다. 잠시후 다시 시도해 주세요.');
		},
		success : function(data){
			//중복된 아이디가 아닙니다.
			if(data == 0){
				emailDupCheckSpan.innerHTML = '사용 가능 이메일 입니다.';
				emailDupCheckSpan.style.color = 'var(--fontColor)';
			}
			//증복 아이디
			else if(data == 1){
				emailDupCheckSpan.innerHTML = '이미 사용중인 이메일 입니다.';
				emailDupCheckSpan.style.color = 'red';
			}
			else{
				emailDupCheckSpan.innerHTML = '심각한 오류 발생.';
				emailDupCheckSpan.style.color = 'red';
			}
		}
	});
}
//아이디 중복 확인 함수
function checkDupId(){
	if((member_id.value).trim().length == 0 || idSpan.innerHTML != '성공'){
        idDupCheckSpan.innerHTML = '';
        return;
    }

    const param = {
			"id" : member_id.value
	}
	
	$.ajax({
		url : 'checkDupIdAjax',
		data : JSON.stringify(param),
		type : 'post',
		contentType : 'application/json; charset=utf-8',
		error : function(){
			alert('죄송합니다. 잠시후 다시 시도해 주세요.');
		},
		success : function(data){
			//중복된 아이디가 아닙니다.
			if(data == 0){
				idDupCheckSpan.innerHTML = '사용 가능 아이디 입니다.';
				idDupCheckSpan.style.color = 'var(--fontColor)';
			}
			//증복 아이디
			else if(data == 1){
				idDupCheckSpan.innerHTML = '이미 사용중인 아이디 입니다.';
				idDupCheckSpan.style.color = 'red';
			}
			else{
				idDupCheckSpan.innerHTML = '심각한 오류 발생.';
				idDupCheckSpan.style.color = 'red';
			}
		}
	});
}

//가입하기 버튼 누르면 작동할 함수
function preDoJoinCheck(){
    //이메일 중복 확인
	if(emailDupCheckSpan.innerHTML != '사용 가능 이메일 입니다.'){
		alert('이미 사용중인 이메일 입니다.');
		return false;
	}
    //이메일 양식 확인
	else if(emailSpan.innerHTML != '성공'){
        alert('이메일 이름양식을 확인해 주세요');
        return false;
    }
    else if(domainSpan.innerHTML != '성공'){
        alert('도메인 양식을 확인해 주세요');
        return false;
    }
    
    //아이디 중복 확인
    else if(idDupCheckSpan.innerHTML != '사용 가능 아이디 입니다.'){
    	alert('사용할수 없는 아이디 입니다.');
    	return false;
    }
    //아이디 양식 확인
    else if(idSpan.innerHTML != '성공'){
        alert('아이디 양식을 확인해 주세요.');
        return false;
    }
    //비밀번호 양식 확인
    else if(passwordSpan.innerHTML != '성공'){
        alert('비밀번호 양식을 확인해 주세요.');
        return false;
    }
    //비밀번호 체크 확인
    else if(password2Span.innerHTML != '성공'){
        alert('비밀번호 확인을 작성해 주세요.');
        return false;
    }
    //이름 양식 확인
    else if(nameSpan.innerHTML != '성공'){
        alert('이름 양식을 확인해 주세요.');
        return false;
    }
    //핸드폰 양식 확인
    else if(nameSpan.innerHTML != '성공'){
        alert('이름 양식을 확인해 주세요.');
        return false;
    }
    //나이 양식 확인
    else if(ageSpan.innerHTML != '성공'){
        alert('나이 양식을 확인해 주세요.');
        return false;
    }
    //주소 입력 확인
    else if(addressSpan.innerHTML != '성공'){
    	alert('주소를 입력해 주세요.');
    	return false;
    }
    else{
    	//이메일이름 + 도메인 해서 hidden 에 저장
    	let fullEmailText = '';
    	fullEmailText = (emailName.value + '@' + domain.value);
    	document.querySelector('input[name=member_email]').value = fullEmailText.trim();
    	//주소들 합처서 hidden 에 저장
    	let fullAddressText = '';
    	addressInputs.forEach(item => {
    		fullAddressText += (' ' + item.value);
    		document.querySelector('input[name=member_address]').value = fullAddressText.trim();
    	})
    	
    	return true;
    	
    }

}
//나이 체크 함수
function checkAge(){
    const AGE_REG = /\d{1,3}/;
    
    if(member_age.value < 1){
        ageSpan.innerHTML = '1살 이상만 입력해주세요.';
        ageSpan.style.color = 'red';
    }
    else if(member_age.value > 130){
        ageSpan.innerHTML = '130살 이하로 입력해주세요.';
        ageSpan.style.color = 'red';
    }
    else if(member_age.value > 0 && member_age.value < 131){
        ageSpan.innerHTML = '성공';
        ageSpan.style.color = 'var(--fontColor)';
    }
    else{
        ageSpan.innerHTML = '죄송합니다. 다시 입력해 주세요.';
        ageSpan.style.color = 'red';
    }
}

//비밀번호 확인 체크 함수
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
//도메인 양식 체크 함수
function checkDomain(){
    const DOMAIN_REG = /[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/;
    if(emailSpan.innerHTML != '성공')
        return;
    else if(domain.value.length == 0){
        domainSpan.innerHTML = '';
     }
     else if(WS_REG.test(domain.value)){
        domainSpan.innerHTML = '도메인에는 공백이 포함될 수 없습니다.';
        domainSpan.style.color = 'red';
     }
     else if(!DOMAIN_REG.test(domain.value)){
        domainSpan.innerHTML = '도메인 양식이 틀렸습니다.';
        domainSpan.style.color = 'red';
     }
     else{
        domainSpan.innerHTML = '성공';
        domainSpan.style.color = 'var(--fontColor)';
     }
}
//이메일 이름 체크 함수
function checkEmailName(){
     //4~20 영문소문자로 시작 + 숫자
     const EMAILNAME_REG = /^[a-z]+[a-z0-9]{3,19}$/;
    domainSpan.innerHTML = '';
    emailDupCheckSpan.innerHTML = '';
    domain.value = '';
     if(emailName.value.length == 0){
        emailSpan.innerHTML = '';
     }
     else if(WS_REG.test(emailName.value)){
        emailSpan.innerHTML = '이메일에는 공백이 포함될 수 없습니다.';
        emailSpan.style.color = 'red';
     }
     else if(!EMAILNAME_REG.test(emailName.value)){
        emailSpan.innerHTML = '이메일 양식이 틀렸습니다.';
        emailSpan.style.color = 'red';
     }
     else{
        emailSpan.innerHTML = '성공';
        emailSpan.style.color = 'var(--fontColor)';
     }
}

//도메인 선택 하는 함수
function selectDomain(){

	//이메일 중복 확인 초기화
	emailDupCheckSpan.innerHTML = '';
	if(emailSpan.innerHTML != '성공'){
        alert('이메일을 입력해 주세요.');
        return;
    }

    else if(emailSelect.value == 'self'){
        domain.readOnly = false;
        domain.placeholder = '직접 입력해 주세요.';
        domain.value = '';
        
    }
    else if(emailSelect.value == 'init'){
        domain.placeholder = 'Select Your Domain';
        domain.value = '';
        
    }
    else if(emailSelect.value == null || emailSelect.value == undefined ){
        alert('죄송합니다.다시 선택해 주세요.');
        domainSpan.innerHTML = '';
        
    }
    else {
        domain.value = emailSelect.value;
        domainSpan.innerHTML = '성공';
        domainSpan.style.color = 'var(--fontColor)';
    }
}
//핸드폰 체크 함수
function checkphone(){
    //핸드폰
    const HP_REG = /^(01)\d{8,9}$/;
    
    if(member_phone.value.length == 0){
        phoneSpan.innerHTML = '';
    }
    else if(WS_REG.test(member_phone.value)){
        phoneSpan.innerHTML = '핸드폰 번호에는 공백이 포함될 수 없습니다.';
        phoneSpan.style.color = 'red';
    }
    else if(!HP_REG.test(member_phone.value)){
        phoneSpan.innerHTML = '핸드폰 번호 양식이 틀렸습니다.';
        phoneSpan.style.color = 'red';
    }
    else{
        phoneSpan.innerHTML = '성공';
        phoneSpan.style.color = 'var(--fontColor)';
    }
}
//이름 한글 체크 함수
function checkName(){
    //한글만
    const NAME_REG = /^[가-힣]{1,}$/;
    
    if(member_name.value.length == 0){
        nameSpan.innerHTML = '';
    }
    else if(WS_REG.test(member_name.value)){
        nameSpan.innerHTML = '이름에는 공백이 포함될 수 없습니다.';
        nameSpan.style.color = 'red';
    }
    else if(!NAME_REG.test(member_name.value)){
        nameSpan.innerHTML = '이름 양식이 틀렸습니다.';
        nameSpan.style.color = 'red';
    }
    else{
        nameSpan.innerHTML = '성공';
        nameSpan.style.color = 'var(--fontColor)';
    }
}
//아이디 정규식 체크 함수
function checkId(){
    //4~20 영문으로 시작 + 숫자
    const ID_REG = /^[a-zA-Z]+[a-zA-Z0-9]{3,19}$/;
    //아이디 중복 확인 초기화
    idDupCheckSpan.innerHTML = '';
    if(member_id.value.length == 0){
        idSpan.innerHTML = '';
    }
    else if(WS_REG.test(member_id.value)){
        idSpan.innerHTML = '아이디에는 공백이 포함될 수 없습니다.';
        idSpan.style.color = 'red';
    }
    else if(!ID_REG.test(member_id.value)){
        idSpan.innerHTML = '아이디 양식이 틀렸습니다.';
        idSpan.style.color = 'red';
    }
    else{
        idSpan.innerHTML = '성공';
        idSpan.style.color = 'var(--fontColor)';
    }
}
//비밀번호 입력시 정규식 + 비밀번호 확인 인풋 초기화 하는 함수
function checkRegAndInitPw2(){
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

//다음 주소 검색 함수
function searchAddress() {
    new daum.Postcode({
        oncomplete: function(data) {
        	
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
            // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            let roadAddr = data.roadAddress; // 도로명 주소 변수
            let extraRoadAddr = ''; // 참고 항목 변수

            // 법정동명이 있을 경우 추가한다. (법정리는 제외)
            // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
            if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                extraRoadAddr += data.bname;
            }
            // 건물명이 있고, 공동주택일 경우 추가한다.
            if(data.buildingName !== '' && data.apartment === 'Y'){
               extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
            }
            // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
            if(extraRoadAddr !== ''){
                extraRoadAddr = ' (' + extraRoadAddr + ')';
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('postcode').value = data.zonecode;
            document.getElementById("roadAddress").value = roadAddr;
//            document.getElementById("jibunAddress").value = data.jibunAddress;
            addressSpan.innerHTML = '성공';
            addressSpan.style.color = 'var(--fontColor)';
            // 참고항목 문자열이 있을 경우 해당 필드에 넣는다.
            if(roadAddr !== ''){
                document.getElementById("extraAddress").value = extraRoadAddr;
            } else {
                document.getElementById("extraAddress").value = '';
            }

            let guideTextBox = document.getElementById("guide");
            // 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
            if(data.autoRoadAddress) {
                let expRoadAddr = data.autoRoadAddress + extraRoadAddr;
                guideTextBox.innerHTML = '(예상 도로명 주소 : ' + expRoadAddr + ')';
                guideTextBox.style.display = 'block';

            } else if(data.autoJibunAddress) {
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