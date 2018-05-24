package com.gnaderi.interview.phoneshop.exception;

public class PhoneShopException extends RuntimeException {

    public PhoneShopException(String message) {
        super(message);
    }

    public PhoneShopException(String message, Throwable cause) {
        super(message, cause);
    }
}