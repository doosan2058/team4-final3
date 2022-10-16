let draw_button = document.querySelector('.font_top_button'); // 버튼을 눌렀을때 이벤트 발생
// let draw_modal= document.querySelector('.modal_container'); // 응모 성공 모달
let draw_final_modal = document.querySelector('.modal_final_container');
let draw_final_button = document.querySelectorAll('.font_top_button_end');
// let modal_close=document.querySelector('.modal_bottom_btn');
let modal_final_close = document.querySelectorAll ('.modal_final_bottom_btn');
let modal_final_middle = document.querySelectorAll('.modal_final_middle');



//draw_button.addEventListener('click',function(){
   // location.href = 'draw_customer_count.html';
    // draw_modal.style.display = "block";
    // document.body.style.overflow = "hidden"; 
//})

// modal_close.addEventListener('click',function(){
//     draw_modal.style.display = "none";
//     document.body.style.overflow = "unset";
// })
for(let i=0; i<modal_final_close.length; i++){
    modal_final_close[i].addEventListener('click',function(){
        
        draw_final_modal.style.display = "none";
        document.body.style.overflow = "unset";
    })
}

for(let i=0; i<draw_final_button.length; i++){
    draw_final_button[i].addEventListener('click',function(){
        draw_final_modal.style.display = "block";
        document.body.style.overflow = "hidden"; 
    })
}

// for(let i=0; i<draw_final_button.length; i++){
//     draw_final_button[i].addEventListener('click',function(){
//         let final_font = Json.parse(JSON.stringify(final_first));
//         for(let j=0; j<modal_final_middle.length; j++){
//             modal_final_middle[j].textContent=final_font[j].name;
//         }
//     })
// }

