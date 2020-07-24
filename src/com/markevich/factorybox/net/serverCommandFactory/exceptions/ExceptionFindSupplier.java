package com.markevich.factorybox.net.serverCommandFactory.exceptions;

public class ExceptionFindSupplier extends RuntimeException {
    public ExceptionFindSupplier(String className) {
        System.out.println("Can't find supplier: " + className + "!!!");
    }
}
