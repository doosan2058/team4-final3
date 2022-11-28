// 광고 컨테이너
const adContainer = document.querySelector('.adContainer');
/* 현재 페이지 번호 */
const currentPageInput = document.querySelector('#currentPageInput');
/* 전체 페이지 번호 */
const totalPageInput = document.querySelector('#totalPageInput');
//베스트 상품
const productBest = document.querySelectorAll('.productBest');
//상품
const product = document.querySelectorAll('.product');
//탑10 상품 디비전
const itemBestDiv = document.querySelector('.itemBestDiv');
//탑10 오른쪽 이동 스팬
const forwardSpan = document.querySelector('.forwardSpan');
//탑10 왼쪽 이동 스팬
const backSpan = document.querySelector('.backSpan');
//광고 네비
const adNavi = document.querySelectorAll('.adNavi');
//네비 전역 카운트
let count = 0;
// 입고순, 판매순, 가격순 검색 옵션
const optionLabel = document.querySelectorAll('.optionLabel');
// 현재 선택된 검색 옵션 디자인
const optionLabelUnderLineDiv = document.querySelector('#optionLabelUnderLineDiv');
// 옵션 선택
const optionSearchSpan = document.querySelector('#optionSearchSpan');
const youtubeListIcon = document.querySelectorAll('.youtubeListIcon');
const youtubeContainer = document.querySelector('.youtubeContainer');
const closeYoutubeContainerIcon = document.querySelector('.closeYoutubeContainerIcon');
const adIframe = document.querySelector('.adIframe')
const viewMoreSpan = document.querySelector('.viewMoreSpan');
// ==================================================================================================

window.addEventListener('load', shopMainInit);
window.addEventListener('resize', shopMainMediaQuery);

productBest.forEach((item) => {
    item.addEventListener('click', goDetailPage);
});
product.forEach((item) => {
    item.addEventListener('click', goDetailPage);
})
adNavi.forEach((item)=> {
    item.addEventListener('click', moveAd);
})
optionLabel.forEach((item) => {
    item.addEventListener('click', changeUnderLineColor);
});
optionSearchSpan.addEventListener('click', selectOption);
youtubeListIcon.forEach((item) => {
    item.addEventListener('click', showYoutubeCon);
});
youtubeContainer.addEventListener('click', closeYoutubeCon);
closeYoutubeContainerIcon.addEventListener('click', closeYoutubeCon);
viewMoreSpan.addEventListener('click', loadMoreItem);
// ==================================================================================================

//유튜브 컨테이너 닫기
function closeYoutubeCon(e) {
    if (e.target.className == 'youtubeContainer' || e.target.className == 'xi-close closeYoutubeContainerIcon') {
        adIframe.src = '';
        youtubeContainer.children[1].style.animation = 'hiddenModal 0.3s 1 forwards';
        setTimeout(function () {
            youtubeContainer.style.display = 'none';
        }, 300);
    }
}
//유튜브 컨테이너 보이기
function showYoutubeCon() {
    youtubeContainer.style.display = 'block';
    youtubeContainer.children[1].style.animation = 'showModal 0.3s 1 forwards';
    console.log(this.dataset.url);
    adIframe.src = this.dataset.url;

}

function selectOption(){
    let currentPage = 1;

    let searchOption = null;
    let obj_length = document.getElementsByName("searchOptionRadioBox").length;

    for (let i=0; i<obj_length; i++) {
        if (document.getElementsByName("searchOptionRadioBox")[i].checked == true) {
            searchOption = document.getElementsByName("searchOptionRadioBox")[i].value;
        }
    }
    const pageShop = {
        currentPage: currentPage,
        totalPage: totalPageInput.value,
        category_id: document.querySelector('.categorySelect').value,
        brand_id: document.querySelector('.brandSelect').value,
        searchOption : searchOption
    }

    $.ajax({
        type: 'post',
        url: '/shop/list',
        data: JSON.stringify(pageShop),
        dataType: 'html',
        contentType: 'application/json; charset=utf-8',
        error: function (data) {
            alert('상품 옵션 처리중 오류가 발생하였습니다. 관리자에게 문의해 주세요.');
        },
        success: function (data) {
            $('.itemAllDiv').html(data);
            // 현제 페이지, 전체 페이지 업데이트
            const currentPageTemp = $('.currentPageTemp').val();
            const totalPageTemp = $('.totalPageTemp').val();
            currentPageInput.value = currentPageTemp;
            totalPageInput.value = totalPageTemp;
            viewMoreSpan.style.display = 'block';

            //임시 페이지 정보 삭제
            $('.currentPageTemp').remove();
            $('.totalPageTemp').remove();

            if(currentPageInput.value == totalPageInput.value){
                viewMoreSpan.style.display = 'none';
            }

            //클릭 이벤트 추가
            const product = document.querySelectorAll('.product');
            product.forEach((item) => {
                item.addEventListener('click', goDetailPage);
            });
        }
    });
}

function changeUnderLineColor(){
    optionLabelUnderLineDiv.style.left = this.offsetLeft + 'px';
}

