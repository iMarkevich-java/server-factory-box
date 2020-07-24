package com.markevich.factorybox.net.serverCommandFactory.exceptions;

public class ExceptionFlushOutputStream extends RuntimeException {
    public ExceptionFlushOutputStream(String className) {
        System.out.println("Can't flush output stream: " + className + "!!!");
    }
}
