package com.gnaderi.interview.phoneshop.controller;

import com.gnaderi.interview.phoneshop.outbound.Invoice;
import org.junit.Test;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PhoneShopControllerTest {
    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();

    @Test
    public void purchase() {

        String createRequestBody = "{\"items\": [5,1,2,2,3,3,3,1,5,5,5,5,5,5,5,3,2,1,5]}";
        HttpEntity<String> request = new HttpEntity<>(createRequestBody, getHttpHeaders());
        ResponseEntity<Invoice> response = restTemplate.exchange(
                createURLWithPort("/phoneshop/purchase"),
                HttpMethod.POST, request, Invoice.class);
        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assert.assertEquals(response.getBody().toString(), "{\"number\":1,\"date\":\"2018-05-24\",\"dueDate\":\"2018-06-07\",\"amount\":2470.0,\"tax\":345.8,\"total\":2815.8,\"items\":[{\"rowNumber\":0,\"productName\":\"Phone Case\",\"desc\":\"Phone Case\",\"productType\":\"Phone Case\",\"amount\":0.0,\"tax\":0.0,\"total\":0.0}, {\"rowNumber\":1,\"productName\":\"Pear myPhoneX\",\"desc\":\"Pear myPhoneX\",\"productType\":\"Phone\",\"amount\":500.0,\"tax\":70.0,\"total\":570.0}, {\"rowNumber\":2,\"productName\":\"Pear myPhone7\",\"desc\":\"Pear myPhone7\",\"productType\":\"Phone\",\"amount\":300.0,\"tax\":42.0,\"total\":342.0}, {\"rowNumber\":3,\"productName\":\"Pear myPhone7\",\"desc\":\"Pear myPhone7\",\"productType\":\"Phone\",\"amount\":300.0,\"tax\":42.0,\"total\":342.0}, {\"rowNumber\":4,\"productName\":\"SIM Card \",\"desc\":\"SIM Card\",\"productType\":\"SIM Card\",\"amount\":0.0,\"tax\":0.0,\"total\":0.0}, {\"rowNumber\":5,\"productName\":\"SIM Card \",\"desc\":\"SIM Card\",\"productType\":\"SIM Card\",\"amount\":0.0,\"tax\":0.0,\"total\":0.0}, {\"rowNumber\":6,\"productName\":\"SIM Card \",\"desc\":\"SIM Card\",\"productType\":\"SIM Card\",\"amount\":0.0,\"tax\":0.0,\"total\":0.0}, {\"rowNumber\":7,\"productName\":\"Pear myPhoneX\",\"desc\":\"Pear myPhoneX\",\"productType\":\"Phone\",\"amount\":500.0,\"tax\":70.0,\"total\":570.0}, {\"rowNumber\":8,\"productName\":\"Phone Case\",\"desc\":\"Phone Case\",\"productType\":\"Phone Case\",\"amount\":0.0,\"tax\":0.0,\"total\":0.0}, {\"rowNumber\":9,\"productName\":\"Phone Case\",\"desc\":\"Phone Case\",\"productType\":\"Phone Case\",\"amount\":10.0,\"tax\":1.4,\"total\":11.4}, {\"rowNumber\":10,\"productName\":\"Phone Case\",\"desc\":\"Phone Case\",\"productType\":\"Phone Case\",\"amount\":10.0,\"tax\":1.4,\"total\":11.4}, {\"rowNumber\":11,\"productName\":\"Phone Case\",\"desc\":\"Phone Case\",\"productType\":\"Phone Case\",\"amount\":10.0,\"tax\":1.4,\"total\":11.4}, {\"rowNumber\":12,\"productName\":\"Phone Case\",\"desc\":\"Phone Case\",\"productType\":\"Phone Case\",\"amount\":10.0,\"tax\":1.4,\"total\":11.4}, {\"rowNumber\":13,\"productName\":\"Phone Case\",\"desc\":\"Phone Case\",\"productType\":\"Phone Case\",\"amount\":10.0,\"tax\":1.4,\"total\":11.4}, {\"rowNumber\":14,\"productName\":\"Phone Case\",\"desc\":\"Phone Case\",\"productType\":\"Phone Case\",\"amount\":10.0,\"tax\":1.4,\"total\":11.4}, {\"rowNumber\":15,\"productName\":\"SIM Card \",\"desc\":\"SIM Card\",\"productType\":\"SIM Card\",\"amount\":0.0,\"tax\":0.0,\"total\":0.0}, {\"rowNumber\":16,\"productName\":\"Pear myPhone7\",\"desc\":\"Pear myPhone7\",\"productType\":\"Phone\",\"amount\":300.0,\"tax\":42.0,\"total\":342.0}, {\"rowNumber\":17,\"productName\":\"Pear myPhoneX\",\"desc\":\"Pear myPhoneX\",\"productType\":\"Phone\",\"amount\":500.0,\"tax\":70.0,\"total\":570.0}, {\"rowNumber\":18,\"productName\":\"Phone Case\",\"desc\":\"Phone Case\",\"productType\":\"Phone Case\",\"amount\":10.0,\"tax\":1.4,\"total\":11.4}],\"errors\":[]}");

    }
    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }

    private HttpHeaders getHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("Cache-Control", "no-cache");
        return headers;
    }
}