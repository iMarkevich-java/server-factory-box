package com.markevich.factorybox.service.material;

import biznesObgectFactory.Material;
import com.markevich.factorybox.dao.xmldb.XmlDaoFactory;

public class LoadMaterialByID {
    private final XmlDaoFactory dao = new XmlDaoFactory();

    protected LoadMaterialByID() {
    }

    public Material loadMaterialById(String id) {
        return dao.getMaterialDao().loadById(id);
    }
}
