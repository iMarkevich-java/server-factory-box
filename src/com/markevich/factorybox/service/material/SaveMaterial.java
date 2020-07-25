package com.markevich.factorybox.service.material;

import businessObjectFactoryBox.Material;
import com.markevich.factorybox.dao.xmldb.XmlDaoFactory;

public class SaveMaterial {
    private final XmlDaoFactory dao = new XmlDaoFactory();

    protected SaveMaterial() {
    }

    public void saveMaterial(Material material) {
        dao.getMaterialDao().save(material);
    }
}
