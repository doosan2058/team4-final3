package com.goott.service;

import java.util.List;

import com.goott.domain.ProductCategoryVO;

public interface ProductCategoryService {
	/**
	 * 현재 상품 카테고리 목록 가져오기
	 *
	 * @return
	 */
	List<ProductCategoryVO> getList();
}
