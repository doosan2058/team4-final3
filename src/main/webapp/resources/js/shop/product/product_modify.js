'use strict'

// 파일 인풋
const mainFile = document.querySelector('#mainFile');
const subFile = document.querySelector('#subFile');
// 최종 3개 이미지 파일 담을 배열
let fileArrayMain = [];
let fileArraySub = [];
// 이미지 타입
const imgTypeArray = ['image/jpeg','image/png','image/jpg','image/gif'];

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

//==================================================================================================
// 파일 인풋 파일추가 이벤트
mainFile.addEventListener('change' , addListMain );
subFile.addEventListener('change' , addListSub );
preBtn.addEventListener('click', showPreDiv);
closePreDivIcon.addEventListener('click', closePreDiv);
//==================================================================================================
function initModifyProduct(){
	const initMain = document.querySelectorAll('.mainImgUrlHidden');
	const initSub = document.querySelectorAll('.subImgUrlHidden');
	initMain.forEach((item) => {
		if(item.value != '' || item.value != null || item.value != undefined || item.value.length != 0){

		}
	});
	initSub.forEach((item) => {
		if(item.value != '' || item.value != null || item.value != undefined || item.value.length != 0){

		}
	});
}
function closePreDiv(){
	document.querySelector('.prePageDiv').style.display = 'none';
}
function showPreDiv(){
	document.querySelector('.prePageDiv').style.display = 'block';
}
// mainDiv에 커스텀 리스트 추가 함수
function addListMain(){
	// 만약 기존 이미지 선택 개수와 현재 선택 개수의 합이 3을 넘는다면
	if((mainFile.files.length + fileArrayMain.length) > 3){
		alert('이미지는 4개 이상 업로드 할수 없습니다.');
	}
	// 이미지 선택 개수가 3개 이하라면
	else{

		for(let i = 0; i < mainFile.files.length; i++){
			if( imgTypeArray.indexOf(mainFile.files[i].type) != -1 )
				fileArrayMain.push(mainFile.files[i]);
			else{
				alert('이미지 파일만 등록할수 있습니다.');
				break;
			}
		}
	}
	drawListMain();
}
// subDiv에 커스텀 리스트 추가 함수
function addListSub(){
	// 만약 기존 이미지 선택 개수와 현재 선택 개수의 합이 3을 넘는다면
	if((subFile.files.length + fileArraySub.length) > 2){
		alert('이미지는 3개 이상 업로드 할수 없습니다.');
//		console.log('이미지는 3개 이상 업로드 할수 없습니다.');



	}
	// 이미지 선택 개수가 3개 이하라면
	else{
		for(let i = 0; i < subFile.files.length; i++){
			if( imgTypeArray.indexOf(subFile.files[i].type) != -1 )
				fileArraySub.push(subFile.files[i]);
			else{
				alert('이미지 파일은 등록할수 없습니다.');
				break;
			}
		}

	}
	drawListSub();
}

