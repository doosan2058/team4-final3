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
                <table>
                    <thead>
                    <tr>
                        <th></th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td><p>카테고리 선택</p></td>
                        <td>
                            <select name="product_category_id" class="productAddInputs">
                            <c:forEach var="item" items="${categoryList }">
                                <option value="${item.product_category_id }">${item.product_category_name }</option>
                            </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td><p>브랜드 선택</p></td>
                        <td><select name="product_brand_id" class="productAddInputs">
                            <c:forEach var="item" items="${brandList }">
                                <option value="${item.product_brand_id }">${item.product_brand_name }</option>
                            </c:forEach>
                        </select></td>
                    </tr>
                    <tr>
                        <td><p>한정판 여부</p></td>
                        <td><input type="radio" name="product_limited" value="한정판">한정판 <input type="radio"
                                                                                                    name="product_limited"
                                                                                                    value="일반"
                                                                                                    checked="checked">일반
                        </td>
                    </tr>
                    <tr>
                        <td><p>상품 재고</p></td>
                        <td><input type="number" class="productAddInputs" name="product_stock" required="required"
                                   min="1" max="99" placeholder="개수">
                        </td>
                    </tr>
                    <tr>
                        <td><p>상품 이름</p></td>
                        <td><input type="text" class="productAddInputs" name="product_name" required="required"
                                   maxlength="20"></td>
                    </tr>
                    <tr>
                        <td><p>상품 코멘트</p></td>
                        <td><textarea name="product_comment" id="formText" placeholder="상품 설명 작성(200자)"
                                      maxlength="200"></textarea></td>
                    </tr>
                    <tr>
                        <td><p>상품 가격</p></td>
                        <td><input type="number" class="productAddInputs" name="product_price" id="" required="required"
                                   min="1" max="99999999" placeholder="원">
                        </td>
                    </tr>
                    <tr>
                        <td><p>상품 이미지(최대 3개)</p></td>
                        <td><input type="file" class="productAddInputs" name="imgs1" id="mainFile" multiple>
                            <div class="mainFileListDiv"></div>
                        </td>
                    </tr>
                    <tr>
                        <td><p>상품 설명 이미지(최대 2개)</p></td>
                        <!-- <td><input type="file" name="imgs2" multiple></td> -->
                        <td><input type="file" class="productAddInputs" name="imgs2" id="subFile" multiple>
                            <div class="subFileListDiv"></div>
                        </td>
                    </tr>
                    <tr>
                        <td><p>상품 유튜브 주소</p></td>
                        <td><input type="text" class="productAddInputs" name="product_youtube_url" maxlength="100"></td>
                    </tr>
                    <tr>
                        <td><p>상품 배송일</p></td>
                        <td><input type="number" class="productAddInputs" name="product_delivery_day"
                                   required="required" min="1" max="10" placeholder="평균 배송일">
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" class="btnDiv">
                            <input type="button" class="productAddBtns preBtn" value="미리보기">
                            <input type="submit" class="productAddBtns" value="등록" id="addBtn">
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <!--미리보기-->
            <div class="prePageDiv">
                <span class="material-symbols-outlined closePreDivIcon">
                    close
                </span>
                <div class="prePageDivTop">
                    <div class="prePageDivTopLeft">
                        <div class="productImgMain">상품 메인 이미지</div>
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