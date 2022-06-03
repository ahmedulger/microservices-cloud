package com.ulger.orderservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    private ProductServiceFeignClient productServiceFeignClient;

    public TestController(ProductServiceFeignClient productServiceFeignClient) {
        this.productServiceFeignClient = productServiceFeignClient;
    }

    @Value("${test.greeting}")
    private String test;

    @RequestMapping("/greeting")
    public String greeting() {
        return test + " - " + productServiceFeignClient.greeting();
    }
}