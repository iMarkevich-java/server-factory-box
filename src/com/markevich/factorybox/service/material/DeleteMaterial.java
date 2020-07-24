package com.markevich.factorybox.service.material;

import com.markevich.factorybox.dao.xmldb.XmlDaoFactory;

public class DeleteMaterial {
    private final XmlDaoFactory dao = new XmlDaoFactory();

    protected DeleteMaterial() {
    }

    public void deleteMaterial(String id) {
        dao.getMaterialDao().delete(id);
    }
}
