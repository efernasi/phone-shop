package com.gnaderi.interview.phoneshop.repository;

import com.gnaderi.interview.phoneshop.entity.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface ProductTypeCrudRepository extends JpaRepository<ProductType, Integer> {
    ProductType findByName(String type);
}

