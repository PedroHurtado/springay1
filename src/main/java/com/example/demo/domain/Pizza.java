package com.example.demo.domain;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;


/*
 * pizza={
   id,
   name,
   description,
   url,
   ingredients->set(n:m)
   price->sum(cost ingredientes)+ 20 % beneficios
}

ingredients{
   id,
   name,
   cost
}

pizza->n:m->ingredients

es igual a otra si sus identificadores son iguales

	1 Tomate 2.5
	1 tomate 3

equals
hascode
set<Ingredient>

la pizza permite agregar y eliminar ingredientes
 * 
 */

public class Pizza extends BaseEntity {
    private static final Double PROFIT = 1.20D;
    private String name;
    private String description;
    private String url;
    private final Set<Ingredient> ingredients;

    protected Pizza(
            final UUID id,
            String name,
            String description,
            String url,
            Set<Ingredient> ingredients) {
        super(id);
        this.name = name;
        this.description = description;
        this.url = url;
        this.ingredients = ingredients;
    }
    
    public String getDescription() {
        return description;
    }
    public Set<Ingredient> getIngredients() {
        return new HashSet<>(ingredients);
    }
    public String getName() {
        return name;
    }
    public String getUrl() {
        return url;
    }  
    public double getPrice() {
        //stream
        //filter,sort,find, map, reduce       

        var price = 0D;
        for (Ingredient ingredient : ingredients) {
            price+=ingredient.getCost();
        }
        return price * PROFIT;
    
        /*return ingredients.stream()
                .mapToDouble(Ingredient::getCost)   //map                             
                .sum() * PROFIT;*/
    }
    void addIngredient(Ingredient ingredient) {
        //add ingredient
        ingredients.add(ingredient);       
    }
    void removeIngredient(Ingredient ingredient) {
        //update ingredient
        ingredients.remove(ingredient);
    }
    void Update(String name, String description, String url) {
        //update
        this.name = name;
        this.description = description;
        this.url = url;      
    }
    public static Pizza create(
            UUID id,
            String name,
            String description,
            String url,
            Set<Ingredient> ingredients) {
                //crear
        return new Pizza(id, name, description, url, new HashSet<>(ingredients));
    }

}

