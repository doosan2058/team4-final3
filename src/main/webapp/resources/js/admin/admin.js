/**
 * 
 */
'use strict'

//네비
const nav = document.querySelector('.nav');
//메인
const main = document.querySelector('.main');
//네비 인아웃 플래그
let navFlag = false;
//네비 아이콘
const navIcon = document.querySelector('.navIcon');
//모든 회원 보기
const allMemberAnchor = document.querySelector('.allMemberAnchor');
//프로모션 메일 보내기
const mailAnchor = document.querySelector('.mailAnchor');
//모니터링
const graphAnchor = document.querySelector('.graphAnchor');
//메인으로
const homeAnchor = document.querySelector('.homeAnchor');
// =====================================================================================================
navIcon.addEventListener('click', navInOut);
allMemberAnchor.addEventListener('click', showMemberList);
mailAnchor.addEventListener('click', showSend);
graphAnchor.addEventListener('click', monitoringFunc);
homeAnchor.addEventListener('click', goHome);

// =====================================================================================================

//메인으로
function goHome(){
	let result = confirm('메인 화면으로 나가시겠습니까?');
	if(result){
		location.href = '/';
	}
}
//모니터링 정보
function monitoringFunc(){
	
	
	$.ajax({
		type: 'get',
		url: '/admin/count',
		error: function(){
			alert('죄송합니다. 잠시후 다시 시도해 주세요.');
		},
		success: function(data){
		
			$('.mainBottom').html(data);
			
			let dailyInputs = document.querySelectorAll('.dailyCount');
			let weekInputs = document.querySelectorAll('.weekCount');
			
			let dailyInputsBoard = document.querySelectorAll('.dailyCountBoard');
			let weekInputsBoard = document.querySelectorAll('.weekCountBoard');

			let dailyInputsQna = document.querySelectorAll('.dailyCountQna');
			let weekInputsQna = document.querySelectorAll('.weekCountQna');
			
			let dailyInputsShop = document.querySelectorAll('.dailyCountShop');
			let weekInputsShop = document.querySelectorAll('.weekCountShop');
			
			let dailyCountDateArr = [];
			let dailyCountCountArr = [];
			let weekCountDateArr = [];
			let weekCountCountArr = [];
			
			let dailyCountDateBoardArr = [];
			let dailyCountCountBoardArr = [];
			let weekCountDateBoardArr = [];
			let weekCountCountBoardArr = [];
			
			let dailyCountDateQnaArr = [];
			let dailyCountCountQnaArr = [];
			let weekCountDateQnaArr = [];
			let weekCountCountQnaArr = [];
			
			let dailyCountDateShopArr = [];
			let dailyCountCountShopArr = [];
			let weekCountDateShopArr = [];
			let weekCountCountShopArr = [];
			
			dailyInputs.forEach((item) => {
				dailyCountDateArr.push(item.dataset.date);
				dailyCountCountArr.push(item.dataset.count);
			});
			weekInputs.forEach((item) => {
				weekCountDateArr.push(item.dataset.date);
				weekCountCountArr.push(item.dataset.count);
			});
			
			dailyInputsBoard.forEach((item) => {
				dailyCountDateBoardArr.push(item.dataset.date);
				dailyCountCountBoardArr.push(item.dataset.count);
			});
			weekInputsBoard.forEach((item) => {
				weekCountDateBoardArr.push(item.dataset.date);
				weekCountCountBoardArr.push(item.dataset.count);
			});
			
			dailyInputsQna.forEach((item) => {
				dailyCountDateQnaArr.push(item.dataset.date);
				dailyCountCountQnaArr.push(item.dataset.count);
			});
			weekInputsQna.forEach((item) => {
				weekCountDateQnaArr.push(item.dataset.date);
				weekCountCountQnaArr.push(item.dataset.count);
			});
			
			dailyInputsShop.forEach((item) => {
				dailyCountDateShopArr.push(item.dataset.date);
				dailyCountCountShopArr.push(item.dataset.count);
			});
			weekInputsShop.forEach((item) => {
				weekCountDateShopArr.push(item.dataset.date);
				weekCountCountShopArr.push(item.dataset.count);
			});
			
			
			new Chart(document.getElementById("line-chart"), {
				  type: 'line',
				  data: {
				    labels: dailyCountDateArr.reverse(),
				    datasets: [{ 
				        data: dailyCountCountArr.reverse(),
				        label: "일간 접속자",
				        borderColor: "#3e95cd",
				        fill: false
				      }
				    ]
				  },
				  options: {
					  responsive: false,
				    title: {
				      display: true,
				      text: '팀4'
				    }
				  }
				  
				});
			
			new Chart(document.getElementById("line-chart2"), {
				  type: 'line',
				  data: {
				    labels: weekCountDateArr.reverse(),
				    datasets: [{ 
				        data: weekCountCountArr.reverse(),
				        label: "주간 접속자",
				        borderColor: "#3e95cd",
				        fill: false
				      }
				    ]
				  },
				  options: {
					  responsive: false,
				    title: {
				      display: true,
				      text: '팀4'
				    }
				  }
				});
			
			new Chart(document.getElementById("line-chart3"), {
				  type: 'line',
				  data: {
				    labels: dailyCountDateBoardArr.reverse(),
				    datasets: [{ 
				        data: dailyCountCountBoardArr.reverse(),
				        label: "일간 자유게시글",
				        borderColor: "#3e95cd",
				        fill: false
				      }
				    ]
				  },
				  options: {
					  responsive: false,
				    title: {
				      display: true,
				      text: '팀4'
				    }
				  }
				});
			
			new Chart(document.getElementById("line-chart4"), {
				  type: 'line',
				  data: {
				    labels: weekCountDateBoardArr.reverse(),
				    datasets: [{ 
				        data: weekCountCountBoardArr.reverse(),
				        label: "주간 자유게시글",
				        borderColor: "#3e95cd",
				        fill: false
				      }
				    ]
				  },
				  options: {
					  responsive: false,
				    title: {
				      display: true,
				      text: '팀4'
				    }
				  }
				});
			
			new Chart(document.getElementById("line-chart5"), {
				  type: 'line',
				  data: {
				    labels: dailyCountDateQnaArr.reverse(),
				    datasets: [{ 
				        data: dailyCountCountQnaArr.reverse(),
				        label: "일간 질문게시글",
				        borderColor: "#3e95cd",
				        fill: false
				      }
				    ]
				  },
				  options: {
					  responsive: false,
				    title: {
				      display: true,
				      text: '팀4'
				    }
				  }
				});
			
			new Chart(document.getElementById("line-chart6"), {
				  type: 'line',
				  data: {
				    labels: weekCountDateQnaArr.reverse(),
				    datasets: [{ 
				        data: weekCountCountQnaArr.reverse(),
				        label: "주간 질문게시글",
				        borderColor: "#3e95cd",
				        fill: false
				      }
				    ]
				  },
				  options: {
					  responsive: false,
				    title: {
				      display: true,
				      text: '팀4'
				    }
				  }
				});
			new Chart(document.getElementById("line-chart7"), {
				  type: 'line',
				  data: {
				    labels: dailyCountDateShopArr.reverse(),
				    datasets: [{ 
				        data: dailyCountCountShopArr.reverse(),
				        label: "일간 매출",
				        borderColor: "#3e95cd",
				        fill: false
				      }
				    ]
				  },
				  options: {
					  responsive: false,
				    title: {
				      display: true,
				      text: '팀4'
				    }
				  }
				});
			
			new Chart(document.getElementById("line-chart8"), {
				  type: 'line',
				  data: {
				    labels: weekCountDateShopArr.reverse(),
				    datasets: [{ 
				        data: weekCountCountShopArr.reverse(),
				        label: "주간 매출",
				        borderColor: "#3e95cd",
				        fill: false
				      }
				    ]
				  },
				  options: {
					  responsive: false,
				    title: {
				      display: true,
				      text: '팀4'
				    }
				  }
				});
			
		}
	});
}

