package com.markevich.factorybox.service.user;

import biznesObgectFactory.User;
import com.markevich.factorybox.dao.xmldb.XmlDaoFactory;

public class VerificationUser {
    private final XmlDaoFactory dao = new XmlDaoFactory();

    public VerificationUser() {
    }

    public Boolean verificationUser(User user) {
        return dao.getUserDao().verification(user);
    }
}
