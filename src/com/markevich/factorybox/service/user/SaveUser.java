package com.markevich.factorybox.service.user;

import biznesObgectFactory.User;
import com.markevich.factorybox.dao.xmldb.XmlDaoFactory;

public class SaveUser {
    private final XmlDaoFactory dao = new XmlDaoFactory();

    public SaveUser() {
    }

    public void saveUser(User user) {
        dao.getUserDao().save(user);
    }
}
