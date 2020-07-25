package com.markevich.factorybox.service.client;

import businessObjectFactoryBox.Client;
import com.markevich.factorybox.dao.xmldb.XmlDaoFactory;

public class LoadClientByID {
    private final XmlDaoFactory dao = new XmlDaoFactory();

    protected LoadClientByID() {
    }

    public Client loadClientById(String id) {
        return dao.getClientDao().loadById(id);
    }
}
