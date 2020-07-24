package com.markevich.factorybox.service.staff;

import biznesObgectFactory.Staff;
import com.markevich.factorybox.dao.xmldb.XmlDaoFactory;

public class LoadStaffByID {
    private final XmlDaoFactory dao = new XmlDaoFactory();

    protected LoadStaffByID() {
    }

    public Staff loadStaffById(String id) {
        return dao.getStaffDao().loadById(id);
    }
}
