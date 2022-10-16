let user_id = document.querySelector('#user_id');//현제 로그인중인 아이디
let list_board_title = document.querySelectorAll('.board_title');// 게시판 제목

for(let i=0; i<list_board_title.length; i++){ // 게시글 선택 시  본인 여부에 따라 보여시는 상세페이지가 다르게
	list_board_title[i].addEventListener('click',function(){
		if(user_id.value==this.parentNode.children[3].textContent){
			location.href='/community/freeNotice/detail_user?board_id='+this.parentNode.children[0].textContent;
		} else {
			location.href='/community/freeNotice/detail?board_id='+this.parentNode.children[0].textContent;
		}
	})
}