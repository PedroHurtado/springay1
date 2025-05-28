package com.example.demo.features.ingredient.query;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@Configuration
public class QueryIngredient {
    
    public record Response(UUID id,String name, double cost) {
    }
    @RestController
    public class Controller{        
        
        @GetMapping("/ingredients")
        public ResponseEntity<?> hander(
            @RequestParam(required = false) String name,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "25") int size, 
            @RequestHeader(name="dni", defaultValue = "52803357A") String dni
        ){
            List<Response> list = new ArrayList<>();
            return ResponseEntity.ok().body(list);
        }
    }
}
