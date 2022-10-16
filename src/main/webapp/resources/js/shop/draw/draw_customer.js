const draw_final_modal = document.querySelector('.modal_final_container');
let draw_final_button = document.querySelectorAll('.font_top_button_end');
let modal_close=document.querySelector('.modal_bottom_btn');
let modal_final_close = document.querySelectorAll ('.modal_final_bottom_btn');
let modal_final_middle = document.querySelectorAll('.modal_final_middle');
let limitedBtns = document.querySelector('#limitedBtns');
const login_id = document.querySelector("#login_id").value;// 현재 로그인 아이디
const draw_btn = document.querySelectorAll(".font_top_button");// 응모 버튼
const font_top_button = document.querySelectorAll('.font_top_button');
const limiteProduct = document.querySelector('#limiteProduct');
let winnerFlag = false;

font_top_button.forEach((item) => {
	item.addEventListener('mouseenter', changeText);
	item.addEventListener('mouseleave', initText);
});

function changeText(){
	if(this.value == '이벤트 진행')
		this.value = '응모하기';
}	

function initText(){
	if(this.value == '응모하기')
		this.value = '이벤트 진행';
}


for(let i=0; i<modal_final_close.length; i++){
    modal_final_close[i].addEventListener('click',function(){
    	winnerFlag = false;
    	limitedBtns.style.display = 'none';
        draw_final_modal.style.display = "none";
        // document.body.style.overflow = "unset";
    })
}
for(let i=0; i<draw_final_button.length; i++){
    draw_final_button[i].addEventListener('click',function(){
    	
        draw_final_modal.style.display = "block";
        
        limiteProduct.value = this.nextElementSibling.value;
        // document.body.style.overflow = "hidden";
    }) 
}


// 이벤트 응모하기
for(let i = 0; i < draw_btn.length; i++) {
    draw_btn[i].addEventListener("click", function () {
        let draw_id = draw_btn[i].nextElementSibling.defaultValue;
//        console.log("응모 이벤트 번호 : " + draw_id);
//        console.log("현재 로그인 유저 아이디 : " + login_id);
        if (login_id == null || login_id == "" || login_id.legnth == 0 || login_id == undefined) {
            if (confirm("로그인이 필요한 서비스입니다. 로그인 하시겠습니까?")) {
                location.href = '/login';
            }
            else
                return;
        } else {
        	if(this.value == '이벤트 마감'){
        		alert('이미 마감된 이벤트 입니다.');
        		return;
        	}
            let draw_apply = {
                draw_id: draw_id,
                member_id: login_id
            }
            console.log("응모 지원 정보 " + draw_apply);
            $.ajax({
                type: "POST",
                url: "/shop/draw_customer_button",
                contentType: "application/json",
                data: JSON.stringify(draw_apply),
                success: function (data) {
                    if (data == "fail") {
                        alert("이미 응모했습니다.");
                    } else if (data == "success") {
                        alert("응모에 성공하셨습니다.");
                    }
                    /*
					 * $('.draw_customer').load( location.href + '
					 * .draw_customer');
					 */
                },
                error: function (data) {
                    /* console.log(data); */
                    alert('죄송합니다. 잠시후 다시 시도해 주세요.');
                }
            });
        }
    })

}
// 응모 결과 보기 버튼
let draw_button2 = document.querySelectorAll('.font_top_button_end'); // 폰트 퍼튼
																		// 값
draw_button2.forEach((item) => { 
    item.addEventListener("click", function () {
        let draw_id = this.previousElementSibling.value;
        
        let draw_button3 = {
            draw_id: draw_id
        };
        $.ajax({
            url: '/shop/draw_customer_button_ajax',
            contentType: 'application/json;charset=utf-8',
            data: JSON.stringify(draw_button3),
            dataType: 'json',
            type: 'post',
            error: function () {
                alert('죄송합니다. 잠시후 다시 시도해 주세요.');
            },
            success: function(data) {
            	$('.modal_final_middle').html('');
                let dataArr = data;
                dataArr.forEach( (item) => {
                	
                	let elem = document.createElement('span');
            		elem.className = 'winnerSpan';
                	elem.innerHTML = item.member_id;
                	
                	$('.modal_final_middle').append(elem);
                });
                
                let winnerSpans = document.querySelectorAll('.winnerSpan');
                
                for(let i = 0; i < winnerSpans.length; i++){
                	if(winnerSpans[i].innerHTML == login_id){
                		winnerFlag = true;
                		alert('축하합니다. 당첨 되셨습니다.');
                		limitedBtns.style.display = 'block';
                		limitedBtns.addEventListener('click', function(){
                			location.href = '/order?product_id=' + limiteProduct.value;
;                		});
                	}
                	
                		
                	
                		
                }
                
               
            }
        });
    });
});
