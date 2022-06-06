package com.ulger.orderservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/oauth/authorized")
public class OAuth2CallbackTestController {

    @GetMapping
    public String success(HttpServletRequest request) {
        return request.getParameter("code");
    }
}