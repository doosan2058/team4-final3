package com.goott.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.goott.domain.PageReview;
import com.goott.domain.PageShop;
import com.goott.domain.ProductReviewVO;
import com.goott.domain.ProductVO;

public interface ProductService {
	
	/**
	 * 주문서 작성시에 필요한 상품 정보 가져오기
	 * @param product_id 상품 아이디 (PK)
	 * @return 상품 번호, 재고량, 상품 이름, 상품 가격, 썸네일 이미지, 배송일
	 */
	public Map<String, Object> getOrderDetailInfo(int product_id);
	/**
	 * 상품 등록
	 * 상품 이미지 저장소에 저장후
	 * 주소만 상품 엔티티에 저장
	 * @param productVO 상품 엔티티
	 * @param imgs1 상품 이미지 1, 2, 3
	 * @param imgs2 상품 설명 이미지 1, 2, 3
	 */
	public void saveProduct(ProductVO productVO, MultipartFile[] imgs1, MultipartFile[] imgs2); //상품 입력
	
	/**
	 * 상품 정보 수정
	 * 기존 상품 관련 이미지 모두 삭제후 
	 * 상품 이미지 새로 저장후 주소만 상품 엔티티에 저장
	 * @param productVO 상품 엔티티
	 * @param imgs1 상품 이미지 1, 2, 3
	 * @param imgs2 상품 설명 이미지 1, 2, 3
	 */
	public int updateProduct(ProductVO productVO, MultipartFile[] imgs1, MultipartFile[] imgs2); //상품 입력
	
	/**
	 * 상품 상세 정보
	 * @param product_id 상품 아이디(PK)
	 * @return 상품 엔티티
	 */
	public ProductVO getPrdocutDetail(int product_id); 
	

	public List<ProductVO> getProductList(PageShop pageShop); 
	/**
	 * 상품 전체 개수(일반 회원)
	 * 한정판, 삭제상품 미포함
	 * @param category_id 카테고리 아이디(FK)
	 * @return 상품 개수
	 */
	public int getPageTotalNum(int category_id, int brand_id);
	/**
	 * 카테고리 별 상품 10개씩 가져오기(관리자)
	 * 한정판, 삭제상품 포함 모든 상품
	 * @param pageShop 페이지 엔티티
	 * @return 상품 10개 ( 0 : 모든 상품 )
	 */
	public List<ProductVO> getProductListAll(PageShop pageShop);
	/**
	 * 상품 전체 갯수 (관리자)
	 * @param category_id 카테고리 아이디(FK)
	 * @return 카테고리별 상품 총 개수 ( 0 : 모든 상품 )
	 */
	public int getPageTotalNumAll(int category_id); 
	
	
	/**
	 * 상품 카테고리 번호, 브랜드 번호 가져오기
	 * @param product_id 상품 번호
	 * @return 카테고리 번호, 브랜드 번호
	 */
	public Map<String, Object> getProductCidBid(int product_id);
	/**
	 * 상품 리뷰 리스트 가져오기
	 * @param product_id 상품 번호(PK), 현재 리뷰 페이지
	 * @return 리뷰 엔티티 리스트 10개
	 */
	public List<ProductReviewVO> getProductReviewList(PageReview pageReview);
	
	/**
	 * 제일 최신 상품 가져오기
	 * @return 상품 엔티티
	 */
	public ProductVO getNewestProduct();
	
	/**
	 * 리뷰 총 개수 가져오기
	 * @param product_id 상품 번호(PK)
	 * @return 리뷰 개수
	 */
	public int getReviewTotalNum(int product_id);
	
	/**
	 * 상품 구매 후기 배송 속도 정보 가져오기
	 * @param product_id 상품 번호(PK)
	 * @return 느려요,보통이에요,빨라요 count
	 */
	public List<Map<String, Object>> getSpeedAverage(int product_id);
	/**
	 * 상품 평점 정보 가져오기
	 * @param product_id
	 * @return (1, 2, 3, 4, 5)
	 */
	public List<Map<String, Object>> getGradeAverage(int product_id);
	/**
	 * 상품 비공개 처리 하기
	 * @param product_id 상품 번호(PK)
	 */
	public int setProductClosed(int product_id);
	/**
	 * 상품 판매 탑 10 가져오기
	 * @return map(product_id, count, product_name, product_thumbnail_img_url) 상위 10개
	 */
	public List<Map<String, Object>> getProductTopSales();
}
