package com.markevich.factorybox.dao.xmldb.exception;

public class ExceptionFindSupplierId extends RuntimeException {
    public ExceptionFindSupplierId(String className) {
        System.out.println("Can't find supplier id: " + className);
    }
}
