'use strict'

/*별들 */
const star = document.querySelectorAll('.star');
/*별점 스팬 */
const productGradeSpan = document.querySelector('.productGradeSpan');
/*별점 인풋 히든 */
const productGradeInput = document.querySelector('#productGradeInput');
/*배송 속도 디비전 */
const speedSpan = document.querySelectorAll('.speedSpan');
/*배송 속도 인풋 히든*/
const productSpeedInput = document.querySelector('#productSpeedInput');
//리뷰 이미지
const fileImg = document.querySelector('#fileImg');
//리뷰 동영상
const fileVideo = document.querySelector('#fileVideo');
const reviewTextArea = document.querySelector('#reviewTextArea');
const reviewTextLengthSpan = document.querySelector('.reviewTextLengthSpan');
const uploadImgSpan = document.querySelector('.uploadImgSpan');
const uploadVedioSpan = document.querySelector('.uploadVedioSpan');
//이미지 타입
const imgTypeArray = ['image/jpeg','image/png','image/jpg','image/gif'];
//비디오 타입
const videoTypeArray = ['video/webm','video/mp4'];
// =========================================================================================

speedSpan.forEach((item) => {
    item.addEventListener('click' , selectSpeed);
})

star.forEach((item) => {
    item.addEventListener('mouseenter' , drawStar);
});

fileImg.addEventListener('change', checkImg);
fileVideo.addEventListener('change', checkVideo);
reviewTextArea.addEventListener('keyup', changeTextLength);

// ==========================================================================================

function changeTextLength(){
	const textLength = reviewTextArea.value.length;
	reviewTextLengthSpan.innerHTML = `(${textLength}/500)`;
}
//리뷰 이미지 체크
function checkImg(){
	if(fileImg.files[0] != null){
		let imgFile = fileImg.files[0];
		//이미지 파일 아니면 파일 객체 초기화
		if(imgTypeArray.indexOf(imgFile.type) == -1){
			alert('이미지 파일이 아닙니다.');
			let dataTransfer = new DataTransfer();
			fileImg.files = dataTransfer.files;
			uploadImgSpan.innerHTML = '';
			return;
		}
		uploadImgSpan.innerHTML = imgFile.name;
	}
	else{
		uploadImgSpan.innerHTML = '';
	}

}
//리뷰 비디오 체크
function checkVideo(){
	if(fileVideo.files[0] != null){
		let videoFile = fileVideo.files[0];
		if(videoTypeArray.indexOf(videoFile.type) == -1){
			alert('죄송합니다. webm/mp4 파일만 업로드 하실수 있습니다.');
			let dataTransfer = new DataTransfer();
			fileVideo.files = dataTransfer.files;
			uploadVedioSpan.innerHTML = '';
			return;
		}
		if(videoFile.size > 40000000){
			alert('죄송합니다. 동영상의 용량이 너무 큽니다.');
			let dataTransfer = new DataTransfer();
			fileVideo.files = dataTransfer.files;
			uploadVedioSpan.innerHTML = '';
			return;
		}
		uploadVedioSpan.innerHTML = videoFile.name;
	}
	else
		uploadVedioSpan.innerHTML = '';

}
//폼 체크 함수
function checkForm(){
	
	if(productGradeInput.value == null || productGradeInput.value == undefined || (productGradeInput.value).trim() == ''){
		alert('별점을 작성해 주세요.');
		return false;
	}
	else if(productSpeedInput.value == null || productSpeedInput.value == undefined || (productSpeedInput.value).trim() == ''){
		alert('배송속도를 선택해 주세요.')
		return false;
	}
	else
		return true;
	
}
/*배송 속도 선택 함수 */
function selectSpeed(){
	speedSpan.forEach((item) => {
		item.style.color = 'var(--subFontColor4)';
		item.style.backgroundColor = 'var(--subColor)';
    })
    this.style.color = 'var(--mainColor)';
	this.style.backgroundColor = 'var(--basicFontColor)';
    productSpeedInput.value = this.innerHTML;

}

/*별들 색칠하는 함수 */
function drawStar(e){
    star.forEach((item) => {
        item.className = 'xi-star-o star';
    });

    for(let i = 0; i < star.length; i++){
        star[i].className = 'xi-star star';
        productGradeSpan.innerHTML = `${i + 1}`;
        productGradeInput.value = `${i + 1}`;
		if(star[i].dataset.num == e.target.dataset.num)
            break;
    }
   
}