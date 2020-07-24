package com.markevich.factorybox.gui.exceptions;

public class ExceptionCloseServerSocket extends RuntimeException {
    public ExceptionCloseServerSocket(String className) {
        System.out.println("Can't close server socket: " + className + "!!!");
    }
}
