package com.gnaderi.interview.phoneshop.rule;

import com.gnaderi.interview.phoneshop.TestConfiguration;
import com.gnaderi.interview.phoneshop.entity.Product;
import com.gnaderi.interview.phoneshop.outbound.Invoice;
import com.gnaderi.interview.phoneshop.outbound.Item;
import com.gnaderi.interview.phoneshop.repository.ProductCrudRepository;
import com.gnaderi.interview.phoneshop.service.InvoiceCalculatorService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfiguration.class}, loader = AnnotationConfigContextLoader.class)
public class PhoneShopRuleSetTests {
    @Autowired
    private InvoiceCalculatorService calculatorService;

    @Autowired
    private ProductCrudRepository productCrudRepository;

    @Test
    public void testFreeSimRule() {
        Invoice invoice = new Invoice();
        List<Item> items = new ArrayList<>();
        Product phone = productCrudRepository.findById(1).get();
        items.add(new Item(0, phone.getName(), phone.getDesc(), phone.getProductType().getId(), phone.getProductType().getName(), phone.getPrice()));
        Product sim = productCrudRepository.findById(3).get();
        items.add(new Item(1, sim.getName(), sim.getDesc(), sim.getProductType().getId(), sim.getProductType().getName(), sim.getPrice()));
        items.add(new Item(2, sim.getName(), sim.getDesc(), sim.getProductType().getId(), sim.getProductType().getName(), sim.getPrice()));
        invoice.setItems(items);
        Invoice invoiceAfterFare = calculatorService.calculateInvoice(invoice);
        assertEquals(Math.round(invoiceAfterFare.getTotal()), Math.round((phone.getPrice()+sim.getPrice()*1+sim.getPrice()*0)*(1+0.14)));

    }

    @Test
    public void testSimPromotionRule() {
        Invoice invoice = new Invoice();
        List<Item> items = new ArrayList<>();
        Product sim = productCrudRepository.findById(3).get();
        items.add(new Item(1, sim.getName(), sim.getDesc(), sim.getProductType().getId(), sim.getProductType().getName(), sim.getPrice()));
        items.add(new Item(2, sim.getName(), sim.getDesc(), sim.getProductType().getId(), sim.getProductType().getName(), sim.getPrice()));
        items.add(new Item(3, sim.getName(), sim.getDesc(), sim.getProductType().getId(), sim.getProductType().getName(), sim.getPrice()));
        items.add(new Item(4, sim.getName(), sim.getDesc(), sim.getProductType().getId(), sim.getProductType().getName(), sim.getPrice()));
        invoice.setItems(items);
        Invoice invoiceAfterFare = calculatorService.calculateInvoice(invoice);
        //        Buy One Get One Free
        assertEquals(Math.round(invoiceAfterFare.getTotal()), Math.round(2 * 0 + 2 * 20*(1+0.14)));

    }

    @Test
    public void testInsuranceDiscountRule() {
        Invoice invoice = new Invoice();
        List<Item> items = new ArrayList<>();
        Product phone = productCrudRepository.findById(1).get();
        items.add(new Item(0, phone.getName(), phone.getDesc(), phone.getProductType().getId(), phone.getProductType().getName(), phone.getPrice()));
        Product insurance = productCrudRepository.findById(4).get();
        items.add(new Item(1, insurance.getName(), insurance.getDesc(), insurance.getProductType().getId(), insurance.getProductType().getName(), insurance.getPrice()));
        items.add(new Item(2, insurance.getName(), insurance.getDesc(), insurance.getProductType().getId(), insurance.getProductType().getName(), insurance.getPrice()));
        invoice.setItems(items);
        Invoice invoiceAfterFare = calculatorService.calculateInvoice(invoice);
        for (Item e : invoiceAfterFare.getItems()) {
            if (e.getProductTypeId().equals(insurance.getProductType().getId())) {
                assertEquals(Math.round(e.getAmount()), 90);
            }

        }
    }

