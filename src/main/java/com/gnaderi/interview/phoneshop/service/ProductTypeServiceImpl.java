package com.gnaderi.interview.phoneshop.service;

import com.gnaderi.interview.phoneshop.entity.ProductType;
import com.gnaderi.interview.phoneshop.outbound.ProductTypeOutbound;
import com.gnaderi.interview.phoneshop.repository.ProductTypeCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductTypeServiceImpl implements ProductTypeService {
    @Autowired
    private ProductTypeCrudRepository repository;

    @Override
    public ProductType findById(Integer productTypeId) {
        return repository.findById(productTypeId).orElse(null);
    }

    @Override
    public List<ProductType> findAllProductTypes() {
        return repository.findAll();
    }

    @Override
    public ProductType create(ProductTypeOutbound productType) {

        ProductType existingProductType = repository.findByName(productType.getTypeName());
        if (existingProductType == null) {
            ProductType newEntity = new ProductType(productType.getTypeName(), productType.getDesc());
            return repository.save(newEntity);
        }
        return update(productType, existingProductType);
    }

    @Override
    public ProductType update(ProductTypeOutbound updatedPTD, ProductType existPT) {
        existPT.setName(updatedPTD.getTypeName());
        existPT.setDesc(updatedPTD.getDesc());
        return repository.saveAndFlush(existPT);
    }

    @Override
    public void deleteType(Integer productTypeId) {
        repository.deleteById(productTypeId);
    }

    @Override
    public void deleteType(ProductType type) {
        this.deleteType(type.getId());
    }
}
