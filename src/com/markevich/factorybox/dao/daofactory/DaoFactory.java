package com.markevich.factorybox.dao.daofactory;

import com.markevich.factorybox.dao.daointerface.Dao;
import com.markevich.factorybox.dao.xmldb.XMLUserDao;

public abstract class DaoFactory {

    public abstract XMLUserDao getUserDao();

    public abstract Dao getStaffDayDao();

    public abstract Dao getClientDao();

    public abstract Dao getMaterialDao();

    public abstract Dao getStaffDao();

    public abstract Dao getOrderDao();

    public abstract Dao getProductDao();

    public abstract Dao getSupplierDao();
}

