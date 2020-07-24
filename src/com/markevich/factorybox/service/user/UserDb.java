package com.markevich.factorybox.service.user;

import java.io.Serializable;
import java.math.BigInteger;

public class UserDb implements Serializable {
    private BigInteger id;
    private String name;
    private String passwordDB;
    private String saltUser;

    public UserDb() {
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPasswordDB() {
        return passwordDB;
    }

    public void setPasswordDB(String passwordDB) {
        this.passwordDB = passwordDB;
    }

    public String getSaltUser() {
        return saltUser;
    }

    public void setSaltUser(String saltUser) {
        this.saltUser = saltUser;
    }
}
