const campsite_search = document.querySelector(".campsite_search"); // 캠핑장 찾기 모달 팝업 버튼
const modal = document.querySelector(".section_header_back"); // 캠핑장 찾기 모달
const modal_search = document.querySelector(".modal_search"); // 모달에서 검색 버튼
const modal_submit = document.querySelector(".modal_submit"); // 모달 확인 버튼
const modal_close = document.querySelector(".modal_close"); // 모달 닫기 버튼
const search_category = document.querySelector(".section_header_modal_category"); //모달 카테고리
const search_region = document.querySelector(".search_region"); //모달 지역 검색
const search_keyword = document.querySelector(".search_keyword"); //모달 검색 키워드
const search_result = document.querySelector(".section_header_modal_result_box"); // 모달 검색 시 내용이 표시될 곳
const camping_title = document.querySelector(".section_header_headline")// 제목 입력
const camping_date = document.querySelector(".section_header_select_date")// 출발일
const camping_period = document.querySelector(".section_header_select_fewDays")// 기간 선택
const camping_region = document.querySelector(".section_header_select_area")// 지역 선택
const camping_recruitment = document.querySelector(".section_header_select_count")// 인원 선택
const camping_campsite = document.querySelector(".camping_campsite")//캠핑장 선택
const submit_btn = document.querySelector(".section_footer_registrationBtn")//캠핑장 선택
const input_form = document.querySelector(".section")//input form
let write_point = document.querySelector(".write_point"); // 글쓰기 포인트 담아줄 input

submit_btn.addEventListener("click",function(){ //글 입력 유효성
	if(camping_title.value==null || camping_title.value==""){
		alert("제목을 입력해 주세요");
	} else if(camping_date.value==null || camping_date.value==""){
		alert("출발일을 지정해 주세요");
	} else if(camping_period.value==null || camping_period.value==""){
		alert("캠핑 기간을 지정해 주세요");
	} else if(camping_region.value==null || camping_region.value==""){
		alert("캠핑 기간을 지정해 주세요");
	} else if(camping_recruitment.value==null || camping_recruitment.value==""){
		alert("모집 인원을 지정해 주세요");
	} else if(camping_campsite.value==null || camping_campsite.value==""){
		alert("캠핑장소를 선택해 주세요");
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

document.addEventListener('keydown', function(event) { // 엔터키 이벤트 막기
	  if (event.keyCode === 13) {
	    event.preventDefault();
	  };
	}, true);


campsite_search.addEventListener("click",function(){ //캠핑장 찾기 모달 팝업
	modal.style.display = "block";
	document.body.style= "overflow: hidden";
})

modal_search.addEventListener("click",function(){ // 캠핑장 검색
	let campsite_search = {
			search_category : search_category.value,
			search_region : search_region.value,
			search_keyword : search_keyword.value
		};
		console.log(campsite_search);
		$.ajax({
			type : "POST",
			url : "/community/joinNotice/campsite",
			dataType : "text",
			contentType : "application/json",
			data : JSON.stringify(campsite_search),
			success : function(data) {
				$(search_result).html(data);
			},
			error : function(data) {
//				console.log(data);
			}
		});
})

modal_submit.addEventListener("click",function(){ //모달 창 확인
	let list = document.getElementsByName("list");
	for(let i=0; i<list.length; i++){
		if (list[i].checked){
			document.getElementsByName("camping_campsite")[0].value = list[i].id;
		}
	}
//	console.log(list);
	modal.style.display = "none";
	document.body.style= "overflow: auto";
})

modal_close.addEventListener("click",function(){ //모달 창 닫기
	modal.style.display = "none";
	document.body.style= "overflow: auto";
})


