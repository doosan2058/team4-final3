'use strict'
//삭제 버튼
const deleteBtn = document.querySelector('#deleteBtn');
//구매 버튼
const buyBtn = document.querySelector('#buyBtn');
//장바구니 체크 라디오 버튼
const basketRadio = document.querySelectorAll('.basketRadio');
// =====================================================
deleteBtn.addEventListener('click', deleteBasket);
buyBtn.addEventListener('click', buyBasket);
basketRadio.forEach((item) => {
	item.addEventListener('click', changeSelectedLineColor);
})

// =====================================================
//장바구니 라디오 버튼 체크시 라인 색 변경
function changeSelectedLineColor(){
	const td = document.querySelectorAll('td');
	td.forEach((item) => {
		item.style.backgroundColor = 'var(--subColor)';
	});
	this.parentElement.nextElementSibling.style.backgroundColor = 'var(--sub2Color)';
	this.parentElement.nextElementSibling.nextElementSibling.style.backgroundColor = 'var(--sub2Color)';
	this.parentElement.nextElementSibling.nextElementSibling.nextElementSibling.style.backgroundColor = 'var(--sub2Color)';
}
//장바구니 삭제
function deleteBasket(){
	let basket_id = $("input[name='basketRadio']:checked").val();
	if(basket_id == null || basket_id == undefined || basket_id =='' || basket_id.length == 0){
		alert('상품을 선택해 주세요.');
		return;
	}
    let param = {basket_id : basket_id}
	$.ajax({
		url: '/user/deleteBasket',
		type: 'post',
		dataType: 'text',
		contentType: 'application/json;charset=utf-8',
		data: JSON.stringify(param),
		error: function(){
			alert('죄송합니다. 잠시후 다시 시도해 주세요.');
		},
		success: function(data){
			alert(data);
			location.href = '/user/basket';
		}
	});
}
//장바구니 구매
function buyBasket(){
	let basket_id = $("input[name='basketRadio']:checked").val();
	if(basket_id == null || basket_id == undefined || basket_id =='' || basket_id.length == 0){
		alert('상품을 선택해 주세요.');
		return;
	}
    let param = {basket_id : basket_id}
	$.ajax({
		url: '/user/buyBasket',
		type: 'post',
		dataType: 'json',
		contentType: 'application/json;charset=utf-8',
		data: JSON.stringify(param),
		error: function(){
			alert('죄송합니다. 잠시후 다시 시도해 주세요.');
		},
		success: function(data){
			let product_id = data.product_id;
			let basket_amount = data.basket_amount;
			location.href = '/order?product_id=' + product_id + '&order_quantity=' + basket_amount;
		}
	});
}