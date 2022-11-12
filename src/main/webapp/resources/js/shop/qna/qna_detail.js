'use strict'

const attachmentIcon = document.querySelector('.attachmentIcon');
const qnaImgContainer = document.querySelector('.qnaImgContainer');
const closeQnaImgContainer = document.querySelector('.closeQnaImgContainer');
// =============================================================================

attachmentIcon.addEventListener('click', showQnaImg);
closeQnaImgContainer.addEventListener('click', closeSelectContainer);

// =============================================================================


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