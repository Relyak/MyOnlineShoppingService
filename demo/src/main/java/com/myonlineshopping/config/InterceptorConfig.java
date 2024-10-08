package com.myonlineshopping.config;

import com.myonlineshopping.interceptors.AccountServiceInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
@Profile("prod")
public class InterceptorConfig implements WebMvcConfigurer {
    @Autowired
    AccountServiceInterceptor asi;
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(asi);
    }
}