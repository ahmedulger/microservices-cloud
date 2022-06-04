package com.ulger.productservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Value("${test.greeting}")
    private String test;

    @GetMapping("/greeting")
    public String greeting() {
        return test;
    }
}