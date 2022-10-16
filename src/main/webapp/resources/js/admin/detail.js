/**
 * 
 */
'use strict'

const arrowIcon = document.querySelector('.arrowIcon');
const topRight = document.querySelector('.topRight');
let arrowFlag = false;
// ==========================================================
arrowIcon.addEventListener('click', showInfoDiv);
// ==========================================================

//회원 정보 디비전 내리기
function showInfoDiv(){
	if(!arrowFlag){
		topRight.style.height = '500px';
		arrowFlag = true;
		arrowIcon.style.transform = 'translateX(-50%) rotateZ(180deg)';
	}
	else{
		topRight.style.height = '300px';
		arrowFlag = false;
		arrowIcon.style.transform = 'translateX(-50%) rotateZ(0deg)';
	}
	
}