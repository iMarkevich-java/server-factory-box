package com.markevich.factorybox.net.exeptions;

public class ExceptionCloseSocket extends RuntimeException {
    public ExceptionCloseSocket(String className) {
        System.out.println("Can't close socket " + className);
    }
}
