package com.markevich.factorybox.service.material;

import businessObjectFactoryBox.Material;
import com.markevich.factorybox.dao.xmldb.XmlDaoFactory;

import java.util.List;

public class LoadAllMaterial {
    private final XmlDaoFactory dao = new XmlDaoFactory();

    protected LoadAllMaterial() {
    }

    public List<Material> loadAllMaterial() {
        return dao.getMaterialDao().loadAll();
    }
}
