/**
 * 
 */
'use strict'
//뒤로가기 버튼
const backBtn = document.querySelector('#backBtn');

// =======================================================

backBtn.addEventListener('click', goBack);

// =======================================================

function goBack(){
	location.href = '/login';
}