package com.myonlineshopping.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {
    @RequestMapping(value = "/account")
    public String account() {
        return "account";
    }
}