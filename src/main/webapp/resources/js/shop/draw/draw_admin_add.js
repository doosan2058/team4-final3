/*한정판 상품 목록 컨테이너 */
const selectDrawProductContainer = document.querySelector('.selectDrawProductContainer');
/*한정판 상품 */
const drawItem = document.querySelectorAll('.drawItem');
/*한정판 상품 이름 집어넣을 스팬 */
const selectedDrawName = document.querySelector('#selectedDrawName');
/*한정판 상품 목록 보기 버튼 */
const selectDrawBtn = document.querySelector('#selectDrawBtn');
/*서브밋 버튼*/
const file_submit = document.querySelector('.file_submit');


file_submit.addEventListener("click",function(){ // 글입력 유효성
	if(tag.value==null || tag.value==""){
		alert("말머리를 선택해 주세요");
	} else if(title.value==null || title.value==""){
		alert("제목을 입력해 주세요");
	} else {
		let add_point = Math.round(Math.random()*10);
		write_point.value = add_point;
		if(add_point==0){
			alert("글쓰기 포인트 "+add_point+"점 획득(ㅠㅠ 일진이 사납네요)");
		}else if(add_point==10){
			alert("글쓰기 포인트 "+add_point+"점 획득(복권 사도 되겠어요!)");
		}else {
			alert("글쓰기 포인트 "+add_point+"점 획득");
		}
		input_form.submit();
	}
})



 // value 값을 찾아서 



var now_utc = Date.now() // 지금 날짜를 밀리초로
// getTimezoneOffset()은 현재 시간과의 차이를 분 단위로 반환
var timeOff = new Date().getTimezoneOffset()*60000; // 분단위를 밀리초로 변환
// new Date(now_utc-timeOff).toISOString()은 '2022-05-11T18:09:38.134Z'를 반환
var today = new Date(now_utc-timeOff).toISOString().split("T")[0];
document.getElementById("Date").setAttribute("min", today);
document.getElementById("Date_event_end").setAttribute("min", today);
document.getElementById("Date_pur_start").setAttribute("min", today);
document.getElementById("Date_start").setAttribute("min", today);




/*var now_utc2 = Date.now()+7  //// 지금 날짜를 밀리초로
var timeOff2 = new Date().getTimezoneOffset()*60000; // 분단위를 밀리초로 변환
var today2 = new Date(now_utc2-timeOff).toISOString().split("T")[0]; // new Date(now_utc-timeOff).toISOString()은 '2022-05-11T18:09:38.134Z'를 반환-
document.getElementById("Date_event_end").setAttribute("min", today);*/









selectDrawBtn.addEventListener('click' , showDrawContainer);

/*한정판 상품목록 컨테이너 여는 함수 */
function showDrawContainer(){
    selectDrawProductContainer.style.display = 'block';
}