package com.markevich.factorybox.service.client;

import biznesObgectFactory.Client;
import com.markevich.factorybox.dao.xmldb.XmlDaoFactory;

import java.util.List;

public class LoadAllClient {
    private final XmlDaoFactory dao = new XmlDaoFactory();

    protected LoadAllClient() {
    }

    public List<Client> loadAllClient() {
        return dao.getClientDao().loadAll();
    }
}
