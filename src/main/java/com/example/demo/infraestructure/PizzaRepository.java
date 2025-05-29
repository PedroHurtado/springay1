package com.example.demo.infraestructure;

import java.util.UUID;

import com.example.demo.core.CustomRepository;
import com.example.demo.domain.Pizza;

public interface PizzaRepository extends CustomRepository<Pizza,UUID> {
    
}
