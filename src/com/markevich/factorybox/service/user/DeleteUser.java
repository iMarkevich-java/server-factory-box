package com.markevich.factorybox.service.user;

import com.markevich.factorybox.dao.xmldb.XmlDaoFactory;

public class DeleteUser {
    private final XmlDaoFactory dao = new XmlDaoFactory();

    public DeleteUser() {
    }

    public void deleteUser(String userName) {
        dao.getUserDao().delete(userName);
    }
}
