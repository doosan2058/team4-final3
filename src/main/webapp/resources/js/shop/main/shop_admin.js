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
//탑10 상품 디비전
const itemBestDiv = document.querySelector('.itemBestDiv');
//탑10 오른쪽 이동 스팬
const forwardSpan = document.querySelector('.forwardSpan');
//탑10 왼쪽 이동 스팬
const backSpan = document.querySelector('.backSpan');
// ==================================================================================================

window.addEventListener('load', loadFunc);


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
//시작 함수
function loadFunc() {
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
                window.open("/shop/popup", "신상품", "width=500, height=500");
            }
        } else
            window.open("/shop/popup", "신상품", "width=500, height=500");
    }
	//소팅 메뉴들 슬라이드업
    $('.sortInnerDiv').hide();

    //미디어쿼리
    mediaQuery();
}

function closeCategoryDiv() {
    side.style.display = 'none';
}

function showCategoryDiv() {

    side.style.display = 'block';
}

function rollbackSortingSpanColor() {
    //데스크탑 사이즈 일때만
    if (matchMedia("screen and (min-width:1024px)").matches) {
        this.children[0].style.color = 'var(--basicFontColor)';
    }
}

function changeSortingSpanColor() {
    //데스크탑 사이즈 일때만
    if (matchMedia("screen and (min-width:1024px)").matches) {
        this.children[0].style.color = 'var(--fontColor)';
    }
}

//소팅 메뉴 슬라이드 함수
function sortingMenuSlide() {
    $(this.nextElementSibling).slideToggle();
}

//상세 페이지 이동
function goDetailPage() {
    const locationText = this.children[0].value;
    location.href = `/product/detail?product_id=${locationText}`;
}

//상품 호버 종료 이벤트
function rollbackBackgroundColor() {
    //데스크탑 사이즈일때만
    if (matchMedia("screen and (min-width:1024px)").matches) {
        //배경
        this.style.backgroundColor = 'var(--sub2Color)';
        this.style.border = '3px solid var(--subColor)';
        this.style.transform = 'translateY(0)';
        //폰트
        this.children[1].children[0].style.color = 'white';
        this.children[3].children[0].style.transform = 'scale(1,1)';
    }

}

//상품 호버 시작 이벤트
function changeBackgroundColor() {
    //데스크탑 사이즈일때만
    if (matchMedia("screen and (min-width:1024px)").matches) {
        //배경
        this.style.backgroundColor = 'var(--mainColor)';
        this.style.border = '3px solid var(--subColor)';
        this.style.transform = 'translateY(-5px)';

        //폰트
        this.children[1].children[0].style.color = 'var(--hoverColor)';
        this.children[3].children[0].style.transform = 'scale(1.1,1.1)';
    }

}

