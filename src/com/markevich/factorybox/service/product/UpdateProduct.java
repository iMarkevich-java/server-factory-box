package com.markevich.factorybox.service.product;

import businessObjectFactoryBox.Product;
import com.markevich.factorybox.dao.xmldb.XmlDaoFactory;

public class UpdateProduct {
    private final XmlDaoFactory dao = new XmlDaoFactory();

    protected UpdateProduct() {
    }

    public void updateProduct(Product product) {
        dao.getProductDao().update(product);
    }
}
