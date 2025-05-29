package com.example.demo.features.pizza.commands;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
//import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Ingredient;
import com.example.demo.domain.Pizza;
import com.example.demo.infraestructure.IngredientRepository;
import com.example.demo.infraestructure.PizzaRepository;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Configuration
public class PizzaCreate {
    
    public record Request(
        String name,
        String description,
        String url,
        Set<UUID> ingredientes
    ) {
    }

    public record ResponseIngredient(
        UUID id,
        String name        
    ){
        
    }
    public record Response(
        UUID id,
        String name,
        String description,
        String url,
        double price,
        Stream<ResponseIngredient> ingredientes
    ){
        
    }

    @RestController 
    public class Controller{
        
        private final IngredientRepository ingredientRepository;
        private final PizzaRepository repository;

        public Controller(            
            final PizzaRepository repository,
            final IngredientRepository ingredientRepository            
            ){
            this.repository =repository;
            this.ingredientRepository = ingredientRepository;
        }
        @PostMapping("path")
        public ResponseEntity<?> postMethodName(@RequestBody Request request) { 

            /*
             * var ingredients = request.ingredients().stream()
            		.map(id -> ingredientRepository.findByIdWithNotFound(id))
                    .collect(Collectors.toSet());
             * 
             */
            var ingredients = new HashSet<Ingredient>();  
            
            for(var id:request.ingredientes){
                var ingredient = this.ingredientRepository.findByIdWithNotFound(id);
                ingredients.add(ingredient);
            }        
            
            var pizza = Pizza.create(
                UUID.randomUUID(), 
                request.name(), 
                request.description(), 
                request.url(), 
                ingredients);

            repository.save(pizza);

            Response response =new Response(
                pizza.getId(), 
                pizza.getName(), 
                pizza.getDescription(), 
                pizza.getUrl(), 
                pizza.getPrice(), 
                pizza.getIngredients()
                    .stream()
                    .map(i->new ResponseIngredient(i.getId(),i.getName())) 
                    //.collect(Collectors.toSet())             
                );    

            return ResponseEntity.status(201).body(response);
        }
        
    }
}
