let grade = document.querySelectorAll(".grade");

for(let i=0; i<grade.length;i++){
	grade[i].addEventListener('mouseenter',changeColor);
	grade[i].addEventListener('mouseleave',initColor);
}
function changeColor(){
	let text = this.dataset.hover;
	this.style.boxShadow = `2px 2px 2px 0px ${text}`;
	

	
}
function initColor(){
	this.style.boxShadow = 'none';
	
}