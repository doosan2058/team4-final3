package com.goott.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.goott.domain.BasketVO;
import com.goott.domain.ProductReviewVO;
import com.goott.domain.SalesVO;

public interface UserService {
    /**
     * 회원 구매 확정 목록 가져오기
     *
     * @param member_id 회원 아이디
     * @return 구매 목록 리스트
     */
    public List<SalesVO> getUserSalesList(String member_id);

    /**
     * 상품 리뷰 작성
     *
     * @param productReviewVO 상품 리뷰 엔티티
     */
    public String writeReview(ProductReviewVO productReviewVO, int sales_id, MultipartFile fileImg, MultipartFile fileVideo);

    /**
     * 상품 리뷰 작성 위한 정보 가져오기
     *
     * @param sales_id 매출 번호(PK)
     * @return member_id, product_id
     */
    public Map<String, Object> getReviewInfo(int sales_id);

    /**
     * 리뷰 작성 여부 업데이트
     *
     * @param sales_id 매출 번호(PK)
     */
    public void setReviewState(int sales_id);

    /**
     * 회원 프로필 이미지 저장
     *
     * @param file      이미지
     * @param member_id 아이디
     */
    public Map<String, Object> changeProfileImg(MultipartFile file, String member_id);

    /**
     * DB에 저장된 회원의 이미지 저장 주소 가져오기
     *
     * @param member_id 아이디
     * @return 주소
     */
    public String getUserImgUrl(String member_id);

    /**
     * 비밀번호 변경하기
     *
     * @param member_id 아이디
     * @param member_pw 비밀번호
     * @return 비밀번호가 변경 되었습니다. / 비밀번호 변경에 실패 하였습니다. 잠시후 다시 시도해 주세요.
     */
    public String changeUserPw(String member_id, String member_pw);

    /**
     * 비밀번호 초기화
     *
     * @param member_id 아이디
     * @param member_email 이메일
     * @return 존재하지 않는 아이디 입니다. / 이메일 정보가 틀립니다. / 입력하신 메일로 초기화 비밀번호를 발송하였습니다. 로그인후 비밀번호를 재설정 해주세요.
     */
    public String forgotPassword(String member_id, String member_email);

    /**
     * 프로필 이미지, 등급 이름, 등급 색, 등급 폰트 색, 등급 할인율, 등급 적립율, 등급 이미지
     *
     * @param member_id 아이디
     * @return 프로필 이미지, 등급 이름, 등급 색, 등급 폰트 색, 등급 할인율, 등급 적립율, 등급 이미지
     */
    public Map<String, Object> getUserProfileImgUrlAndGradeName(String member_id);

    /**
     * 리뷰 좋아요 눌르기
     *
     * @param member_id 아이디
     * @param product_review_id 리뷰 번호
     * @return 처리 결과
     */
    public String reviewHelpful(String member_id, int product_review_id);

    /**
     * 리뷰 도움이 되요 값 가져오기
     *
     * @param product_review_id 리뷰 번호
     * @return 추천 개수
     */
    public int getHelpful(int product_review_id);

    /**
     * 장바구니 담기
     *
     * @param basketVO 장바구니 엔티티
     * @return 처리 결과
     */
    public String setBasket(BasketVO basketVO);

    /**
     * 장바구니 목록 가져오기
     *
     * @param member_id 회원 아이디
     * @return 장바구니 엔티티 list
     */
    public List<BasketVO> getBasketList(String member_id);

    /**
     * 장바구니 삭제
     *
     * @param basket_id 장바구니 번호
     * @return 처리 결과
     */
    public int deleteBasket(int basket_id);

	/**
	 * 장바구니 물품 구매하기
	 * @param basket_id 장바구니 번호
	 * @return 처리 결과(장바구니 물품 삭제 + 물품 구매 이동)
	 */
    public Map<String, Object> buyToBasket(int basket_id);

	/**
	 * 장바구니 목록
	 * @param memberId 회원 아이디
	 * @return 해당 회원의 장바구니 레코드 list
	 */
    List<BasketVO> selectBasketList(String memberId);
}
