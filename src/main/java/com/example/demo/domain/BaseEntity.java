package com.example.demo.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.example.demo.core.Event;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseEntity {
    private final List<Event> events = new ArrayList<>();
    @Id
    private final UUID id;
    protected BaseEntity(){
        this(UUID.randomUUID());
    }
    protected BaseEntity(final UUID id) {
        this.id = id;
    }
    public UUID getId() {
        return id;
    }
    @Override
    public boolean equals(Object obj) {     
        //java 16 Pattern Matching for instanceof (JEP 394).
        //https://openjdk.org/jeps/394  
        if (obj instanceof BaseEntity e) {  
            return id.equals(e.id);   //true or false
        }
        return false;        
    }
    @Override
    public int hashCode() {        
        return id.hashCode();
    }
    public void add(Event event){
        events.add(event);
    }
    public void remove(Event event){
        events.remove(event);
    }
    public void clear(){
        events.clear();
    }
    public List<Event> getEvents(){
        return new ArrayList<>(events);
    }

    
}

