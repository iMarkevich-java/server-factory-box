package com.markevich.factorybox.service.staffday;

import com.markevich.factorybox.dao.xmldb.XmlDaoFactory;

public class DeleteStaffDay {
    private final XmlDaoFactory dao = new XmlDaoFactory();

    protected DeleteStaffDay() {
    }

    public void deleteStaffDay(String staffId) {
        dao.getStaffDayDao().delete(staffId);
    }
}
