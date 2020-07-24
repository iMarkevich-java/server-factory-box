package com.markevich.factorybox.service.user;

import com.markevich.factorybox.dao.xmldb.XmlDaoFactory;

import java.util.List;

public class LoadAllUser {
    private final XmlDaoFactory dao = new XmlDaoFactory();

    public LoadAllUser() {
    }

    public List<UserDb> loadAllUser() {
        return dao.getUserDao().loadAll();
    }

}
