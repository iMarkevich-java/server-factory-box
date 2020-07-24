package com.markevich.factorybox.service.staffday;

import biznesObgectFactory.StaffDays;
import com.markevich.factorybox.dao.xmldb.XmlDaoFactory;

public class SaveStaffDay {
    private final XmlDaoFactory dao = new XmlDaoFactory();

    protected SaveStaffDay() {
    }

    public void saveStaffDay(StaffDays staffDay) {
        dao.getStaffDayDao().save(staffDay);
    }
}
