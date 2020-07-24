package com.markevich.factorybox.dao.xmldb.exception;

public class ExceptionFindClientId extends RuntimeException {
    public ExceptionFindClientId(String className) {
        System.out.println("Can't find client id: " + className);
    }
}