function moveAd(){
    adContainer.style.transition = '.2s';
    adContainer.style.left = parseInt(this.dataset.index) * -100 + '%';
    count = parseInt(this.dataset.index);
    for(let i = 0; i < adNavi.length; i++){
        adNavi[i].style.backgroundColor = 'white';
        adNavi[i].style.transform = 'scale(1,1)';
    }
    adNavi[count].style.backgroundColor = 'var(--fontColor)';
    adNavi[count].style.transform = 'scale(1.5,1.5)';
}
//시작 함수
function shopMainInit() {
    /* 광고 복사 (1 뒤 , 5 앞) */
    let firstAd = adContainer.children[0].cloneNode(true);
    adContainer.append(firstAd);

    count = 1;
    let interval = setInterval(() => {
        adContainer.style.transition = '.2s';
        adContainer.style.left = count * -100 + '%';
        for(let i = 0; i < adNavi.length; i++){
            adNavi[i].style.backgroundColor = 'white';
            adNavi[i].style.transform = 'scale(1,1)';
        }
        if(count < 5){
            adNavi[count].style.backgroundColor = 'var(--fontColor)';
            adNavi[count].style.transform = 'scale(1.5,1.5)';

        }
        count++;

        if (count == 6) {
            setTimeout(() => {
                adContainer.style.transition = '0s';
                adContainer.style.left = '0%';
                count = 1;
                adNavi[0].style.backgroundColor = 'var(--fontColor)';
                adNavi[0].style.transform = 'scale(1.5,1.5)';
            }, 201);
        }

    }, 5 * 1000);

    //탑 10 상품 복사
    for (let i = 0; i < 5; i++) {
        let copyProduct = itemBestDiv.children[i].cloneNode(true);
        itemBestDiv.append(copyProduct);
    }
    for (let i = 0; i < 5; i++) {
        let copyProduct = itemBestDiv.children[9].cloneNode(true);
        $('.itemBestDiv').prepend(copyProduct);
    }

    //팝업
    //로컬 스토리지 확인, 없으면 null 반환
    //로컬 스토리지에 key가 있으면 만료시간 체크
    //데스크탑 사이즈 일때만 팝업창 오픈
    if (matchMedia("screen and (min-width:1024px)").matches) {
        let text = localStorage.getItem('key');
        let date = new Date(text);
        let today = new Date();

        if (text != null) {
            if (today > date) {
                localStorage.removeItem('key');
                window.open("/shop/popup", "신상품", "width=320, height=500");
            }
        } else
            window.open("/shop/popup", "신상품", "width=320, height=500");
    }
    //미디어쿼리
    shopMainMediaQuery();
}

//상세 페이지 이동
function goDetailPage() {
    const locationText = this.children[0].value;
    location.href = `/product/detail?product_id=${locationText}`;
}

/* 더보기 클릭 */
function loadMoreItem() {
    // 현재 페이지 + 1 전달
    let currentPage = parseInt(currentPageInput.value) + 1;

    const pageShop = {
        currentPage: currentPage,
        totalPage: totalPageInput.value,
        category_id: document.querySelector('.categorySelect').value,
        brand_id: document.querySelector('.brandSelect').value,
        searchOption : document.querySelector('.searchOptionRadioBox').value
    }


    $.ajax({
        type: 'post',
        url: '/shop/list',
        data: JSON.stringify(pageShop),
        dataType: 'html',
        contentType: 'application/json; charset=utf-8',
        error: function (data) {
            alert('상품 더보기 처리중 오류가 발생하였습니다. 관리자에게 문의해 주세요.');
        },
        success: function (data) {
            $('.itemAllDiv').append(data);
            // 현제 페이지, 전체 페이지 업데이트
            const currentPageTemp = $('.currentPageTemp').val();
            const totalPageTemp = $('.totalPageTemp').val();
            currentPageInput.value = currentPageTemp;
            totalPageInput.value = totalPageTemp;

            if(currentPageInput.value == totalPageInput.value){
                viewMoreSpan.style.display = 'none';
            }

            //임시 페이지 정보 삭제
            $('.currentPageTemp').remove();
            $('.totalPageTemp').remove();

            //클릭 이벤트 추가
            const product = document.querySelectorAll('.product');
            product.forEach((item) => {
                item.addEventListener('click', goDetailPage);
            });
        }
    });


}

