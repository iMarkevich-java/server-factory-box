package com.markevich.factorybox.service.staffday;

import biznesObgectFactory.StaffDays;
import com.markevich.factorybox.dao.xmldb.XmlDaoFactory;

public class LoadStaffDayByID {
    private final XmlDaoFactory dao = new XmlDaoFactory();

    protected LoadStaffDayByID() {
    }

    public StaffDays loadStaffDayById(String id) {
        return dao.getStaffDayDao().loadById(id);
    }
}
