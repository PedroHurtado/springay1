package com.example.demo.features.ingredient;

import java.util.UUID;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.infraestructure.IngredientRepository;

@Configuration
public class IngredientRemove {
    @RestController
    public class Controller{
        private final IngredientRepository repository;
        public Controller(final IngredientRepository repository){
            this.repository = repository;
        }
        @DeleteMapping("/ingredients/{id}")
        public ResponseEntity<?> handler(@PathVariable UUID id ){
            var ingredient = repository.findByIdWithNotFound(id);
            repository.delete(ingredient);            
            return ResponseEntity.status(204).body(null);
        }
    }
}
