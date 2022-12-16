'use strict'

// 등급 추가 폼 양식 체크
function checkGradeAddForm() {

    const imgTypeArray = ['image/jpeg', 'image/png', 'image/jpg', 'image/gif']; //이미지 타입
    const grade_img_url = document.querySelector('#grade_img_url'); // 파일 인풋
    const doubleRegrex = /^[0]*\.?[\d]{0,2}$/; // 소수 정규표현식 (0.xx)
    const grade_discount = document.querySelector('#grade_discount').value; // 할인율
    const grade_accrual_rate = document.querySelector('#grade_accrual_rate').value; // 할인율
    if (doubleRegrex.test(grade_discount) == true && doubleRegrex.test(grade_accrual_rate) == true && imgTypeArray.indexOf(grade_img_url.files[0].type) != -1) {
        const result = confirm('새로운 등급을 추가하시겠어요?');
        if (result)
            return true;
        else
            return false;
    } else if (doubleRegrex.test(grade_discount) == false) {
        alert('할인율은 소수점 2째 자리까지, 1보다 작게 입력하셔야 합니다.');
        return false;
    } else if (doubleRegrex.test(grade_accrual_rate) == false) {
        alert('적립율은 소수점 2째 자리까지, 1보다 작게 입력하셔야 합니다.');
        return false;
    } else if (imgTypeArray.indexOf(grade_img_url.files[0].type) == -1) { // 사진 타입 업로드 체크
        alert('이미지 파일만 업로드 할수 있습니다.');
        return false;
    } else {
        alert('심각한 오류가 발생하였습니다. 잠시후 다시 시도해 주세요.');
        return false;
    }

}