package com.markevich.factorybox.service.exceptions;

public class ExceptionGeneratePassword extends RuntimeException {
    public ExceptionGeneratePassword(String className) {
        System.out.println("Can't generate secret password: " + className + "!!!");
    }
}
