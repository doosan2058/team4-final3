package com.goott.service;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.goott.domain.PageReview;
import com.goott.domain.PageShop;
import com.goott.domain.ProductReviewVO;
import com.goott.domain.ProductVO;
import com.goott.mapper.ProductMapper;

import lombok.extern.log4j.Log4j;
import net.coobird.thumbnailator.Thumbnails;

@Service
@Log4j
public class ProductServiceImpl implements ProductService {

    @Inject
    ProductMapper productMapper;

    @Autowired
    S3Service s3Service;

    /**
     * 이미지 저장 메서드
     * 상품 이미지 저장 폴더에 저장 후, 저장위치만 vo에 저장
     * 상품 메인 이미지는 썸네일 이미지 생성
     */
    @Override
    public void saveProduct(ProductVO productVO, MultipartFile[] imgs1, MultipartFile[] imgs2) {

        //상품 이미지
        if (!imgs1[0].getOriginalFilename().isEmpty()) {
            for (int i = 0; i < imgs1.length; i++) {
                if (i == 0) {
                    String imgUrl[] = saveMainImg(imgs1[0]);
                    productVO.setProduct_img_url1(imgUrl[0]);
                    productVO.setProduct_thumbnail_img_url(imgUrl[1]);
                } else if (i == 1)
                    productVO.setProduct_img_url2(saveImg(imgs1[1]));
                else
                    productVO.setProduct_img_url3(saveImg(imgs1[2]));
            }
        }

        //상품 설명 이미지
        if (!imgs2[0].getOriginalFilename().isEmpty()) {
            for (int i = 0; i < imgs2.length; i++) {
                if (i == 0)
                    productVO.setProduct_description_img_url1(saveImg(imgs2[0]));
                else
                    productVO.setProduct_description_img_url2(saveImg(imgs2[1]));
            }
        }
        productMapper.insert(productVO);
    }

    //메인 상품 이미지 저장 + 썸네일 이미지 저장 메서드
    public String[] saveMainImg(MultipartFile file)  {
        String[] urlArr = new String[2];
        String saveType = "productImg";
        String mainImgUrl = s3Service.uploadS3Img(file, saveType);
        String thumbnailImgUrl = s3Service.uploadS3ThumbnailImg(file);
        urlArr[0] = mainImgUrl;
        urlArr[1] = thumbnailImgUrl;

        // urlArr[0] 메인사진 주소
        // urlArr[1] 썸네일 사진 주소
        return urlArr;
    }

    //상품 이미지 파일, 상품 설명 파일 오늘 날짜 폴더에 저장
    //없으면 폴더 생성
    public String saveImg(MultipartFile file) {
        String saveType = "productImg";
        return s3Service.uploadS3Img(file, saveType);
    }

    //사진 삭제 함수
    public void deleteImg(String img_url) {
        s3Service.deleteS3Img(img_url);
    }

    @Override //상품 상세 정보
    public ProductVO getPrdocutDetail(int product_id) {

        return productMapper.get(product_id);
    }

    @Override //상품 10개씩 불러오기
    public List<ProductVO> getProductList(PageShop pageShop) {

        return productMapper.list(pageShop);
    }

    @Override //페이지 전체 번호
    public int getPageTotalNum(int category_id, int brand_id) {

        return productMapper.total(category_id, brand_id);
    }

    @Override //상품 10개씩 관리자
    public List<ProductVO> getProductListAll(PageShop pageShop) {

        return productMapper.listAll(pageShop);
    }

    @Override //페이지 전체 번호 관리자
    public int getPageTotalNumAll(int category_id, int brand_id) {

        return productMapper.totalAll(category_id, brand_id);
    }

