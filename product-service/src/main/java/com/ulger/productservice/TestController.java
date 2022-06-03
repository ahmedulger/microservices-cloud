package com.ulger.productservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Value("${test.greeting}")
    private String test;

    @RequestMapping("/greeting")
    public String greeting() {
        return test;
    }
}