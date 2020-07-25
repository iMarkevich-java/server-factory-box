package com.markevich.factorybox.service.supplier;

import businessObjectFactoryBox.Supplier;
import com.markevich.factorybox.service.interfaces.Service;

import java.util.List;

public class SupplierService implements Service<Supplier> {

    @Override
    public List<Supplier> loadAll() {
        LoadAllSupplier loadAllSupplier = new LoadAllSupplier();
        return loadAllSupplier.loadAllSupplier();
    }

    @Override
    public Supplier loadById(String id) {
        LoadSupplierByID loadSupplierByID = new LoadSupplierByID();
        return loadSupplierByID.loadSupplierById(id);
    }

    @Override
    public void save(Supplier supplier) {
        SaveSupplier saveSupplier = new SaveSupplier();
        saveSupplier.saveSupplier(supplier);
    }

    @Override
    public void update(Supplier supplier) {
        UpdateSupplier updateSupplier = new UpdateSupplier();
        updateSupplier.updateSupplier(supplier);
    }

    @Override
    public void delete(String id) {
        DeleteSupplier deleteSupplier = new DeleteSupplier();
        deleteSupplier.deleteSupplier(id);
    }
}
