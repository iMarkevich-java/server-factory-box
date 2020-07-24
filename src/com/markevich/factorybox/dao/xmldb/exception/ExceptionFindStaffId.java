package com.markevich.factorybox.dao.xmldb.exception;

public class ExceptionFindStaffId extends RuntimeException {
    public ExceptionFindStaffId(String className) {
        System.out.println("Can't find staff id: " + className);
    }
}
