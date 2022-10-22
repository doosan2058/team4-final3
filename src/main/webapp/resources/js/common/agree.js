'use strict'
//모든 이용약관 동의 체크박스
const allAgreeCheckBox = document.querySelector('#allAgreeCheckBox');
//로그인 페이지로 넘어가는 버튼
const nextBtn = document.querySelector('#nextBtn');

//약관 동의 체크박스들 (0,1 = 필수, 2 = 선택)
const agreeCheckBoxs = document.querySelectorAll('.agreeCheckBoxs');

//==================================================================================
allAgreeCheckBox.addEventListener('click', ckeckAllCheckBox);
nextBtn.addEventListener('click' , checkAgrees);

//==================================================================================

//모든 이용약관 동의 체크박스 체크시 이하 모든 약관 체크 되는 함수
function ckeckAllCheckBox(){
console.log(agreeCheckBoxs);
   for(let item of agreeCheckBoxs){
        item.checked = allAgreeCheckBox.checked;
   }
}

//필수 이용약관 동의 체크되었는지 확인하는 함수
function checkAgrees(){
    
    if(agreeCheckBoxs[0].checked == false){
        alert('서비스 이용약관에 동의하셔야 합니다.');
    }
    else if(agreeCheckBoxs[1].checked == false){
        alert('개인정보 수집 및 이용에 대한 안내에 동의하셔야 합니다.');
    }
    else{
        let text = agreeCheckBoxs[2].checked;
        text = (text == true) ? 'y' : 'n';
        location.href = 'join?member_email_agree=' + text;
    }
}