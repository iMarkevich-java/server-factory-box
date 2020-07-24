package com.markevich.factorybox.gui.exceptions;

public class ExceptionCloseOutputStream extends RuntimeException {
    public ExceptionCloseOutputStream(String className) {
        System.out.println("Can't close output stream: " + className + "!!!");
    }
}
