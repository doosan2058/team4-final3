//체크박스 전체 선택 함수
function selectAll(selectAll){
    const checkboxes
    = document.querySelectorAll('input[type="checkbox"]');

checkboxes.forEach((checkbox)=>{
    checkbox.checked = selectAll.checked
})
}
//체크박스 선택 비교 함수
function checkSelectAll(){
    const checkboxes
    = document.querySelectorAll('input[name="grade"]');
    const checked
    = document.querySelectorAll('input[name="grade"]:checked');
    const selectAll
    = document.querySelector('input[name="selectall"]');


    if(checked.length === selectAll.length){
        selectAll.checked = true;
    }else{
        selectAll.checked = false;
    }

}

//삭제 버튼 클릭 시 selectDelete()함수 실행
function selectDelete(){
	
	const checked
	= document.querySelectorAll('input[name="grade"]:checked');
	
	//체크박스를 선택 or 선택하지않았을 때 팝업창 띄우기
	if(checked.length < 1){
		alert('삭제할 등급을 선택하지 않았습니다.');
		return false;
	}else{
		let confirmCk = confirm('해당 등급을 삭제하시겠습니까?');
		
		if(confirmCk){
				let valueArr = new Array();
				let list = document.querySelectorAll('input[name="grade"]');
//				console.log(list);
				for(let i=0; i<list.length; i++){
					if(list[i].checked){
						valueArr.push(list[i].value);
					}
				}
//				console.log(valueArr);
				
				//삭제 
				$.ajax({
					url : "/gradePolicy_adminDelete", 
					type :"post",
					dataType : "text",
					data : { valueArr : valueArr},	//보내고자 하는 데이터 변수 설정
					success : function(data){
						alert(data);
						location.replace("/gradePolicy_admin"); //해당 페이지로 새로고침
					},
					error : function(){
						alert('삭제하지 못했습니다. 다시 시도해주세요.');
					}
				});
		}
	}
};


//추가 버튼 클릭 시 단계 다음 정보 가져오기


