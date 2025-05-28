package com.example.demo.features.ingredient;

import java.util.UUID;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Configuration
public class IngredientUpdate {
    
    public record Request(UUID id,String name,Double cost) {
    }
    @RestController
    public class Controller{
        
        @PutMapping("ingredients/{id}")
        public ResponseEntity<?> handler(@PathVariable UUID id,@RequestBody Request req){
            return ResponseEntity.status(204).body(null);
        }
    }
}
