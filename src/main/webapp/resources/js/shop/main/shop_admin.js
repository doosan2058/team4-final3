/*광고 컨테이너 */
const adContainer = document.querySelector('.adContainer');
/*광고 왼쪽 버튼 */
//const carousel_button_L = document.querySelector('.carousel_button_L');
/*광고 오른쪽 버튼 */
//const carousel_button_R = document.querySelector('.carousel_button_R');
/*상품 탑 10 컨테이너 왼쪽 이동 버튼 */
const best_button_L = document.querySelector('.best_button_L');
/*상품 탑 10 컨테이너 오른쪽 이동 버튼 */
const best_button_R = document.querySelector('.best_button_R');
/*상품 탐 10 컨테이너 */
const section_best_items = document.querySelector('.section_best_items');
/*상품 탑 10 부모 컨테이너 */
const section_best = document.querySelector('.section_best');
/*상품 더 불러오기 디비전 */
const section_view_more = document.querySelector('.section_view_more');
/*현재 페이지 번호 */
const currentPageInput = document.querySelector('#currentPageInput');
/*전체 페이지 번호 */
const totalPageInput = document.querySelector('#totalPageInput');
/*상품 디비전 */
const productDiv = document.querySelector('.productDiv');
const categorySpan = document.querySelectorAll('.categorySpan');
/* 검색 태그 전역 변수 */
let category = '0';
//==================================================================================================

window.onload = function(){
	 /* 광고 복사 (1 뒤 , 5 앞) */
    let firstAd = adContainer.children[0].cloneNode(true);
//    let lastAd = adContainer.children[4].cloneNode(true);
//    adContainer.insertBefore(lastAd,adContainer.children[0]);
    adContainer.append(firstAd);
    
    let count = 1;
    let interval = setInterval(() => {
    	adContainer.style.transition = '.2s';
    	adContainer.style.left = count * -100 + '%';
    	count++;
    	
    	if(count == 6){
    		setTimeout(() => {
    			adContainer.style.transition = '0s';
    			adContainer.style.left = '0%';
    			count = 1;
			}, 201);
    	}
    	
	}, 5 * 1000);
    
    /*탑 10 상품 복사 (1~5 뒤에붙힘 / 6~10 앞에 붙힘) */
    for(let i = 0; i < 5; i++){
        let cloneItem = section_best_items.children[i].cloneNode(true);
        section_best_items.append(cloneItem);
    }
    for(let i = 0; i < 5; i++){
        let cloneItem = section_best_items.children[9].cloneNode(true);
        section_best_items.insertBefore(cloneItem,section_best_items.children[0]);
    }

    section_best_items.style.left = '-100%';
//    adContainer.style.left = '-100%';

  //팝업
    //로컬 스토리지 확인, 없으면 null 반환
    //로컬 스토리지에 key가 있으면 만료시간 체크
	let text = localStorage.getItem('key');
	if(text != null || text != undefined){
		let today = new Date();
		if(today > text){
//			console.log(today);
//			console.log(text);
			
			localStorage.removeItem('key');
			window.open("/shop/popup", "신상품", "width=500, height=500");
		}
		
	}
	else
		window.open("/shop/popup", "신상품", "width=500, height=500");
}

//==================================================================================================
categorySpan.forEach(item => {
	item.addEventListener('click' , setCategoryLoad);
})
/*carousel_button_L.addEventListener('click', adToLeft);
carousel_button_R.addEventListener('click', adToRight);*/

best_button_L.addEventListener('click' , divToleft);
best_button_R.addEventListener('click' , divToRight);

section_view_more.addEventListener('click' , loadMoreItem);

//==================================================================================================

