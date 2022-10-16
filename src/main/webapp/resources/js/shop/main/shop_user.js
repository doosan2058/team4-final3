/*광고 컨테이너 */
const adContainer = document.querySelector('.adContainer');
/* 상품 더 불러오기 디비전 */
const viewMoreDiv = document.querySelector('.viewMoreDiv');
/* 현재 페이지 번호 */
const currentPageInput = document.querySelector('#currentPageInput');
/* 전체 페이지 번호 */
const totalPageInput = document.querySelector('#totalPageInput');
/* 상품 디비전 */
const itemAllDiv = document.querySelector('.itemAllDiv');
/* 카테고리 스팬들 */
const categorySpan = document.querySelectorAll('.categorySpan');
/* 검색 카테고리 전역 변수 */
let category = '0';
/* 브랜드 스팬들 */
const brandSpan = document.querySelectorAll('.brandSpan');
/* 검색 브랜드 전역 변수 */
let brand = '0';
//베스트 상품
const productBest = document.querySelectorAll('.productBest');
//상품
const product = document.querySelectorAll('.product');
//소팅 디비전
const sortMenuSpanDiv = document.querySelectorAll('.sortMenuSpanDiv');
//카테고리 더보기
const categorySpanAnchor = document.querySelector('.categorySpanAnchor');
//사이드 컨테이너
const side = document.querySelector('.side');
//사이드 컨테이너 닫기
const closeSideSpan = document.querySelector('.closeSideSpan');
// ==================================================================================================

window.onload = function () {
    /* 광고 복사 (1 뒤 , 5 앞) */
    let firstAd = adContainer.children[0].cloneNode(true);

    adContainer.append(firstAd);

    let count = 1;
    let interval = setInterval(() => {
        adContainer.style.transition = '.2s';
        adContainer.style.left = count * -100 + '%';
        count++;

        if (count == 6) {
            setTimeout(() => {
                adContainer.style.transition = '0s';
                adContainer.style.left = '0%';
                count = 1;
            }, 201);
        }

    }, 5 * 1000);

    //팝업
    //로컬 스토리지 확인, 없으면 null 반환
    //로컬 스토리지에 key가 있으면 만료시간 체크

    let text = localStorage.getItem('key');
    let date = new Date(text);
    let today = new Date();

    if (text != null) {


        if (today > date) {

            localStorage.removeItem('key');
            window.open("/shop/popup", "신상품", "width=500, height=500");
        }

    } else
        window.open("/shop/popup", "신상품", "width=500, height=500");

    //소팅 메뉴들 슬라이드업
    $('.sortInnerDiv').hide();

    //미디어쿼리
    mediaQuery();
}

// ==================================================================================================
window.addEventListener('resize', mediaQuery);

