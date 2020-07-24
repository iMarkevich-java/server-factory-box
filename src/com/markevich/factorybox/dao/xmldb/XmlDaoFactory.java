package com.markevich.factorybox.dao.xmldb;

import com.markevich.factorybox.dao.daofactory.DaoFactory;

public class XmlDaoFactory extends DaoFactory {

    @Override
    public XMLMaterialDao getMaterialDao() {
        return new XMLMaterialDao();
    }

    @Override
    public XMLUserDao getUserDao() {
        return new XMLUserDao();
    }

    @Override
    public XMLStaffDayDao getStaffDayDao() {
        return new XMLStaffDayDao();
    }

    @Override
    public XMLClientDao getClientDao() {
        return new XMLClientDao();
    }

    @Override
    public XMLStaffDao getStaffDao() {
        return new XMLStaffDao();
    }

    @Override
    public XMLOrderDao getOrderDao() {
        return new XMLOrderDao();
    }

    @Override
    public XMLProductDao getProductDao() {
        return new XMLProductDao();
    }

    @Override
    public XMLSupplierDao getSupplierDao() {
        return new XMLSupplierDao();
    }
}
