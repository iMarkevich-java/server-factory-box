package com.markevich.factorybox.dao.xmldb.exception;

public class ExceptionReadXMLFile extends RuntimeException {
    public ExceptionReadXMLFile(String className) {
        System.out.println("Can't read from XML file: " + className + "!!!");
    }
}
