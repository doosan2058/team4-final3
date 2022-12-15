package com.goott.service;

import java.util.List;



import com.goott.domain.ProductBrandVO;




public interface ProductBrandService {
	/**
	 * 현재 상품 브랜드 목록 가져오기
	 *
	 * @return
	 */
	List<ProductBrandVO> getList();

	
}
