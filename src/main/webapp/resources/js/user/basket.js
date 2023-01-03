'use strict'
//삭제 버튼
const deleteBtn = document.querySelector('#deleteBtn');
//구매 버튼
const buyBtn = document.querySelector('#buyBtn');

// =====================================================
deleteBtn.addEventListener('click', deleteBasket);
buyBtn.addEventListener('click', buyBasket);
// =====================================================

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
			let tempForm = document.createElement("form");
			tempForm.setAttribute("method","post");
			tempForm.setAttribute("action","/order");
			let tempProductId = document.createElement("input");
			tempProductId.setAttribute("type","hidden");
			tempProductId.setAttribute("name","product_id");
			tempProductId.setAttribute("value",product_id);
			let tempOrderQuantity = document.createElement("input");
			tempOrderQuantity.setAttribute("type", "hidden");
			tempOrderQuantity.setAttribute("name", "order_quantity");
			tempOrderQuantity.setAttribute("value", basket_amount);
			tempForm.appendChild(tempProductId);
			tempForm.appendChild(tempOrderQuantity);
			document.body.appendChild(tempForm);
			tempForm.submit();
		}
	});
}