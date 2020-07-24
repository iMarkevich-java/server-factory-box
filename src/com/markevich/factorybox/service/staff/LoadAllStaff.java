package com.markevich.factorybox.service.staff;

import biznesObgectFactory.Staff;
import com.markevich.factorybox.dao.xmldb.XmlDaoFactory;

import java.util.List;

public class LoadAllStaff {
    private final XmlDaoFactory dao = new XmlDaoFactory();

    protected LoadAllStaff() {
    }

    public List<Staff> loadAllStaff() {
        return dao.getStaffDao().loadAll();
    }
}
