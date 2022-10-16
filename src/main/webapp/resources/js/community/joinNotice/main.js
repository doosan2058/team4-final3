let page = Math.ceil(camping_count/10); //전체 게시글 수를 10개 단위로 나눠서 페이지 개수를 구함
let page_count = 1; // 페이지를 표시해 줄 변수
const page_area = document.querySelector(".section_notice_footer_page"); // 페이지가 표시 될 공간
const first_btn = document.querySelector(".first_btn"); // 페이지 제일 처음으로 가는 버튼
const end_btn = document.querySelector(".end_btn"); // 페이지 제일 마지막으로 가는 버튼
const search_btn = document.querySelector(".side_nav_search_btn"); // 검색 버튼
const search_option = document.querySelector(".side_nav"); // 검색 시 submit 할 form
let camping_title = document.querySelectorAll(".camping_title"); // 게시글 제목 (상세페이지로 넘어가게)
const write_btn = document.querySelector(".write_btn"); // 글쓰기 버튼
const delete_btn = document.querySelector(".delete_btn"); // 관리자 글 삭제 버튼
const hit_sort = document.querySelector(".sort"); // 조회순 버튼
const side_form = document.querySelector(".side_nav_form"); //조회순 submit 할 form

hit_sort.addEventListener("click",function(){ // 조회순 정렬
	if(sort_value == "desc"){
		document.querySelector(".hit_value").value="asc";
		side_form.action="/community/joinNotice/main";
		side_form.submit();
	} else {
		side_form.action="/community/joinNotice/main";
		side_form.submit();
	}
})

delete_btn.addEventListener("click",function(){ // 관리자 글 삭제하기
	if (confirm("게시글을 삭제하시겠습니까?")) {
		let list_check = document.querySelectorAll(".list_check");
		let delete_list = [];
		for(i=0; i<list_check.length; i++){
			if(list_check[i].checked){
				delete_list.push(list_check[i].parentNode.nextElementSibling[1].value);
			}
		}
		
		$.ajax({
			type : "POST",
			traditional : true,
			url : "/community/joinNotice/admin_delete",
			async: false,
			data : {
				        delete_list : delete_list        // 보내고자 하는 data 변수 설정
				    },
			success : function(data) {
				document.location.reload();
			},
			error : function(data) {
//				console.log(data);
			}
		});
	}
	
	
})

for(let i=0; i<camping_title.length; i++){ // 제목 누르면 상세페이지로
	camping_title[i].addEventListener("click",function(){
		this.parentNode.nextElementSibling.submit();
	})
}

write_btn.addEventListener("click",function(){ // 글쓰기 버튼 누르면 로그인 여부 확인
	let user_id = document.querySelector("#user_id");
	if(user_id.value==null || user_id.value==""){
		if(confirm("로그인이 필요한 서비스입니다. 로그인 하시겠습니까?")) {
			location.href='/login';
		}
	} else {
		location.href='input';
	}
})

search_btn.addEventListener("click",function(){ // 검색 시 form submit
	search_option.action="/community/joinNotice/main";
	search_option.submit();
})

//페이징??
for(let i=0; i<page; i++){
	let page_div = document.createElement('div');// 페이지 숫자를 감싸는 태그
	let page_btn = document.createElement( 'p' );// 페이지가 표시될 태그
	let page_text = document.createTextNode(page_count);
	
	page_btn.append(page_text);// 페이지 숫자를 p태그에 삽입
	page_div.append(page_btn); // p태그 div에 삽입
	page_area.append(page_div);// 페이지 표시
	
	if(i==0){
		page_btn.style.color = "rgb(142, 206, 231)"; // 처음 페이지 번호 색상 지정
	}
	
	page_btn.addEventListener("click",function(){
		if(i>1 && page>5){
			page_area.style.left = -(i-2)*60+"px";
		} else {
			page_area.style.left = 0;
		}
		
		$(page_area).children().children().css("color","white");
		this.style.color="rgb(142, 206, 231)"; // 선택한 페이지 번호 색성 변경
		
		let change_page = {
				page_count : i*10,
				camping_region : camping_region,
				camping_recruitment : camping_recruitment,
				camping_departure : camping_departure,
				camping_period : camping_period,
				camping_close : camping_close,
				keyword : keyword,
				sort_value : sort_value
		};

		$.ajax({
			type : "POST",
			url : "/community/joinNotice/change_page",
			dataType : "text",
			contentType : "application/json",
			data : JSON.stringify(change_page),
			success : function(data) {
				$('.section_notice_main').html(data);
				let camping_title = document.querySelectorAll(".camping_title"); // 게시글 제목 (상세페이지로 넘어가게)
				for(let i=0; i<camping_title.length; i++){ // 제목 누르면 상세페이지로
					camping_title[i].addEventListener("click",function(){
						this.parentNode.nextElementSibling.submit();
					})
				}
			},
			error : function(data) {
//				console.log(data);
			}
		});
	})
	page_count++;
	
	// 페이지 제일 처음로 가게
	first_btn.addEventListener("click",function(){
		page_area.style.left = 0;
		$(page_area).children().children().css("color","white");
		$(page_area).children().first().children().css("color","rgb(142, 206, 231)");
		
		let change_page = {
				page_count : 0,
				camping_region : camping_region,
				camping_recruitment : camping_recruitment,
				camping_departure : camping_departure,
				camping_period : camping_period,
				camping_close : camping_close,
				keyword : keyword,
				sort_value : sort_value
		};

		$.ajax({
			type : "POST",
			url : "/community/joinNotice/change_page",
			dataType : "text",
			contentType : "application/json",
			data : JSON.stringify(change_page),
			success : function(data) {
				$('.section_notice_main').html(data);
				let camping_title = document.querySelectorAll(".camping_title"); // 게시글 제목 (상세페이지로 넘어가게)
				for(let i=0; i<camping_title.length; i++){ // 제목 누르면 상세페이지로
					camping_title[i].addEventListener("click",function(){
						this.parentNode.nextElementSibling.submit();
					})
				}
			},
			error : function(data) {
//				console.log(data);
			}
		});
	})
	
	//페이지 제일 마지막으로 가게
	end_btn.addEventListener("click",function(){
		if(page>5){
			page_area.style.left = -(page-3)*60+"px";
		}
		
		$(page_area).children().children().css("color","white");
		$(page_area).children().last().children().css("color","rgb(142, 206, 231)");
		
		let change_page = {
				page_count : (page-1)*10,
				camping_region : camping_region,
				camping_recruitment : camping_recruitment,
				camping_departure : camping_departure,
				camping_period : camping_period,
				camping_close : camping_close,
				keyword : keyword,
				sort_value : sort_value
		};

		$.ajax({
			type : "POST",
			url : "/community/joinNotice/change_page",
			dataType : "text",
			contentType : "application/json",
			data : JSON.stringify(change_page),
			success : function(data) {
				$('.section_notice_main').html(data);
				let camping_title = document.querySelectorAll(".camping_title"); // 게시글 제목 (상세페이지로 넘어가게)
				for(let i=0; i<camping_title.length; i++){ // 제목 누르면 상세페이지로
					camping_title[i].addEventListener("click",function(){
						this.parentNode.nextElementSibling.submit();
					})
				}
			},
			error : function(data) {
//				console.log(data);
			}
		});
	})
}