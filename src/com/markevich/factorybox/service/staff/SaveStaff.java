package com.markevich.factorybox.service.staff;

import businessObjectFactoryBox.Staff;
import com.markevich.factorybox.dao.xmldb.XmlDaoFactory;

public class SaveStaff {
    private final XmlDaoFactory dao = new XmlDaoFactory();

    protected SaveStaff() {
    }

    public void saveStaff(Staff staff) {
        dao.getStaffDao().save(staff);
    }
}
