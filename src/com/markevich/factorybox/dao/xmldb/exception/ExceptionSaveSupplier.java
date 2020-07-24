package com.markevich.factorybox.dao.xmldb.exception;

public class ExceptionSaveSupplier extends RuntimeException {
    public ExceptionSaveSupplier(String className) {
        System.out.println("Can't save supplier: " + className + "!!!");
    }
}
