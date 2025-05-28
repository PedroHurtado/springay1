package com.example.demo.domain;

import java.util.UUID;

public abstract class BaseEntity {
    private final UUID id;
    protected BaseEntity(final UUID id) {
        this.id = id;
    }
    public UUID getId() {
        return id;
    }
    @Override
    public boolean equals(Object obj) {     
        //java 16 Pattern Matching for instanceof (JEP 394).
        //https://openjdk.org/jeps/394  
        if (obj instanceof BaseEntity e) {  
            return id.equals(e.id);   //true or false
        }
        return false;        
    }
    @Override
    public int hashCode() {        
        return id.hashCode();
    }
    
}

