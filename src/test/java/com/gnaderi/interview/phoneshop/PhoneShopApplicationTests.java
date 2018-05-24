package com.gnaderi.interview.phoneshop;


import com.gnaderi.interview.phoneshop.outbound.ProductCatalog;
import com.gnaderi.interview.phoneshop.outbound.ProductDetails;
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
public class PhoneShopApplicationTests {
    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();

    @Test
    public void getAllProducts() throws Exception {
        HttpEntity<String> entity = new HttpEntity<>(null, getHttpHeaders());

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/phoneshop/products"),
                HttpMethod.GET, entity, String.class);
        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assert.assertEquals(response.getBody(), "{\"productCatalog\":[{\"id\":1,\"productName\":\"Pear myPhoneX\",\"desc\":\"Pear myPhoneX\",\"category\":\"Phone\",\"price\":500.0,\"quantity\":200},{\"id\":2,\"productName\":\"Pear myPhone7\",\"desc\":\"Pear myPhone7\",\"category\":\"Phone\",\"price\":300.0,\"quantity\":200},{\"id\":3,\"productName\":\"SIM Card \",\"desc\":\"SIM Card\",\"category\":\"SIM Card\",\"price\":20.0,\"quantity\":200},{\"id\":4,\"productName\":\"Phone Insurance\",\"desc\":\"Phone Insurance (for 2 years)\",\"category\":\"Phone Insurance\",\"price\":120.0,\"quantity\":200},{\"id\":5,\"productName\":\"Phone Case\",\"desc\":\"Phone Case\",\"category\":\"Phone Case\",\"price\":10.0,\"quantity\":200}]}");
    }

    @Test
    public void createNewProduct() throws Exception {

        String createRequestBody = "{\n" +
                "   \"productName\": \"Pear Samsung Galaxy S9\",\n" +
                "    \"desc\": \"Latest Samsung Galaxy S9 2018\",\n" +
                "    \"category\": \"Phone\",\n" +
                "    \"price\": 700,\n" +
                "    \"quantity\": 150\n" +
                "}";
        HttpEntity<String> request = new HttpEntity<>(createRequestBody, getHttpHeaders());

        ResponseEntity<ProductDetails> response = restTemplate.exchange(
                createURLWithPort("/phoneshop/products"), HttpMethod.POST, request, ProductDetails.class);

        Assert.assertEquals(response.getStatusCode(), HttpStatus.CREATED);
        Assert.assertEquals(response.getBody().getProductName(), "Pear Samsung Galaxy S9");
        Assert.assertEquals(response.getBody().toString(), "{\"id\":6,\"productName\":\"Pear Samsung Galaxy S9\",\"desc\":\"Latest Samsung Galaxy S9 2018\",\"category\":\"Phone\",\"price\":700.0,\"quantity\":150}");

    }

    @Test
    public void updateProduct() throws Exception {

        String createRequestBody = "{\n" +
                "   \"productName\": \"Samsung Galaxy S9\",\n" +
                "   \"desc\": \"Latest Samsung Galaxy S9 2017\",\n" +
                "   \"category\": \"Phone\",\n" +
                "   \"price\": 600,\n" +
                "   \"quantity\": 100\n" +
                "}";
        HttpEntity<String> request = new HttpEntity<>(createRequestBody, getHttpHeaders());

        ResponseEntity<ProductDetails> response = restTemplate.exchange(
                createURLWithPort("/phoneshop/products/5"), HttpMethod.PUT, request, ProductDetails.class);


        System.out.println("response = " + response.getBody().toString());

        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assert.assertEquals(response.getBody().getProductName(), "Samsung Galaxy S9");
        Assert.assertEquals(response.getBody().toString(), "{\"id\":5,\"productName\":\"Samsung Galaxy S9\",\"desc\":\"Latest Samsung Galaxy S9 2017\",\"category\":\"Phone\",\"price\":600.0,\"quantity\":100}");

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
