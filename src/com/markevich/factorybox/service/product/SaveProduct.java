package com.markevich.factorybox.service.product;

import businessObjectFactoryBox.Product;
import com.markevich.factorybox.dao.xmldb.XmlDaoFactory;

public class SaveProduct {
    private final XmlDaoFactory dao = new XmlDaoFactory();

    protected SaveProduct() {
    }

    public void saveProduct(Product product) {
        dao.getProductDao().save(product);
    }
}
