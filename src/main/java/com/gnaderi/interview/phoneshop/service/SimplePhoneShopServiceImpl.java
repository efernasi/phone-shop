package com.gnaderi.interview.phoneshop.service;

import com.gnaderi.interview.phoneshop.entity.Product;
import com.gnaderi.interview.phoneshop.entity.ProductType;
import com.gnaderi.interview.phoneshop.exception.PhoneShopException;
import com.gnaderi.interview.phoneshop.inbound.OrderRequest;
import com.gnaderi.interview.phoneshop.outbound.Invoice;
import com.gnaderi.interview.phoneshop.outbound.Item;
import com.gnaderi.interview.phoneshop.outbound.ProductDetails;
import com.gnaderi.interview.phoneshop.repository.ProductCrudRepository;
import com.gnaderi.interview.phoneshop.repository.ProductTypeCrudRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SimplePhoneShopServiceImpl implements PhoneShopService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SimplePhoneShopServiceImpl.class);
    @Autowired
    private ProductCrudRepository productRepos;
    @Autowired
    private ProductTypeCrudRepository typeRepos;

    @Autowired
    private InvoiceCalculatorService calculatorService;

    @Override
    public List<Product> findAllProduct() {
        return productRepos.findAll();
    }

    private static Integer INVOICE_NUMBER = 1;//TODO create a method that keep track of Invoice Number.

    @Override
    public Invoice purchase(OrderRequest orderRequest) {
        Invoice invoice = new Invoice();
        invoice.setNumber(INVOICE_NUMBER++);
        invoice.setDate(LocalDate.now());
        invoice.setDueDate(LocalDate.now().plusDays(14));
        List<Item> items = new ArrayList<>(orderRequest.getItems().size());
        Integer rowNumber = 0;
        for (Integer productId : orderRequest.getItems()) {
            Optional<Product> product = productRepos.findById(productId);
            if (!product.isPresent()) {
                throw new PhoneShopException("Invalid product Id. id=" + productId);
            }
            items.add(new Item(rowNumber++, product.get().getName(), product.get().getDesc(), product.get().getProductType().getId(), product.get().getProductType().getName(), product.get().getPrice()));
        }
        invoice.setItems(items);
        Invoice invoiceAfterDiscountAndTax = calculatorService.calculateInvoice(invoice);
        if (invoice.hasError()) {
            throw new PhoneShopException(invoice.getErrors());

        }
        LOGGER.info("Final invoice after applied discount and tax:{}", invoiceAfterDiscountAndTax);

        return invoiceAfterDiscountAndTax;
    }

    @Override
    public Product create(ProductDetails product) {
        Product existingProduct = productRepos.findByName(product.getProductName());
        if (existingProduct == null) {
            ProductType type = typeRepos.findByName(product.getCategory());

            if (type == null) {
                throw new PhoneShopException("Invalid product category/type.");
            }
            Product newEntity = new Product(product.getProductName(), product.getDesc(), product.getQuantity(), product.getPrice(), type);
            return productRepos.save(newEntity);
        }
        return update(product, existingProduct);
    }

    @Override
    public Product findById(Integer productId) {
        try {
            return productRepos.findById(productId).get();
        } catch (Exception ex) {
            LOGGER.info("Invalid product id.");
            return null;
        }
    }

    @Override
    public Product update(ProductDetails updatedProduct, Product existingProduct) {
        existingProduct.setName(updatedProduct.getProductName());
        existingProduct.setDesc(updatedProduct.getDesc());
        existingProduct.setPrice(updatedProduct.getPrice());
        existingProduct.setQuantity(updatedProduct.getQuantity());
        existingProduct.setProductType(typeRepos.findByName(updatedProduct.getCategory()));
        return productRepos.saveAndFlush(existingProduct);
    }

    @Override
    public void delete(Product product) {
        productRepos.delete(product);
    }


}
