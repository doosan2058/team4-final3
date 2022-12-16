'use strict'
//원본 이미지 주소
const member_profile_img_url = document.querySelector('#member_profile_img_url');
//구매 확정 td
const confirmBuy = document.querySelectorAll('.confirmBuy');
//회원 아이디 스팬
const userNameSpan = document.querySelector('.userNameSpan');
//회원 등급 보기
const userGradeDiv = document.querySelector('.userGradeDiv');
//로그아웃 버튼
const logOutBtn = document.querySelector('#logOutBtn');
/*구매 내역 모달 컨테이너 */
const orderHistoryModalCon = document.querySelector('.orderHistoryModalCon');
/*구매 내역 디비전 */
const userOrderHistoryDiv = document.querySelector('.userOrderHistoryDiv');
//회원 탈퇴 앵커
const deleteMemberAnchor = document.querySelector('.deleteMemberAnchor');
//회원 탈퇴 컨테이너
const deleteMemberContainer = document.querySelector('.deleteMemberContainer');
//회원 탈퇴 텍스트 인풋
const deleteInputs = document.querySelector('.deleteInputs');
//회원 탈퇴 스팬
const deleteSpan = document.querySelector('.deleteSpan');
//회원 탈퇴 컨테이너 닫기 아이콘
const closeIcon = document.querySelector('.closeIcon');
//회원 탈퇴 버튼
const deleteBtns = document.querySelector('.deleteBtns');
//프로필 이미지
const userImg = document.querySelector('#userImg');
//프로필 이미지 파일 인풋
const file = document.querySelector('.file');
//이미지 취소
const imgCancel = document.querySelector('.imgCancel');
//이미지 에이잭스 서브밋 업로드 앵커
const imgSubmit = document.querySelector('.imgSubmit');
//프로필 이미지 폼
const profileImgForm = document.querySelector('#profileImgForm');
//이미지 타입
const imgTypeArray = ['image/jpeg', 'image/png', 'image/jpg', 'image/gif'];
//비밀번호 변경 앵커
const changePasswordAnchor = document.querySelector('.changePasswordAnchor');
//비밀번호 변경 컨테이너
const changePasswordContainer = document.querySelector('.changePasswordContainer');
//비밀번호 체크 버튼
const passwordBtn = document.querySelector('#passwordBtn');
//비밀번호 체크 인풋
const passwordInput = document.querySelector('#passwordInput');
//비밀번호 체크 폼
const passwordCheckForm = document.querySelector('#passwordCheckForm');
//내글보기 앵커
const myListAnchor = document.querySelector('.myListAnchor');
//내글보기 컨테이너
const myListContainer = document.querySelector('.myListContainer');
//내글보기 닫기
const myListHeader = document.querySelector('.myListHeader');
// ==================================================================================================


confirmBuy.forEach((item) => {
    item.addEventListener('click', purchaseProduct);
    item.addEventListener('mouseenter', changeUpText);
    item.addEventListener('mouseleave', changeDownText);
})


logOutBtn.addEventListener('click', doLogOut);
userGradeDiv.addEventListener('click', showMyGrade);
userOrderHistoryDiv.addEventListener('click', showSalesDiv);
orderHistoryModalCon.addEventListener('click', closeSalesDiv);
deleteMemberAnchor.addEventListener('click', showDeleteMemberCon);
deleteMemberContainer.addEventListener('click', closeDeleteMemberCon);
deleteInputs.addEventListener('keyup', checkDeleteText);
closeIcon.addEventListener('click', closeIconFunc);
deleteBtns.addEventListener('click', doDeleteMember);
file.addEventListener('change', changeProfileImg);
imgCancel.addEventListener('click', cancelProfileImg);
imgSubmit.addEventListener('click', doImgAjax);
changePasswordAnchor.addEventListener('click', showChangeCon);
changePasswordContainer.addEventListener('click', closeChangeCon);
passwordBtn.addEventListener('click', checkPw);
myListAnchor.addEventListener('click', showList);
myListHeader.addEventListener('click', closeList);

// =====================================================================================================

function closeList() {
    myListContainer.children[0].style.animation = 'hiddenModal 0.3s 1 forwards';
    setTimeout(function () {
        myListContainer.style.display = 'none';
    }, 300);
}

function showList() {
    myListContainer.style.display = 'block';
    myListContainer.children[0].style.animation = 'showModal 0.3s 1 forwards';
}

//비밀번호 체크 
function checkPw() {
    const pwText = passwordInput.value;
    const idText = userNameSpan.innerHTML;
    const param = {member_pw: pwText, member_id: idText}

    $.ajax({
        url: '/user/check',
        type: 'post',
        contentType: 'application/json; charset=utf-8',
        data: JSON.stringify(param),
        dataType: 'text',
        error: function () {
            alert('죄송합니다. 잠시후 다시 시도해 주세요.');
        },
        success: function (data) {
            document.querySelector('.pwCheckSpan').innerHTML = data;
            document.querySelector('.pwCheckSpan').style.color = 'var(--fontColor)';
            if (document.querySelector('.pwCheckSpan').innerHTML == '일치') {
                location.href = '/user/change_password';
            }
        }
    });
}

//비밀번호 변경 컨테이너 보이기
function showChangeCon() {
    changePasswordContainer.style.display = 'block';
    changePasswordContainer.children[0].style.animation = 'showModal 0.3s 1 forwards';
}

//비밀번호 변경 컨테이너 닫기
function closeChangeCon(e) {
    if (e.target.className == 'changePasswordContainer') {
        changePasswordContainer.children[0].style.animation = 'hiddenModal 0.3s 1 forwards';
        setTimeout(function () {
            changePasswordContainer.style.display = 'none';
        }, 300);

    }
}

