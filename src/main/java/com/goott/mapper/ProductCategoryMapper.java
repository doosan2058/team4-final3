package com.goott.mapper;

import java.util.List;

import com.goott.domain.ProductCategoryVO;

public interface ProductCategoryMapper {
	/**
	 * 상품 카테고리 리스트
	 *
	 * @return 상품 카테고리 리스트
	 */
	List<ProductCategoryVO> get();
}
