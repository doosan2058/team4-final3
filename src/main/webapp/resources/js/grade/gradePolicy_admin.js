'use strict'

const gradePolicyLineDiv = document.querySelectorAll('.gradePolicyLineDiv');
const explainGradePolicyDiv = document.querySelector('.explainGradePolicyDiv');
const closeIcon = document.querySelector('.closeIcon');


// ====================================================================================
gradePolicyLineDiv.forEach((item) => {
    item.addEventListener('click', showGradeInfo);
});
closeIcon.addEventListener('click', closeGradeInfo);
submitBtn.addEventListener('click', checkGradeAddForm);

// ====================================================================================

// 등급 추가 폼 양식 체크
function checkGradeAddForm(){
    alert('test');
}

function closeGradeInfo(){
    explainGradePolicyDiv.style.display = 'none';
}
function showGradeInfo(){
    if(matchMedia('(min-width : 1px) and (max-width : 767px)').matches){
        explainGradePolicyDiv.style.display = 'block';
        const explainGradePolicyDivSpan = document.querySelectorAll('.explainGradePolicyDivSpan');

        for(let i = 0; i < explainGradePolicyDivSpan.length; i++){
            explainGradePolicyDivSpan[i].innerHTML = this.children[i + 1].innerHTML;
        }
    }

}






