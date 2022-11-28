'use strict'

// 이벤트 응모 버튼
const drawApplicationBtn = document.querySelectorAll('.drawApplicationBtn');
// 당첨자 확인 버튼
const showWinnerBtn = document.querySelectorAll('.showWinnerBtn');
// 당첨자 리스트 컨테이너
const winnerListContainer = document.querySelector('.winnerListContainer');
// 당첨자 리스트 컨테이너 닫기
const closeWinnerListContainerIcon = document.querySelector('.closeWinnerListContainerIcon');
// =======================================================================================

drawApplicationBtn.forEach((item) => {
    item.addEventListener('click', applicationDraw);
});
showWinnerBtn.forEach( (item) => {
    item.addEventListener('click', showWinnerList);
});
closeWinnerListContainerIcon.addEventListener('click', closeWinnerList);



// =======================================================================================

function buyLimitedProduct(){
    if(this.innerHTML == '미당첨')
        alert('다음에 다시 응모해 주세요.');
    else if(this.previousElementSibling.innerHTML == loginCheckHiddenInput.value){
        const result = confirm('당첨을 축하드립니다. 이상품을 구매하시겠어요?');
        if(result){
            const tempForm = document.createElement('form');
            const product_id = document.createElement('input');
            const pid = document.querySelector('#winnerListDivDrawIdInput').value;
            product_id.setAttribute('type', 'hidden');
            product_id.setAttribute('name', 'product_id');
            product_id.setAttribute('value', pid);
            tempForm.appendChild(product_id);
            tempForm.setAttribute('method','post');
            tempForm.setAttribute('action','/order');
            document.body.appendChild(tempForm);
            tempForm.submit();
        }
    }
    else
        alert('당첨된 아이디로 로그인 해 주세요.');
}

/*당첨자 리스트 보기*/
function showWinnerList(){
    winnerListContainer.style.display = 'block';
    winnerListContainer.children[0].style.animation = 'showModal 0.3s 1 forwards';
    document.querySelector('#winnerListDivDrawIdInput').value = this.nextElementSibling.value;
    // 당첨자 리스트 비동기 호출
    const draw_id = this.previousElementSibling.previousElementSibling.value;
    const param = { draw_id : draw_id};
    $.ajax({
        url : '/draw/drawEnterList',
        type : 'post',
        data : JSON.stringify(param),
        contentType: 'application/json',
        dataType : 'json',
        error : function(){
            alert('죄송합니다. 잠시후 다시 시도해 주세요.');
        },
        success : function (data){
            const tempContainer = document.createElement('div');

            // 리스트 길이만큼 반복
            for(let i = 0; i < data.length; i++){
                const tempDiv = document.createElement('div');
                tempDiv.className = 'winnerLineDiv';
                const tempIdSpan = document.createElement('span');
                tempIdSpan.innerHTML = data[i].member_id;
                const tempResultSpan = document.createElement('span');
                tempResultSpan.innerHTML = ( data[i].draw_winning == 'y' ) ? '당첨' : '미당첨';
                tempDiv.appendChild(tempIdSpan);
                tempDiv.appendChild(tempResultSpan);
                tempResultSpan.addEventListener('click', buyLimitedProduct);
                tempContainer.appendChild(tempDiv);
            }

            $('.winnerListBottom').html(tempContainer);

        }
    });
}

/*당첨자 리스트 컨테이너 닫기*/
function closeWinnerList(){
    winnerListContainer.children[0].style.animation = 'hiddenModal 0.3s 1 forwards';
    setTimeout(function (){
        winnerListContainer.style.display = 'none';
    } , 300);
}

// 응모하기
function applicationDraw(){

    let today = new Date();
    let eventStartDate = new Date(this.previousElementSibling.previousElementSibling.previousElementSibling.value);
    const lastDate = this.previousElementSibling.previousElementSibling.value + ' 23:59:59';
    let eventEndDate = new Date(lastDate);

    if(today < eventStartDate){
        alert('아직 이벤트 응모 기간이 아닙니다.');
        return;
    }
    else if(today > eventEndDate){
        alert('이벤트 응모 기간이 지났습니다.');
        return;
    }
    else{

        const login_id = loginCheckHiddenInput.value;
        if(login_id == '' || login_id == null || login_id == undefined || login_id.trim().length == 0){
            alert('로그인이 필요한 서비스 입니다.');
        }
        else{
            const draw_id = this.previousElementSibling.value;
            const member_id = login_id;
            // 이벤트 pk, 로그인 아이디
            const param = {draw_id : draw_id, member_id : member_id};

            $.ajax({
                url : '/draw/applicationDraw',
                type : 'post',
                data : JSON.stringify(param),
                contentType : 'application/json',
                dataType : 'text',
                error : function(){
                    alert('죄송합니다. 잠시후 다시 시도해 주세요.');
                },
                success : function (data){
                    alert(data);
                }
            });
        }
    }

}