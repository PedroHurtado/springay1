package com.example.demo.domain;

import java.time.LocalDateTime;
import java.util.UUID;

import com.example.demo.core.Event;
import java.sql.Timestamp;
import jakarta.persistence.Entity;

@Entity
public class Ingredient extends BaseEntity{

    private String name;
    private Double cost;
    protected Ingredient(){
        super();
    }
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
        
        var ingredient = new Ingredient(id, name, cost);
        
        var event = new Event(UUID.randomUUID(), 
            Timestamp.valueOf(LocalDateTime.now()),
            "ingredient_create", 
            ingredient);
            
        ingredient.add(event);

        return ingredient;
    }
    
    
}

