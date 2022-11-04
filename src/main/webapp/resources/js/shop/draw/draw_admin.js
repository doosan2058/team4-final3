'use strict'

// 이벤트 수정 버튼
const drawModifyBtn = document.querySelectorAll('.drawModifyBtn');
// 당첨자 확인 버튼
const showWinnerBtn = document.querySelectorAll('.showWinnerBtn');
// 당첨자 리스트 컨테이너
const winnerListContainer = document.querySelector('.winnerListContainer');
// 당첨자 리스트 컨테이너 닫기
const closeWinnerListContainerIcon = document.querySelector('.closeWinnerListContainerIcon');
// 새로운 이벤트 등록 버튼
const addDrawBtn = document.querySelector('.addDrawBtn');
// =======================================================================================

drawModifyBtn.forEach((item) => {
    item.addEventListener('click', goDrawModify);
});
showWinnerBtn.forEach( (item) => {
    item.addEventListener('click', showWinnerList);
});
closeWinnerListContainerIcon.addEventListener('click', closeWinnerList);
addDrawBtn.addEventListener('click', addDraw);


// =======================================================================================

/*새로운 이벤트 등록*/
function addDraw(){
    location.href = '/draw/add';
}
/*당첨자 리스트 보기*/
function showWinnerList(){
    winnerListContainer.style.display = 'block';
    winnerListContainer.children[0].style.animation = 'showModal 0.3s 1 forwards';

    // 당첨자 리스트 비동기 호출
    const draw_id = this.previousElementSibling.previousElementSibling.value;
    const param = { draw_id : draw_id};

    document.querySelector('#winnerListContainerDrawId').value = draw_id;

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
                const tempIdAnchor = document.createElement('a');
                tempIdAnchor.innerHTML = data[i].member_id;
                tempIdAnchor.href = `/admin/detail?member_id=${data[i].member_id}`;
                const tempResultSpan = document.createElement('span');

                tempResultSpan.innerHTML = ( data[i].draw_winning == 'y' ) ? '당첨' : '미당첨';
                tempDiv.appendChild(tempIdAnchor);
                tempDiv.appendChild(tempResultSpan);
                tempResultSpan.addEventListener('click', enterUser);
                tempContainer.appendChild(tempDiv);
            }

            $('.winnerListBottom').html(tempContainer);

        }
    });

    // 이벤트 인원 정보 비동기 호출
    $.ajax({
        url : '/draw/drawEnterInfo',
        type : 'post',
        data : JSON.stringify(param),
        contentType: 'application/json',
        dataType : 'json',
        error : function(){
            alert('죄송합니다. 잠시후 다시 시도해 주세요.');
        },
        success : function (data){
            document.querySelector('.reqruitSpan').innerHTML = `${data.draw_reqruit}`;
            document.querySelector('.applicationSpan').innerHTML = `${data.application}`;
            document.querySelector('.winningSpan').innerHTML = `${data.draw_winning}`;
        }
    });
}

/*당첨 처리 */
function enterUser(e){
    if(this.innerHTML == '당첨'){
        alert('이미 당첨된 회원 입니다.');
        return;
    }
    else if(document.querySelector('.reqruitSpan').innerHTML == document.querySelector('.winningSpan').innerHTML){
        alert('모집 정원이 초과 입니다.');
        return;
    }
    const result = confirm('이 회원을 당첨 처리 할까요?');

    if(result){
        const member_id = this.previousElementSibling.innerHTML;
        const draw_id = document.querySelector('#winnerListContainerDrawId').value;

        const param = { draw_id : draw_id, member_id : member_id };

        $.ajax({
            type : 'post',
            url : '/draw/userEnter',
            data : JSON.stringify(param),
            contentType : 'application/json',
            dataType : 'text',
            error : function (){
                alert('죄송합니다. 잠시후 다시 시도해 주세요.');
            },
            success : function (data){
               alert(data);
               e.target.innerHTML = '당첨';
               document.querySelector('.winningSpan').innerHTML = parseInt(document.querySelector('.winningSpan').innerHTML) + 1;
            }
        });
    }
}

/*당첨자 리스트 컨테이너 닫기*/
function closeWinnerList(){
    winnerListContainer.children[0].style.animation = 'hiddenModal 0.3s 1 forwards';
    setTimeout(function (){
        winnerListContainer.style.display = 'none';
    } , 300);
}

/*이벤트 수정 페이지 이동*/
function goDrawModify(){
    const tempUri =  '/draw/modify?draw_id=' + this.previousElementSibling.value;
    location.href = tempUri;
}

