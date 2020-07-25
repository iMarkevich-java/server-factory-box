package com.markevich.factorybox.service.client;

import businessObjectFactoryBox.Client;
import com.markevich.factorybox.dao.xmldb.XmlDaoFactory;

public class SaveClient {
    private final XmlDaoFactory dao = new XmlDaoFactory();

    protected SaveClient() {
    }

    public void saveClient(Client client) {
        dao.getClientDao().save(client);
    }
}
