package com.markevich.factorybox.service.material;

import businessObjectFactoryBox.Material;
import com.markevich.factorybox.dao.xmldb.XmlDaoFactory;

public class UpdateMaterial {
    private final XmlDaoFactory dao = new XmlDaoFactory();

    protected UpdateMaterial() {
    }

    public void updateMaterial(Material material) {
        dao.getMaterialDao().update(material);
    }
}
