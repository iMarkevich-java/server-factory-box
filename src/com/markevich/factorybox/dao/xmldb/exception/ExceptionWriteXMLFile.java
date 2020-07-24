package com.markevich.factorybox.dao.xmldb.exception;

public class ExceptionWriteXMLFile extends RuntimeException {
    public ExceptionWriteXMLFile(String className) {
        System.out.println("Can't write XML file: " + className + "!!!");
    }
}
