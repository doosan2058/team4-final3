package com.goott.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.goott.domain.BasketVO;
import com.goott.domain.ProductReviewVO;
import com.goott.domain.SalesVO;

@Mapper
public interface UserMapper {
	/**
	 * 유저의 구매확정 목록 가져오기
	 * @param member_id 회원 아이디
	 * @return 구매 목록 리스트
	 */
	public List<SalesVO> selectSalesAll(String member_id);
	/**
	 * 상품 리뷰 등록
	 * @param productReviewVO 상품 리뷰 엔티티
	 */
	public void insertReview(ProductReviewVO productReviewVO);
	
	/**
	 * 리뷰 작성 여부 업데이트
	 * @param sales_id 매출 번호(PK)
	 */
	public void updateReviewState(int sales_id);
	/**
	 * 회원 프로필 이미지 저장
	 * @param map 이미지주소, 아이디
	 */
	public void updateUserImg(Map<String, Object> map);
	
	/**
	 * 회원 프로필 이미지 저장 주소 가져오기
	 * @param member_id 아이디
	 * @return 프로필 이미지 저장 주소
	 */
	public String selectUserImg(String member_id);
	/**
	 * 비밀번호 변경
	 * @param member_id
	 * @param member_pw
	 * @return 변경된 레코드 수
	 */
	public int updatePw(@Param("member_id") String member_id, @Param("member_pw") String member_pw);
	/**
	 * 프로필 이미지, 등급 이름, 등급 색, 등급 폰트 색, 등급 할인율, 등급 적립율, 등급 이미지
	 * @param member_id 아이디
	 * @return
	 */
	public Map<String, Object> selectUserGradeAndProfileImgUrl(String member_id);
	
	/**
	 * 리뷰 좋아요 등록
	 * @param helpfulVO
	 * @return
	 */
	public int insertHelpful(Map<String, Object> map);
	/**
	 * 리뷰 좋아요 개수 업데이트
	 * @param product_review_id
	 * @return
	 */
	public int updateHelpful(int product_review_id);
	/**
	 * 리뷰 좋아요 눌렀는지 확인
	 * @param map
	 * @return
	 */
	public int selectCountHelpful(Map<String, Object> map);
	/**
	 * 리뷰 도움이 되요 가져오기
	 * @param product_review_id
	 * @return
	 */
	public int selectHelpful(int product_review_id);
	/**
	 * 장바구니 담기
	 * @param basketVO
	 * @return
	 */
	public int insertBasket(BasketVO basketVO);
	/**
	 * 장바구니 목록
	 * @param member_id
	 * @return
	 */
	public List<BasketVO> selectBasketList(String member_id);
	/**
	 * 장바구니 삭제
	 * @param basket_id
	 * @return
	 */
	public int deleteBasket(int basket_id);
	
	public Map<String, Object> selectBasketInfo(int basket_id);
	
}
