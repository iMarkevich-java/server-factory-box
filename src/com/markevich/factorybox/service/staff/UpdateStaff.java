package com.markevich.factorybox.service.staff;

import businessObjectFactoryBox.Staff;
import com.markevich.factorybox.dao.xmldb.XmlDaoFactory;

public class UpdateStaff {
    private final XmlDaoFactory dao = new XmlDaoFactory();

    protected UpdateStaff() {
    }

    public void updateStaff(Staff staff) {
        dao.getStaffDao().update(staff);
    }
}
