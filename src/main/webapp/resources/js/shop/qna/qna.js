let login_id = document.querySelector(".sesseionlogin_id");
    let QnA_Title_btn = document.querySelector("#QnA_Title_btn");
    let checkboxbtn = document.querySelector("#checkboxbtn");
    let checktype = document.querySelectorAll(".checktype");
    let asked = document.querySelector("#QnA_Title_btnAsked");
    
    QnA_Title_btn.addEventListener('click',function(){
    	if(login_id.value!=null && login_id.value!=''){

    		location.href="/shop/question";
    	}else{
    		alert("로그인 후 이용해주세요!");
    		location.href="/login";
    	}
    	
    });
    
    asked.addEventListener('click', function(){
    	location.href="/shop/asked_question";
    }); 
    
    
    checkboxbtn.addEventListener('click', function(){
    	for(let i=0; i<checktype.length; i++){
    		if(checktype[i].checked == true){

    			document.sk.submit();
    		}
    	}
    		
    })