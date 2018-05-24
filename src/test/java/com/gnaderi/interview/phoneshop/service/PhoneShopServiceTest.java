package com.gnaderi.interview.phoneshop.service;

import com.gnaderi.interview.phoneshop.TestConfiguration;
import com.gnaderi.interview.phoneshop.entity.Product;
import com.gnaderi.interview.phoneshop.outbound.ProductDetails;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfiguration.class, PhoneShopServiceTest.class}, loader = AnnotationConfigContextLoader.class)
@Configuration
public class PhoneShopServiceTest {
    @Autowired
    private PhoneShopService phoneShopService;

    @Test
    public void testLoadAllProducts() {
        Assert.assertEquals(phoneShopService.findAllProduct().size(), 5);
    }

    @Test
    public void testFindById() {
        Product product = phoneShopService.findById(1);
        Assert.assertEquals(product.getName(), "Pear myPhoneX");
        Assert.assertEquals(Math.round(product.getPrice()), 500);
    }

    @Test
    public void testCreateProduct() {
        ProductDetails productDetails = new ProductDetails("MyTestPhone", "This is my Test Phone", "Phone", 1000.0, 100);
        Product createdProduct = phoneShopService.create(productDetails);
        Assert.assertEquals(createdProduct.getName(), "MyTestPhone");
        Assert.assertEquals(createdProduct.getProductType().getId().intValue(), 1);
        Assert.assertEquals(createdProduct.getQuantity().intValue(), 100);
        Assert.assertEquals(phoneShopService.findAllProduct().size(), 6);
        phoneShopService.delete(createdProduct);
        Assert.assertEquals(phoneShopService.findAllProduct().size(), 5);

    }

    @Test
    public void testUpdateProduct() {
        ProductDetails productDetails = new ProductDetails(1, "MyTestPhone", "This is my Test Phone", "Phone", 1000.0, 100);

        phoneShopService.update(productDetails, phoneShopService.findById(1));
        Product updatedProd = phoneShopService.findById(1);
        Assert.assertEquals(updatedProd.getName(), "MyTestPhone");
        Assert.assertEquals(updatedProd.getProductType().getId().intValue(), 1);
        Assert.assertEquals(updatedProd.getQuantity().intValue(), 100);
    }

    @Bean
    PhoneShopService phoneShopService() {
        return new SimplePhoneShopServiceImpl();
    }
}
