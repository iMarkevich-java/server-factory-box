package com.markevich.factorybox.dao.xmldb.exception;

public class ExceptionSaveClient extends RuntimeException {
    public ExceptionSaveClient(String className) {
        System.out.println("Can't save client: " + className + "!!!");
    }
}
