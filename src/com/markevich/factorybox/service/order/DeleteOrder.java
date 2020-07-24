package com.markevich.factorybox.service.order;

import com.markevich.factorybox.dao.xmldb.XmlDaoFactory;

public class DeleteOrder {
    private final XmlDaoFactory dao = new XmlDaoFactory();

    protected DeleteOrder() {
    }

    public void deleteOrder(String id) {
        dao.getOrderDao().delete(id);
    }
}
