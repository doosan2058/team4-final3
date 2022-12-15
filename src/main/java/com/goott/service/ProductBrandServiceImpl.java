package com.goott.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.goott.domain.ProductBrandVO;
import com.goott.mapper.ProductBrandMapper;

@Service
public class ProductBrandServiceImpl implements ProductBrandService {

    @Inject
    ProductBrandMapper ProductBrandMapper;

    @Override
    public List<ProductBrandVO> getList() {

        return ProductBrandMapper.get();
    }

}
