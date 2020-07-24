package com.markevich.factorybox.dao.xmldb.exception;

public class ExceptionFindProductId extends RuntimeException {
    public ExceptionFindProductId(String className) {
        System.out.println("Can't find product id: " + className);
    }
}
