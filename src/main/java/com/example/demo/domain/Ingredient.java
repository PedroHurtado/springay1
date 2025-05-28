package com.example.demo.domain;

import java.util.UUID;

public class Ingredient extends BaseEntity{

    private String name;
    private Double cost;
    protected Ingredient(final UUID id ,String name, Double cost){ 
        super(id);
        this.name = name;
        this.cost = cost;
        
    }
    public void Update(String name, Double cost){
        //evento update
        this.name = name;
        this.cost = cost;
    }
    public String getName() {
        return name;
    }
    public Double getCost(){
        return cost;
    }
    public static Ingredient create(UUID id,String name, Double cost){
        //evento create
        return new Ingredient(id, name, cost);
    }
    
    
}

