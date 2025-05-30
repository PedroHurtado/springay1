package com.example.demo.features.ingredient;

import java.util.UUID;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Ingredient;
import com.example.demo.infraestructure.IngredientRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


//https://www.jimmybogard.com/vertical-slice-architecture/
@Configuration
public class IngredientCreate {
    public record Request(String name,Double cost) {
    }
    public record Response(UUID id, String name,Double cost){

    }
    @RestController
    public class Controller{

        private final Service service;
        public Controller(final Service service){
            this.service = service;
        }
        @PostMapping("/ingredients")     
        public ResponseEntity<?> handler(@RequestBody Request req){            
            return ResponseEntity.status(201).body(
                service.handler(req)
            );
        }
    }
    public interface  Service {
        Response handler(Request req);        
    }
    @org.springframework.stereotype.Service
    public class ServiceImpl implements Service{
        
        @PersistenceContext
        private EntityManager entityManager;
        private final IngredientRepository repository;
        public ServiceImpl(IngredientRepository repository){
            this.repository = repository;
        }
        @Override
        @Transactional
        public Response handler(Request req) {
            var ingredient= Ingredient.create(UUID.randomUUID(), req.name(), req.cost());            
            //repository.save(ingredient);            
            //ingredient.getEvents();
            //save eventos
            entityManager.persist(ingredient);
            ingredient.clear();
            
            return new Response(ingredient.getId(), ingredient.getName(), ingredient.getCost());
        }

    }
}
