'use strict'

// 파일 인풋
const mainFile = document.querySelector('#mainFile');
const subFile = document.querySelector('#subFile');
// 최종 3개 이미지 파일 담을 배열
let fileArrayMain = [];
let fileArraySub = [];
// 이미지 타입
const imgTypeArray = ['image/jpeg', 'image/png', 'image/jpg', 'image/gif'];

// 파일 리스트 출력 컨테이너
const mainFileListDiv = document.querySelector('.mainFileListDiv');
const subFileListDiv = document.querySelector('.subFileListDiv');
// 상품 이미지 미리보기 디비전
const productImgMain = document.querySelector('.productImgMain');
const productImgFirst = document.querySelector('.productImgFirst');
const productImgSecond = document.querySelector('.productImgSecond');
const productImgThird = document.querySelector('.productImgThird');
const subImgFirst = document.querySelector('.subImgFirst');
const subImgSecond = document.querySelector('.subImgSecond');
//미리보기 버튼
const preBtn = document.querySelector('.preBtn');
//미리보기 닫기 아이콘
const closePreDivIcon = document.querySelector('.closePreDivIcon');
const productMainImgSpan = document.querySelector('#productMainImgSpan');
const productSubImgSpan = document.querySelector('#productSubImgSpan');
const product_name = document.querySelector('#product_name');
const productNameLengthSpan = document.querySelector('#productNameLengthSpan');
const product_comment = document.querySelector('#product_comment');
const productCommentLengthSpan = document.querySelector('#productCommentLengthSpan');
const product_youtube_url = document.querySelector('#product_youtube_url');
const productUrlLengthSpan = document.querySelector('#productUrlLengthSpan');
//==================================================================================================
window.addEventListener('load', productModifyInit);
mainFile.addEventListener('change', addListMain);
subFile.addEventListener('change', addListSub);
preBtn.addEventListener('click', showPreDiv);
closePreDivIcon.addEventListener('click', closePreDiv);
product_name.addEventListener('keyup', checkProductNameInputLength);
product_comment.addEventListener('keyup', checkProductCommentInputLength);
product_name.addEventListener('keyup', checkProductNameInputLength);
product_youtube_url.addEventListener('keyup', checkProductUrlInputLength);
//==================================================================================================

function productModifyInit(){
	productUrlLengthSpan.innerHTML = `(${product_youtube_url.value.length}/100)`;
	productCommentLengthSpan.innerHTML = `(${product_comment.value.length}/200)`;
	productNameLengthSpan.innerHTML = `(${product_name.value.length}/20)`;

	const mainImgs = document.querySelectorAll('.mainImgs');
	const subImgs = document.querySelectorAll('.subImgs');
	let mainImgsCount = 0;
	let subImgsCount = 0;
	mainImgs.forEach((item) => {
		if(item.src != ''){
			mainImgsCount++;
		}
	});
	subImgs.forEach((item) => {
		if(item.src != ''){
			subImgsCount++;
		}
	});

	switch (mainImgsCount) {
		case 0:
			productImgMain.style.border = 'none';
			productImgMain.style.display = 'none';
			productImgFirst.style.border = 'none';
			productImgFirst.style.display = 'none';
			productImgSecond.style.border = 'none';
			productImgSecond.style.display = 'none';
			productImgThird.style.border = 'none';
			productImgThird.style.display = 'none';
			break;
		case 1:
			productImgMain.style.border = '1px solid red';
			productImgMain.style.display = 'flex';
			productImgFirst.style.border = '1px solid red';
			productImgFirst.style.display = 'block';
			productImgSecond.style.border = 'none';
			productImgSecond.style.display = 'none';
			productImgThird.style.border = 'none';
			productImgThird.style.display = 'none';
			break;
		case 2:
			productImgMain.style.border = '1px solid red';
			productImgMain.style.display = 'flex';
			productImgFirst.style.border = '1px solid red';
			productImgFirst.style.display = 'block';
			productImgSecond.style.border = '1px solid blue';
			productImgSecond.style.display = 'block';
			productImgThird.style.border = 'none';
			productImgThird.style.display = 'none';
			break;
		case 3:
			productImgMain.style.border = '1px solid red';
			productImgMain.style.display = 'flex';
			productImgFirst.style.border = '1px solid red';
			productImgFirst.style.display = 'block';
			productImgSecond.style.border = '1px solid blue';
			productImgSecond.style.display = 'block';
			productImgThird.style.border = '1px solid green';
			productImgThird.style.display = 'block';
			break;
	}

	switch (subImgsCount) {
		case 0:
			subImgFirst.style.border = 'none';
			subImgFirst.style.display = 'none';
			subImgSecond.style.border = 'none';
			subImgSecond.style.display = 'none';
			break;
		case 1:
			subImgFirst.style.border = '1px solid yellow';
			subImgFirst.style.display = 'block';
			subImgSecond.style.border = 'none';
			subImgSecond.style.display = 'none';
			break;
		case 2:
			subImgFirst.style.border = '1px solid yellow';
			subImgFirst.style.display = 'block';
			subImgSecond.style.border = '1px solid orangered';
			subImgSecond.style.display = 'block';
			break;

	}

}

