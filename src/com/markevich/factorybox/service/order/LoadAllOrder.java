package com.markevich.factorybox.service.order;

import biznesObgectFactory.Order;
import com.markevich.factorybox.dao.xmldb.XmlDaoFactory;

import java.util.List;

public class LoadAllOrder {
    private final XmlDaoFactory dao = new XmlDaoFactory();

    protected LoadAllOrder() {
    }

    public List<Order> loadAllOrder() {
        return dao.getOrderDao().loadAll();
    }
}
