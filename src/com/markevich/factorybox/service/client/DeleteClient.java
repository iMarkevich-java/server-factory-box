package com.markevich.factorybox.service.client;

import com.markevich.factorybox.dao.xmldb.XmlDaoFactory;

public class DeleteClient {
    private final XmlDaoFactory dao = new XmlDaoFactory();

    protected DeleteClient() {
    }

    public void deleteClient(String id) {
        dao.getClientDao().delete(id);
    }
}
