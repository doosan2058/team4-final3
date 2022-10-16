let tag = document.querySelector(".section_header_brackets"); // 말머리 선택 select 박스
let title = document.querySelector(".section_header_headline"); // 제목 입력 박스
let input_form = document.querySelector(".section"); // input form
let submit_btn = document.querySelector(".section_footer_registrationBtn"); // 글등록 버튼

submit_btn.addEventListener("click",function(){ // 글입력 유효성
	if(tag.value==null || tag.value==""){
		alert("말머리를 선택해 주세요");
	} else if(title.value==null || title.value==""){
		alert("제목을 입력해 주세요");
	} else {
		input_form.submit();
	}
})


