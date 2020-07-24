package com.markevich.factorybox.net.serverCommandFactory.exceptions;

public class ExceptionNotFindIdSupplier extends RuntimeException {
    public ExceptionNotFindIdSupplier(String className) {
        System.out.println("Don't find id supplier: " + className + "!!!");
    }
}
