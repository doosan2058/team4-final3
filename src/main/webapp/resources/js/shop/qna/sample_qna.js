// 질문 제목
const sampleQnaInnerTop = document.querySelectorAll('.sampleQnaInnerTop');

// ===============================================================================

sampleQnaInnerTop.forEach((item) => {
    item.addEventListener('click', toggleInnerBottomDiv);
})

// ===============================================================================

// 샘플 질문 답변 토글
function toggleInnerBottomDiv(){
    const innerBottom = document.querySelectorAll('.sampleQnaInnerBottom');

    this.nextElementSibling.classList.toggle('active');


}