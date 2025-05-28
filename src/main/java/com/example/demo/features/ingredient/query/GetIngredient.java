package com.example.demo.features.ingredient.query;

import java.util.UUID;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.infraestructure.IngredientRepository;

import org.springframework.web.bind.annotation.GetMapping;


@Configuration
public class GetIngredient {
    public record Response(UUID id,String name, double cost) {
    }
    @RestController
    public class Controller{        
        
        private final IngredientRepository repository;
        public Controller(final IngredientRepository repository){
            this.repository = repository;
        }
        @GetMapping("/ingredients/{id}")
        public ResponseEntity<?> handler(@PathVariable UUID id){

            var ingredient = this.repository.findByIdWithNotFound(id);

            var response = new Response(
                    ingredient.getId(), 
                    ingredient.getName(), 
                    ingredient.getCost());
                    
            return ResponseEntity.ok().body(response);
            
        }
    }
}
