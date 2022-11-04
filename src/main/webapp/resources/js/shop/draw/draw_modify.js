// 이벤트 시작 날짜
const draw_event_start_date = document.querySelector('#draw_event_start_date');
// 이벤트 종료 날짜
const draw_event_end_date = document.querySelector('#draw_event_end_date');
// 수정 버튼
const drawModifyBtn = document.querySelector('.drawModifyBtn');

// ==========================================================================================

window.addEventListener('load', initDrawModify);
draw_event_start_date.addEventListener('change', initDrawEndDate);
drawModifyBtn.addEventListener('click', checkForm);
// ==========================================================================================

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
}

// 이벤트 종료 날짜 초기화
function initDrawEndDate(){
    draw_event_end_date.value = '';
    draw_event_end_date.setAttribute('min', draw_event_start_date.value);
}