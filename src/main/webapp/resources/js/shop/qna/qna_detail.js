'use strict'

const attachmentIcon = document.querySelector('.attachmentIcon');
const qnaImgContainer = document.querySelector('.qnaImgContainer');
const closeQnaImgContainer = document.querySelector('.closeQnaImgContainer');
const qnaDetailModifyAnchor = document.querySelector('#qnaDetailModifyAnchor');
const qnaDetailDeleteAnchor = document.querySelector('#qnaDetailDeleteAnchor');
// =============================================================================

window.addEventListener('load', qnaDetailInit);
attachmentIcon.addEventListener('click', showQnaImg);
closeQnaImgContainer.addEventListener('click', closeSelectContainer);

// =============================================================================

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
        if(document.querySelector('#authCheckHiddenInput').value != '관리자'){
            alert('삭제된 게시글 입니다.');
            location.href = '/qna';
        }
    }
    // 공개 게시글
    else {
        // 작성자 == 로그인유저
        if (document.querySelector('#loginCheckHiddenInput').value == document.querySelector('.qna_member_id').innerHTML) {
            qnaDetailModifyAnchor.style.display = 'inline-block';
            qnaDetailDeleteAnchor.style.display = 'inline-block';
        }
        // 작성자 != 로그인유저
        else {
            qnaDetailModifyAnchor.style.display = 'none';
            qnaDetailDeleteAnchor.style.display = 'none';
        }
    }


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