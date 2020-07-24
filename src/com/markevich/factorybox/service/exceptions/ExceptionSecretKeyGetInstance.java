package com.markevich.factorybox.service.exceptions;

public class ExceptionSecretKeyGetInstance extends RuntimeException {
    public ExceptionSecretKeyGetInstance(String className) {
        System.out.println("Can't generate secret key: " + className + "!!!");
    }
}
