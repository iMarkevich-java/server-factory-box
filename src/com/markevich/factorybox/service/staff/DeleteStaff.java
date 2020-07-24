package com.markevich.factorybox.service.staff;

import com.markevich.factorybox.dao.xmldb.XmlDaoFactory;

public class DeleteStaff {
    private final XmlDaoFactory dao = new XmlDaoFactory();

    protected DeleteStaff() {
    }

    public void deleteStaff(String id) {
        dao.getStaffDao().delete(id);
    }
}
