package com.markevich.factorybox.service.staffday;

import biznesObgectFactory.StaffDays;
import com.markevich.factorybox.dao.xmldb.XmlDaoFactory;

import java.util.List;

public class LoadAllStaffDay {
    private final XmlDaoFactory dao = new XmlDaoFactory();

    protected LoadAllStaffDay() {
    }

    public List<StaffDays> loadAllStaffDay() {
        return dao.getStaffDayDao().loadAll();
    }
}
