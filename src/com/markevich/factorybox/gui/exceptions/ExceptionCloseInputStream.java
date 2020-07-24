package com.markevich.factorybox.gui.exceptions;

public class ExceptionCloseInputStream extends RuntimeException {
    public ExceptionCloseInputStream(String className) {
        System.out.println("Can't close input stream: " + className + "!!!");
    }
}
