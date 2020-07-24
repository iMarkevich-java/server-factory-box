package com.markevich.factorybox.service.supplier;

import com.markevich.factorybox.dao.xmldb.XmlDaoFactory;

public class DeleteSupplier {
    private final XmlDaoFactory dao = new XmlDaoFactory();

    protected DeleteSupplier() {
    }

    public void deleteSupplier(String id) {
        dao.getSupplierDao().delete(id);
    }
}