function checkProductUrlInputLength(){
	productUrlLengthSpan.innerHTML = `(${this.value.length}/100)`;
}
function checkProductCommentInputLength(){
	productCommentLengthSpan.innerHTML = `(${this.value.length}/200)`;
}
function checkProductNameInputLength(){
	productNameLengthSpan.innerHTML = `(${this.value.length}/20)`;
}
function closePreDiv() {
	document.querySelector('.prePageDiv').style.display = 'none';
	closePreDivIcon.style.display = 'none';
}

function showPreDiv() {
	document.querySelector('.prePageDiv').style.display = 'block';
	closePreDivIcon.style.display = 'block';
}

// 상품 이미지 업로드
function addListMain() {
	// 이미지 3개 초과 체크
	if ((mainFile.files.length + fileArrayMain.length) > 3) {
		alert('이미지는 4개 이상 업로드 할수 없습니다.');
	}
	else {
		// 이미지 파일 체크
		for (let i = 0; i < mainFile.files.length; i++) {
			if (imgTypeArray.indexOf(mainFile.files[i].type) == -1) {
				alert('이미지 파일만 등록할수 있습니다.');
				return;
			}
		}
		// 메인 이미지 배열로 복사
		for(let i = 0; i < mainFile.files.length; i++){
			fileArrayMain.push(mainFile.files[i]);
		}
		drawListMain();
	}
}

// 설명 이미지 업로드
function addListSub() {
	// 이미지 2개 초과 체크
	if ((subFile.files.length + fileArraySub.length) > 2) {
		alert('이미지는 3개 이상 업로드 할수 없습니다.');
	}
	else {
		// 이미지 파일 체크
		for (let i = 0; i < subFile.files.length; i++) {
			if (imgTypeArray.indexOf(subFile.files[i].type) == -1) {
				alert('이미지 파일만 등록할수 있습니다.');
				return;
			}
		}
		// 설명 이미지 배열로 복사
		for(let i = 0; i < subFile.files.length; i++){
			fileArraySub.push(subFile.files[i]);
		}
		drawListSub();
	}
}

// 상품 이미지 리스트 화면 출력
function drawListMain() {
	// 메인 파일 제목 출력
	if(fileArrayMain.length == 0)
		productMainImgSpan.innerHTML = '';
	else if(fileArrayMain.length == 1)
		productMainImgSpan.innerHTML = fileArrayMain[0].name;
	else if(fileArrayMain.length > 1)
		productMainImgSpan.innerHTML = fileArrayMain.length + '개 파일';
	else{
		alert('상품 사진 업로드중 오류가 발생하였습니다. 관리자에게 문의해 주세요.');
		return;
	}

	mainFileListDiv.innerHTML = '';
	let dataTransfer = new DataTransfer();
	fileArrayMain.forEach(item => {
		mainFileListDiv.innerHTML += `<p class = 'imgList'> ${item.name} <button id = '${item.lastModified}${item.name}' class = 'deleteBtn'>X</button> </p>`;
		dataTransfer.items.add(item);
	});
	mainFile.files = dataTransfer.files;
	const deleteBtns = document.querySelectorAll('.deleteBtn');
	deleteBtns.forEach(item => item.addEventListener('click', deleteListMain));

	preMainImgs();

	// 상품이미지 시각화
	switch (fileArrayMain.length) {
		case 0:
			productImgMain.style.border = 'none';
			productImgMain.style.display = 'none';
			productImgFirst.style.border = 'none';
			productImgFirst.style.display = 'none';
			productImgSecond.style.border = 'none';
			productImgSecond.style.display = 'none';
			productImgThird.style.border = 'none';
			productImgThird.style.display = 'none';
			break;
		case 1:
			productImgMain.style.border = '1px solid red';
			productImgMain.style.display = 'flex';
			productImgFirst.style.border = '1px solid red';
			productImgFirst.style.display = 'block';
			productImgSecond.style.border = 'none';
			productImgSecond.style.display = 'none';
			productImgThird.style.border = 'none';
			productImgThird.style.display = 'none';
			break;
		case 2:
			productImgMain.style.border = '1px solid red';
			productImgMain.style.display = 'flex';
			productImgFirst.style.border = '1px solid red';
			productImgFirst.style.display = 'block';
			productImgSecond.style.border = '1px solid blue';
			productImgSecond.style.display = 'block';
			productImgThird.style.border = 'none';
			productImgThird.style.display = 'none';
			break;
		case 3:
			productImgMain.style.border = '1px solid red';
			productImgMain.style.display = 'flex';
			productImgFirst.style.border = '1px solid red';
			productImgFirst.style.display = 'block';
			productImgSecond.style.border = '1px solid blue';
			productImgSecond.style.display = 'block';
			productImgThird.style.border = '1px solid green';
			productImgThird.style.display = 'block';
			break;
	}
}

