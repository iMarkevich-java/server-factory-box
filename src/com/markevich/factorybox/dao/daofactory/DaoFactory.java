package com.markevich.factorybox.dao.daofactory;

import com.markevich.factorybox.dao.xmldb.*;
import com.markevich.factorybox.dao.daointerface.Dao;

public abstract class DaoFactory {

    public abstract XMLUserDao getUserDao();

    public abstract XMLStaffDayDao getStaffDayDao();

    public abstract XMLClientDao getClientDao();

    public abstract XMLMaterialDao getMaterialDao();

    public abstract XMLStaffDao getStaffDao();

    public abstract XMLOrderDao getOrderDao();

    public abstract XMLProductDao getProductDao();

    public abstract XMLSupplierDao getSupplierDao();
}

