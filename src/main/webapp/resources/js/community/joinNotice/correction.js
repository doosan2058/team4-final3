const campsite_search = document.querySelector(".campsite_search"); // 캠핑장 찾기 모달 팝업 버튼
const modal = document.querySelector(".section_header_back"); // 캠핑장 찾기 모달
const modal_search = document.querySelector(".modal_search"); // 모달에서 검색 버튼
const modal_submit = document.querySelector(".modal_submit"); // 모달 확인 버튼
const modal_close = document.querySelector(".modal_close"); // 모달 닫기 버튼
const search_category = document.querySelector(".section_header_modal_category"); //모달 카테고리
const search_region = document.querySelector(".search_region"); //모달 지역 검색
const search_keyword = document.querySelector(".search_keyword"); //모달 검색 키워드
const search_result = document.querySelector(".section_header_modal_result_box"); // 모달 검색 시 내용이 표시될 곳
const camping_applicant = document.querySelector(".section_header_select_applicant"); // 현재 참여 인원
const camping_title = document.querySelector(".section_header_headline")// 제목 입력
const camping_date = document.querySelector(".section_header_select_date")// 출발일
const camping_period = document.querySelector(".section_header_select_fewDays")// 기간 선택
const camping_region = document.querySelector(".section_header_select_area")// 지역 선택
const camping_recruitment_num = document.querySelector(".section_header_select_count")// 인원 선택
const camping_campsite = document.querySelector(".camping_campsite")//캠핑장 선택
const submit_btn = document.querySelector(".section_footer_registrationBtn")//캠핑장 선택
const input_form = document.querySelector(".section")//input form

submit_btn.addEventListener("click",function(){ // 글입력 유효성
	if(camping_title.value==null || camping_title.value==""){
		alert("제목을 입력해 주세요");
	} else if(camping_date.value==null || camping_date.value==""){
		alert("출발일을 지정해 주세요");
	} else if(camping_period.value==null || camping_period.value==""){
		alert("캠핑 기간을 지정해 주세요");
	} else if(camping_region.value==null || camping_region.value==""){
		alert("캠핑 기간을 지정해 주세요");
	} else if(camping_recruitment_num.value==null || camping_recruitment_num.value==""){
		alert("모집 인원을 지정해 주세요");
	} else {
		input_form.submit();
	}
})

document.addEventListener('keydown', function(event) { // 엔터키 이벤트 막기
	  if (event.keyCode === 13) {
	    event.preventDefault();
	  };
	}, true);

for(let i=0; i<camping_recruitment; i++){ //현재 참여 인원 option을 참여 가능 인원만큼만 생성
	let option_tag = document.createElement("option");
	option_tag.textContent = i+1;
	option_tag.value = i+1;
	if(applicant_count==i+1){
		option_tag.setAttribute('selected',true);
	}
	camping_applicant.append(option_tag);
}

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
	modal.style.display = "none";
	document.body.style= "overflow: auto";
})

modal_close.addEventListener("click",function(){ //모달 창 닫기
	modal.style.display = "none";
	document.body.style= "overflow: auto";
})


