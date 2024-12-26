package com.spring.springconfiguration.controller;

import com.spring.springconfiguration.currency.CurrencyConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @Autowired
    public CurrencyConfiguration currencyConfiguration;

    @GetMapping("currency-config")
    public CurrencyConfiguration getCurrencyConfiguration(){
        return currencyConfiguration;
    }

    @GetMapping("/hello")
    public String getString(){
        return "hello";
    }
}
