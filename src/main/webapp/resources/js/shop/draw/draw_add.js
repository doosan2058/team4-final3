// 이벤트 상품 등록
const addDrawProductDiv = document.querySelector('.addDrawProductDiv');
// 상품 선택 컨테이너
const selectProductContainer = document.querySelector('.selectProductContainer');
// 상품 선택 컨테이너 닫기
const closeIcon = document.querySelector('.closeIcon');
// 상품 검색
const selectSpan = document.querySelector('.selectSpan');
// 카테고리 셀렉트
const categorySelect = document.querySelector('#categorySelect');
// 카테고리 셀렉트
const brandSelect = document.querySelector('#brandSelect');
// 상품 취소
const closeImgWrapDivIcon = document.querySelector('.closeImgWrapDivIcon');

// 이벤트 시작 날짜
const draw_event_start_date = document.querySelector('#draw_event_start_date');
// 이벤트 종료 날짜
const draw_event_end_date = document.querySelector('#draw_event_end_date');
// 이벤트 제목
const draw_title = document.querySelector('#draw_title');
// 이벤트 내용
const draw_comment = document.querySelector('#draw_comment');
// 이벤트 등록 버튼
const addDrawBtn = document.querySelector('.addDrawBtn');
const commentLengthSpan = document.querySelector('.commentLengthSpan');
const titleLengthSpan = document.querySelector('.titleLengthSpan');
// =======================================================================================
window.addEventListener('load', initDrawAdd);
addDrawProductDiv.addEventListener('click', showSelectContainer);
closeIcon.addEventListener('click', closeSelectContainer)
selectSpan.addEventListener('click', searchLimitedProduct);
closeImgWrapDivIcon.addEventListener('click', cancelProduct);
addDrawBtn.addEventListener('click', addDraw);
draw_event_start_date.addEventListener('change', initDrawEndDate);
draw_comment.addEventListener('keyup', checkDrawCommentLength);
draw_title.addEventListener('keyup', checkDrawTitleLength);
// =======================================================================================
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

function checkDrawAdd() {
    const product_id = document.querySelector('#product_id').value;

    if (product_id == '' || product_id == undefined || product_id == null || product_id.trim().length == 0) {
        alert('상품을 등록해 주세요.');
        return false;
    } else if (draw_title.value == '' || draw_title.value == undefined || draw_title.value == null || draw_title.value.trim().length == 0) {
        alert('이벤트 제목을 입력해 주세요.');
        return false;
    } else if (draw_comment.value == '' || draw_comment.value == undefined || draw_comment.value == null || draw_comment.value.trim().length == 0) {
        alert('이벤트 내용을 입력해 주세요.');
        return false;
    } else
        return true;
}

// 상품 선택 컨테이너 열기
function showSelectContainer() {
    selectProductContainer.style.display = 'block';
    selectProductContainer.children[0].style.animation = 'showModal 0.3s 1 forwards';
    document.querySelector('.drawProductImg').style.display = 'block';
}

// 상품 선택 컨테이너 닫기
function closeSelectContainer() {
    selectProductContainer.children[0].style.animation = 'hiddenModal 0.3s 1 forwards';
    setTimeout(function () {
        selectProductContainer.style.display = 'none';
        document.querySelector('.drawProductImg').style.display = 'none';
    }, 300)
}

// 한정판 상품 검색
function searchLimitedProduct() {
    const category = categorySelect.value;
    const brand = brandSelect.value;
    const param = {product_category_id: category, product_brand_id: brand};

    $.ajax({
        url: '/draw/search',
        type: 'post',
        data: JSON.stringify(param),
        contentType: 'application/json',
        dataType: 'json',
        error: function () {
            alert('죄송합니다. 잠시후 다시 시도해 주세요.');
        },
        success: function (data) {
            // 결과 구역 초기화
            $('.selectProductDivRightMain').html('');

            if (data.length > 0) {
                for (let i = 0; i < data.length; i++) {
                    const drawLineDivTemp = document.createElement('div');
                    drawLineDivTemp.className = 'drawLineDiv';

                    const drawImgTemp = document.createElement('img');
                    drawImgTemp.className = 'drawImg';
                    drawImgTemp.src = data[i].product_img_url1;

                    const drawIdSpanTemp = document.createElement('span');
                    drawIdSpanTemp.className = 'drawIdSpan';
                    drawIdSpanTemp.innerHTML = data[i].product_id;

                    const drawNameSpanTemp = document.createElement('span');
                    drawNameSpanTemp.className = 'drawNameSpan';
                    drawNameSpanTemp.innerHTML = data[i].product_name;

                    const drawPriceSpanTemp = document.createElement('span');
                    drawPriceSpanTemp.className = 'drawPriceSpan';
                    drawPriceSpanTemp.innerHTML = data[i].product_price;

                    drawLineDivTemp.appendChild(drawImgTemp);
                    drawLineDivTemp.appendChild(drawIdSpanTemp);
                    drawLineDivTemp.appendChild(drawNameSpanTemp);
                    drawLineDivTemp.appendChild(drawPriceSpanTemp);

                    document.querySelector('.selectProductDivRightMain').appendChild(drawLineDivTemp);
                    drawLineDivTemp.addEventListener('mouseenter', showImg);
                    drawLineDivTemp.addEventListener('mouseleave', hideImg);
                    drawLineDivTemp.addEventListener('click', selectProduct);
                }
            } else {
                const drawEmptySpanTemp = document.createElement('span');
                drawEmptySpanTemp.innerHTML = '검색 결과가 없습니다.';
                drawEmptySpanTemp.className = 'drawEmptySpan';
                document.querySelector('.selectProductDivRightMain').appendChild(drawEmptySpanTemp);
            }


        }
    });
}

/*선택 상품 취소*/
function cancelProduct() {
    document.querySelector('.productMainImgWrapDiv').style.display = 'none';
    document.querySelector('.addDrawProductDiv').style.display = 'flex';
    document.querySelector('.selectedProductNameSpan').innerHTML = '';
    document.querySelector('.selectedProductPriceSpan').innerHTML = '';
    document.querySelector('#product_id').value = '';
    document.querySelector('.productMainImg').src = '';
}

function selectProduct() {

    document.querySelector('.selectedProductNameSpan').innerHTML = this.children[2].innerHTML;
    document.querySelector('.selectedProductPriceSpan').innerHTML = this.children[3].innerHTML + '원';
    document.querySelector('#product_id').value = this.children[1].innerHTML;
    document.querySelector('.productMainImg').src = this.children[0].src;
    document.querySelector('.productMainImgWrapDiv').style.display = 'flex';
    document.querySelector('.addDrawProductDiv').style.display = 'none';
    closeSelectContainer();

}

function showImg() {
    document.querySelector('.drawProductImg').src = this.children[0].src;
}

function hideImg() {
    document.querySelector('.drawProductImg').src = '';
}

// 이벤트 수정 페이지 초기화
function initDrawAdd() {
    const offset = 1000 * 60 * 60 * 9;

    const todayGMTMills = new Date().getTime();
    const todayUTCMills = todayGMTMills + offset;

    const today = new Date(todayUTCMills);

    draw_event_start_date.setAttribute('min', today.toISOString().slice(0, 10));

}

// 이벤트 종료 날짜 초기화
function initDrawEndDate() {
    draw_event_end_date.value = '';
    draw_event_end_date.setAttribute('min', draw_event_start_date.value);
}