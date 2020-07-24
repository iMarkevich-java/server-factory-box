package com.markevich.factorybox.service.staffday;

import biznesObgectFactory.Day;
import com.markevich.factorybox.dao.xmldb.XmlDaoFactory;

public class DeleteStaffDayDay {
    private final XmlDaoFactory dao = new XmlDaoFactory();

    protected DeleteStaffDayDay() {
    }

    public void deleteStaffDayDay(Day day) {
        dao.getStaffDayDao().deleteDay(day);
    }
}
