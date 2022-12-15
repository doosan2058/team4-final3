package com.goott.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.goott.domain.ProductBrandVO;




public interface ProductBrandMapper {
	/**
	 * 상품 브랜드 리스트
	 *
	 * @return 상품 브랜드 리스트
	 */
	List<ProductBrandVO> get();
}
