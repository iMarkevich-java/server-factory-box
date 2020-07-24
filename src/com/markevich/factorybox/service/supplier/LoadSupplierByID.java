package com.markevich.factorybox.service.supplier;

import biznesObgectFactory.Supplier;
import com.markevich.factorybox.dao.xmldb.XmlDaoFactory;

public class LoadSupplierByID {
    private final XmlDaoFactory dao = new XmlDaoFactory();

    protected LoadSupplierByID() {
    }

    public Supplier loadSupplierById(String id) {
        return dao.getSupplierDao().loadById(id);
    }
}
