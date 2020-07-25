package com.markevich.factorybox.service.staffday;

import businessObjectFactoryBox.StaffDays;
import com.markevich.factorybox.dao.xmldb.XmlDaoFactory;

public class UpdateStaffDay {
    private final XmlDaoFactory dao = new XmlDaoFactory();

    protected UpdateStaffDay() {
    }

    public void updateStaffDay(StaffDays staffDay) {
        dao.getStaffDayDao().update(staffDay);
    }
}
