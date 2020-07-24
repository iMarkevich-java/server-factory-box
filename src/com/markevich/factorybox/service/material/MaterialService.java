package com.markevich.factorybox.service.material;

import biznesObgectFactory.Material;
import com.markevich.factorybox.service.interfaces.Service;

import java.util.List;

public class MaterialService implements Service<Material> {

    @Override
    public List<Material> loadAll() {
        LoadAllMaterial loadAllMaterial = new LoadAllMaterial();
        return loadAllMaterial.loadAllMaterial();
    }

    @Override
    public Material loadById(String id) {
        LoadMaterialByID loadMaterialByID = new LoadMaterialByID();
        return loadMaterialByID.loadMaterialById(id);
    }

    @Override
    public void save(Material material) {
        SaveMaterial saveMaterial = new SaveMaterial();
        saveMaterial.saveMaterial(material);
    }

    @Override
    public void update(Material material) {
        UpdateMaterial updateMaterial = new UpdateMaterial();
        updateMaterial.updateMaterial(material);
    }

    @Override
    public void delete(String id) {
        DeleteMaterial deleteMaterial = new DeleteMaterial();
        deleteMaterial.deleteMaterial(id);
    }
}
