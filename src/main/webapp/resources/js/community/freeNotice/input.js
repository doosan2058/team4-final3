let tag = document.querySelector(".section_header_brackets"); // 말머리 선택 select 박스
let title = document.querySelector(".section_header_headline"); // 제목 입력 박스
let input_form = document.querySelector(".section"); // input form
let submit_btn = document.querySelector(".section_footer_registrationBtn"); // 글등록 버튼
let write_point = document.querySelector(".write_point"); // 글쓰기 포인트 담아줄 input

submit_btn.addEventListener("click",function(){ // 글입력 유효성
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


