package com.markevich.factorybox.gui.exceptions;

public class ExceptionFindIcon extends RuntimeException {
    public ExceptionFindIcon(String className) {
        System.out.println("Can't find icon: " + className + "!!!");
    }
}
