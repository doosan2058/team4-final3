package com.goott.service;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.goott.domain.BasketVO;
import com.goott.domain.ProductReviewVO;
import com.goott.domain.SalesVO;
import com.goott.mapper.MemberMapper;
import com.goott.mapper.OrderMapper;
import com.goott.mapper.ProductMapper;
import com.goott.mapper.UserMapper;

import lombok.extern.log4j.Log4j;

@Log4j
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserMapper userMapper;
	@Inject
	OrderMapper orderMapper;
	@Inject
	MemberService memberService;
	@Inject
	ProductMapper productMapper;
	@Inject
	MemberMapper memberMapper;
	@Inject
	MailSendService mailSendService;
	//회원 구매 확정 목록 가져오기
	@Override
	public List<SalesVO> getUserSalesList(String member_id) {
		
		return userMapper.selectSalesAll(member_id);
	}
	//리뷰 파일 저장
	public String saveReviewFile(MultipartFile file) {
		//리뷰 파일 저장 루트 주소
		String filePath = "C:/uploadtest/reviewFiles/";
		
		//오늘날짜 폴더 주소 생성
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		String today = sdf.format(date);
		today.replace("/", File.separator);

		//폴더 생성
		File folder = new File(filePath + today);
		folder.mkdirs();
		
		//리뷰 파일 이름 변경
		String extention = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.'));
		String uuid = UUID.randomUUID().toString();
		String randomFileName = uuid + extention;
		
		//리뷰 파일 저장
		File reviewFile = new File(filePath + today, randomFileName);
		//전송된 멀티파일 > 로컬로 복사
		try {
			file.transferTo(reviewFile);
			String dbUrl = today + "/" + randomFileName;
			dbUrl.replace("/", File.separator);
			return dbUrl;
		} catch (IllegalStateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "리뷰 파일 저장중 오류가 발생하였습니다. 잠시후 다시 시도해 주세요.";
		}
		
		
	}
	
	//상품 리뷰 작성
	//리뷰 테이블 insert + 매출 데이터 리뷰 작성 여부 업데이트
	@Override
	@Transactional
	public String writeReview(ProductReviewVO productReviewVO, int sales_id, MultipartFile fileImg, MultipartFile fileVideo) {
		
		//리뷰 이미지, 동영상 저장   / 없으면 스킵
		if(!fileImg.isEmpty()) {
			String resultText = saveReviewFile(fileImg);
			//만약 저장 시도중 에러 발생 하였다면
			if(resultText.equals("리뷰 파일 저장중 오류가 발생하였습니다. 잠시후 다시 시도해 주세요."))
				return resultText;
			//정상 저장
			productReviewVO.setProduct_review_img_url(resultText);
		}
		if(!fileVideo.isEmpty()) {
			String resultText = saveReviewFile(fileVideo);
			//만약 저장 시도중 에러 발생 하였다면
			if(resultText.equals("리뷰 파일 저장중 오류가 발생하였습니다. 잠시후 다시 시도해 주세요."))
				return resultText;
			//정상 저장
			productReviewVO.setProduct_review_video_url(resultText);
		}
		//리뷰 데이터 insert
		userMapper.insertReview(productReviewVO);
		//매출 레코드 > 리뷰 작성 여부 업데이트
		this.setReviewState(sales_id);
		
		return "리뷰 작성 되었습니다.";
	}
	//리뷰 작성 위해 대상 상품, 구매자 정보 가져오기
	@Override
	public Map<String, Object> getReviewInfo(int sales_id) {
		
		return orderMapper.selectReviewInfo(sales_id);
	}
	
	//리뷰 작성후 리뷰상태 업데이트
	@Override
	public void setReviewState(int sales_id) {
		userMapper.updateReviewState(sales_id);
		
	}
	//회원 이미지 저장
	@Override
	public Map<String, Object> changeProfileImg(MultipartFile file, String member_id) {
		//기본 이미지 저장 주소
		String basicImgUrl = "/resources/img/user/basic_profile.jpg";
		
		
		String filePath = "C:/uploadtest/uploadUserProfileImg/" + member_id;
		
		filePath.replace("/", File.separator);
		
		//폴더 생성
		File folder = new File(filePath);
		folder.mkdirs();
		
		String uuid = UUID.randomUUID().toString();
		String extention = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf('.'));
		String fileName = uuid + extention;
		fileName.replace("/", File.separator);
		
		//이미지 파일 저장
		File img = new File(filePath, fileName);
		try {
			file.transferTo(img);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//이전 프로필 이미지 삭제
		String exImgUrl = this.getUserImgUrl(member_id);
		//만약 기본 프로필 주소라면 삭제처리 생략
		if(exImgUrl.equals(basicImgUrl)) {
//			log.info("기본 프로필 이미지여서 프로필 이미지 삭제 처리를 넘어갑니다.");
		}
		else {
			exImgUrl = "C:/uploadtest" + exImgUrl;
			exImgUrl = exImgUrl.replace("/", File.separator);
			
			
			
			String exImgUrlFileName = exImgUrl.substring(exImgUrl.lastIndexOf(File.separator) + 1);
//			log.info(exImgUrlFileName);
			String exImgUrlFile = exImgUrl.substring(0, exImgUrl.lastIndexOf(File.separator) + 1);
//			log.info(exImgUrlFile);
			
			File oldImg = new File(exImgUrlFile, exImgUrlFileName);
			
			if(oldImg.exists()) {
				oldImg.delete();

			}
//			else
//				log.info("이전 프로필 이미지를 찾을수 없습니다.");
		}
		
			
		
		//디비에 저장할 프로필 이미지 주소
		String member_profile_img_url = "/uploadUserProfileImg/" + member_id + "/" + fileName;
		member_profile_img_url.replace("/", File.separator);
		Map<String, Object> map = new HashMap<>();
		map.put("member_profile_img_url", member_profile_img_url);
		map.put("member_id", member_id);
		
		
		//디비 로직
		userMapper.updateUserImg(map);
		
		return map;
	}
	
	//프로필 이미지 주소 
	@Override
	public String getUserImgUrl(String member_id) {
		
		return userMapper.selectUserImg(member_id);
	}
	//비밀번호 변경
	//비밀번호가 변경 되었습니다. / 비밀번호 변경에 실패 하였습니다. 잠시후 다시 시도해 주세요.
	@Transactional
	@Override
	public String changeUserPw(String member_id, String member_pw) {
		//비밀번호 암호화
		String encondedPw = memberService.pwEncode(member_pw);
		//비밀번호 업데이트 로직 작성
		int resultCount = userMapper.updatePw(member_id, encondedPw);
		//비밀번호 변경 되었으면 비밀번호 초기화 상태도 n으로 변경(비밀번호 초기화 신청 상태일수 있기 때문)
		if(resultCount == 1) {
			//비밀번호 초기화 완료 업데이트
			memberMapper.updateCompletePw(member_id);
			return "비밀번호가 변경 되었습니다.";
		}
		else
			return "비밀번호 변경에 실패 하였습니다. 잠시후 다시 시도해 주세요.";
	}
	
	//비밀번호 초기화
	// 존재하지 않는 아이디 입니다. / 이메일 정보가 틀립니다. / 입력하신 메일로 초기화 비밀번호를 발송하였습니다. 로그인후 비밀번호를 재설정 해주세요.
	@Transactional
	@Override
	public String forgotPassword(String member_id, String member_email) {
//		log.info("입력한 아이디 : " + member_id);
//		log.info("입력한 이메일 : " + member_email);
		//아이디 존재하는지 확인
		int idCount = memberMapper.countId(member_id);
		//아이디가 존재하지 않는다면
		if(idCount != 1) 
			return "존재하지 않는 아이디 입니다.";
		
		//해당 아이디에 이메일 맞는지 확인
		String checkEmail = memberMapper.selectEmail(member_id);
		//이메일이 일치하지 않는다면
		if(!checkEmail.equals(member_email))
			return "이메일 정보가 틀립니다.";
		
		//이메일로 랜덤 비밀번호 전송
		String code = mailSendService.forgotPassword(member_email);
		//해당 아이디의 비밀번호 랜덤 코드로 변경
		String member_pw = memberService.pwEncode(code);
		//암호화된 비밀번호 해당 아이디의 컬럼으로 업데이트
		int resultCount = userMapper.updatePw(member_id, member_pw);
//		log.info("비밀번호 초기화로 변경 디비에 저장 여부 : " + resultCount);
		//비밀번호 초기화 컬럼 업데이트
		memberMapper.updateInitPw(member_email);
		return "입력하신 메일로 초기화 비밀번호를 발송하였습니다. 로그인후 비밀번호를 재설정 해주세요.";
	}
	
	//프로필 이미지, 등급 이름, 등급 색, 등급 폰트 색, 등급 할인율, 등급 적립율, 등급 이미지
	@Override
	public Map<String, Object> getUserProfileImgUrlAndGradeName(String member_id) {
		
		return userMapper.selectUserGradeAndProfileImgUrl(member_id);
	}
	
	//리뷰 좋아요 누르기
	@Transactional
	@Override
	public String reviewHelpful(String member_id, int product_review_id) {
		//좋아요 눌렀는지 확인
		Map<String, Object> map = new HashMap<>();
		map.put("member_id", member_id);
		map.put("product_review_id", product_review_id);
		int count = userMapper.selectCountHelpful(map);
		//count가 0보다 크면 해당 리뷰에 좋아요 누른상태
		if(count > 0) {
			return "해당 리뷰글을 이미 추천 하셨습니다.";
		}
		
		//좋아요 테이블에 내용 넣기
		int countInsert = userMapper.insertHelpful(map);
		//만약 countInsert의 숫자가 1이 아니라면 에러
		if(countInsert != 1) {
			return "죄송합니다. 잠시후 다시 시도해 주세요.";
		}
		//리뷰의 helpful 숫자 업데이트
		int countUpdate = userMapper.updateHelpful(product_review_id);
		//만약 countUpdate의 숫자가 1이 아니라면 에러
		if(countUpdate != 1) {
			return "죄송합니다. 잠시후 다시 시도해 주세요.";
		}
		return "해당 리뷰글을 추천하였습니다.";
	}
	//리뷰 도움이 되요 가져오기
	@Override
	public int getHelpful(int product_review_id) {
		
		return userMapper.selectHelpful(product_review_id);
	}
	@Override
	public String setBasket(BasketVO basketVO) {
		int result = userMapper.insertBasket(basketVO);
		
		if(result == 1) {
			return "장바구니에 담았습니다.";
		}
		else
			return "죄송합니다. 잠시후 다시 시도해 주세요.";
	}
	
	@Override
	public List<BasketVO> getBasketList(String member_id) {
	
		return userMapper.selectBasketList(member_id);
	}
	@Override
	public int deleteBasket(int basket_id) {
		int count = userMapper.deleteBasket(basket_id);
		return count;
	}
	@Override
	public Map<String, Object> buyToBasket(int basket_id) {
		Map<String, Object> map = userMapper.selectBasketInfo(basket_id);
		userMapper.deleteBasket(basket_id);
		
		return map;
	}
	
	
	

}
