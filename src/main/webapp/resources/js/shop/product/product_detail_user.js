'use strict'
//상품 키
const product_id = document.querySelector('#product_id');
/*메인 이미지 */
const productMainImg = document.querySelector('.productMainImg');
/*서브 이미지들 */
const productSubImg = document.querySelectorAll('.productSubImg');
/*상품 가격*/
const priceHiddenInput = document.querySelector('#priceHiddenInput');
/*상품 가격 td */
const productPriceTd = document.querySelector('#productPriceTd');
/*상품 수량 text */
const quantityText = document.querySelector('#quantityText');
/*상품 수량 업 btn */
const quantityUpBtn = document.querySelector('#quantityUpBtn');
/*상품 수량 다운 btn */
const quantityDownBtn = document.querySelector('#quantityDownBtn');
/*총 상품 가격 span */
const productPriceSpan = document.querySelector('#productPriceSpan');
/*장바구니 담기 btn */
const basketBtn = document.querySelector('#basketBtn');
/*상품 상세 정보 펼처 보기 디비전 */
const viewMoreDiv = document.querySelector('.viewMoreDiv');
/*상품 상세 이미지 컨테이너 디비전 */
const productImgDiv = document.querySelector('.productImgDiv');
/*네비게이터 컨테이너 */
const navigatorContainer = document.querySelector('.navigatorContainer');
/*네비게이터 상단 이동 디비전 */
const toTopDiv = document.querySelector('.toTopDiv');
/*네비게이터 하단 이동 디비전 */
const toBottomDiv = document.querySelector('.toBottomDiv');
/*리뷰 컨테이너 */
const productBottom = document.querySelector('.productBottom');
/*네비게이터 리뷰 이동 디비전 */
const toReviewCon = document.querySelector('.toReviewCon');
//리뷰 현재 페이지 
const currentPage = document.querySelector('#currentPage');
//리뷰 더보기 디비전
const viewMoreReviewsDiv = document.querySelector('.viewMoreReviewsDiv');
//유튜브 보기 디비전
const youtubeDiv = document.querySelector('.youtubeDiv');
//유튜브 컨테이너
const youtubeContainer = document.querySelector('.youtubeContainer');
//리뷰 좋아요 스팬
const thumbs = document.querySelectorAll('.thumb');
//리뷰 이미지 확대 컨테이너
const fullImageContainer = document.querySelector('.fullImageContainer');
//리뷰 이미지 확대 컨테이너 닫기
const closeFullImageContainerIcon = document.querySelector('.closeFullImageContainerIcon');
//유튜브 닫기
const closeYoutubeContainerIcon = document.querySelector('.closeYoutubeContainerIcon');
//유튜브 주소
const product_youtube_url_hidden = document.querySelector('#product_youtube_url_hidden');
//iframe
const adIframe = document.querySelector('.adIframe');

//==================================================================================================

window.addEventListener('load', initProductDetail);
window.addEventListener('scroll' , showHideNavigator);
productSubImg.forEach((item) => {
    item.addEventListener('mouseenter', changeMainImg );
});
quantityUpBtn.addEventListener('click' , quantityUp);
quantityDownBtn.addEventListener('click' , quantityDown);
basketBtn.addEventListener('click' , basketFunc);
viewMoreDiv.addEventListener('click' , fullHeightContainer);
toTopDiv.addEventListener('click' , scrollToTop);
toBottomDiv.addEventListener('click' , scrollToBottom);
toReviewCon.addEventListener('click' , scrollToReview);
viewMoreReviewsDiv.addEventListener('click' , viewMoreReview);
youtubeDiv.addEventListener('click', showYoutubeCon);
youtubeContainer.addEventListener('click', closeYoutubeCon);
thumbs.forEach((item) => {
	item.addEventListener('click', doHelpful);
});
quantityText.addEventListener('blur', quantitySetting);
closeFullImageContainerIcon.addEventListener('click', closeFullImageContainer);
closeYoutubeContainerIcon.addEventListener('click', closeYoutubeContainer);

//==================================================================================================

