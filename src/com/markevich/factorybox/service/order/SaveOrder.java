package com.markevich.factorybox.service.order;

import biznesObgectFactory.Order;
import com.markevich.factorybox.dao.xmldb.XmlDaoFactory;

public class SaveOrder {
    private final XmlDaoFactory dao = new XmlDaoFactory();

    protected SaveOrder() {
    }

    public void saveOrder(Order order) {
        dao.getOrderDao().save(order);
    }
}
