package com.markevich.factorybox.dao.xmldb.exception;

public class ExceptionFindOrderId extends RuntimeException {
    public ExceptionFindOrderId(String className) {
        System.out.println("Can't find order id: " + className);
    }
}
