package com.markevich.factorybox.dao.xmldb.exception;

public class ExceptionSaveOrder extends RuntimeException {
    public ExceptionSaveOrder(String className) {
        System.out.println("Can't save order: " + className + "!!!");
    }
}