//탑10 오른쪽 이동 데스크탑
function moveRightD() {
    itemBestDiv.style.transition = '.3s';

    if(itemBestDiv.style.left == 20 * -14 + '%'){
        itemBestDiv.style.left = parseInt((itemBestDiv.style.left).replace('%', '')) - 20 + '%';
        setTimeout(function (){
            itemBestDiv.style.transition = '0s';
            itemBestDiv.style.left = 20 * -5 + '%';

        } , 301);
    }
    else if(itemBestDiv.style.left == ''){
        itemBestDiv.style.left = '-120%';
    }
    else
        itemBestDiv.style.left = parseInt((itemBestDiv.style.left).replace('%', '')) - 20 + '%';

}
//탑10 오른쪽 이동 태블릿
function moveRightT() {
    itemBestDiv.style.transition = '.3s';

    if(itemBestDiv.style.left == 50 * -14 + '%'){
        itemBestDiv.style.left = parseInt((itemBestDiv.style.left).replace('%', '')) - 50 + '%';
        setTimeout(function (){
            itemBestDiv.style.transition = '0s';
            itemBestDiv.style.left = 50 * -5 + '%';
            } , 301);
    }
    else if(itemBestDiv.style.left == ''){
        itemBestDiv.style.left = '-300%';
    }
    else
    itemBestDiv.style.left = parseInt((itemBestDiv.style.left).replace('%', '')) - 50 + '%';

}
//탑10 오른쪽 이동 스마트폰
function moveRightS() {
    itemBestDiv.style.transition = '.3s';

    if(itemBestDiv.style.left == 100 * -14 + '%'){
        itemBestDiv.style.left = parseInt((itemBestDiv.style.left).replace('%', '')) - 100 + '%';
        setTimeout(function (){
            itemBestDiv.style.transition = '0s';
            itemBestDiv.style.left = 100 * -5 + '%';
        } , 301);
    }
    else if(itemBestDiv.style.left == ''){
        itemBestDiv.style.left = '-600%';
    }
    else
    itemBestDiv.style.left = parseInt((itemBestDiv.style.left).replace('%', '')) - 100 + '%';

}
//탑10 왼쪽 이동 데스크탑
function moveLeftD() {

    itemBestDiv.style.transition = '.3s';

    if(itemBestDiv.style.left == `-20%`){
        itemBestDiv.style.left = parseInt((itemBestDiv.style.left).replace('%', '')) + 20 + '%';
        setTimeout(function (){
            itemBestDiv.style.transition = '0s';
            itemBestDiv.style.left = 20 * -10 + '%';
            } , 301);
    }
    else if(itemBestDiv.style.left == ''){
        itemBestDiv.style.left = '-80%';
    }
    else
    itemBestDiv.style.left = parseInt((itemBestDiv.style.left).replace('%', '')) + 20 + '%';
}
//탑10 왼쪽 이동 태블릿
function moveLeftT() {

    itemBestDiv.style.transition = '.3s';

    if(itemBestDiv.style.left == `-50%`){
        itemBestDiv.style.left = parseInt((itemBestDiv.style.left).replace('%', '')) + 50 + '%';
        setTimeout(function (){
            itemBestDiv.style.transition = '0s';
            itemBestDiv.style.left = 50 * -10 + '%';
            } , 301);
    }
    else if(itemBestDiv.style.left == ''){
        itemBestDiv.style.left = '-200%';
    }
    else
    itemBestDiv.style.left = parseInt((itemBestDiv.style.left).replace('%', '')) + 50 + '%';
}
//탑10 왼쪽 이동 스마트폰
function moveLeftS() {
    itemBestDiv.style.transition = '.3s';

    if(itemBestDiv.style.left == `-100%`){
        itemBestDiv.style.left = parseInt((itemBestDiv.style.left).replace('%', '')) + 100 + '%';
        setTimeout(function (){
            itemBestDiv.style.transition = '0s';
            itemBestDiv.style.left = 100 * -10 + '%';
            } , 301);
    }
    else if(itemBestDiv.style.left == ''){
        itemBestDiv.style.left = '-400%';
    }
    else
    itemBestDiv.style.left = parseInt((itemBestDiv.style.left).replace('%', '')) + 100 + '%';
}

function shopMainMediaQuery() {

    // 스마트폰
    if (matchMedia("screen and (min-width:1px)").matches && matchMedia("screen and (max-width:767px)").matches) {
        //탑10 상품 슬라이드 이벤트 초기화
        forwardSpan.removeEventListener('click', moveRightD);
        backSpan.removeEventListener('click', moveLeftD);
        forwardSpan.removeEventListener('click', moveRightT);
        backSpan.removeEventListener('click', moveLeftT);
        forwardSpan.addEventListener('click', moveRightS);
        backSpan.addEventListener('click', moveLeftS);
    }
    // 태블릿
    if (matchMedia("screen and (min-width:768px)").matches && matchMedia("screen and (max-width:1023px)").matches) {
        //탑10 상품 슬라이드 이벤트 초기화
        forwardSpan.removeEventListener('click', moveRightD);
        backSpan.removeEventListener('click', moveLeftD);
        forwardSpan.removeEventListener('click', moveRightS);
        backSpan.removeEventListener('click', moveLeftS);
        forwardSpan.addEventListener('click', moveRightT);
        backSpan.addEventListener('click', moveLeftT);
    }
    // 데스크탑
    if (matchMedia("screen and (min-width:1024px)").matches) {
        //탑10 상품 슬라이드 이벤트 초기화
        forwardSpan.removeEventListener('click', moveRightS);
        backSpan.removeEventListener('click', moveLeftS);
        forwardSpan.removeEventListener('click', moveRightT);
        backSpan.removeEventListener('click', moveLeftT);
        forwardSpan.addEventListener('click', moveRightD);
        backSpan.addEventListener('click', moveLeftD);
    }
}

