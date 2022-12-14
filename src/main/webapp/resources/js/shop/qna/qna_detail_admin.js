'use strict'

const attachmentIcon = document.querySelector('.attachmentIcon');
const qnaImgContainer = document.querySelector('.qnaImgContainer');
const closeQnaImgContainer = document.querySelector('.closeQnaImgContainer');

const answer_text = document.querySelector('.answer_text');
const answerTextLengthSpan = document.querySelector('.answerTextLengthSpan');
// =============================================================================

window.addEventListener('load', qnaDetailInit);
attachmentIcon.addEventListener('click', showQnaImg);
closeQnaImgContainer.addEventListener('click', closeSelectContainer);
answer_text.addEventListener('keyup', checkAnswerLength);

// =============================================================================
function checkAnswerLength() {
    const answerLength = answer_text.value.length;
    answerTextLengthSpan.innerHTML = `(${answerLength}/500)`;
}

function qnaDetailInit() {
    // 비공개 게시글
    if (document.querySelector('#qna_public').value == 'n') {
        // 작성자 == 로그인유저 or 관리자
        if (document.querySelector('#loginCheckHiddenInput').value == document.querySelector('.qna_member_id').innerHTML
            || document.querySelector('#authCheckHiddenInput').value == '관리자') {

        }
        // 작성자 != 로그인유저
        else {
            alert('비공개 게시글 입니다.');
            location.href = '/qna';
        }
    }
    // 삭제 게시글
    else if (document.querySelector('#qna_delete').value == 'y') {
        // 관리자제외 접근 불허
        if (document.querySelector('#authCheckHiddenInput').value != '관리자') {
            alert('삭제된 게시글 입니다.');
            location.href = '/qna';
        }
    }
    else{

    }

    const initAnswerLength = answer_text.value.length;
    answerTextLengthSpan.innerHTML = `(${initAnswerLength}/500)`;





}

function showQnaImg() {
    qnaImgContainer.style.display = 'block';
    qnaImgContainer.children[1].style.animation = 'showModal 0.3s 1 forwards';
}

// 상품 선택 컨테이너 닫기
function closeSelectContainer() {
    qnaImgContainer.children[1].style.animation = 'hiddenModal 0.3s 1 forwards';
    setTimeout(function () {
        qnaImgContainer.style.display = 'none';
    }, 300)
}