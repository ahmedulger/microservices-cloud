package com.ulger.orderservice.configuration;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;


@Configuration
@EnableEurekaClient
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.ulger")
public class CloudConfiguration {

}