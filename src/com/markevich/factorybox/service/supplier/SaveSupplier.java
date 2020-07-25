package com.markevich.factorybox.service.supplier;

import businessObjectFactoryBox.Supplier;
import com.markevich.factorybox.dao.xmldb.XmlDaoFactory;

public class SaveSupplier {
    private final XmlDaoFactory dao = new XmlDaoFactory();

    protected SaveSupplier() {
    }

    public void saveSupplier(Supplier supplier) {
        dao.getSupplierDao().save(supplier);
    }
}
