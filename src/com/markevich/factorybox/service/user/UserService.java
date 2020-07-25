package com.markevich.factorybox.service.user;

import businessObjectFactoryBox.User;

import java.util.List;

public class UserService {


    public List<UserDb> loadAll() {
        LoadAllUser loadAllUser = new LoadAllUser();
        return loadAllUser.loadAllUser();
    }

    public void save(User user) {
        SaveUser saveUser = new SaveUser();
        saveUser.saveUser(user);
    }

    public void delete(String name) {
        DeleteUser deleteUser = new DeleteUser();
        deleteUser.deleteUser(name);
    }

    public Boolean verification(User user) {
        VerificationUser verificationUser = new VerificationUser();
        return verificationUser.verificationUser(user);
    }
}
