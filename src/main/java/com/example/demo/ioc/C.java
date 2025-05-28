package com.example.demo.ioc;

import org.springframework.stereotype.Repository;

@Repository
public class C implements IC {
    public C(){
        System.out.println("Constructor C");
    }
    public void print(){
        System.out.println("print method c");
    }
}
