package com.markevich.factorybox.gui.exceptions;

public class ExceptionCreateOutputStream extends RuntimeException {
    public ExceptionCreateOutputStream(String className) {
        System.out.println("Can't create output stream: " + className + "!!!!!");
    }
}
