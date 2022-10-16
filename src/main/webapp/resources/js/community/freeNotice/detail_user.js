let board_id = $('.board_id').val(); // ajax 통신에 이용할 게시판 번호
let s_count = 5; // view more에 사용할 변수
const get_reply_count = document.querySelector(".reply_count"); // 댓글수가 표시되는 부분
const delete_btn = document.querySelector(".section_header_info_btns_deleteBtn"); // 삭제하기 버튼
const delete_form = document.querySelector(".delete_form"); // 삭제할 때 쓸 form
let reply_delete = document.querySelectorAll(".xi-close");// 댓글 삭제 버튼

delete_btn.addEventListener("click", function() { //게시글 삭제
	if (confirm("게시글을 삭제하시겠습니까?")) {
		delete_form.submit();
	}
})


var delete_reply = function () { //댓글 삭제 함수
	if (confirm("댓글을 삭제하시겠습니까?")) {
		let board_reply_id = this.nextElementSibling.value;
		let reply_box = this.parentNode.parentNode;
		let reply_id = {
				board_reply_id : board_reply_id
		};
		$.ajax({
			type : "POST",
			url : "/community/freeNotice/delete_reply",
			dataType : "text",
			contentType : "application/json",
			data : JSON.stringify(reply_id),
			success : function(data) {
				reply_box.style.display="none";
				$('.reply_count').load(location.href+' .reply_count');
			},
			error : function(data) {
				console.log(data);
			}
		});
	}
}

for(let i=0; i<reply_delete.length; i++){ // 댓글 삭제
	reply_delete[i].addEventListener("click",delete_reply)
}



//댓글 달기
$(function() {
	$('.section_footer_btnArea_btn').click(function() {
		let user_id = document.querySelector('#user_id').value; // 현재 접속중인 유저 아이디
		if(user_id==null || user_id==""){
			if(confirm("로그인이 필요한 서비스입니다. 로그인 하시겠습니까?")) {
				location.href='/login';
			}
		} else {
			let comment = $('.section_footer_commentInput').val();
			if (comment == "" || comment == null) {
				alert("내용을 입력하세요");
			} else {  
				
				let reply_info = {
					id : user_id,
					comment : comment,
					board_id : board_id
				};
				$.ajax({
					type : "POST",
					url : "/community/freeNotice/reply_input",
					dataType : "text",
					contentType : "application/json",
					data : JSON.stringify(reply_info),
					success : function(data) {
						$('.section_footer_commentList').prepend(data);
						$('.reply_count').load(location.href+' .reply_count');
						let delete_btn = document.querySelector(".xi-close"); //댓글 추가 후 삭제 버튼
						delete_btn.addEventListener("click",delete_reply)// 댓글 추가 후 삭제
					},
					error : function(data) {
//						console.log(data);
					}
				});
				document.querySelector(".section_footer_commentInput").value = "";
			}
		}
	});
});

// 댓글 view more
$(function() {
	$('.section_footer_commentList_viewMore_btn').click(function() {
		let view_more = {
				board_id : board_id,
				s_count : s_count,
		};
		$.ajax({
			type : "POST",
			url : "/community/freeNotice/view_more",
			dataType : "text",
			contentType : "application/json",
			data : JSON.stringify(view_more),
			success : function(data) {
				$('.section_footer_commentList').append(data);
			},
			error : function(data) {
//				console.log(data);
			}
		});
		s_count = s_count+5;
		if(s_count>reply_count || s_count==reply_count){
			document.querySelector(".section_footer_commentList_viewMore").style.display = "none";
		}
	})
})