categorySpan.forEach(item => {
    item.addEventListener('click', setCategoryLoad);
});
brandSpan.forEach(item => {
    item.addEventListener('click', setBrandLoad);
})
viewMoreDiv.addEventListener('click', loadMoreItem);
productBest.forEach((item) => {
    item.addEventListener('mouseenter', changeBackgroundColor);
    item.addEventListener('mouseleave', rollbackBackgroundColor);
    item.addEventListener('click', goDetailPage);
});
product.forEach((item) => {
    item.addEventListener('mouseenter', changeBackgroundColor);
    item.addEventListener('mouseleave', rollbackBackgroundColor);
    item.addEventListener('click', goDetailPage);
})
sortMenuSpanDiv.forEach((item) => {
    item.addEventListener('click', sortingMenuSlide);
    item.addEventListener('mouseenter', changeSortingSpanColor);
    item.addEventListener('mouseleave', rollbackSortingSpanColor);
})
categorySpanAnchor.addEventListener('click', showCategoryDiv);
closeSideSpan.addEventListener('click', closeCategoryDiv);
categorySpanAnchor.addEventListener('mouseenter', showToolTipBox);
categorySpanAnchor.addEventListener('mouseleave', hideToolTipBox);
// ==================================================================================================
function closeCategoryDiv(){
    side.style.display = 'none';
}
function showCategoryDiv(){

    side.style.display = 'block';
}
function rollbackSortingSpanColor(){
    this.children[0].style.color = 'var(--basicFontColor)';
}
function changeSortingSpanColor(){
    this.children[0].style.color = 'var(--fontColor)';
}
//소팅 메뉴 슬라이드 함수
function sortingMenuSlide(){
    $(this.nextElementSibling).slideToggle();
}
//상세 페이지 이동
function goDetailPage(){
    const locationText = this.children[0].value;
    location.href = `/product/detail?product_id=${locationText}`;
}
//상품 호버 이벤트
function rollbackBackgroundColor() {
    this.style.backgroundColor = 'var(--sub2Color)';
    this.style.border = '3px solid var(--subColor)';
    
    this.children[1].children[0].style.color = 'white';
    this.children[2].children[0].style.transform = 'scale(1,1)';
}
//상품 호버 이벤트
function changeBackgroundColor() {
    this.style.backgroundColor = 'var(--fontColor)';
    this.style.border = '3px solid var(--fontColor)';
    this.children[1].children[0].style.color = 'var(--hoverColor)';
    this.children[2].children[0].style.transform = 'scale(1.1,1.1)';
}
//카테고리 클릭
function setCategoryLoad() {
    category = this.dataset.categoryId;
    brand = 0;
    const param = {category_id: category, brand_id : brand};
    $.ajax({
        type: 'post',
        url: '/shop/list/category',
        data: JSON.stringify(param),
        contentType: 'application/json; charset=utf-8',
        error: function () {
            alert('죄송합니다. 잠시후 다시 시도해 주세요.');
        },
        success: function (data) {
            $('.itemAllDiv').html(data);
            // 현제 페이지, 전체 페이지 업데이트
            const currentPageTemp = $('.currentPageTemp').val();
            const totalPageTemp = $('.totalPageTemp').val();
            currentPageInput.value = currentPageTemp;
            totalPageInput.value = totalPageTemp;

            if (currentPageInput.value == totalPageInput.value) {
                viewMoreDiv.style.display = 'none';
            } else {

                viewMoreDiv.style.display = 'flex';
            }
            //임시 페이지 정보 삭제
            $('.currentPageTemp').remove();
            $('.totalPageTemp').remove();

            //호버 이벤트 추가
            const product = document.querySelectorAll('.product');
            product.forEach((item) => {
                item.addEventListener('mouseenter', changeBackgroundColor);
                item.addEventListener('mouseleave', rollbackBackgroundColor);
                item.addEventListener('click', goDetailPage);
            });
        }
    });
}
/* 더보기 클릭 */
function loadMoreItem() {
    // 현재 페이지 + 1 전달
    let currentPage = parseInt(currentPageInput.value) + 1;

    const pageShop = {
        currentPage: currentPage,
        totalPage: totalPageInput.value,
        category_id: category,
        brand_id : brand
    }
    
    $.ajax({
        type: 'post',
        url: '/shop/list',
        data: JSON.stringify(pageShop),
        dataType: 'html',
        contentType: 'application/json; charset=utf-8',
        error: function (data) {
            alert('죄송합니다. 잠시후 다시 시도해 주세요.');
        },
        success: function (data) {
            $('.itemAllDiv').append(data);
            // 현제 페이지, 전체 페이지 업데이트
            const currentPageTemp = $('.currentPageTemp').val();
            const totalPageTemp = $('.totalPageTemp').val();
            currentPageInput.value = currentPageTemp;
            totalPageInput.value = totalPageTemp;

            if (currentPageInput.value == totalPageInput.value) {
                viewMoreDiv.style.display = 'none';
            } else {
                viewMoreDiv.style.display = 'flex';
            }
            //임시 페이지 정보 삭제
            $('.currentPageTemp').remove();
            $('.totalPageTemp').remove();

            //호버 이벤트 추가
            const product = document.querySelectorAll('.product');
            product.forEach((item) => {
                item.addEventListener('mouseenter', changeBackgroundColor);
                item.addEventListener('mouseleave', rollbackBackgroundColor);
                item.addEventListener('click', goDetailPage);
            });
        }
    });


}

//브랜드 클릭
function setBrandLoad() {
    brand = this.dataset.brandId;
    category = 0;
    const param = {category_id: category, brand_id : brand};
    $.ajax({
        type: 'post',
        url: '/shop/list/category',
        data: JSON.stringify(param),
        contentType: 'application/json; charset=utf-8',
        error: function () {
            alert('죄송합니다. 잠시후 다시 시도해 주세요.');
        },
        success: function (data) {
            $('.itemAllDiv').html(data);
            // 현제 페이지, 전체 페이지 업데이트
            const currentPageTemp = $('.currentPageTemp').val();
            const totalPageTemp = $('.totalPageTemp').val();
            currentPageInput.value = currentPageTemp;
            totalPageInput.value = totalPageTemp;

            if (currentPageInput.value == totalPageInput.value) {
                viewMoreDiv.style.display = 'none';
            } else {
                viewMoreDiv.style.display = 'flex';
            }
            //임시 페이지 정보 삭제
            $('.currentPageTemp').remove();
            $('.totalPageTemp').remove();

            //호버 이벤트 추가
            const product = document.querySelectorAll('.product');
            product.forEach((item) => {
                item.addEventListener('mouseenter', changeBackgroundColor);
                item.addEventListener('mouseleave', rollbackBackgroundColor);
            });
        }
    });
}







function mediaQuery(){
    const side = document.querySelector('.side');

    if (matchMedia("screen and (min-width:320px)").matches && matchMedia("screen and (max-width:767px)").matches) {
        // 스마트폰
        side.style.display = 'none';
        side.style.position = 'fixed';
        side.style.top = '80px';
        side.style.right = '0';
        side.style.width = '100%';
        side.style.zIndex = '99';
        closeSideSpan.style.zIndex = '100';
        $('.sortInnerDiv').slideUp();
    }
    if (matchMedia("screen and (min-width:768px)").matches && matchMedia("screen and (max-width:1023px)").matches) {
        // 태블릿
        side.style.display = 'none';
        side.style.position = 'fixed';
        side.style.top = '80px';
        side.style.right = '0';
        side.style.width = '100%';
        side.style.zIndex = '99';
        closeSideSpan.style.zIndex = '100';
        $('.sortInnerDiv').slideUp();
    }
    if (matchMedia("screen and (min-width:1024px)").matches) {
        // 데스크탑
        side.style.display = 'block';
        side.style.width = '20%';
        side.style.position = 'relative';
        closeSideSpan.style.zIndex = '-1';
        $('.sortInnerDiv').slideUp();

    }
}

