	let page = Math.ceil(board_count/10); //전체 게시글 수를 10개 단위로 나눠서 페이지 개수를 구함
	let page_count = 1; // 페이지를 표시해 줄 변수
	const page_area = document.querySelector(".section_notice_footer_page"); // 페이지가 표시 될 공간
	const first_btn = document.querySelector(".first_btn"); // 페이지 제일 처음으로 가는 버튼
	const end_btn = document.querySelector(".end_btn"); // 페이지 제일 마지막으로 가는 버튼
	const tag_list = document.querySelectorAll('.side_nav_tag'); //사이드 메뉴의 태그 리스트
	const side_form1 = document.querySelector(".side_nav_form1"); //태그 클릭 시 submit 할 form
	const side_form2 = document.querySelector(".side_nav_form2"); //검색 시 submit 할 form
	const side_form3 = document.querySelector(".side_nav_form3"); //조회순 submit 할 form
	const side_form4 = document.querySelector(".side_nav_form4"); //추천순 submit 할 form
	const input_hidden = document.querySelector(".side_nav_input"); //태그 클릭 시 정보 실려 보낼 인풋 태그
	const search_btn = document.querySelector(".side_nav_search_btn"); // 검색 버튼
	let board_title = document.querySelectorAll(".board_title"); // 게시글 제목 (상세페이지로 넘어가게)
	const write_btn = document.querySelector(".write_btn"); // 글쓰기 버튼
	const delete_btn = document.querySelector(".delete_btn"); // 관리자 글 삭제 버튼
	const hit_sort = document.querySelector(".hit_sort"); // 조회순 버튼
	const recommend_sort = document.querySelector(".recommend_sort"); //추천순 버튼
	
	hit_sort.addEventListener("click",function(){ // 조회순 정렬
		if(sort_value == "h_desc"){
			document.querySelector(".hit_value").value="h_asc";
			side_form3.action="/community/freeNotice/main";
			side_form3.submit();
		} else {
			side_form3.action="/community/freeNotice/main";
			side_form3.submit();
		}
	})
	
	recommend_sort.addEventListener("click",function(){ // 추천순 정렬
		if(sort_value == "r_desc"){
			document.querySelector(".rec_value").value="r_asc";
			side_form4.action="/community/freeNotice/main";
			side_form4.submit();
		} else {
			side_form4.action="/community/freeNotice/main";
			side_form4.submit();
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
				url : "/community/freeNotice/admin_delete",
				async: false,
				data : {
					        delete_list : delete_list        // 보내고자 하는 data 변수 설정
					    },
				success : function(data) {
					document.location.reload();
				},
				error : function(data) {
//					console.log(data);
				}
			});
		}
		
	})
	
	for(let i=0; i<board_title.length; i++){ // 제목 누르면 상세페이지로
		board_title[i].addEventListener("click",function(){
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
	
	for(let i=0; i<tag_list.length; i++){ // 태그 별 글 목록이 나오게  
		tag_list[i].addEventListener("click",function(){
			input_hidden.value = i;
			side_form1.action="/community/freeNotice/main";
			side_form1.submit();
		})
	}
	
	var tag_color = function(){ //태그 누르면 색 변하게
		document.querySelector(".side_nav").children[tag_id].style.color = "rgb(142, 206, 231)";
	}
	
	tag_color();

	search_btn.addEventListener("click",function(){ // 검색 키워드에 맞게 글 목록이 나오게
		side_form2.action="/community/freeNotice/main";
		side_form2.submit();
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
					board_tag_id : tag_id,
					keyword : searching_keyword,
					sort_value : sort_value
			};

			$.ajax({
				type : "POST",
				url : "/community/freeNotice/change_page",
				dataType : "text",
				contentType : "application/json",
				data : JSON.stringify(change_page),
				success : function(data) {
					$('.section_notice_main').html(data);
					let board_title = document.querySelectorAll(".board_title");
					for(let i=0; i<board_title.length; i++){ // 제목 누르면 상세페이지로
						board_title[i].addEventListener("click",function(){
							this.parentNode.nextElementSibling.submit();
						})
					}
				},
				error : function(data) {
//					console.log(data);
				}
			});
		})
		page_count++;
	}
	
	// 페이지 제일 처음로 가게
	first_btn.addEventListener("click",function(){
		page_area.style.left = 0;
		$(page_area).children().children().css("color","white");
		$(page_area).children().first().children().css("color","rgb(142, 206, 231)");
		
		let change_page = {
				page_count : 0,
				board_tag_id : tag_id,
				keyword : searching_keyword,
				sort_value : sort_value
		};

		$.ajax({
			type : "POST",
			url : "/community/freeNotice/change_page",
			dataType : "text",
			contentType : "application/json",
			data : JSON.stringify(change_page),
			success : function(data) {
				$('.section_notice_main').html(data);
				let board_title = document.querySelectorAll(".board_title");
				for(let i=0; i<board_title.length; i++){ // 제목 누르면 상세페이지로
					board_title[i].addEventListener("click",function(){
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
				board_tag_id : tag_id,
				keyword : searching_keyword,
				sort_value : sort_value
		};

		$.ajax({
			type : "POST",
			url : "/community/freeNotice/change_page",
			dataType : "text",
			contentType : "application/json",
			data : JSON.stringify(change_page),
			success : function(data) {
				$('.section_notice_main').html(data);
				let board_title = document.querySelectorAll(".board_title");
				for(let i=0; i<board_title.length; i++){ // 제목 누르면 상세페이지로
					board_title[i].addEventListener("click",function(){
						this.parentNode.nextElementSibling.submit();
					})
				}
			},
			error : function(data) {
//				console.log(data);
			}
		});
	})

