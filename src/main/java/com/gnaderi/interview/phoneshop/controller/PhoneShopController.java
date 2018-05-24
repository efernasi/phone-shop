package com.gnaderi.interview.phoneshop.controller;

import com.gnaderi.interview.phoneshop.entity.Product;
import com.gnaderi.interview.phoneshop.inbound.OrderRequest;
import com.gnaderi.interview.phoneshop.outbound.Invoice;
import com.gnaderi.interview.phoneshop.outbound.ProductCatalog;
import com.gnaderi.interview.phoneshop.outbound.ProductDetails;
import com.gnaderi.interview.phoneshop.service.PhoneShopService;
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
import java.util.Optional;

@Api(value = "Phone Shop REST API Interface", tags = "Phone Shop REST API Interface", description = "Phone Shop REST API Interface")
@PropertySource(value = "classpath:application.properties")
@RestController
@RequestMapping(value = "/phoneshop")
public class PhoneShopController {
    private static final Logger LOGGER = LoggerFactory.getLogger(PhoneShopController.class);

    @Autowired
    private PhoneShopService service;

    @Autowired
    private RequestValidator requestValidator;

    @ApiOperation(value = "Get a list of all products stored in application.", notes = "Retrieve all products details.", produces = MediaType.TEXT_PLAIN_VALUE, response = ProductCatalog.class, responseContainer = "String", httpMethod = "GET")
    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<ProductCatalog> fetchAllProduct() {
        LOGGER.info("Incoming request for all products.");
        final List<Product> products = service.findAllProduct();
        final List<ProductDetails> productDetails = new ArrayList<>(products.size());
        products.forEach(p -> productDetails.add(new
                ProductDetails(p.getId(), p.getName(), p.getDesc(), p.getProductType().getName(), p.getPrice(), p.getQuantity())));
        ProductCatalog catalog = new ProductCatalog(productDetails);
        LOGGER.info("Outbound databind response:{}", catalog);
        return new ResponseEntity<>(catalog, HttpStatus.OK);

    }

    @ApiOperation(value = "Receive a list of products as an orderRequest to buy from the  Phone Shop.", notes = "Recieve an orderRequest for buying a list of products in store and will receive an invoice.", produces = MediaType.TEXT_PLAIN_VALUE, response = String.class, responseContainer = "String", httpMethod = "POST")
    @RequestMapping(value = "purchase", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity purchase(@RequestBody OrderRequest orderRequest) {
        LOGGER.info("Incoming purchase orderRequest request info#{}.", orderRequest);
        try {
            requestValidator.validateRequest(orderRequest);
            Invoice invoice = service.purchase(orderRequest);
            LOGGER.info("Outbound databind response:{}", invoice);
            return invoice != null ? new ResponseEntity<>(invoice, HttpStatus.OK) :
                    new ResponseEntity<>("Unable to finish purchase operation.", HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {
            return new ResponseEntity<>("Unable to finish purchase request:" + ex.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @ApiOperation(value = "Receive a product details as a request to be created on Phone Shop.", notes = "Receive an request for create a new product in store and return the product with Id.", produces = MediaType.TEXT_PLAIN_VALUE, response = String.class, responseContainer = "String", httpMethod = "POST")
    @RequestMapping(value = "/products", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity createProduct(@RequestBody ProductDetails request) {
        LOGGER.info("Incoming request to create a Product info#{}.", request);
        try {
            requestValidator.validateRequest(request);
            Product product = service.create(request);
            if (product != null) {
                ProductDetails productDetails = new ProductDetails(product);
                LOGGER.info("Outbound databind response:{}", productDetails);
                return new ResponseEntity<>(productDetails, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>("Unable to finish create product operation.", HttpStatus.BAD_REQUEST);
            }

        } catch (Exception ex) {
            return new ResponseEntity<>("Unable to finish create product operation:" + ex.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @ApiOperation(value = "Receive a product details as an updateRequest to be updated on Phone Shop.", notes = "Receive an updateRequest for update an existing product in store.", produces = MediaType.TEXT_PLAIN_VALUE, response = String.class, responseContainer = "String", httpMethod = "PUT")
    @RequestMapping(value = "/products/{productId}", method = RequestMethod.PUT)
    public @ResponseBody
    ResponseEntity updateProduct(@PathVariable Integer productId, @RequestBody ProductDetails updateRequest) {
        LOGGER.info("Incoming request for update a Product info#{}.", updateRequest);
        try {
            requestValidator.validateRequest(updateRequest);
            Product product = service.findById(productId);
            if (product == null) {
                return new ResponseEntity<>("Invalid product id.", HttpStatus.BAD_REQUEST);
            }
            product = service.update(updateRequest, product);
            if (product != null) {
                ProductDetails productDetails = new ProductDetails(product);
                LOGGER.info("Outbound databind response:{}", productDetails);
                return new ResponseEntity<>(new ProductDetails(product), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Unable to finish updating product operation.", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>("Unable to finish updating product operation:" + ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
