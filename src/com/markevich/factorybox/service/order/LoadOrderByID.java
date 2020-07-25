package com.markevich.factorybox.service.order;

import businessObjectFactoryBox.Order;
import com.markevich.factorybox.dao.xmldb.XmlDaoFactory;

public class LoadOrderByID {
    private final XmlDaoFactory dao = new XmlDaoFactory();

    protected LoadOrderByID() {
    }

    public Order loadOrderById(String id) {
        return dao.getOrderDao().loadById(id);
    }
}
