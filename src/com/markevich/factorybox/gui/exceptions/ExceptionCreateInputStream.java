package com.markevich.factorybox.gui.exceptions;

public class ExceptionCreateInputStream extends RuntimeException {

    public ExceptionCreateInputStream(String className) {
        System.out.println("Can't create input stream " + className);
    }
}
