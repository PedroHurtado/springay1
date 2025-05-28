package com.example.demo.features.ingredient.query;

import java.util.UUID;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@Configuration
public class GetIngredient {
    public record Response(UUID id,String name, double cost) {
    }
    @RestController
    public class Controller{        
        
        @GetMapping("/ingredients/{id}")
        public ResponseEntity<?> handler(@PathVariable UUID id){
            var response = new Response(UUID.randomUUID(), "Tomate", 2.5);
            return ResponseEntity.ok().body(response);
            // si el registro no existe devolvemos un 404
        }
    }
}