function closeYoutubeContainer(){
	youtubeContainer.style.display = 'none';
	adIframe.src = '';
}
function closeFullImageContainer(){
	fullImageContainer.style.display = 'none';
	fullImageContainer.children[1].src = '';
}
function reviewImageFullSize(){

	if(document.querySelectorAll('.reviewImage').length > 0){
		const reviewImage = document.querySelectorAll('.reviewImage');
		reviewImage.forEach((item) => {
			item.addEventListener('click', () => {showFullImage(item.src)});
		})
	}
}
function showFullImage(url){
	fullImageContainer.style.display = 'block';
	fullImageContainer.children[1].src = url;
}
//상품 개수 직접 입력 가격 세팅 함수
function quantitySetting(){
	const tempText = this.value;
	if(isNaN(tempText) == true){
		alert('숫자를 입력해 주세요.');
		this.value = '1';
		return;
	}
	else if(this.value > 99 || this.value < 1){
		alert('상품의 개수는 1 ~ 99 사이를 입력하셔야 합니다.');
		this.value = '1';
		return;
	}
	else{
		productPriceSpan.innerHTML = priceToString(parseInt(tempText) * parseInt(priceHiddenInput.value));
	}
}

function initProductDetail(){
	/*테이블에서 가격정보 읽어와 초기 가격값 출력 */
	if(productPriceTd.innerHTML != undefined){
		productPriceSpan.innerHTML = productPriceTd.innerHTML;
	}
	//평점차트
	gradeChart();
	//배송속도 차트
	speedChart();
	//리뷰 이미지 풀사이즈
	reviewImageFullSize();
}
//리뷰 좋아요 
function doHelpful(){
	let loginText = document.querySelector('#loginInput').value;
	if(loginText == null || loginText == undefined || loginText.length == 0){
		alert('추천은 로그인 후 이용할수 있습니다.');
		return;
	}
	let thisSpan = this;
	const product_review_id = this.previousElementSibling.value;
	const param = { product_review_id : product_review_id };
	$.ajax({
		type: 'post',
		url: '/user/helpful',
		dataType: 'json',
		contentType: 'application/json; charset=utf-8',
		data: JSON.stringify(param),
		error: function(){
			alert('죄송합니다. 잠시후 다시 시도해 주세요.');
		},
		success: function(data){
			if(data.msg == '해당 리뷰글을 추천하였습니다.'){
				thisSpan.classList.add('helpfulAni');
				thisSpan.nextElementSibling.innerHTML = data.count;
			}
			else
				alert(data.msg);
		}
	});
}
//유튜브 컨테이너 닫기
function closeYoutubeCon(e){
	if(e.target.className == 'youtubeContainer')
		youtubeContainer.style.display = 'none';
}
//유튜브 컨테이너 보이기
function showYoutubeCon(){
	youtubeContainer.style.display = 'block';
	adIframe.src = product_youtube_url_hidden.value;
}
//수평 차트 평점 함수
function gradeChart(){
	if(document.querySelectorAll('.gradeProduct').length == 0){
		document.querySelector('.averageGradeSpan').innerHTML = '0';
		return;
	}


	//평점
	const gradeProduct = document.querySelectorAll('.gradeProduct');
	let labels = [];
	let datas = [];
	gradeProduct.forEach((item) => {
		labels.push(item.dataset.grade);
		datas.push(item.value);
	});
	let totalNum = 0;
	let totalCount = 0;
	//리뷰 평균 구하기
	for(let i = 0; i < labels.length; i++){
		let tempNum = labels[i].slice(0,-1);
		totalNum += (tempNum * datas[i]);
		totalCount += parseInt(datas[i]);
	}
	//평점
	let averageGrade = Math.round((totalNum / totalCount) * 10) / 10;
	if(isNaN(averageGrade))
		document.querySelector('.averageGradeSpan').innerHTML = '0';
	else
		document.querySelector('.averageGradeSpan').innerHTML = averageGrade;
	
	new Chart(document.getElementById("bar-chart-horizontal"), {
	    type: 'bar',
	    data: {
	      labels: labels.reverse(),
	      datasets: [
	        {
	          label: "평점 수",
	          backgroundColor: ["#3e95cd","#8e5ea2","#3cba9f","#e8c3b9","#c45850"],
	          data: datas.reverse()
	        }
	      ]
	    },
	    options: {

	      legend: { display: false },
	      scales: {
			  y: {
				  beginAtZero: true
	            }
	        },
	      title: {
	        display: true,
	        text: '구매자 분들의 후기 점수에요'
	      }
	    }
	});
}
//파이차트 배송 속도 나타내는 함수
function speedChart(){
	if(document.querySelectorAll('.speedProduct').length == 0)
		return;

	//배송 속도 평균
    const speedProduct = document.querySelectorAll('.speedProduct');
    let labels = [];
    let datas = [];
    speedProduct.forEach((item) => {
    	labels.push(item.dataset.speed);
    	datas.push(item.value);
    })
   
    new Chart(document.getElementById("pie-chart"), {
        type: 'pie',
        data: {
          labels: labels,
          datasets: [{
            label: "Delivery Speed",
            backgroundColor: ["#3e95cd","#8e5ea2","#3cba9f"],
            data: datas
          }]
        },
        options: {

          title: {
            display: true,
            text: '구매자 분들이 작성해준 후기에요'
          }
        }
    });
}
//리뷰 10개씩 더보기 함수
function viewMoreReview(){
	let temp = parseInt(currentPage.value);
	temp += 1;
	//현재 페이지 1 증가
	currentPage.value = temp;
	let param = { product_id : product_id.value ,currentPage : currentPage.value };
	//ajax 처리후 다음 10개 가져오기 로직 작성
	$.ajax({  
		type: 'post',
		url: '/product/review',
		data: JSON.stringify(param),
		dataType: 'html',
		contentType:'application/json; charset=utf-8',
		error: function(){
			alert('죄송합니다. 잠시후 다시 시도해 주세요.');
		},
		success: function(data){
			$('.reviewBottom').append(data);
			//끝페이지면 더보기 비활성화
			if(	document.querySelector('#pageEnd').value == 'true'){
				viewMoreReviewsDiv.style.display = 'none';
			}
			document.querySelector('#pageEnd').remove();
			//리뷰 좋아요 스팬
			const thumbs = document.querySelectorAll('.thumb');
			thumbs.forEach((item) => {
				item.addEventListener('click', doHelpful);
			});
			//리뷰 풀사이즈
			reviewImageFullSize();
		}
	});
}
/*리뷰 구역 이동 함수 */
function scrollToReview(){
    window.scroll({ 
        top: productBottom.offsetTop, 
        behavior: 'smooth' 
    });
}
/*상단 이동 함수 */
function scrollToTop(){
    window.scroll({ 
        top: 0, 
        behavior: 'smooth' 
    });
}
/*하단 이동 함수 */
function scrollToBottom(){
    window.scroll({ 
        top: document.documentElement.scrollHeight, 
        behavior: 'smooth' 
    });
}
/*네비게이터 화면 출력 함수 */
function showHideNavigator(){
    let evHeight = window.scrollY;
    if(evHeight > 700){
        navigatorContainer.style.opacity = '1';
        navigatorContainer.style.pointerEvents = 'auto';
    }
    else{
        navigatorContainer.style.opacity = '0';
        navigatorContainer.style.pointerEvents = 'none';
    }
}
/*펼처 보기 클릭시 상품 상세 이미지 컨테이너 높이 늘어나는 함수 */
function fullHeightContainer(){
    productImgDiv.style.height = 'auto';
    this.style.display = 'none';
}
/*서브이미지에 마우스 호버시 메인 이미지 바뀌는 함수*/
function changeMainImg(){
    productMainImg.src = this.src;
}
/*상품 갯수 추가 함수 */
function quantityUp(){
    if(quantityText.value === '99')
        return;
	quantityText.value = parseInt(quantityText.value) + 1 + '';
    productPriceSpan.innerHTML = parseInt(productPriceTd.innerHTML.replace('원','').replace(/,/g,'')) * parseInt(quantityText.value);
    productPriceSpan.innerHTML = priceToString(productPriceSpan.innerHTML); 
}
/*상품 갯수 제거 함수 */
function quantityDown(){
    if(quantityText.value === '1')
        return;
    quantityText.value = parseInt(quantityText.value) - 1 + '';
    productPriceSpan.innerHTML = parseInt(productPriceTd.innerHTML.replace('원','').replace(/,/g,'')) * parseInt(quantityText.value);
    productPriceSpan.innerHTML = priceToString(productPriceSpan.innerHTML);
}
/*가격 정규식 변환 함수 */
function priceToString(price) {
    return price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',') + ' 원';
}
/*장바구니 담기 클릭시 실행되는 함수 ajax */
/*유저 페이지에서만 실행 */
function basketFunc(){
	let loginText = document.querySelector('#loginInput').value;
	let count = quantityText.value;
	let product = product_id.value;
	if(loginText == null || loginText == undefined || loginText.length == 0){
		alert('장바구니 담기는 회원만 이용할 수 있습니다.');
		return;
	}
	let param = {member_id : loginText,product_id : product,basket_amount : count};
	$.ajax({
		url: 'basket',
		type: 'post',
		contentType: 'application/json;charset=utf-8',
		dataType: 'text',
		data: JSON.stringify(param),
		error: function(){
			alert('죄송합니다. 잠시후 다시 시도해 주세요.');
		},
		success: function(data){
			alert(data);
		}
	});
}