// 설명 이미지 리스트 화면 출력
function drawListSub() {
	// 설명 파일 제목 출력
	if(fileArraySub.length == 0)
		productSubImgSpan.innerHTML = '';
	else if(fileArraySub.length == 1)
		productSubImgSpan.innerHTML = fileArraySub[0].name;
	else if(fileArraySub.length > 1)
		productSubImgSpan.innerHTML = fileArraySub.length + '개 파일';
	else{
		alert('설명 사진 업로드중 오류가 발생하였습니다. 관리자에게 문의해 주세요.');
		return;
	}

	subFileListDiv.innerHTML = '';
	let dataTransfer = new DataTransfer();
	fileArraySub.forEach(item => {
		subFileListDiv.innerHTML += `<p class = 'imgListSub'> ${item.name} <button id = '${item.lastModified}${item.name}' class = 'deleteSubBtn'>X</button> </p>`;
		dataTransfer.items.add(item);
	});
	subFile.files = dataTransfer.files;
	const deleteSubBtns = document.querySelectorAll('.deleteSubBtn');
	deleteSubBtns.forEach(item => item.addEventListener('click', deleteListSub));

	preSubImgs();

	// 상품이미지 시각화
	switch (fileArraySub.length) {
		case 0:
			subImgFirst.style.border = 'none';
			subImgFirst.style.display = 'none';
			subImgSecond.style.border = 'none';
			subImgSecond.style.display = 'none';
			break;
		case 1:
			subImgFirst.style.border = '1px solid yellow';
			subImgFirst.style.display = 'block';
			subImgSecond.style.border = 'none';
			subImgSecond.style.display = 'none';
			break;
		case 2:
			subImgFirst.style.border = '1px solid yellow';
			subImgFirst.style.display = 'block';
			subImgSecond.style.border = '1px solid orangered';
			subImgSecond.style.display = 'block';
			break;

	}
}

// 메인리스트 삭제 함수
function deleteListMain() {
	// 삭제 버튼 클릭한 파일 제외
	fileArrayMain = fileArrayMain.filter(item => (item.lastModified + item.name) != this.id);
	this.parentElement.remove();
	drawListMain();
}

// 서브리스트 삭제 함수
function deleteListSub() {
	// 삭제 버튼 클릭한 파일 제외
	fileArraySub = fileArraySub.filter(item => (item.lastModified + item.name) != this.id);
	this.parentElement.remove();
	drawListSub();
}

// 상품 이미지 미리보기 태그 생성
function preMainImgs() {
	let imgs = document.querySelectorAll('.mainImgs');
	imgs.forEach(item => {
		item.style.display = 'none';
	});

	for (let i = 0; i < fileArrayMain.length; i++) {
		imgs[i].style.display = 'block';
		let reader = new FileReader();
		if (fileArrayMain[i])
			reader.readAsDataURL(fileArrayMain[i]);
		reader.addEventListener('load', function () {
			imgs[i].src = reader.result;
			if(i == 0){
				document.querySelector('#prevMainImg').src = imgs[0].src;
			}
		});

	}


}

// 설명 이미지 미리보기 태그 생성
function preSubImgs() {
	let imgs = document.querySelectorAll('.subImgs');
	imgs.forEach(item => {
		item.style.display = 'none';
	});

	for (let i = 0; i < fileArraySub.length; i++) {
		let reader = new FileReader();
		imgs[i].style.display = 'block';
		if (fileArraySub[i])
			reader.readAsDataURL(fileArraySub[i]);
		reader.addEventListener('load', function () {
			imgs[i].src = reader.result;
		});

	}
}

