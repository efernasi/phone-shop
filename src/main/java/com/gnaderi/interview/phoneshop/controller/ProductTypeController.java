package com.gnaderi.interview.phoneshop.controller;

import com.gnaderi.interview.phoneshop.entity.ProductType;
import com.gnaderi.interview.phoneshop.outbound.ProductCatalog;
import com.gnaderi.interview.phoneshop.outbound.ProductTypeOutbound;
import com.gnaderi.interview.phoneshop.service.ProductTypeService;
import com.gnaderi.interview.phoneshop.util.RequestValidator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Api(value = "Phone Shop REST API Interface", tags = "Phone Shop REST API Interface", description = "Phone Shop REST API Interface")
@PropertySource(value = "classpath:application.properties")
@RestController
@RequestMapping(value = "/phoneshop")
public class ProductTypeController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductTypeController.class);

    @Autowired
    private ProductTypeService service;

    @Autowired
    private RequestValidator requestValidator;

    @ApiOperation(value = "Get a list of all product types stored in application.", notes = "Retrieve all product types details.", produces = MediaType.TEXT_PLAIN_VALUE, response = ProductCatalog.class, responseContainer = "String", httpMethod = "GET")
    @RequestMapping(value = "/types", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity fetchAllProductTypes() {
        LOGGER.info("Incoming request for all ProductTypes.");
        final List<ProductType> allTypes = service.findAllProductTypes();
        final List<ProductTypeOutbound> typeDetails = new ArrayList<>(allTypes.size());
        allTypes.forEach(p -> typeDetails.add(new ProductTypeOutbound(p.getId(), p.getName(), p.getDesc())));
        LOGGER.info("Outbound databind response:{}", typeDetails);
        return new ResponseEntity<>(typeDetails, HttpStatus.OK);

    }


    @ApiOperation(value = "Receive a product type details as a request to create a ProductType on Phone Shop.", notes = "Receive an request for create a new product type in store and return the ProductType with Id.", produces = MediaType.TEXT_PLAIN_VALUE, response = String.class, responseContainer = "String", httpMethod = "POST")
    @RequestMapping(value = "/types", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity createProductType(@RequestBody ProductTypeOutbound request) {
        LOGGER.info("Incoming request for creating a Product Type info#{}.", request);
        try {
            requestValidator.validateRequest(request);
            ProductType type = service.create(request);
            if (type != null) {
                ProductTypeOutbound productTypeOutbound = new ProductTypeOutbound(type);
                LOGGER.info("Outbound databind response:{}", productTypeOutbound);
                return new ResponseEntity<>(productTypeOutbound, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>("Unable to finish create ProductType operation.", HttpStatus.BAD_REQUEST);
            }

        } catch (Exception ex) {
            return new ResponseEntity<>("Unable to finish create ProductType operation:" + ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @ApiOperation(value = "Receive a product type details to update a ProductType on Phone Shop.", notes = "Receive an updateRequest for update an existing ProductType in store.", produces = MediaType.TEXT_PLAIN_VALUE, response = String.class, responseContainer = "String", httpMethod = "PUT")
    @RequestMapping(value = "/types/{typeId}", method = RequestMethod.PUT)
    public @ResponseBody
    ResponseEntity updateProduct(@PathVariable Integer typeId, @RequestBody ProductTypeOutbound updateRequest) {
        LOGGER.info("Incoming Update Request For Product Type Info#{}.", updateRequest);
        try {
            requestValidator.validateRequest(updateRequest);
            ProductType type = service.findById(typeId);
            if (type == null) {
                return new ResponseEntity<>("Invalid productType id.", HttpStatus.BAD_REQUEST);
            }
            type = service.update(updateRequest, type);
            if (type != null) {
                ProductTypeOutbound productTypeOutbound = new ProductTypeOutbound(type);
                LOGGER.info("Outbound databind response:{}", productTypeOutbound);
                return new ResponseEntity<>(productTypeOutbound, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Unable to finish updating product type operation.", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>("Unable to finish updating product type operation:" + ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
