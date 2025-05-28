package com.example.demo.core;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;


@NoRepositoryBean
public interface CustomRepository<T,ID> extends CrudRepository<T,ID> {
    default T findByIdWithNotFound(ID id) {
        return this.findById(id).orElseThrow(()->new NotFoundException());
    }
    
} 
