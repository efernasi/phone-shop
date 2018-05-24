package com.gnaderi.interview.phoneshop.service;


import com.gnaderi.interview.phoneshop.entity.Product;
import com.gnaderi.interview.phoneshop.inbound.OrderRequest;
import com.gnaderi.interview.phoneshop.outbound.Invoice;
import com.gnaderi.interview.phoneshop.outbound.ProductDetails;

import java.util.List;

public interface PhoneShopService {

    Product findById(Integer productId);

    List<Product> findAllProduct();

    Invoice purchase(OrderRequest basket);

    Product create(ProductDetails product);

    Product update(ProductDetails product, Product productEntity);

    void delete(Product product);

}