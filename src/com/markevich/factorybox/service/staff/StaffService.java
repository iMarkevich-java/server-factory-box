package com.markevich.factorybox.service.staff;

import businessObjectFactoryBox.Staff;
import com.markevich.factorybox.service.interfaces.Service;

import java.util.List;

public class StaffService implements Service<Staff> {

    @Override
    public List<Staff> loadAll() {
        LoadAllStaff loadAllStaff = new LoadAllStaff();
        return loadAllStaff.loadAllStaff();
    }

    @Override
    public Staff loadById(String id) {
        LoadStaffByID loadStaffByID = new LoadStaffByID();
        return loadStaffByID.loadStaffById(id);
    }

    @Override
    public void save(Staff staff) {
        SaveStaff saveStaff = new SaveStaff();
        saveStaff.saveStaff(staff);
    }

    @Override
    public void update(Staff staff) {
        UpdateStaff updateStaff = new UpdateStaff();
        updateStaff.updateStaff(staff);
    }

    @Override
    public void delete(String id) {
        DeleteStaff deleteStaff = new DeleteStaff();
        deleteStaff.deleteStaff(id);
    }
}
