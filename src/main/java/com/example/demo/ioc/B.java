package com.example.demo.ioc;



//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class B implements IB{
    //@Autowired No 
    //private C c;  //Nulo en el constructor
    private final IC c;
    public B(IC c){        
        System.out.println("Constructor B");
        this.c = c;
        
    }
    public void print(){
        this.c.print();
    }
}
