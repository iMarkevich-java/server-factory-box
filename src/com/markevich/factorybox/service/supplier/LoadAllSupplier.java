package com.markevich.factorybox.service.supplier;

import businessObjectFactoryBox.Supplier;
import com.markevich.factorybox.dao.xmldb.XmlDaoFactory;

import java.util.List;

public class LoadAllSupplier {
    private final XmlDaoFactory dao = new XmlDaoFactory();

    protected LoadAllSupplier() {
    }

    public List<Supplier> loadAllSupplier() {
        return dao.getSupplierDao().loadAll();
    }
}
