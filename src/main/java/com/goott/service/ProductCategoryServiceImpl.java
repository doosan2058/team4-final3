package com.goott.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.goott.domain.ProductCategoryVO;
import com.goott.mapper.ProductCategoryMapper;
@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {
	@Inject
	ProductCategoryMapper productCategoryMapper;
	@Override
	public List<ProductCategoryVO> getList() {
		
		return productCategoryMapper.get(); 
	}

}
