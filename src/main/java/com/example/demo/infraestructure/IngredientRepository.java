package com.example.demo.infraestructure;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.domain.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient,UUID> {
    
}
