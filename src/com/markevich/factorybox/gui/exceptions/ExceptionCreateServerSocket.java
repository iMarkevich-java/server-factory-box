package com.markevich.factorybox.gui.exceptions;

public class ExceptionCreateServerSocket extends RuntimeException {
    public ExceptionCreateServerSocket(String className) {
        System.out.println("Can't create server socket: " + className + "!!!");
    }
}
