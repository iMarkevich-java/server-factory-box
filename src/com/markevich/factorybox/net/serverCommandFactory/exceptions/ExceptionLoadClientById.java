package com.markevich.factorybox.net.serverCommandFactory.exceptions;

public class ExceptionLoadClientById extends RuntimeException {
    public ExceptionLoadClientById(String className) {
        System.out.println("Can't load client by id : " + className);
    }
}