//비동기 프로필 이미지 업로드
function doImgAjax() {
    let imgForm = profileImgForm;
    let formData = new FormData(imgForm);
    //- contentType : false 로 선언 시 content-type 헤더가 multipart/form-data로 전송되게 함
    //- processData : false로 선언 시 formData를 string으로 변환하지 않음

    $.ajax({
        type: 'post',
        url: '/user/profile',
        data: formData,
        dataType: 'json',
        contentType: false,
        processData: false,
        error: function () {
            alert('죄송합니다. 잠시후 다시 시도해 주세요.');
        },
        success: function (data) {

            member_profile_img_url.value = data.member_profile_img_url;
            userImg.src = data.member_profile_img_url;
            alert('프로필 이미지가 변겅되었습니다.');
            //앵커들 숨기기
            imgSubmit.style.display = 'none';
            imgCancel.style.display = 'none';
            let dataTransfer = new DataTransfer();
            file.files = dataTransfer.files;
        }
    });
}


//프로필 이미지 취소
function cancelProfileImg() {
    userImg.src = member_profile_img_url.value;
    let dataTransfer = new DataTransfer();
    file.files = dataTransfer.files;
    //앵커들 숨기기
    imgSubmit.style.display = 'none';
    imgCancel.style.display = 'none';
}

//프로필 이미지 변경 함수
function changeProfileImg() {


    if ((imgTypeArray.indexOf(file.files[0].type) == -1)) {
        alert('프로필 사진은 이미지 파일만 쓸수 있습니다.');
        let dataTransfer = new DataTransfer();
        file.files = dataTransfer.files;
        return;
    }
    //앵커들 보이기
    imgSubmit.style.display = 'block';
    imgCancel.style.display = 'block';

    let fileReader = new FileReader();
    fileReader.addEventListener('load', function () {
        userImg.src = fileReader.result;
    });
    if (file.files[0])
        fileReader.readAsDataURL(file.files[0]);
}

//삭제 버튼 클릭시 삭제 요청
function doDeleteMember() {
    let result = confirm('정말 탈퇴 하시겠습니까?');

    if (result) {
        //삭제 요청시 폼 생성
        //인풋 member_id 생성후 로그인 유저 아이디 넣기
        //post 방식으로 컨트롤러 /user/delete 호출

        let tempForm = document.createElement('form');

        tempForm.setAttribute('method', 'post');
        tempForm.setAttribute('action', '/user/delete');

        let tempInput = document.createElement('input');

        tempInput.setAttribute('type', 'hidden');
        tempInput.setAttribute('name', 'member_id');

        let tempText = userNameSpan.innerHTML;

        tempInput.setAttribute('value', tempText);

        document.body.appendChild(tempForm);
        tempForm.appendChild(tempInput);


        tempForm.submit();
    }


}

//삭제 텍스트 체크 함수
function checkDeleteText() {
    const text = deleteSpan.innerHTML;

    if (this.value == text) {
        deleteBtns.style.textDecoration = 'none';
        deleteBtns.style.cursor = 'pointer';
        deleteBtns.disabled = false;
    } else {
        deleteBtns.style.textDecoration = 'line-through';
        deleteBtns.style.cursor = 'not-allowed';
        deleteBtns.disabled = true;

    }
}

//닫기 아이콘 으로 회원탈퇴창 컨테이너 닫기
function closeIconFunc() {
    deleteMemberContainer.children[0].style.animation = 'hiddenModal 0.3s 1 forwards';
    setTimeout(function () {
        deleteMemberContainer.style.display = 'none';
    }, 300);
}

//회원탈퇴창 컨테이너 닫기
function closeDeleteMemberCon(e) {
    if (e.target.className == 'deleteMemberContainer') {
        deleteMemberContainer.children[0].style.animation = 'hiddenModal 0.3s 1 forwards';
        setTimeout(function () {
            deleteMemberContainer.style.display = 'none';
        }, 300);
    }

}

//회원탈퇴창 컨테이너 열기
function showDeleteMemberCon() {
    deleteMemberContainer.style.display = 'block';
    deleteMemberContainer.children[0].style.animation = 'showModal 0.3s 1 forwards';
}

//구매확정하기 글자 변경 이벤트
function changeUpText() {
    if (matchMedia('screen and (min-width: 1024px)').matches)
        this.innerHTML = '구매확정';
}

//구매확정하기 글자 변경 이벤트
function changeDownText() {
    this.innerHTML = this.dataset.state;
}

//구매 확정 목록 보기
function showSalesDiv() {
    orderHistoryModalCon.style.display = 'block';
    orderHistoryModalCon.children[0].style.animation = 'showModal 0.3s 1 forwards';

}

//구매 확정 목록 닫기
function closeSalesDiv(e) {
    if (e.target.className == 'orderHistoryModalCon') {
        orderHistoryModalCon.children[0].style.animation = 'hiddenModal 0.3s 1 forwards';
        setTimeout(function () {
            orderHistoryModalCon.style.display = 'none';
        }, 300);
    }

}

//구매 확정하기 버튼
function purchaseProduct() {
    const order_id = this.previousElementSibling.previousElementSibling.previousElementSibling.previousElementSibling.previousElementSibling.innerHTML;


    const result = confirm('구매확정 하시겠습니까?');
    if (result) {
        location.href = '/order/confirm?order_id=' + order_id;
    }

}

//내등급 정보 보기
function showMyGrade() {
    location.href = '/grade?member_id=' + userNameSpan.innerHTML;
}

//로그아웃 함수
function doLogOut(e) {
    e.preventDefault();
    const result = confirm('로그아웃 하시겠습니까?');
    if (result) {
        location.href = '/logout';
    }
}