//카테고리 클릭
function setCategoryLoad() {
    category = this.dataset.categoryId;
    brand = 0;
    const param = {category_id: category, brand_id: brand};
    $.ajax({
        type: 'post',
        url: '/shop/list/category/admin',
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
        brand_id: brand
    }

    $.ajax({
        type: 'post',
        url: '/shop/list/admin',
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
    const param = {category_id: category, brand_id: brand};
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

//탑10 오른쪽 이동 데스크탑
function moveRightD() {
    itemBestDiv.style.transition = '.3s';

    if (itemBestDiv.style.left == 20 * -14 + '%') {
        itemBestDiv.style.pointerEvents = 'none';
        setTimeout(function () {
            itemBestDiv.style.transition = '0s';
            itemBestDiv.style.left = 20 * -5 + '%';
            itemBestDiv.style.pointerEvents = 'auto';
        }, 301);
    }
    itemBestDiv.style.left = parseInt((itemBestDiv.style.left).replace('%', '')) - 20 + '%';

}

//탑10 오른쪽 이동 태블릿
function moveRightT() {
    itemBestDiv.style.transition = '.3s';

    if (itemBestDiv.style.left == 50 * -14 + '%') {
        itemBestDiv.style.pointerEvents = 'none';
        setTimeout(function () {
            itemBestDiv.style.transition = '0s';
            itemBestDiv.style.left = 50 * -5 + '%';
            itemBestDiv.style.pointerEvents = 'auto';
        }, 301);
    }
    itemBestDiv.style.left = parseInt((itemBestDiv.style.left).replace('%', '')) - 50 + '%';

}

//탑10 오른쪽 이동 스마트폰
function moveRightS() {
    itemBestDiv.style.transition = '.3s';

    if (itemBestDiv.style.left == 100 * -14 + '%') {
        itemBestDiv.style.pointerEvents = 'none';
        setTimeout(function () {
            itemBestDiv.style.transition = '0s';
            itemBestDiv.style.left = 100 * -5 + '%';
            itemBestDiv.style.pointerEvents = 'auto';
        }, 301);
    }
    itemBestDiv.style.left = parseInt((itemBestDiv.style.left).replace('%', '')) - 100 + '%';

}

//탑10 왼쪽 이동 데스크탑
function moveLeftD() {

    itemBestDiv.style.transition = '.3s';

    if (itemBestDiv.style.left == `-20%`) {
        itemBestDiv.style.pointerEvents = 'none';
        setTimeout(function () {
            itemBestDiv.style.transition = '0s';
            itemBestDiv.style.left = 20 * -10 + '%';
            itemBestDiv.style.pointerEvents = 'auto';
        }, 301);
    }
    itemBestDiv.style.left = parseInt((itemBestDiv.style.left).replace('%', '')) + 20 + '%';
}

//탑10 왼쪽 이동 태블릿
function moveLeftT() {

    itemBestDiv.style.transition = '.3s';

    if (itemBestDiv.style.left == `-50%`) {
        itemBestDiv.style.pointerEvents = 'none';
        setTimeout(function () {
            itemBestDiv.style.transition = '0s';
            itemBestDiv.style.left = 50 * -10 + '%';
            itemBestDiv.style.pointerEvents = 'auto';
        }, 301);
    }
    itemBestDiv.style.left = parseInt((itemBestDiv.style.left).replace('%', '')) + 50 + '%';
}

//탑10 왼쪽 이동 스마트폰
function moveLeftS() {

    itemBestDiv.style.transition = '.3s';

    if (itemBestDiv.style.left == `-100%`) {
        itemBestDiv.style.pointerEvents = 'none';
        setTimeout(function () {
            itemBestDiv.style.transition = '0s';
            itemBestDiv.style.left = 100 * -10 + '%';
            itemBestDiv.style.pointerEvents = 'auto';
        }, 301);
    }
    itemBestDiv.style.left = parseInt((itemBestDiv.style.left).replace('%', '')) + 100 + '%';
}

function mediaQuery() {
    const side = document.querySelector('.side');

    if (matchMedia("screen and (min-width:320px)").matches && matchMedia("screen and (max-width:767px)").matches) {
        // 스마트폰
        side.style.display = 'none';
        side.style.position = 'fixed';
        side.style.top = '0';
        side.style.right = '0';
        side.style.width = '100%';
        side.style.zIndex = '999';
        closeSideSpan.style.zIndex = '100';
        $('.sortInnerDiv').slideUp();
        //탑10 상품 이동
        itemBestDiv.style.left = '-500%';

        forwardSpan.removeEventListener('click', moveRightD);
        backSpan.removeEventListener('click', moveLeftD);
        forwardSpan.removeEventListener('click', moveRightT);
        backSpan.removeEventListener('click', moveLeftT);
        forwardSpan.addEventListener('click', moveRightS);
        backSpan.addEventListener('click', moveLeftS);
    }
    if (matchMedia("screen and (min-width:768px)").matches && matchMedia("screen and (max-width:1023px)").matches) {
        // 태블릿
        side.style.display = 'none';
        side.style.position = 'fixed';
        side.style.top = '0';
        side.style.right = '0';
        side.style.width = '100%';
        side.style.zIndex = '999';
        closeSideSpan.style.zIndex = '100';
        $('.sortInnerDiv').slideUp();
        //탑10 상품 이동
        itemBestDiv.style.left = '-250%';

        forwardSpan.removeEventListener('click', moveRightD);
        backSpan.removeEventListener('click', moveLeftD);
        forwardSpan.removeEventListener('click', moveRightS);
        backSpan.removeEventListener('click', moveLeftS);
        forwardSpan.addEventListener('click', moveRightT);
        backSpan.addEventListener('click', moveLeftT);
    }
    if (matchMedia("screen and (min-width:1024px)").matches) {
        // 데스크탑
        side.style.display = 'block';
        side.style.width = '20%';
        side.style.position = 'relative';
        closeSideSpan.style.zIndex = '-1';
        $('.sortInnerDiv').slideUp();
        //탑10 상품 이동
        itemBestDiv.style.left = '-100%';

        forwardSpan.removeEventListener('click', moveRightS);
        backSpan.removeEventListener('click', moveLeftS);
        forwardSpan.removeEventListener('click', moveRightT);
        backSpan.removeEventListener('click', moveLeftT);
        forwardSpan.addEventListener('click', moveRightD);
        backSpan.addEventListener('click', moveLeftD);


    }
}

