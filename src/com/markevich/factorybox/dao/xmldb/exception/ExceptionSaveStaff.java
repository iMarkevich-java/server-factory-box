package com.markevich.factorybox.dao.xmldb.exception;

public class ExceptionSaveStaff extends RuntimeException {
    public ExceptionSaveStaff(String className) {
        System.out.println("Can't save staff: " + className + "!!!");
    }
}
