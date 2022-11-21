<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- css 링크 -->
    <link rel="stylesheet" href="/resources/css/shop/product/product_add.css">
    <!-- 글로벌 css -->
    <link rel="stylesheet" href="/resources/css/global.css">

    <title>상품 등록 페이지</title>
</head>
<body>
<!-- 헤더 -->
<jsp:include page="../shop_header.jsp"/>
<!--메인-->
<main>
    <form action="register" method="post" enctype="multipart/form-data">
        <!--상품 등록 컨테이너-->
        <div class="registProductCon">
            <div class="registProductDiv">
                <div class="productAddLineDiv">
                    <span class="productAddTitleSpan">브랜드<span class="requiredSpan"> *</span></span>
                    <select name="product_brand_id" class="productAddInputs">
                        <c:forEach var="item" items="${brandList}">
                            <option value="${item.product_brand_id }">${item.product_brand_name }</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="productAddLineDiv">
                    <span class="productAddTitleSpan">카테고리<span class="requiredSpan"> *</span></span>
                    <select name="product_category_id" class="productAddInputs">
                        <c:forEach var="item" items="${categoryList}">
                            <option value="${item.product_category_id }">${item.product_category_name }</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="productAddLineDiv">
                    <span class="productAddTitleSpan">한정판<span class="requiredSpan"> *</span></span>
                    <div class="productAddLineInnerDiv">
                        <label for="productLimitieN">일반</label>
                        <input type="radio" name="product_limited" value="일반" checked="checked" id="productLimitieN">
                        <label for="productLimitieY">한정판</label>
                        <input type="radio" name="product_limited" value="한정판" id="productLimitieY">
                    </div>

                </div>
                <div class="productAddLineDiv">
                    <span class="productAddTitleSpan">재고<span class="requiredSpan"> *</span></span>
                    <input type="number" class="productAddInputs" name="product_stock" required="required" min="1" max="99" placeholder="재고를 입력하세요.(1 ~ 99)">
                </div>
                <div class="productAddLineDiv">
                    <span class="productAddTitleSpan">이름<span class="requiredSpan"> *</span></span>
                    <input type="text" class="productAddInputs" name="product_name" id="product_name" required="required" maxlength="20" placeholder="상품의 이름을 입력하세요.(최대 20자)">
                    <span id="productNameLengthSpan" class="productAddInputLengthSpan">(0/20)</span>
                </div>
                <div class="productAddLineDiv">
                    <span class="productAddTitleSpan">설명</span>
                    <textarea name="product_comment" id="product_comment" placeholder="상품의 설명을 작성하세요.(최대 200자)" maxlength="200"></textarea>
                    <span id="productCommentLengthSpan" class="productAddInputLengthSpan">(0/200)</span>
                </div>
                <div class="productAddLineDiv">
                    <span class="productAddTitleSpan">가격<span class="requiredSpan"> *</span></span>
                    <input type="number" class="productAddInputs" name="product_price" required="required" min="1" max="99999999" placeholder="가격을 입력하세요.(1 ~ 99,999,999)">
                </div>
                <div class="productAddLineDiv">
                    <span class="productAddTitleSpan">상품 이미지(최대 3개)<span class="requiredSpan"> *</span></span>
                    <div class="productAddLineInnerDiv">
                        <label for="mainFile" class="fileAddLabel">+</label>
                        <span id="productMainImgSpan"></span>
                        <input type="file" name="imgs1" id="mainFile" multiple required>
                    </div>


                </div>
                <div class="productAddLineDiv">
                    <div class="mainFileListDiv"></div>
                </div>
                <div class="productAddLineDiv">
                    <span class="productAddTitleSpan">설명 이미지(최대 2개)<span class="requiredSpan"> *</span></span>
                    <div class="productAddLineInnerDiv">
                        <label for="subFile" class="fileAddLabel">+</label>
                        <span id="productSubImgSpan"></span>
                        <input type="file" name="imgs2" id="subFile" multiple required>
                    </div>


                </div>
                <div class="productAddLineDiv">
                    <div class="subFileListDiv"></div>
                </div>
                <div class="productAddLineDiv">
                    <span class="productAddTitleSpan">유튜브 주소</span>
                    <input type="text" class="productAddInputs" name="product_youtube_url" id="product_youtube_url" maxlength="100" placeholder="광고 주소를 입력하세요.(최대 100자)">
                    <span id="productUrlLengthSpan" class="productAddInputLengthSpan">(0/100)</span>
                </div>
                <div class="productAddLineDiv">
                    <span class="productAddTitleSpan">배송 예정일 <span class="requiredSpan"> *</span></span>
                    <input type="number" class="productAddInputs" name="product_delivery_day" required="required" min="1" max="10" placeholder="평균 배송일을 입력하세요.(1 ~ 10)">
                </div>
                <div class="btnDiv">
                    <input type="button" class="productAddBtns preBtn" value="미리보기">
                    <input type="submit" class="productAddBtns" value="등록" id="addBtn">
                </div>
            </div>
            <!--미리보기-->
            <div class="prePageDiv">
                <i class="xi-close closePreDivIcon" style="display: none"></i>
                <div class="prePageDivTop">
                    <div class="prePageDivTopLeft">
                        <div class="productImgMain">
                            <img id="prevMainImg">
                        </div>
                        <div class="productImgFirst">
                            <img class="mainImgs">
                        </div>
                        <div class="productImgSecond">
                            <img class="mainImgs">
                        </div>
                        <div class="productImgThird">
                            <img class="mainImgs">
                        </div>
                    </div>
                    <div class="prePageDivTopRight"></div>
                </div>
                <div class="prePageDivBottom">
                    <div class="subImgFirst">
                        <img class="subImgs">
                    </div>
                    <div class="subImgSecond">
                        <img class="subImgs">
                    </div>
                </div>

            </div>
        </div>
    </form>

</main>
<!-- 푸터 -->
<jsp:include page="../shop_footer.jsp"/>
<!-- js -->
<script src="/resources/js/shop/product/product_add.js"></script>
</body>
</html>