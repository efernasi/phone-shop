package com.gnaderi.interview.phoneshop.service;

import com.gnaderi.interview.phoneshop.exception.PhoneShopException;
import com.gnaderi.interview.phoneshop.outbound.Invoice;
import com.gnaderi.interview.phoneshop.outbound.Item;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvoiceCalculatorService {

    @Autowired
    private KieContainer kieContainer;

    public Invoice calculateInvoice(Invoice invoice) {
        KieSession kieSession = kieContainer.newKieSession("ksession-rules");
        kieSession.insert(invoice);
        kieSession.fireAllRules();
        kieSession.dispose();
        invoice.getItems().forEach(e -> e.setTotal(e.getAmount() + e.getTax()));
        invoice.setAmount(invoice.getItems().stream().mapToDouble(Item::getAmount).sum());
        invoice.setTax(Math.round(invoice.getItems().stream().mapToDouble(Item::getTax).sum()*100)/100.0);
        invoice.setTotal(Math.round(invoice.getItems().stream().mapToDouble(Item::getTotal).sum()*100)/100.0);
        return invoice;
    }
}