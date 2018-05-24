package com.gnaderi.interview.phoneshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class PhoneShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(PhoneShopApplication.class, args);
    }

}
