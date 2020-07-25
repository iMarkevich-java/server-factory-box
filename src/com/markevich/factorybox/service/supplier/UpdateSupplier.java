package com.markevich.factorybox.service.supplier;

import businessObjectFactoryBox.Supplier;
import com.markevich.factorybox.dao.xmldb.XmlDaoFactory;

public class UpdateSupplier {
    private final XmlDaoFactory dao = new XmlDaoFactory();

    protected UpdateSupplier() {
    }

    public void updateSupplier(Supplier supplier) {
        dao.getSupplierDao().update(supplier);
    }
}
