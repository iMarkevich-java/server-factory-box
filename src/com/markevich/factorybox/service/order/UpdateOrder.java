package com.markevich.factorybox.service.order;

import businessObjectFactoryBox.Order;
import com.markevich.factorybox.dao.xmldb.XmlDaoFactory;

public class UpdateOrder {
    private final XmlDaoFactory dao = new XmlDaoFactory();

    protected UpdateOrder() {
    }

    public void updateOrder(Order order) {
        dao.getOrderDao().update(order);
    }
}
