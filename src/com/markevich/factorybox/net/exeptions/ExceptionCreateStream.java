package com.markevich.factorybox.net.exeptions;

public class ExceptionCreateStream extends RuntimeException {
    public ExceptionCreateStream(String className) {
        System.out.println("Can't create input or output stream: " + className);
    }
}
