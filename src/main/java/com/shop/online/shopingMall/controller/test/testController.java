package com.shop.online.shopingMall.controller.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class testController {

    @Autowired
    TestService testService;

    @GetMapping()
    public void test(@PathVariable int id) {

    }
}
