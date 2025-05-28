package com.example.demo.ioc;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class A implements IA {    

    private final IB b;
    public A(final IB b){
        System.out.println("Constructor A");
        this.b = b;       
        print();
    }
    public void print(){
        this.b.print();
    }
}
