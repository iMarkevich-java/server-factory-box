package com.markevich.factorybox.service.product;

import businessObjectFactoryBox.Product;
import com.markevich.factorybox.dao.xmldb.XmlDaoFactory;

public class LoadProductByID {
    private final XmlDaoFactory dao = new XmlDaoFactory();

    protected LoadProductByID() {
    }

    public Product loadProductById(String id) {
        return dao.getProductDao().loadById(id);
    }
}