    @Override
    public int updateProduct(ProductVO productVO, MultipartFile[] imgs1, MultipartFile[] imgs2) {

        // 상품 이미지 변경
        if (!imgs1[0].getOriginalFilename().isEmpty()) {
            // 기존 파일 삭제
            if(!productVO.getProduct_thumbnail_img_url().equals("no url"))
                deleteImg(productVO.getProduct_thumbnail_img_url());
            if(!productVO.getProduct_img_url1().equals("no url"))
                deleteImg(productVO.getProduct_img_url1());
            if(!productVO.getProduct_img_url2().equals("no url"))
                deleteImg(productVO.getProduct_img_url2());
            if(!productVO.getProduct_img_url3().equals("no url"))
                deleteImg(productVO.getProduct_img_url3());

            for (int i = 0; i < imgs1.length; i++) {
                if (i == 0) {
                    String imgUrl[] = saveMainImg(imgs1[0]);
                    productVO.setProduct_img_url1(imgUrl[0]);
                    productVO.setProduct_thumbnail_img_url(imgUrl[1]);
                } else if (i == 1)
                    productVO.setProduct_img_url2(saveImg(imgs1[1]));
                else
                    productVO.setProduct_img_url3(saveImg(imgs1[2]));
            }
        } else {
            // 상품 이미지 유지
            ProductVO exProductVO = this.getPrdocutDetail(productVO.getProduct_id());
            // 기존 이미지 주소들 업데이트 할 productVO에 저장
            productVO.setProduct_thumbnail_img_url(exProductVO.getProduct_thumbnail_img_url());
            productVO.setProduct_img_url1(exProductVO.getProduct_img_url1());
            productVO.setProduct_img_url2(exProductVO.getProduct_img_url2());
            productVO.setProduct_img_url3(exProductVO.getProduct_img_url3());
        }

        //상품 설명 이미지 변경
        if (!imgs2[0].getOriginalFilename().isEmpty()) {
            // 이전 파일 삭제
            if(!productVO.getProduct_description_img_url1().equals("no url"))
                deleteImg(productVO.getProduct_description_img_url1());
            if(!productVO.getProduct_description_img_url2().equals("no url"))
                deleteImg(productVO.getProduct_description_img_url2());

            for (int i = 0; i < imgs2.length; i++) {
                if (i == 0)
                    productVO.setProduct_description_img_url1(saveImg(imgs2[0]));
                else
                    productVO.setProduct_description_img_url2(saveImg(imgs2[1]));
            }
        } else {
            // 이전 정보 유지
            ProductVO exProductVO = this.getPrdocutDetail(productVO.getProduct_id());
            productVO.setProduct_description_img_url1(exProductVO.getProduct_description_img_url1());
            productVO.setProduct_description_img_url2(exProductVO.getProduct_description_img_url2());
        }

        return productMapper.update(productVO);

    }

    //주문서 작성시 필요한 상품 정보 가져오는 메서드
    @Override
    public Map<String, Object> getOrderDetailInfo(int product_id) {

        return productMapper.selectOrderProduct(product_id);
    }

    //쿠폰 조회시 필요한 상품 카테고리, 브랜드 번호 가져오기
    @Override
    public Map<String, Object> getProductCidBid(int product_id) {

        return productMapper.selectCidBid(product_id);
    }

    //상품 리뷰 10개 가져오기
    @Override
    public List<ProductReviewVO> getProductReviewList(PageReview pageReview) {


        return productMapper.selectReviewAll(pageReview);
    }

    //팝업창에 띄울 제일 최신 상품 정보 가져오기
    @Override
    public ProductVO getNewestProduct() {

        return productMapper.selectNewProduct();
    }

    //리뷰 총 개수
    @Override
    public int getReviewTotalNum(int product_id) {

        return productMapper.selectCountReview(product_id);
    }

    //배송 속도
    @Override
    public List<Map<String, Object>> getSpeedAverage(int product_id) {
        return productMapper.selectCountSpeed(product_id);
    }

    //평점 정보
    @Override
    public List<Map<String, Object>> getGradeAverage(int product_id) {
        return productMapper.selectCountGrade(product_id);
    }

    //상품 비공개 처리
    @Override
    public int setProductClosed(int product_id) {
        return productMapper.updateDelete(product_id);

    }

    @Override
    public List<Map<String, Object>> getProductTopSales() {
        // TODO Auto-generated method stub
        return productMapper.selectTopProduct();
    }

    @Override
    public List<ProductVO> getDrawLimitedProductList(int product_category_id, int product_brand_id) {
        return productMapper.selectDrawLimitedProduct(product_category_id, product_brand_id);
    }

    @Override
    public List<ProductVO> getYoutubeList() {
        return productMapper.selectYoutubeList();
    }


}
