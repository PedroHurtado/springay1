package com.example.demo.infraestructure;

import java.util.List;
import java.util.UUID;
//import java.util.stream.Stream;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.core.CustomRepository;
import com.example.demo.domain.Ingredient;

public interface IngredientRepository extends CustomRepository<Ingredient,UUID> {
    
    @Query("SELECT i from Ingredient i WHERE (:name is NULL OR name LIKE %:name%)")
    List<Ingredient> findByCritereria(@Param("name") String name, Pageable pageable);
}