//메일 보내기 폼
function showSend(){
	$.ajax({
		type: 'get',
		url: '/admin/send',
		error: function(){
			alert('죄송합니다. 잠시후 다시 시도해 주세요.');
		},
		success: function(data){
			
			$('.mainBottom').html(data);
		}
	});
}
//모든 회원 정보
function showMemberList(){
	
	
	
	$.ajax({
		type: 'get',
		url: '/admin/list',
		error: function(){
			alert('죄송합니다. 잠시후 다시 시도해 주세요.');
		},
		success: function(data){
			
			$('.mainBottom').html(data);
		}
	});
}

//네비 인아웃
function navInOut(){
	if(navFlag == false){
		nav.style.width = '20%';
		main.style.width = '80%';
		navFlag = true;
		navIcon.style.transform = 'rotateZ(90deg)';
		allMemberAnchor.innerHTML = '전체 회원 보기';
		mailAnchor.innerHTML = '프로모션 메일 보내기';
		graphAnchor.innerHTML = '모니터링 하기';
		homeAnchor.innerHTML = '메인 화면으로 나가기';
	}
	else{
		nav.style.width = '5%';
		main.style.width = '95%';
		navFlag = false;
		navIcon.style.transform = 'rotateZ(0)';
		allMemberAnchor.innerHTML = '<span class="material-symbols-outlined"> group </span>';
		mailAnchor.innerHTML = '<span class="material-symbols-outlined"> mail </span>';
		graphAnchor.innerHTML = '<span class="material-symbols-outlined"> monitoring </span>';
		homeAnchor.innerHTML = '<span class="material-symbols-outlined"> home </span>';
		
	}
}