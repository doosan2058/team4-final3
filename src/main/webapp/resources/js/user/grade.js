let grade = document.querySelectorAll(".gradeDiv");

for(let i=0; i<grade.length;i++){
	grade[i].addEventListener('mouseenter',changeColor);
	grade[i].addEventListener('mouseleave',initColor);
}
function changeColor(){
	let text = this.dataset.hover;
	this.style.boxShadow = `0px 0px 4px 2px ${text}`;
	

	
}
function initColor(){
	this.style.boxShadow = 'none';
	
}