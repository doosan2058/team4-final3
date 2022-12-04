// 이벤트 시작 날짜
const draw_event_start_date = document.querySelector('#draw_event_start_date');
// 이벤트 종료 날짜
const draw_event_end_date = document.querySelector('#draw_event_end_date');
// 수정 버튼
const drawModifyBtn = document.querySelector('.drawModifyBtn');
// 이벤트 등록 버튼
const addDrawBtn = document.querySelector('.addDrawBtn');
const draw_comment = document.querySelector('#draw_comment');
const commentLengthSpan = document.querySelector('.commentLengthSpan');
const draw_title = document.querySelector('#draw_title');
const titleLengthSpan = document.querySelector('.titleLengthSpan');
// ==========================================================================================

window.addEventListener('load', initDrawModify);
draw_event_start_date.addEventListener('change', initDrawEndDate);
drawModifyBtn.addEventListener('click', checkForm);
addDrawBtn.addEventListener('click', addDraw);
draw_comment.addEventListener('keyup', checkDrawCommentLength);
draw_title.addEventListener('keyup', checkDrawTitleLength);
// ==========================================================================================
function checkDrawTitleLength(){
    // 이벤트 설명 글자수
    let checkTitleLength = draw_title.value.length;
    titleLengthSpan.innerHTML = `(${checkTitleLength}/100)`;
}
function checkDrawCommentLength(){
    // 이벤트 설명 글자수
    let checkCommentLength = draw_comment.value.length;
    commentLengthSpan.innerHTML = `(${checkCommentLength}/100)`;
}
/*새로운 이벤트 등록*/
function addDraw(){
    location.href = '/draw/add';
}

// 수정 확인
function checkForm(){
    const result = confirm('이벤트를 수정하시겠습니까?');
    if(result){
        document.querySelector('#drawModifyForm').submit();
    }
}

// 이벤트 수정 페이지 초기화
function initDrawModify(){
    const offset = 1000 * 60 * 60 * 9;

    const startDateUTC = new Date(draw_event_start_date.previousElementSibling.value);
    const startDateGMT = new Date( startDateUTC.getTime() + offset );
    const endDateUTC = new Date(draw_event_end_date.previousElementSibling.value);
    const endDateGMT = new Date( endDateUTC.getTime() + offset );

    draw_event_start_date.value = startDateGMT.toISOString().slice(0,10);
    draw_event_end_date.value = endDateGMT.toISOString().slice(0,10);

    draw_event_end_date.setAttribute('min', draw_event_start_date.value);

    // 이벤트 제목 글자수 초기화
    const initTitleCount = draw_title.value.length;
    titleLengthSpan.innerHTML = `(${initTitleCount}/100)`;
    // 이벤트 설명 글자수 초기화
    const initCommentCount = draw_comment.value.length;
    commentLengthSpan.innerHTML = `(${initCommentCount}/100)`;
}

// 이벤트 종료 날짜 초기화
function initDrawEndDate(){
    draw_event_end_date.value = '';
    draw_event_end_date.setAttribute('min', draw_event_start_date.value);
}