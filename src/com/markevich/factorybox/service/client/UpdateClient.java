package com.markevich.factorybox.service.client;

import businessObjectFactoryBox.Client;
import com.markevich.factorybox.dao.xmldb.XmlDaoFactory;

public class UpdateClient {
    private final XmlDaoFactory dao = new XmlDaoFactory();

    protected UpdateClient() {
    }

    public void updateClient(Client client) {
        dao.getClientDao().update(client);
    }
}
