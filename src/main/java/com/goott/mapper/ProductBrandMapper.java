package com.goott.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.goott.domain.ProductBrandVO;




public interface ProductBrandMapper {
	
	List<ProductBrandVO> get();
}
