package com.goott.service;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.inject.Inject;

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
	
	/**
	 * 이미지 저장 메서드
	 * 상품 이미지 저장 폴더에 저장 후, 저장위치만 vo에 저장
	 * 상품 메인 이미지는 썸네일 이미지 생성
	 * 
	 */
	@Override 
	public void saveProduct(ProductVO productVO, MultipartFile[] imgs1, MultipartFile[] imgs2) {
		
//		log.info("이미지 저장 처리 전 : " + productVO);
		
		//상품 이미지 
		if(!imgs1[0].getOriginalFilename().isEmpty()) {
			for(int i = 0; i < imgs1.length; i++) {
				if(i == 0) {
					String imgUrl[] = saveMainImg(imgs1[0]);
					productVO.setProduct_img_url1(imgUrl[0]);
					productVO.setProduct_thumbnail_img_url(imgUrl[1]);
				}
					
				else if(i == 1)
					productVO.setProduct_img_url2(saveImg(imgs1[1]));
				else
					productVO.setProduct_img_url3(saveImg(imgs1[2]));
			}
		}
		//상품 설명 이미지 
		if(!imgs2[0].getOriginalFilename().isEmpty()) {
			for(int i = 0; i < imgs2.length; i++) {
				if(i == 0)
					productVO.setProduct_description_img_url1(saveImg(imgs2[0]));
				else 
					productVO.setProduct_description_img_url2(saveImg(imgs2[1]));
			}
		}
		
//		log.info("이미지 저장 처리 후 : " + productVO);
		productMapper.insert(productVO);
//		log.info("디비 저장 완료");
		
		
	}
	
	//메인 상품 이미지 저장 + 썸네일 이미지 저장 메서드
	public String[] saveMainImg(MultipartFile file) {
		String[] urlArr = new String[2];
		urlArr[0] = "no url";
		urlArr[1] = "no url";
		
		if(file.getOriginalFilename().equals("") || file.getOriginalFilename() != null) {
			//랜덤 이름 생성
			String uuid = UUID.randomUUID().toString();
			//메인 이미지 원본 이름
			String imgName = file.getOriginalFilename();
			//메인 이미지 확장자
			String extention = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.'));
			//생성할 썸네일 이미지 이름
			String thumbnailImgName = "s_" + uuid + extention;
			//상품 이미지 저장할 위치
			String filePath = "C:/uploadtest/productImg/";
			filePath = filePath.replace("/", File.separator);
			//오늘 날짜로 하위 폴더 생성
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String today = sdf.format(date);
			String todayPath = today.replace("-", File.separator);
			File folder = new File(filePath + todayPath);
			
			if(folder.mkdirs())
				log.info("폴더 생성");
			else
				log.info("폴더 존재");
			
			//파라미터로 넘어온 메인 이미지 , 썸네일 이미지 저장
			
			File saveFile = new File(filePath + todayPath, uuid + extention);
			File thumbFile = new File(filePath + todayPath, thumbnailImgName);
			try {
				file.transferTo(saveFile);
//				log.info("메인 상품 이미지 저장");
				//사진 원본 파일, 사이즈, 썸네일 파일
				Thumbnails.of(saveFile).size(100, 200).toFile(thumbFile);
//				log.info("썸네일 이미지 저장");
				urlArr[0] = todayPath + File.separator + uuid + extention;
				urlArr[1] = todayPath + File.separator + thumbnailImgName;
			} catch (IllegalStateException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//메인 이미지 저장
			
		}
		//urlArr[0] 메인사진 주소
		//urlArr[1] 썸네일 사진 주소
		return urlArr;
		
	}
	
	//상품 이미지 파일, 상품 설명 파일 오늘 날짜 폴더에 저장
	//없으면 폴더 생성
	public String saveImg(MultipartFile file) {
		if(file.getOriginalFilename().equals("") || file.getOriginalFilename() != null) {
			//파일 확장자 + 랜덤 문자열로 한글이 없는 더미 파일명 생성
			//오늘 날짜 문자열로 변환하여 파일 경로 생성
			//랜덤 이름
			String uuid = UUID.randomUUID().toString();
			//확장자
			String fileExtention = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.'));
			
			
			//상품 관련 이미지 저장 폴더
			String filePath = "C:/uploadtest/productImg/";
			filePath = filePath.replace("/", File.separator);
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String today = sdf.format(date);
			String todayPath = today.replace("-", File.separator);

			//날짜별 폴더 생성
			File folder = new File(filePath + todayPath);
			folder.mkdirs();
//			if(folder.mkdirs())
//				log.info("폴더 생성");
//			else
//				log.info("폴더 존재");
			//파라미터로 넘어온 이미지 파일 신규 파일에 복사하여 c드라이브에 저장
			File saveImg = new File(filePath + todayPath, uuid + fileExtention);
			try {
				file.transferTo(saveImg);
//				log.info("저장 성공");
//				log.info("원본 파일 이름 : " + file.getOriginalFilename());
//				log.info("저장 위치 : " + filePath + todayPath + "/" + uuid + fileExtention);
				//디비에 저장할 사진 위치 리턴
				return todayPath + File.separator + uuid + fileExtention;
				
			} catch (IllegalStateException | IOException e) {
				
				e.printStackTrace();
				
			}
			
		}
		return "File Error";
	}
	
	//사진 삭제 함수
	public void deleteImg(String img_url) {
		if(img_url.equals("no url")) {
//			log.info("입력된 주소 : " + img_url);
//			log.info("삭제 처리 건너뜀");
//			return "no url";
		}
		else {
			String filePath = "C:/uploadtest/productImg/";
			filePath = filePath.replace("/", File.separator);
//			log.info("삭제할 파일 : " + filePath + img_url);
			File deleteFile = new File(filePath, img_url);
			//원본 파일 경로
//			String folderPath = img_url.substring(0,img_url.lastIndexOf(File.separator) + 1);
//			log.info(folderPath);
			//원본 확장자
//			String extention = img_url.substring(img_url.lastIndexOf('.'));
			//임시 파일 이름
//			String temp_img_url = img_url.substring(img_url.lastIndexOf(File.separator) + 1,img_url.lastIndexOf('.')) + "_temp";
//			log.info("임시 파일 이름 : " + temp_img_url + extention);
			
//			File tempFile = new File(filePath + folderPath, temp_img_url + extention);
			
//			try {
//				FileInputStream fis = new FileInputStream(deleteFile);
//				FileOutputStream fos = new FileOutputStream(tempFile);
//				byte[] data = new byte[1024];
//				int input = 0;
//				
//				while((input = fis.read(data)) != -1) {
//					fos.write(data, 0 , input);
//					
//					
//				}
//				log.info("복사 완료");
//			} catch (IOException e) {
//				
//				e.printStackTrace();
//			}

//			log.info("원본 파일 [ " + img_url + " ] 삭제 결과 : " + deleteFile.delete());
//			return filePath + folderPath + temp_img_url + extention;
		}
		
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
//		ProductVO productVOTemp = getPrdocutDetail(productVO.getProduct_id());
		//임시 이미지 리스트
//		List<String> tempList = new ArrayList<>();
		//전에 저장된 전체 이미지 삭제
//		tempList.add(deleteImg(productVOTemp.getProduct_img_url1()));
//		tempList.add(deleteImg(productVOTemp.getProduct_img_url2()));
//		tempList.add(deleteImg(productVOTemp.getProduct_img_url3()));
//		tempList.add(deleteImg(productVOTemp.getProduct_description_img_url1()));
//		tempList.add(deleteImg(productVOTemp.getProduct_description_img_url2()));
		
		
		
		//전에 저장된 상품 이미지 모두 삭제
		deleteImg(productVO.getProduct_thumbnail_img_url());
		deleteImg(productVO.getProduct_img_url1());
		deleteImg(productVO.getProduct_img_url2());
		deleteImg(productVO.getProduct_img_url3());
		deleteImg(productVO.getProduct_description_img_url1());
		deleteImg(productVO.getProduct_description_img_url2());
		
		
		//복사 안한 경로 삭제
//		Iterator it = tempList.iterator();
//		while(it.hasNext()) {
//			String text = it.next().toString();
//			if(text.equals("no url")) {
//				it.remove();
//			}
//		}
//		log.info(tempList);
		//이미지 파일들이 완전히 교체 됬다면 임시 이미지 파일 삭제 
		//작성 해야함..
		
		
//		log.info("이미지 저장 처리 전 : " + productVO);
		
		//상품 이미지 3장 저장 후 저장위치 vo에 저장
		if(!imgs1[0].getOriginalFilename().isEmpty()) {
			for(int i = 0; i < imgs1.length; i++) {
				if(i == 0) {
					String imgUrl[] = saveMainImg(imgs1[0]);
					productVO.setProduct_img_url1(imgUrl[0]);
					productVO.setProduct_thumbnail_img_url(imgUrl[1]);
				}
				else if(i == 1)
					productVO.setProduct_img_url2(saveImg(imgs1[1]));
				else
					productVO.setProduct_img_url3(saveImg(imgs1[2]));
			}
		}
		//상품 설명 이미지 2장 저장후 저장위치 vo에 저장
		if(!imgs2[0].getOriginalFilename().isEmpty()) {
			for(int i = 0; i < imgs2.length; i++) {
				if(i == 0)
					productVO.setProduct_description_img_url1(saveImg(imgs2[0]));
				else 
					productVO.setProduct_description_img_url2(saveImg(imgs2[1]));
			}
		}
		
//		log.info("이미지 저장 처리 후 : " + productVO);
		
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


}