    @Test
    public void testPhoneCaseRule() {
        Invoice invoice = new Invoice();
        List<Item> items = new ArrayList<>();
        Product pc = productCrudRepository.findById(5).get();
        items.add(new Item(1, pc.getName(), pc.getDesc(), pc.getProductType().getId(), pc.getProductType().getName(), pc.getPrice()));
        items.add(new Item(2, pc.getName(), pc.getDesc(), pc.getProductType().getId(), pc.getProductType().getName(), pc.getPrice()));
        items.add(new Item(3, pc.getName(), pc.getDesc(), pc.getProductType().getId(), pc.getProductType().getName(), pc.getPrice()));
        items.add(new Item(4, pc.getName(), pc.getDesc(), pc.getProductType().getId(), pc.getProductType().getName(), pc.getPrice()));
        items.add(new Item(5, pc.getName(), pc.getDesc(), pc.getProductType().getId(), pc.getProductType().getName(), pc.getPrice()));
        items.add(new Item(6, pc.getName(), pc.getDesc(), pc.getProductType().getId(), pc.getProductType().getName(), pc.getPrice()));
        items.add(new Item(7, pc.getName(), pc.getDesc(), pc.getProductType().getId(), pc.getProductType().getName(), pc.getPrice()));
        items.add(new Item(8, pc.getName(), pc.getDesc(), pc.getProductType().getId(), pc.getProductType().getName(), pc.getPrice()));
        items.add(new Item(9, pc.getName(), pc.getDesc(), pc.getProductType().getId(), pc.getProductType().getName(), pc.getPrice()));
        items.add(new Item(10, pc.getName(), pc.getDesc(), pc.getProductType().getId(), pc.getProductType().getName(), pc.getPrice()));
        invoice.setItems(items);
        Invoice invoiceAfterFare = calculatorService.calculateInvoice(invoice);
        Double total = invoiceAfterFare.getItems().stream().mapToDouble(Item::getAmount).sum();
        assertEquals(Math.round(total), 8 * Math.round(pc.getPrice()));
    }

    @Test
    public void testSimMaxLimitRule() {
        Invoice invoice = new Invoice();
        List<Item> items = new ArrayList<>();
        Product pc = productCrudRepository.findById(3).get();
        items.add(new Item(1, pc.getName(), pc.getDesc(), pc.getProductType().getId(), pc.getProductType().getName(), pc.getPrice()));
        items.add(new Item(2, pc.getName(), pc.getDesc(), pc.getProductType().getId(), pc.getProductType().getName(), pc.getPrice()));
        items.add(new Item(3, pc.getName(), pc.getDesc(), pc.getProductType().getId(), pc.getProductType().getName(), pc.getPrice()));
        items.add(new Item(4, pc.getName(), pc.getDesc(), pc.getProductType().getId(), pc.getProductType().getName(), pc.getPrice()));
        items.add(new Item(5, pc.getName(), pc.getDesc(), pc.getProductType().getId(), pc.getProductType().getName(), pc.getPrice()));
        items.add(new Item(6, pc.getName(), pc.getDesc(), pc.getProductType().getId(), pc.getProductType().getName(), pc.getPrice()));
        items.add(new Item(7, pc.getName(), pc.getDesc(), pc.getProductType().getId(), pc.getProductType().getName(), pc.getPrice()));
        items.add(new Item(8, pc.getName(), pc.getDesc(), pc.getProductType().getId(), pc.getProductType().getName(), pc.getPrice()));
        items.add(new Item(9, pc.getName(), pc.getDesc(), pc.getProductType().getId(), pc.getProductType().getName(), pc.getPrice()));
        items.add(new Item(10, pc.getName(), pc.getDesc(), pc.getProductType().getId(), pc.getProductType().getName(), pc.getPrice()));
        items.add(new Item(11, pc.getName(), pc.getDesc(), pc.getProductType().getId(), pc.getProductType().getName(), pc.getPrice()));
        items.add(new Item(12, pc.getName(), pc.getDesc(), pc.getProductType().getId(), pc.getProductType().getName(), pc.getPrice()));
        invoice.setItems(items);
        Invoice invoiceAfterFare = calculatorService.calculateInvoice(invoice);
        assertTrue(invoiceAfterFare.hasError());
        assertEquals(invoiceAfterFare.getErrors().trim().toString(), "SIM MAX LIMIT:The law prevents anyone buying more than 10 SIM cards in a single purchase.");
    }

    @Test
    public void testVATExemptRule() {
        Invoice invoice = new Invoice();
        List<Item> items = new ArrayList<>();
        Product phone = productCrudRepository.findById(1).get();
        items.add(new Item(0, phone.getName(), phone.getDesc(), phone.getProductType().getId(), phone.getProductType().getName(), phone.getPrice()));
        Product insurance = productCrudRepository.findById(4).get();
        items.add(new Item(1, insurance.getName(), insurance.getDesc(), insurance.getProductType().getId(), insurance.getProductType().getName(), insurance.getPrice()));
        items.add(new Item(2, insurance.getName(), insurance.getDesc(), insurance.getProductType().getId(), insurance.getProductType().getName(), insurance.getPrice()));
        invoice.setItems(items);
        Invoice invoiceAfterFare = calculatorService.calculateInvoice(invoice);
        for (Item e : invoiceAfterFare.getItems()) {
            if (e.getProductTypeId().equals(insurance.getProductType().getId())) {
                assertEquals(Math.round(e.getTax()), 0);
            }

        }
    }
}