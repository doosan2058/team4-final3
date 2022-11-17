'use strict'

const qna_title = document.querySelector('#qna_title');
const qnaTitleLengthSpan = document.querySelector('#qnaTitleLengthSpan');
const qna_picture_url = document.querySelector('#qna_picture_url');
const fileIcon = document.querySelector('.xi-paperclip');
const qnaImgDeleteSpan = document.querySelector('.qnaImgDeleteSpan');
const uploadImgContainerCloseIcon = document.querySelector('.uploadImgContainerCloseIcon');
const uploadImgContainer = document.querySelector('.uploadImgContainer');

// 이미지 타입
const imgTypeArray = ['image/jpeg','image/png','image/jpg','image/gif'];

// =====================================================================================================================
qna_title.addEventListener('keyup', changeTitleInputLength);
qna_picture_url.addEventListener('change', uploadImg);
qnaImgDeleteSpan.addEventListener('click', cancelUploadImg);
fileIcon.addEventListener('click', showQnaUploadImgContainer);
uploadImgContainerCloseIcon.addEventListener('click', closeQnaUploadImgContainer);
// =====================================================================================================================


function showQnaUploadImgContainer(){
    uploadImgContainer.style.display = 'block';
    uploadImgContainer.children[1].style.animation = 'showModal 0.3s 1 forwards';
}
function closeQnaUploadImgContainer(){
    uploadImgContainer.children[1].style.animation = 'hiddenModal 0.3s 1 forwards';
    setTimeout(function(){
        uploadImgContainer.style.display = 'none';
    }, 300);
}

function changeTitleInputLength(){
    let titleInputLength = qna_title.value.length;
    qnaTitleLengthSpan.innerHTML = `(${titleInputLength}/30)`;
}

function uploadImg(){
    // jpeg, png, jpg, gif 파일만 업로드
    if(imgTypeArray.indexOf(this.files[0].type) == -1){
        alert('사진 파일만 첨부할수 있습니다.');
        return;
    }
    else{
        if(this.files.length == 1){
            fileIcon.style.display = 'block';
            qnaImgDeleteSpan.style.display = 'block';
            let fileReader = new FileReader();
            fileReader.addEventListener('load', function () {
                document.querySelector('.qnaUploadImg').src = fileReader.result;
            });
            if (qna_picture_url.files[0])
                fileReader.readAsDataURL(qna_picture_url.files[0]);
        }
        else if(this.files.length == 0){
            fileIcon.style.display = 'none';
            qnaImgDeleteSpan.style.display = 'none';
        }
        else{
            alert('첨부파일 선태중 오류가 발생하였습니다. 관리자에게 문의해 주세요.');
            const dataTransfer = new DataTransfer();
            this.files = dataTransfer.files;
        }
    }

}

function cancelUploadImg(){
    const dataTransfer = new DataTransfer();
    qna_picture_url.files = dataTransfer.files;
    fileIcon.style.display = 'none';
    qnaImgDeleteSpan.style.display = 'none';
}