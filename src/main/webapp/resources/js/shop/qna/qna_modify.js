'use strict'

// ===================================================================================================
window.addEventListener('load', qnaModifyInit);

// ===================================================================================================

function qnaModifyInit() {

    // 작성자 == 로그인유저
    if (document.querySelector('#loginCheckHiddenInput').value == document.querySelector('#qna_member_id').value) {

    }
    // 작성자 != 로그인유저
    else {
        alert('권한이 없습니다.');
        location.href = '/qna';
    }


    const qna_category = document.querySelector('#qna_category');
    const selectedValue = qna_category.dataset.exValue;
    for (let i = 0; i < qna_category.options.length; i++) {
        if (qna_category.options[i].value == selectedValue) {
            qna_category.options[i].selected = true;
            break;
        }
    }
}