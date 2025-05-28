package com.example.demo.ioc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class D {
    @Bean
    //Factory method
    public IA getA(IB b){
        return new A(b);
    }
}
