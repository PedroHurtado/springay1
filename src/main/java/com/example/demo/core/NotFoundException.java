package com.example.demo.core;

public class NotFoundException extends RuntimeException {
    public NotFoundException(){
        super("No se ha encontrado el recurso");
    }
    public NotFoundException(String message){
        super(message);
    }
}