// fileArrayMain의 담긴 파일들 리스트로 출력하는 함수
function drawListMain(){
	mainFileListDiv.innerHTML = '';
	let dataTransfer = new DataTransfer();
	fileArrayMain.forEach( item => {
		mainFileListDiv.innerHTML += `<p class = 'imgList'> ${item.name} <button id = '${item.lastModified}${item.name}' class = 'deleteBtn'>X</button> </p>`;
		dataTransfer.items.add(item);
	});
	mainFile.files = dataTransfer.files;
	const deleteBtns = document.querySelectorAll('.deleteBtn');
	deleteBtns.forEach(item => item.addEventListener('click' , deleteListMain));
//	console.log(fileArrayMain);
	preMainImgs();
	// 상품이미지 개수에 따른 시각화
	switch(fileArrayMain.length){
		case 0:
			productImgMain.style.border = 'none';
			productImgMain.style.display = 'none';
			productImgFirst.style.border = 'none';
			productImgFirst.style.display = 'none';
			productImgSecond.style.border = 'none';
			productImgSecond.style.display = 'none';
			productImgThird.style.border = 'none';
			productImgThird.style.display = 'none';
			return;
		case 1:
			productImgMain.style.border = '3px solid red';
			productImgMain.style.display = 'block';
			productImgFirst.style.border = '3px solid red';
			productImgFirst.style.display = 'block';
			productImgSecond.style.border = 'none';
			productImgSecond.style.display = 'none';
			productImgThird.style.border = 'none';
			productImgThird.style.display = 'none';
			return;
		case 2:
			productImgMain.style.border = '3px solid red';
			productImgMain.style.display = 'block';
			productImgFirst.style.border = '3px solid red';
			productImgFirst.style.display = 'block';
			productImgSecond.style.border = '3px solid blue';
			productImgSecond.style.display = 'block';
			productImgThird.style.border = 'none';
			productImgThird.style.display = 'none';
			return;
		case 3:
			productImgMain.style.border = '3px solid red';
			productImgMain.style.display = 'block';
			productImgFirst.style.border = '3px solid red';
			productImgFirst.style.display = 'block';
			productImgSecond.style.border = '3px solid blue';
			productImgSecond.style.display = 'block';
			productImgThird.style.border = '3px solid green';
			productImgThird.style.display = 'block';
			return;
	}
}
// fileArraySub의 담긴 파일들 리스트로 출력하는 함수
function drawListSub(){
	subFileListDiv.innerHTML = '';
	let dataTransfer = new DataTransfer();
	fileArraySub.forEach( item => {
		subFileListDiv.innerHTML += `<p class = 'imgListSub'> ${item.name} <button id = '${item.lastModified}${item.name}' class = 'deleteSubBtn'>X</button> </p>`;
		dataTransfer.items.add(item);
	});
	subFile.files = dataTransfer.files;
	const deleteSubBtns = document.querySelectorAll('.deleteSubBtn');
	deleteSubBtns.forEach(item => item.addEventListener('click' , deleteListSub));
	preSubImgs();
	// 상품이미지 개수에 따른 시각화
	switch(fileArraySub.length){
		case 0:
			subImgFirst.style.border = 'none';
			subImgFirst.style.display = 'none';
			subImgSecond.style.border = 'none';
			subImgSecond.style.display = 'none';
			return;
		case 1:
			subImgFirst.style.border = '3px solid yellow';
			subImgFirst.style.display = 'block';
			subImgSecond.style.border = 'none';
			subImgSecond.style.display = 'none';
			return;
		case 2:
			subImgFirst.style.border = '3px solid yellow';
			subImgFirst.style.display = 'block';
			subImgSecond.style.border = '3px solid orangered';
			subImgSecond.style.display = 'block';
			return;

	}
}

// 메인리스트 삭제 함수
function deleteListMain(){
	fileArrayMain = fileArrayMain.filter( item => (item.lastModified + item.name) != this.id);
	this.parentElement.remove();
	drawListMain();
}

// 서브리스트 삭제 함수
function deleteListSub(){
	fileArraySub = fileArraySub.filter( item => (item.lastModified + item.name) != this.id);
	this.parentElement.remove();
	drawListSub();
}

// 메인 이미지 미리보기 만들기 함수
function preMainImgs(){
	let imgs = document.querySelectorAll('.mainImgs');
	imgs.forEach(item => {
		item.style.display = 'none';
	});

	for(let i = 0; i < fileArrayMain.length; i++){
		imgs[i].style.display = 'block';
		let reader = new FileReader();
		reader.addEventListener('load', function(){
			imgs[i].src = reader.result;
		});
		if(fileArrayMain[i])
			reader.readAsDataURL(fileArrayMain[i]);
	}
}
// 서브 이미지 미리보기 만들기 함수
function preSubImgs(){
	let imgs = document.querySelectorAll('.subImgs');
	imgs.forEach(item => {
		item.style.display = 'none';
	});

	for(let i = 0; i < fileArraySub.length; i++){
		let reader = new FileReader();
		imgs[i].style.display = 'block';
		reader.addEventListener('load', function(){
			imgs[i].src = reader.result;
		});
		if(fileArraySub[i]){
			reader.readAsDataURL(fileArraySub[i]);
		}
	}
}

