'use strict'

const clientPageNumHiddenInput = document.querySelector('#clientPageNumHiddenInput');
const qnaPageAnchor = document.querySelectorAll('.qnaPageAnchor');

const qnaLineDiv = document.querySelectorAll('.qnaLineDiv');
// ===================================================================================

window.addEventListener('load', qnaListInit);

qnaLineDiv.forEach((item) => {
    item.addEventListener('click', goQnaDetailPage);
})
// ===================================================================================

// 질문글 상세보기 이동
function goQnaDetailPage(){
    const publicText = this.children[0].value;
    if(publicText == 'n'){
        alert('비공개 게시글 입니다.');
        return;
    }
    else if(publicText == 'y'){
        const url = this.children[1].children[0].innerHTML;
        const member_id = this.children[2].children[0].innerHTML;
        const tempForm = document.createElement('form');
        const qna_id_input = document.createElement('input');
        const member_id_input = document.createElement('input');
        qna_id_input.setAttribute('type', 'hidden');
        qna_id_input.setAttribute('name', 'qna_id');
        qna_id_input.setAttribute('value', url);
        member_id_input.setAttribute('type', 'hidden');
        member_id_input.setAttribute('name', 'member_id');
        member_id_input.setAttribute('value', member_id);

        tempForm.setAttribute('method','get');
        tempForm.setAttribute('action',`/qna/detail/admin`);
        tempForm.appendChild(qna_id_input);
        tempForm.appendChild(member_id_input);
        document.body.append(tempForm);

        tempForm.submit();
    }
    else{
        alert('게시글 열람중 오류가 발생하였습니다. 관리자에게 문의해 주세요.');
        return;
    }
}



function qnaListInit() {
    // 현재 페이지 앵커 초기화
    qnaPageAnchor.forEach((item) => {
        if (item.innerHTML == clientPageNumHiddenInput.value) {
            item.style.color = 'var(--fontColor)';
            item.style.fontWeight = 'bolder';
            item.style.pointerEvents = 'none';
            item.style.borderBottom = '1px solid white';
        }
    });
}