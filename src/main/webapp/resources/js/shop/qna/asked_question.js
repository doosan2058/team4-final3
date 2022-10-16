var asked_Question_accordion = document.getElementsByClassName("asked_Question_accordion");
var i;

for (i = 0; i < asked_Question_accordion.length; i++) {
    asked_Question_accordion[i].addEventListener("click", function () {
        this.classList.toggle("active");
        var panel = this.nextElementSibling;
        if (panel.style.maxHeight) {
            panel.style.maxHeight = null;
        } else {
            panel.style.maxHeight = panel.scrollHeight + "px";
        }
    });
}

