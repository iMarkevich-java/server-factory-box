package com.markevich.factorybox.dao.xmldb.exception;

public class ExceptionFindStaffDayId extends RuntimeException {
    public ExceptionFindStaffDayId(String className) {
        System.out.println("Can't find staff day by id: " + className);
    }
}
