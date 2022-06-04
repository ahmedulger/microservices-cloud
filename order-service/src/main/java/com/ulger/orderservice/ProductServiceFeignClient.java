package com.ulger.orderservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "product-service")
public interface ProductServiceFeignClient {

    @GetMapping("/product/greeting")
    String greeting();
}