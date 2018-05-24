package com.gnaderi.interview.phoneshop.repository;

import com.gnaderi.interview.phoneshop.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface ProductCrudRepository extends JpaRepository<Product, Integer> {
    Product findByName(String name);
}

