package com.example.demo.features.ingredient;

import java.util.UUID;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Configuration
public class IngredientRemove {
    @RestController
    public class Controller{
        @DeleteMapping("/ingredients/{id}")
        public ResponseEntity<?> handler(@PathVariable UUID id ){
            return ResponseEntity.status(204).body(null);
        }
    }
}
