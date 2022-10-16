'use strict'

/*메인 이미지 */
const productMainImg = document.querySelector('.productMainImg');
/*서브 이미지들 */
const productSubImg = document.querySelectorAll('.productSubImg');
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
const toReveiwCon = document.querySelector('.toReveiwCon');
//리뷰 현재 페이지 
const currentPage = document.querySelector('#currentPage');
//리뷰 더보기 디비전
const veiwMoreRewviesDiv = document.querySelector('.veiwMoreRewviesDiv');
//리뷰 컨테이너
const reviewBottom = document.querySelector('.reviewBottom');
//수정하기 버튼
const modifyBtn = document.querySelector('#modifyBtn');
//수정하기 버튼
const deleteBtn = document.querySelector('#deleteBtn');
//상품 번호
const product_id = document.querySelector('#product_id');
//유튜브 보기 디비전
const youtubeDiv = document.querySelector('.youtubeDiv');
//유튜브 컨테이너
const youtubeContainer = document.querySelector('.youtubeContainer');
//==================================================================================================
window.addEventListener('scroll' , showHideNavigator);

productSubImg.forEach((item) => {
    item.addEventListener('mouseenter', changeMainImg );
});


viewMoreDiv.addEventListener('click' , fullHeightContainer);
toTopDiv.addEventListener('click' , scrollToTop);
toBottomDiv.addEventListener('click' , scrollToBottom);
toReveiwCon.addEventListener('click' , scrollToReview);
veiwMoreRewviesDiv.addEventListener('click' , viewMoreReview);
modifyBtn.addEventListener('click' , goModify);
deleteBtn.addEventListener('click' , goDelete);
youtubeDiv.addEventListener('click', showYoutubeCon);
youtubeContainer.addEventListener('click', closeYoutubeCon);
//==================================================================================================

window.onload = function(){
	speedChart();
	gradeChart();
}

//유튜브 컨테이너 닫기
function closeYoutubeCon(e){
	if(e.target.className == 'youtubeContainer')
		youtubeContainer.style.display = 'none';
}
//유튜브 컨테이너 보이기
function showYoutubeCon(){
	youtubeContainer.style.display = 'block';
}

//수정
function goModify(){
	
	location.href='/product/modify?product_id=' + product_id.value;
}
//비공개
function goDelete(){
	
	location.href='/product/delete?product_id=' + product_id.value;
}
//수평 차트 평점 함수
function gradeChart(){
	//평점
	const gradeProduct = document.querySelectorAll('.gradeProduct');
	let labels = [];
	let datas = [];
	gradeProduct.forEach((item) => {
		labels.push(item.dataset.grade);
		datas.push(item.value);
	});
	console.log(labels);
	console.log(datas);
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

//파이 차트 배송 속도 함수
function speedChart(){
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
            backgroundColor: ["#3e95cd", "#8e5ea2","#3cba9f"],
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
				veiwMoreRewviesDiv.style.display = 'none';
			}
			//일회성 필요하므로 삭제
			document.querySelector('#pageEnd').remove();
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
    if(evHeight > 1000){
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





