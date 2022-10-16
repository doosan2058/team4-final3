let list_btn = document.querySelector(".list_btn");

list_btn.addEventListener('click', function () {
    if ('${sessionScope.login_id}' == 'admin') {
        location.href = "/shop/qna_admin"
    } else {
        location.href = "/shop/qna"
    }


});