function setCategoryLoad(){
	category = this.dataset.categoryId;
	const param = { category_id : category };
	$.ajax({
		type: 'post',
		url: '/shop/list/category/admin',
		data: JSON.stringify(param),
		contentType: 'application/json; charset=utf-8',
		error: function(){
			alert('죄송합니다. 잠시후 다시 시도해 주세요.');
		},
		success: function(data){
			$('.productDiv').html(data);
			// 현제 페이지, 전체 페이지 업데이트
			const currentPageTemp = $('.currentPageTemp').val();
			const totalPageTemp = $('.totalPageTemp').val();
			currentPageInput.value = currentPageTemp;
			totalPageInput.value = totalPageTemp;
			console.log(`카테고리 : ${category} , 현재 페이지 : ${currentPageInput.value} , 전체 페이지  : ${totalPageInput.value}`);
			if(currentPageInput.value == totalPageInput.value){
				section_view_more.style.display = 'none';
			}
			else{
				section_view_more.style.display = 'block';
				section_view_more.style.display = 'flex';
			}
			
			$('.currentPageTemp').remove();
			$('.totalPageTemp').remove();
			
		}
	});
}

/* 상품 10개 더 불러오기 함수 */
function loadMoreItem(){
		// 현재 페이지 + 1 전달
		let currentPage = parseInt(currentPageInput.value) + 1;
		

		const pageShop = {
				currentPage : currentPage, 
				totalPage : totalPageInput.value,
				category_id : category
		}
//		console.log(pageShop);
		$.ajax({
			type: 'post',
			url: '/shop/list/admin',
			data: JSON.stringify(pageShop),
			dataType: 'html',
			contentType: 'application/json; charset=utf-8',
			error: function(data){
				alert('죄송합니다. 잠시후 다시 시도해 주세요.');
			},
			success: function(data){
				$('.productDiv').append(data);
				// 현제 페이지, 전체 페이지 업데이트
				const currentPageTemp = $('.currentPageTemp').val();
				const totalPageTemp = $('.totalPageTemp').val();
				currentPageInput.value = currentPageTemp;
				totalPageInput.value = totalPageTemp;
				console.log(`카테고리 : ${category} , 현재 페이지 : ${currentPageInput.value} , 전체 페이지  : ${totalPageInput.value}`);
				if(currentPageInput.value == totalPageInput.value){
					section_view_more.style.display = 'none';
				}
				else{
					section_view_more.style.display = 'block';
					section_view_more.style.display = 'flex';
				}
				
				$('.currentPageTemp').remove();
				$('.totalPageTemp').remove();
			}
		});
	
	
}



/*광고 왼쪽 이동 */
//function adToLeft(){
//    adContainer.style.transition = '0.2s';
//    let currentLeft = adContainer.style.left;
//    currentLeft = currentLeft.slice(0,-1);
//    adContainer.style.left = parseInt(currentLeft) + 100 + '%';
//    
//    if(adContainer.style.left == '0%'){
//        setTimeout(function(){
//            adContainer.style.transition = '0s';
//            adContainer.style.left = '-500%';
//        } , 201);
//    }
//    
//}
/*광고 오른쪽 이동 */
/*function adToRight(){
    adContainer.style.transition = '0.2s';
    let currentLeft = adContainer.style.left;
    currentLeft = currentLeft.slice(0,-1);
    adContainer.style.left = parseInt(currentLeft) - 100 + '%';
    
    if(adContainer.style.left == '-600%'){
        setTimeout(function(){
            adContainer.style.transition = '0s';
            adContainer.style.left = '-100%';
        } , 201);
    }
   
}*/

/*탑 10 오른쪽 이동 함수 */
function divToRight(){
    section_best_items.style.transition = '0.2s';
    let currentLeft = section_best_items.style.left;
    currentLeft = currentLeft.slice(0,-1);
    section_best_items.style.left = parseInt(currentLeft) - 20 + '%';
    
    if(section_best_items.style.left == '-300%'){
        setTimeout(function(){
            section_best_items.style.transition = '0s';
            section_best_items.style.left = '-100%';
        } , 201);
    }
}
/*탑 10 왼쪽 이동 함수 */
function divToleft(){
    
    section_best_items.style.transition = '0.2s';
    let currentLeft = section_best_items.style.left;
    currentLeft = currentLeft.slice(0,-1);
    section_best_items.style.left = parseInt(currentLeft) + 20 + '%';
    
    if(section_best_items.style.left == '0%'){    
        setTimeout(function(){
            section_best_items.style.transition = '0s';
            section_best_items.style.left = '-200%';
        } , 201);
    }
}

