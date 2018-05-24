package com.gnaderi.interview.phoneshop.service;

import com.gnaderi.interview.phoneshop.entity.ProductType;
import com.gnaderi.interview.phoneshop.outbound.ProductTypeOutbound;

import java.util.List;

public interface ProductTypeService {

    ProductType findById(Integer productTypeId);

    List<ProductType> findAllProductTypes();

    ProductType create(ProductTypeOutbound productType);

    ProductType update(ProductTypeOutbound productTypeOutbound, ProductType productType);

    void deleteType(Integer productTypeId);

    void deleteType(ProductType type);
}
