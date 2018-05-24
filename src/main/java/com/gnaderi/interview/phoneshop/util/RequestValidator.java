package com.gnaderi.interview.phoneshop.util;


import com.gnaderi.interview.phoneshop.exception.PhoneShopException;
import com.gnaderi.interview.phoneshop.inbound.OrderRequest;
import com.gnaderi.interview.phoneshop.outbound.ProductDetails;
import com.gnaderi.interview.phoneshop.outbound.ProductTypeOutbound;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public final class RequestValidator {

    private static final Logger LOGGER = LoggerFactory.getLogger(RequestValidator.class);

    public void validateRequest(final OrderRequest orderRequest) throws PhoneShopException {
        try {
            //TODO
        } catch (NullPointerException | IllegalArgumentException ex) {
            LOGGER.error("Invalid Request:", ex);
            throw new PhoneShopException("Invalid request!");
        }

    }

    public void validateRequest(final ProductDetails request) throws PhoneShopException {
        try {
            //TODO
        } catch (NullPointerException | IllegalArgumentException ex) {
            LOGGER.error("Invalid Request:", ex);
            throw new PhoneShopException("Invalid request!");
        }

    }

    public void validateRequest(final ProductTypeOutbound request) throws PhoneShopException {
        try {
            //TODO
        } catch (NullPointerException | IllegalArgumentException ex) {
            LOGGER.error("Invalid Request:", ex);
            throw new PhoneShopException("Invalid request!");
        }

    }
}