package com.markevich.factorybox.service.product;

import biznesObgectFactory.Product;
import com.markevich.factorybox.dao.xmldb.XmlDaoFactory;

import java.util.List;

public class LoadAllProduct {
    private final XmlDaoFactory dao = new XmlDaoFactory();

    protected LoadAllProduct() {
    }

    public List<Product> loadAllProduct() {
        return dao.getProductDao().loadAll();
    }
}
