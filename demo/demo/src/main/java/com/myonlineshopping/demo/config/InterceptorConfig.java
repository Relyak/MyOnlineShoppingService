package com.myonlineshopping.demo.config;

import com.myonlineshopping.demo.interceptors.AccountServiceInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class InterceptorConfig implements WebMvcConfigurer {
    @Autowired
    AccountServiceInterceptor asi;
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(asi);
    }
}