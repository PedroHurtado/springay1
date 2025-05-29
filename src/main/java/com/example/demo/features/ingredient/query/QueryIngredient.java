package com.example.demo.features.ingredient.query;

import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.infraestructure.IngredientRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@Configuration
public class QueryIngredient {
    
    public record Response(UUID id,String name) {
    }
    @RestController
    public class Controller{        
        private final IngredientRepository repository;
        
        public Controller(final IngredientRepository repository) {
            this.repository = repository;
        }

        @GetMapping("/ingredients")
        public ResponseEntity<?> hander(
            @RequestParam(required = false) String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "25") int size, 
            @RequestHeader(name="dni", defaultValue = "52803357A") String dni
        ){
            var ingredients =  repository.findByCritereria(
                name, PageRequest.of(page, size)
            ).stream(). map(i->new Response(i.getId(), i.getName()))
            //.collect(Collectors.toList())
            ;

            return ResponseEntity.ok().body(ingredients);
        }
    }
}
