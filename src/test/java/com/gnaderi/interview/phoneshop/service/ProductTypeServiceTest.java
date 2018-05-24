package com.gnaderi.interview.phoneshop.service;

import com.gnaderi.interview.phoneshop.TestConfiguration;
import com.gnaderi.interview.phoneshop.entity.ProductType;
import com.gnaderi.interview.phoneshop.outbound.ProductTypeOutbound;
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
@ContextConfiguration(classes = {TestConfiguration.class, ProductTypeServiceTest.class}, loader = AnnotationConfigContextLoader.class)
@Configuration
public class ProductTypeServiceTest {
    @Autowired
    private ProductTypeService service;

    @Test
    public void findById() {
        ProductType phoneType = service.findById(2);
        Assert.assertEquals(phoneType.getId().intValue(), 2);
        Assert.assertEquals(phoneType.getName(), "Phone Insurance");
        Assert.assertEquals(phoneType.getDesc(), "Phone Insurance");
    }

    @Test
    public void findAllProductTypes() {
        Assert.assertEquals(service.findAllProductTypes().size(), 4);
    }

    @Test
    public void create() {
        ProductTypeOutbound typeDetails = new ProductTypeOutbound("Wireless Charger", "Wireless Phone Charger");
        ProductType newType = service.create(typeDetails);
        Assert.assertEquals(service.findAllProductTypes().size(), 5);
        Assert.assertEquals(service.findById(5), newType);
        Assert.assertEquals(newType.getName(), "Wireless Charger");
        Assert.assertEquals(newType.getDesc(), "Wireless Phone Charger");
        service.deleteType(newType);
        Assert.assertEquals(service.findAllProductTypes().size(), 4);



    }

    @Test
    public void update() {
        ProductTypeOutbound typeDetails = new ProductTypeOutbound("Wireless Charger", "Wireless Phone Charger");
        ProductType newType = service.create(typeDetails);
        Assert.assertEquals(service.findAllProductTypes().size(), 5);
        Assert.assertEquals(service.findById(6), newType);
        Assert.assertEquals(newType.getName(), "Wireless Charger");
        Assert.assertEquals(newType.getDesc(), "Wireless Phone Charger");

        //Update
        typeDetails.setTypeName("Apple Wireless Charger");
        typeDetails.setDesc("Apple product wireless charger(Include iWatch,iPhone,iPhad,...");
        service.update(typeDetails,newType);
        Assert.assertEquals(newType.getName(), "Apple Wireless Charger");
        Assert.assertEquals(newType.getDesc(), "Apple product wireless charger(Include iWatch,iPhone,iPhad,...");
        Assert.assertEquals(service.findAllProductTypes().size(), 5);
        Assert.assertEquals(service.findById(6), newType);

        //Delete the created type(Clean up).
        service.deleteType(newType);
        Assert.assertEquals(service.findAllProductTypes().size(), 4);
    }

    @Bean
    ProductTypeService productTypeService() {
        return new ProductTypeServiceImpl();
    }
}