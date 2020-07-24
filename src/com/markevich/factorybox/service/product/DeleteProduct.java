package com.markevich.factorybox.service.product;

import com.markevich.factorybox.dao.xmldb.XmlDaoFactory;

public class DeleteProduct {
    private final XmlDaoFactory dao = new XmlDaoFactory();

    protected DeleteProduct() {
    }

    public void deleteProduct(String id) {
        dao.getProductDao().delete(id);
    }
}
